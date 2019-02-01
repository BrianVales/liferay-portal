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

package com.liferay.headless.form.internal.resource;

import com.liferay.headless.form.dto.FormRecord;
import com.liferay.headless.form.resource.FormRecordResource;
import com.liferay.portal.vulcan.context.Pagination;
import com.liferay.portal.vulcan.dto.Page;

import java.util.Collections;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseFormRecordResourceImpl implements FormRecordResource {

	@Override
	public Page<FormRecord> getFormFormRecordPage(
			Integer parentId, Pagination pagination)
		throws Exception {

		return new Page(Collections.emptyList(), 0);
	}

	@Override
	public FormRecord getFormRecord(Integer id) throws Exception {
		return new FormRecord();
	}

	@Override
	public FormRecord postFormFormRecord(Integer parentId, String acceptlocale)
		throws Exception {

		return new FormRecord();
	}

	@Override
	public FormRecord postFormFormRecordBatchCreate(
			Integer parentId, String acceptlocale)
		throws Exception {

		return new FormRecord();
	}

	@Override
	public FormRecord putFormRecord(Integer id, String acceptlocale)
		throws Exception {

		return new FormRecord();
	}

}