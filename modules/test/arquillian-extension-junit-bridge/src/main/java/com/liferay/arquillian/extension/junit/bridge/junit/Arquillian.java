/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.arquillian.extension.junit.bridge.junit;

import com.liferay.arquillian.extension.junit.bridge.statement.ClientExecutorStatement;
import com.liferay.arquillian.extension.junit.bridge.statement.DeploymentStatement;
import com.liferay.arquillian.extension.junit.bridge.statement.ServerExecutorStatement;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.net.URL;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.junit.AssumptionViolatedException;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.MethodRule;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/**
 * @author Shuyang Zhou
 */
public class Arquillian extends Runner implements Filterable {

	public Arquillian(Class<?> clazz) {
		_clazz = clazz;
	}

	@Override
	public void filter(Filter filter) throws NoTestsRemainException {
		_filter = filter;

		_testClass = new FilteredSortedTestClass(_clazz);

		List<FrameworkMethod> frameworkMethods = _testClass.getAnnotatedMethods(
			Test.class);

		if (frameworkMethods.isEmpty()) {
			throw new NoTestsRemainException();
		}
	}

	@Override
	public Description getDescription() {
		Description description = Description.createSuiteDescription(
			_clazz.getName(), _clazz.getAnnotations());

		for (FrameworkMethod frameworkMethod : _getChildren()) {
			description.addChild(_describeChild(frameworkMethod));
		}

		return description;
	}

	@Override
	public void run(RunNotifier runNotifier) {
		Description description = getDescription();

		try {
			Statement statement = _createClassStatement(runNotifier);

			statement.evaluate();
		}
		catch (AssumptionViolatedException ave) {
			runNotifier.fireTestAssumptionFailed(new Failure(description, ave));
		}
		catch (MultipleFailureException mfe) {
			for (Throwable t : mfe.getFailures()) {
				runNotifier.fireTestFailure(new Failure(description, t));
			}
		}
		catch (Throwable t) {
			runNotifier.fireTestFailure(new Failure(description, t));
		}
	}

	private Statement _createClassStatement(RunNotifier runNotifier) {
		Statement statement = new Statement() {

			@Override
			public void evaluate() {
				for (FrameworkMethod frameworkMethod : _getChildren()) {
					_runMethod(frameworkMethod, runNotifier);
				}
			}

		};

		boolean hasTestMethod = false;

		for (FrameworkMethod frameworkMethod : _getChildren()) {
			if (!_isIgnored(frameworkMethod)) {
				hasTestMethod = true;

				break;
			}
		}

		if (hasTestMethod && !_REMOTE) {
			return new DeploymentStatement(statement, _clazz);
		}

		return statement;
	}

	private Statement _createExecutorStatement(
		FrameworkMethod frameworkMethod, Object target) {

		Method method = frameworkMethod.getMethod();

		if (_REMOTE) {
			return new ServerExecutorStatement(target, method);
		}

		return new ClientExecutorStatement(target, method);
	}

	private Statement _createMethodStatement(FrameworkMethod frameworkMethod) {
		Object target = null;

		try {
			target = _clazz.newInstance();
		}
		catch (ReflectiveOperationException roe) {
			return new Fail(roe);
		}

		Statement statement = _createExecutorStatement(frameworkMethod, target);

		statement = _withTimeout(frameworkMethod, statement);

		return _withMethodRules(statement, frameworkMethod, target);
	}

	private Description _describeChild(FrameworkMethod frameworkMethod) {
		return _methodDescriptions.computeIfAbsent(
			frameworkMethod,
			keyFrameworkMethod -> {
				return Description.createTestDescription(
					_clazz, keyFrameworkMethod.getName(),
					keyFrameworkMethod.getAnnotations());
			});
	}

	private List<FrameworkMethod> _getChildren() {
		TestClass testClass = _getTestClass();

		return testClass.getAnnotatedMethods(Test.class);
	}

	private TestClass _getTestClass() {
		if (_testClass == null) {
			_testClass = new FilteredSortedTestClass(_clazz);
		}

		return _testClass;
	}

	private boolean _isIgnored(FrameworkMethod frameworkMethod) {
		if (frameworkMethod.getAnnotation(Ignore.class) != null) {
			return true;
		}

		return false;
	}

	private void _runMethod(
		FrameworkMethod frameworkMethod, RunNotifier runNotifier) {

		Description description = _describeChild(frameworkMethod);

		if (_isIgnored(frameworkMethod)) {
			runNotifier.fireTestIgnored(description);
		}
		else {
			Statement statement = _createMethodStatement(frameworkMethod);

			runNotifier.fireTestStarted(description);

			try {
				statement.evaluate();
			}
			catch (AssumptionViolatedException ave) {
				runNotifier.fireTestAssumptionFailed(
					new Failure(description, ave));
			}
			catch (MultipleFailureException mfe) {
				for (Throwable t : mfe.getFailures()) {
					runNotifier.fireTestFailure(new Failure(description, t));
				}
			}
			catch (Throwable t) {
				runNotifier.fireTestFailure(new Failure(description, t));
			}
			finally {
				runNotifier.fireTestFinished(description);
			}
		}
	}

	private Statement _withMethodRules(
		Statement statement, FrameworkMethod frameworkMethod, Object target) {

		TestClass testClass = _getTestClass();

		for (MethodRule methodRule :
				testClass.getAnnotatedMethodValues(
					target, Rule.class, MethodRule.class)) {

			statement = methodRule.apply(statement, frameworkMethod, target);
		}

		for (MethodRule methodRule :
				testClass.getAnnotatedFieldValues(
					target, Rule.class, MethodRule.class)) {

			statement = methodRule.apply(statement, frameworkMethod, target);
		}

		return statement;
	}

	private Statement _withTimeout(
		FrameworkMethod frameworkMethod, Statement statement) {

		Test test = frameworkMethod.getAnnotation(Test.class);

		if ((test == null) || (test.timeout() <= 0)) {
			return statement;
		}

		FailOnTimeout.Builder builder = FailOnTimeout.builder();

		builder.withTimeout(test.timeout(), TimeUnit.MILLISECONDS);

		return builder.build(statement);
	}

	private static final boolean _REMOTE;

	static {
		URL url = Arquillian.class.getResource("/arquillian.remote.marker");

		if (url == null) {
			_REMOTE = false;
		}
		else {
			_REMOTE = true;
		}
	}

	private final Class<?> _clazz;
	private Filter _filter;
	private final Map<FrameworkMethod, Description> _methodDescriptions =
		new ConcurrentHashMap<>();
	private TestClass _testClass;

	private class FilteredSortedTestClass extends TestClass {

		@Override
		protected void scanAnnotatedMembers(
			Map<Class<? extends Annotation>, List<FrameworkMethod>>
				frameworkMethodsMap,
			Map<Class<? extends Annotation>, List<FrameworkField>>
				frameworkFieldsMap) {

			super.scanAnnotatedMembers(frameworkMethodsMap, frameworkFieldsMap);

			List<FrameworkMethod> frameworkMethods = frameworkMethodsMap.get(
				Test.class);

			if (_filter != null) {
				Iterator<FrameworkMethod> iterator =
					frameworkMethods.iterator();

				while (iterator.hasNext()) {
					FrameworkMethod frameworkMethod = iterator.next();

					if (_filter.shouldRun(_describeChild(frameworkMethod))) {
						try {
							_filter.apply(frameworkMethod);
						}
						catch (NoTestsRemainException ntre) {
							iterator.remove();
						}
					}
					else {
						iterator.remove();
					}
				}
			}

			frameworkMethods.sort(
				Comparator.comparing(FrameworkMethod::getName));
		}

		private FilteredSortedTestClass(Class<?> clazz) {
			super(clazz);
		}

	}

}