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

package com.liferay.headless.document.library.internal.resource;

import com.liferay.headless.document.library.dto.Folder;
import com.liferay.headless.document.library.resource.FolderResource;
import com.liferay.portal.vulcan.context.Pagination;
import com.liferay.portal.vulcan.dto.Page;

import java.util.Collections;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseFolderResourceImpl implements FolderResource {

	@Override
	public Folder getDocumentsRepository(Long id) throws Exception {
		return new Folder();
	}

	@Override
	public Page<Folder> getDocumentsRepositoryFolderPage(
			Long parentId, Pagination pagination)
		throws Exception {

		return new Page(Collections.emptyList(), 0);
	}

	@Override
	public Folder getFolder(Long id) throws Exception {
		return new Folder();
	}

	@Override
	public Page<Folder> getFolderFolderPage(
			Long parentId, Pagination pagination)
		throws Exception {

		return new Page(Collections.emptyList(), 0);
	}

	@Override
	public Folder postDocumentsRepositoryFolder(Long parentId)
		throws Exception {

		return new Folder();
	}

	@Override
	public Folder postDocumentsRepositoryFolderBatchCreate(Long parentId)
		throws Exception {

		return new Folder();
	}

	@Override
	public Folder postFolderFolder(Long parentId) throws Exception {
		return new Folder();
	}

	@Override
	public Folder postFolderFolderBatchCreate(Long parentId) throws Exception {
		return new Folder();
	}

	@Override
	public Folder putFolder(Long id) throws Exception {
		return new Folder();
	}

}