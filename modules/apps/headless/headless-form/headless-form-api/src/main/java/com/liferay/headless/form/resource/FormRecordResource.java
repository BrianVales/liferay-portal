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

package com.liferay.headless.form.resource;

import com.liferay.headless.form.dto.FormRecord;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-form/1.0.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@Path("/1.0.0")
public interface FormRecordResource {

	@GET
	@Path("/form/{parent-id}/form-record")
	@Produces({"*/*"})
	@RequiresScope("headless-form-application.read")
	public Page<FormRecord> getFormFormRecordPage(
			@PathParam("parent-id") Integer parentId,
			@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/form-record/{id}")
	@Produces({"*/*"})
	@RequiresScope("headless-form-application.read")
	public FormRecord getFormRecord(@PathParam("id") Integer id)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/form/{parent-id}/form-record")
	@POST
	@Produces({"*/*"})
	@RequiresScope("headless-form-application.read")
	public FormRecord postFormFormRecord(
			@PathParam("parent-id") Integer parentId,
			@QueryParam("acceptlocale") String acceptlocale)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/form/{parent-id}/form-record/batch-create")
	@POST
	@Produces({"*/*"})
	@RequiresScope("headless-form-application.write")
	public FormRecord postFormFormRecordBatchCreate(
			@PathParam("parent-id") Integer parentId,
			@QueryParam("acceptlocale") String acceptlocale)
		throws Exception;

	@Consumes({"*/*"})
	@Path("/form-record/{id}")
	@Produces({"*/*"})
	@PUT
	@RequiresScope("headless-form-application.read")
	public FormRecord putFormRecord(
			@PathParam("id") Integer id,
			@QueryParam("acceptlocale") String acceptlocale)
		throws Exception;

}