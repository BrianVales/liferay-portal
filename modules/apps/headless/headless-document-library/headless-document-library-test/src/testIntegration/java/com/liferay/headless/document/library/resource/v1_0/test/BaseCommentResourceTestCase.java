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

package com.liferay.headless.document.library.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.headless.document.library.dto.v1_0.Comment;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseCommentResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		testGroup = GroupTestUtil.addGroup();

		_resourceURL = new URL(
			"http://localhost:8080/o/headless-document-library/v1.0");
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testDeleteComment() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetComment() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetCommentCommentsPage() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetDocumentCommentsPage() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPostCommentComment() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPostDocumentComment() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutComment() throws Exception {
		Assert.assertTrue(true);
	}

	protected void assertEquals(Comment comment1, Comment comment2) {
		Assert.assertTrue(
			comment1 + " does not equal " + comment2,
			equals(comment1, comment2));
	}

	protected void assertEquals(
		List<Comment> comments1, List<Comment> comments2) {

		Assert.assertEquals(comments1.size(), comments2.size());

		for (int i = 0; i < comments1.size(); i++) {
			Comment comment1 = comments1.get(i);
			Comment comment2 = comments2.get(i);

			assertEquals(comment1, comment2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Comment> comments1, List<Comment> comments2) {

		Assert.assertEquals(comments1.size(), comments2.size());

		for (Comment comment1 : comments1) {
			boolean contains = false;

			for (Comment comment2 : comments2) {
				if (equals(comment1, comment2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				comments2 + " does not contain " + comment1, contains);
		}
	}

	protected void assertResponseCode(
		int expectedResponseCode, Http.Response actualResponse) {

		Assert.assertEquals(
			expectedResponseCode, actualResponse.getResponseCode());
	}

	protected void assertValid(Page<Comment> page) {
		boolean valid = false;

		Collection<Comment> comments = page.getItems();

		int size = comments.size();

		if ((page.getItemsPerPage() > 0) && (page.getLastPageNumber() > 0) &&
			(page.getPageNumber() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected boolean equals(Comment comment1, Comment comment2) {
		if (comment1 == comment2) {
			return true;
		}

		return false;
	}

	protected boolean invokeDeleteComment(Long commentId) throws Exception {
		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		options.setLocation(
			_resourceURL + _toPath("/comments/{comment-id}", commentId));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), Boolean.class);
	}

	protected Http.Response invokeDeleteCommentResponse(Long commentId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		options.setLocation(
			_resourceURL + _toPath("/comments/{comment-id}", commentId));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Comment invokeGetComment(Long commentId) throws Exception {
		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL + _toPath("/comments/{comment-id}", commentId));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), Comment.class);
	}

	protected Page<Comment> invokeGetCommentCommentsPage(
			Long commentId, String filterString, Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_getCommentCommentsLocation(
				commentId, filterString, pagination, sortString));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options),
			new TypeReference<Page<Comment>>() {
			});
	}

	protected Http.Response invokeGetCommentCommentsPageResponse(
			Long commentId, String filterString, Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_getCommentCommentsLocation(
				commentId, filterString, pagination, sortString));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Http.Response invokeGetCommentResponse(Long commentId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL + _toPath("/comments/{comment-id}", commentId));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Page<Comment> invokeGetDocumentCommentsPage(
			Long documentId, String filterString, Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_getDocumentCommentsLocation(
				documentId, filterString, pagination, sortString));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options),
			new TypeReference<Page<Comment>>() {
			});
	}

	protected Http.Response invokeGetDocumentCommentsPageResponse(
			Long documentId, String filterString, Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_getDocumentCommentsLocation(
				documentId, filterString, pagination, sortString));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Comment invokePostCommentComment(Long commentId, Comment comment)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(comment),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath("/comments/{comment-id}/comments", commentId));

		options.setPost(true);

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), Comment.class);
	}

	protected Http.Response invokePostCommentCommentResponse(
			Long commentId, Comment comment)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(comment),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath("/comments/{comment-id}/comments", commentId));

		options.setPost(true);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Comment invokePostDocumentComment(
			Long documentId, Comment comment)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(comment),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath("/documents/{document-id}/comments", documentId));

		options.setPost(true);

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), Comment.class);
	}

	protected Http.Response invokePostDocumentCommentResponse(
			Long documentId, Comment comment)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(comment),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath("/documents/{document-id}/comments", documentId));

		options.setPost(true);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Comment invokePutComment(Long commentId, Comment comment)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(comment),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL + _toPath("/comments/{comment-id}", commentId));

		options.setPut(true);

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), Comment.class);
	}

	protected Http.Response invokePutCommentResponse(
			Long commentId, Comment comment)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(comment),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL + _toPath("/comments/{comment-id}", commentId));

		options.setPut(true);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Comment randomComment() {
		return new Comment() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				hasComments = RandomTestUtil.randomBoolean();
				id = RandomTestUtil.randomLong();
				text = RandomTestUtil.randomString();
			}
		};
	}

	protected Group testGroup;

	protected static class Page<T> {

		public Collection<T> getItems() {
			return new ArrayList<>(items);
		}

		public int getItemsPerPage() {
			return itemsPerPage;
		}

		public int getLastPageNumber() {
			return lastPageNumber;
		}

		public int getPageNumber() {
			return pageNumber;
		}

		public int getTotalCount() {
			return totalCount;
		}

		@JsonProperty
		protected Collection<T> items;

		@JsonProperty("pageSize")
		protected int itemsPerPage;

		@JsonProperty
		protected int lastPageNumber;

		@JsonProperty("page")
		protected int pageNumber;

		@JsonProperty
		protected int totalCount;

	}

	private Http.Options _createHttpOptions() {
		Http.Options options = new Http.Options();

		options.addHeader("Accept", "application/json");

		String userNameAndPassword = "test@liferay.com:test";

		String encodedUserNameAndPassword = Base64.encode(
			userNameAndPassword.getBytes());

		options.addHeader(
			"Authorization", "Basic " + encodedUserNameAndPassword);

		options.addHeader("Content-Type", "application/json");

		return options;
	}

	private String _getCommentCommentsLocation(
		Long commentId, String filterString, Pagination pagination,
		String sortString) {

		String url =
			_resourceURL +
				_toPath("/comments/{comment-id}/comments", commentId);

		url += "?filter=" + URLCodec.encodeURL(filterString);
		url += "&page=" + pagination.getPageNumber();
		url += "&pageSize=" + pagination.getItemsPerPage();
		url += "&sort=" + URLCodec.encodeURL(sortString);

		return url;
	}

	private String _getDocumentCommentsLocation(
		Long documentId, String filterString, Pagination pagination,
		String sortString) {

		String url =
			_resourceURL +
				_toPath("/documents/{document-id}/comments", documentId);

		url += "?filter=" + URLCodec.encodeURL(filterString);
		url += "&page=" + pagination.getPageNumber();
		url += "&pageSize=" + pagination.getItemsPerPage();
		url += "&sort=" + URLCodec.encodeURL(sortString);

		return url;
	}

	private String _toPath(String template, Object value) {
		return template.replaceFirst("\\{.*\\}", String.valueOf(value));
	}

	private static final ObjectMapper _inputObjectMapper = new ObjectMapper() {
		{
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};
	private static final ObjectMapper _outputObjectMapper = new ObjectMapper();

	private URL _resourceURL;

}