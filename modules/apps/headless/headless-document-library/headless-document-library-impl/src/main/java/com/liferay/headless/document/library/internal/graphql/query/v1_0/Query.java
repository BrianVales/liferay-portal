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

package com.liferay.headless.document.library.internal.graphql.query.v1_0;

import com.liferay.headless.document.library.dto.v1_0.Comment;
import com.liferay.headless.document.library.dto.v1_0.Document;
import com.liferay.headless.document.library.dto.v1_0.Folder;
import com.liferay.headless.document.library.internal.resource.v1_0.CommentResourceImpl;
import com.liferay.headless.document.library.internal.resource.v1_0.DocumentResourceImpl;
import com.liferay.headless.document.library.internal.resource.v1_0.FolderResourceImpl;
import com.liferay.headless.document.library.resource.v1_0.CommentResource;
import com.liferay.headless.document.library.resource.v1_0.DocumentResource;
import com.liferay.headless.document.library.resource.v1_0.FolderResource;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Collection;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class Query {

	@GraphQLField
	@GraphQLInvokeDetached
	public Comment getComment(@GraphQLName("comment-id") Long commentId)
		throws Exception {

		CommentResource commentResource = _createCommentResource();

		commentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		return commentResource.getComment(commentId);
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Comment> getCommentCommentsPage(
			@GraphQLName("comment-id") Long commentId,
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("Sort[]") Sort[] sorts)
		throws Exception {

		CommentResource commentResource = _createCommentResource();

		commentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		Page paginationPage = commentResource.getCommentCommentsPage(
			commentId, filter, Pagination.of(pageSize, page), sorts);

		return paginationPage.getItems();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Document> getContentSpaceDocumentsPage(
			@GraphQLName("content-space-id") Long contentSpaceId,
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("Sort[]") Sort[] sorts)
		throws Exception {

		DocumentResource documentResource = _createDocumentResource();

		documentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		Page paginationPage = documentResource.getContentSpaceDocumentsPage(
			contentSpaceId, filter, Pagination.of(pageSize, page), sorts);

		return paginationPage.getItems();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Folder> getContentSpaceFoldersPage(
			@GraphQLName("content-space-id") Long contentSpaceId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		FolderResource folderResource = _createFolderResource();

		folderResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		Page paginationPage = folderResource.getContentSpaceFoldersPage(
			contentSpaceId, Pagination.of(pageSize, page));

		return paginationPage.getItems();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Document getDocument(@GraphQLName("document-id") Long documentId)
		throws Exception {

		DocumentResource documentResource = _createDocumentResource();

		documentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		return documentResource.getDocument(documentId);
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Comment> getDocumentCommentsPage(
			@GraphQLName("document-id") Long documentId,
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("Sort[]") Sort[] sorts)
		throws Exception {

		CommentResource commentResource = _createCommentResource();

		commentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		Page paginationPage = commentResource.getDocumentCommentsPage(
			documentId, filter, Pagination.of(pageSize, page), sorts);

		return paginationPage.getItems();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Folder getFolder(@GraphQLName("folder-id") Long folderId)
		throws Exception {

		FolderResource folderResource = _createFolderResource();

		folderResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		return folderResource.getFolder(folderId);
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Document> getFolderDocumentsPage(
			@GraphQLName("folder-id") Long folderId,
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("Sort[]") Sort[] sorts)
		throws Exception {

		DocumentResource documentResource = _createDocumentResource();

		documentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		Page paginationPage = documentResource.getFolderDocumentsPage(
			folderId, filter, Pagination.of(pageSize, page), sorts);

		return paginationPage.getItems();
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Folder> getFolderFoldersPage(
			@GraphQLName("folder-id") Long folderId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		FolderResource folderResource = _createFolderResource();

		folderResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));

		Page paginationPage = folderResource.getFolderFoldersPage(
			folderId, Pagination.of(pageSize, page));

		return paginationPage.getItems();
	}

	private static CommentResource _createCommentResource() {
		return new CommentResourceImpl();
	}

	private static DocumentResource _createDocumentResource() {
		return new DocumentResourceImpl();
	}

	private static FolderResource _createFolderResource() {
		return new FolderResourceImpl();
	}

}