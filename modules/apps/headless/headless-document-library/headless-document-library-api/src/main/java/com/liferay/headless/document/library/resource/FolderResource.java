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

package com.liferay.headless.document.library.resource;

import com.liferay.headless.document.library.dto.Folder;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.vulcan.context.Pagination;
import com.liferay.portal.vulcan.dto.Page;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-document-library/1.0.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@Path("/1.0.0")
public interface FolderResource {

	@GET
	@Path("/documents-repository/{id}")
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.read")
	public Folder getDocumentsRepository(@PathParam("id") Long id)
		throws Exception;

	@GET
	@Path("/documents-repository/{parent-id}/folder")
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.read")
	public Page<Folder> getDocumentsRepositoryFolderPage(
			@PathParam("parent-id") Long parentId,
			@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/folder/{id}")
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.read")
	public Folder getFolder(@PathParam("id") Long id) throws Exception;

	@GET
	@Path("/folder/{parent-id}/folder")
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.read")
	public Page<Folder> getFolderFolderPage(
			@PathParam("parent-id") Long parentId,
			@Context Pagination pagination)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/documents-repository/{parent-id}/folder")
	@POST
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.read")
	public Folder postDocumentsRepositoryFolder(
			@PathParam("parent-id") Long parentId)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/documents-repository/{parent-id}/folder/batch-create")
	@POST
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.write")
	public Folder postDocumentsRepositoryFolderBatchCreate(
			@PathParam("parent-id") Long parentId)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/folder/{parent-id}/folder")
	@POST
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.read")
	public Folder postFolderFolder(@PathParam("parent-id") Long parentId)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/folder/{parent-id}/folder/batch-create")
	@POST
	@Produces({"*/*"})
	@RequiresScope("headless-document-library-application.write")
	public Folder postFolderFolderBatchCreate(
			@PathParam("parent-id") Long parentId)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/folder/{id}")
	@Produces({"*/*"})
	@PUT
	@RequiresScope("headless-document-library-application.read")
	public Folder putFolder(@PathParam("id") Long id) throws Exception;

}