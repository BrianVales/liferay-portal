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

package com.liferay.portlet.documentlibrary.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFileShortcutModel;
import com.liferay.document.library.kernel.model.DLFileShortcutSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ContainerModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DLFileShortcut service. Represents a row in the &quot;DLFileShortcut&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DLFileShortcutModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileShortcutImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileShortcutImpl
 * @see DLFileShortcut
 * @see DLFileShortcutModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class DLFileShortcutModelImpl extends BaseModelImpl<DLFileShortcut>
	implements DLFileShortcutModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file shortcut model instance should use the {@link DLFileShortcut} interface instead.
	 */
	public static final String TABLE_NAME = "DLFileShortcut";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "fileShortcutId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "repositoryId", Types.BIGINT },
			{ "folderId", Types.BIGINT },
			{ "toFileEntryId", Types.BIGINT },
			{ "treePath", Types.VARCHAR },
			{ "active_", Types.BOOLEAN },
			{ "lastPublishDate", Types.TIMESTAMP },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileShortcutId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("repositoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("folderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("toFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("treePath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table DLFileShortcut (uuid_ VARCHAR(75) null,fileShortcutId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,repositoryId LONG,folderId LONG,toFileEntryId LONG,treePath STRING null,active_ BOOLEAN,lastPublishDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table DLFileShortcut";
	public static final String ORDER_BY_JPQL = " ORDER BY dlFileShortcut.fileShortcutId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DLFileShortcut.fileShortcutId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.document.library.kernel.model.DLFileShortcut"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.document.library.kernel.model.DLFileShortcut"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.document.library.kernel.model.DLFileShortcut"),
			true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long FOLDERID_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long STATUS_COLUMN_BITMASK = 16L;
	public static final long TOFILEENTRYID_COLUMN_BITMASK = 32L;
	public static final long UUID_COLUMN_BITMASK = 64L;
	public static final long FILESHORTCUTID_COLUMN_BITMASK = 128L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static DLFileShortcut toModel(DLFileShortcutSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		DLFileShortcut model = new DLFileShortcutImpl();

		model.setUuid(soapModel.getUuid());
		model.setFileShortcutId(soapModel.getFileShortcutId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setRepositoryId(soapModel.getRepositoryId());
		model.setFolderId(soapModel.getFolderId());
		model.setToFileEntryId(soapModel.getToFileEntryId());
		model.setTreePath(soapModel.getTreePath());
		model.setActive(soapModel.isActive());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<DLFileShortcut> toModels(DLFileShortcutSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<DLFileShortcut> models = new ArrayList<DLFileShortcut>(soapModels.length);

		for (DLFileShortcutSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.document.library.kernel.model.DLFileShortcut"));

	public DLFileShortcutModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fileShortcutId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFileShortcutId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileShortcutId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileShortcut.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileShortcut.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DLFileShortcut, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DLFileShortcut, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<DLFileShortcut, Object> attributeGetterFunction = entry.getValue();

			attributes.put(attributeName,
				attributeGetterFunction.apply((DLFileShortcut)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DLFileShortcut, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DLFileShortcut, Object> attributeSetterBiConsumer = attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((DLFileShortcut)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<DLFileShortcut, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DLFileShortcut, Object>> getAttributeSetterBiConsumers() {
		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<DLFileShortcut, Object>> _attributeGetterFunctions;
	private static final Map<String, BiConsumer<DLFileShortcut, Object>> _attributeSetterBiConsumers;

	static {
		Map<String, Function<DLFileShortcut, Object>> attributeGetterFunctions = new LinkedHashMap<String, Function<DLFileShortcut, Object>>();
		Map<String, BiConsumer<DLFileShortcut, ?>> attributeSetterBiConsumers = new LinkedHashMap<String, BiConsumer<DLFileShortcut, ?>>();


		attributeGetterFunctions.put("uuid", DLFileShortcut::getUuid);
		attributeSetterBiConsumers.put("uuid", (BiConsumer<DLFileShortcut, String>)DLFileShortcut::setUuid);
		attributeGetterFunctions.put("fileShortcutId", DLFileShortcut::getFileShortcutId);
		attributeSetterBiConsumers.put("fileShortcutId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setFileShortcutId);
		attributeGetterFunctions.put("groupId", DLFileShortcut::getGroupId);
		attributeSetterBiConsumers.put("groupId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setGroupId);
		attributeGetterFunctions.put("companyId", DLFileShortcut::getCompanyId);
		attributeSetterBiConsumers.put("companyId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setCompanyId);
		attributeGetterFunctions.put("userId", DLFileShortcut::getUserId);
		attributeSetterBiConsumers.put("userId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setUserId);
		attributeGetterFunctions.put("userName", DLFileShortcut::getUserName);
		attributeSetterBiConsumers.put("userName", (BiConsumer<DLFileShortcut, String>)DLFileShortcut::setUserName);
		attributeGetterFunctions.put("createDate", DLFileShortcut::getCreateDate);
		attributeSetterBiConsumers.put("createDate", (BiConsumer<DLFileShortcut, Date>)DLFileShortcut::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", DLFileShortcut::getModifiedDate);
		attributeSetterBiConsumers.put("modifiedDate", (BiConsumer<DLFileShortcut, Date>)DLFileShortcut::setModifiedDate);
		attributeGetterFunctions.put("repositoryId", DLFileShortcut::getRepositoryId);
		attributeSetterBiConsumers.put("repositoryId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setRepositoryId);
		attributeGetterFunctions.put("folderId", DLFileShortcut::getFolderId);
		attributeSetterBiConsumers.put("folderId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setFolderId);
		attributeGetterFunctions.put("toFileEntryId", DLFileShortcut::getToFileEntryId);
		attributeSetterBiConsumers.put("toFileEntryId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setToFileEntryId);
		attributeGetterFunctions.put("treePath", DLFileShortcut::getTreePath);
		attributeSetterBiConsumers.put("treePath", (BiConsumer<DLFileShortcut, String>)DLFileShortcut::setTreePath);
		attributeGetterFunctions.put("active", DLFileShortcut::getActive);
		attributeSetterBiConsumers.put("active", (BiConsumer<DLFileShortcut, Boolean>)DLFileShortcut::setActive);
		attributeGetterFunctions.put("lastPublishDate", DLFileShortcut::getLastPublishDate);
		attributeSetterBiConsumers.put("lastPublishDate", (BiConsumer<DLFileShortcut, Date>)DLFileShortcut::setLastPublishDate);
		attributeGetterFunctions.put("status", DLFileShortcut::getStatus);
		attributeSetterBiConsumers.put("status", (BiConsumer<DLFileShortcut, Integer>)DLFileShortcut::setStatus);
		attributeGetterFunctions.put("statusByUserId", DLFileShortcut::getStatusByUserId);
		attributeSetterBiConsumers.put("statusByUserId", (BiConsumer<DLFileShortcut, Long>)DLFileShortcut::setStatusByUserId);
		attributeGetterFunctions.put("statusByUserName", DLFileShortcut::getStatusByUserName);
		attributeSetterBiConsumers.put("statusByUserName", (BiConsumer<DLFileShortcut, String>)DLFileShortcut::setStatusByUserName);
		attributeGetterFunctions.put("statusDate", DLFileShortcut::getStatusDate);
		attributeSetterBiConsumers.put("statusDate", (BiConsumer<DLFileShortcut, Date>)DLFileShortcut::setStatusDate);


		_attributeGetterFunctions = Collections.unmodifiableMap(attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap((Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getFileShortcutId() {
		return _fileShortcutId;
	}

	@Override
	public void setFileShortcutId(long fileShortcutId) {
		_fileShortcutId = fileShortcutId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getRepositoryId() {
		return _repositoryId;
	}

	@Override
	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	@JSON
	@Override
	public long getFolderId() {
		return _folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		_columnBitmask |= FOLDERID_COLUMN_BITMASK;

		if (!_setOriginalFolderId) {
			_setOriginalFolderId = true;

			_originalFolderId = _folderId;
		}

		_folderId = folderId;
	}

	public long getOriginalFolderId() {
		return _originalFolderId;
	}

	@JSON
	@Override
	public long getToFileEntryId() {
		return _toFileEntryId;
	}

	@Override
	public void setToFileEntryId(long toFileEntryId) {
		_columnBitmask |= TOFILEENTRYID_COLUMN_BITMASK;

		if (!_setOriginalToFileEntryId) {
			_setOriginalToFileEntryId = true;

			_originalToFileEntryId = _toFileEntryId;
		}

		_toFileEntryId = toFileEntryId;
	}

	public long getOriginalToFileEntryId() {
		return _originalToFileEntryId;
	}

	@JSON
	@Override
	public String getTreePath() {
		if (_treePath == null) {
			return "";
		}
		else {
			return _treePath;
		}
	}

	@Override
	public void setTreePath(String treePath) {
		_treePath = treePath;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				DLFileShortcut.class.getName()));
	}

	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException {
		if (!isInTrash()) {
			return null;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry = com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return trashEntry;
		}

		com.liferay.portal.kernel.trash.TrashHandler trashHandler = getTrashHandler();

		if (Validator.isNotNull(trashHandler.getContainerModelClassName(
						getPrimaryKey()))) {
			ContainerModel containerModel = null;

			try {
				containerModel = trashHandler.getParentContainerModel(this);
			}
			catch (NoSuchModelException nsme) {
				return null;
			}

			while (containerModel != null) {
				if (containerModel instanceof TrashedModel) {
					TrashedModel trashedModel = (TrashedModel)containerModel;

					return trashedModel.getTrashEntry();
				}

				trashHandler = com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil.getTrashHandler(trashHandler.getContainerModelClassName(
							containerModel.getContainerModelId()));

				if (trashHandler == null) {
					return null;
				}

				containerModel = trashHandler.getContainerModel(containerModel.getParentContainerModelId());
			}
		}

		return null;
	}

	@Override
	public long getTrashEntryClassPK() {
		return getPrimaryKey();
	}

	/**
	* @deprecated As of Judson (7.1.x), with no direct replacement
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil.getTrashHandler(getModelClassName());
	}

	@Override
	public boolean isInTrash() {
		if (getStatus() == WorkflowConstants.STATUS_IN_TRASH) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInTrashContainer() {
		com.liferay.portal.kernel.trash.TrashHandler trashHandler = getTrashHandler();

		if ((trashHandler == null) ||
				Validator.isNull(trashHandler.getContainerModelClassName(
						getPrimaryKey()))) {
			return false;
		}

		try {
			ContainerModel containerModel = trashHandler.getParentContainerModel(this);

			if (containerModel == null) {
				return false;
			}

			if (containerModel instanceof TrashedModel) {
				return ((TrashedModel)containerModel).isInTrash();
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean isInTrashExplicitly() {
		if (!isInTrash()) {
			return false;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry = com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isInTrashImplicitly() {
		if (!isInTrash()) {
			return false;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry = com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			DLFileShortcut.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLFileShortcut toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DLFileShortcut)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DLFileShortcutImpl dlFileShortcutImpl = new DLFileShortcutImpl();

		dlFileShortcutImpl.setUuid(getUuid());
		dlFileShortcutImpl.setFileShortcutId(getFileShortcutId());
		dlFileShortcutImpl.setGroupId(getGroupId());
		dlFileShortcutImpl.setCompanyId(getCompanyId());
		dlFileShortcutImpl.setUserId(getUserId());
		dlFileShortcutImpl.setUserName(getUserName());
		dlFileShortcutImpl.setCreateDate(getCreateDate());
		dlFileShortcutImpl.setModifiedDate(getModifiedDate());
		dlFileShortcutImpl.setRepositoryId(getRepositoryId());
		dlFileShortcutImpl.setFolderId(getFolderId());
		dlFileShortcutImpl.setToFileEntryId(getToFileEntryId());
		dlFileShortcutImpl.setTreePath(getTreePath());
		dlFileShortcutImpl.setActive(isActive());
		dlFileShortcutImpl.setLastPublishDate(getLastPublishDate());
		dlFileShortcutImpl.setStatus(getStatus());
		dlFileShortcutImpl.setStatusByUserId(getStatusByUserId());
		dlFileShortcutImpl.setStatusByUserName(getStatusByUserName());
		dlFileShortcutImpl.setStatusDate(getStatusDate());

		dlFileShortcutImpl.resetOriginalValues();

		return dlFileShortcutImpl;
	}

	@Override
	public int compareTo(DLFileShortcut dlFileShortcut) {
		long primaryKey = dlFileShortcut.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DLFileShortcut)) {
			return false;
		}

		DLFileShortcut dlFileShortcut = (DLFileShortcut)obj;

		long primaryKey = dlFileShortcut.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		DLFileShortcutModelImpl dlFileShortcutModelImpl = this;

		dlFileShortcutModelImpl._originalUuid = dlFileShortcutModelImpl._uuid;

		dlFileShortcutModelImpl._originalGroupId = dlFileShortcutModelImpl._groupId;

		dlFileShortcutModelImpl._setOriginalGroupId = false;

		dlFileShortcutModelImpl._originalCompanyId = dlFileShortcutModelImpl._companyId;

		dlFileShortcutModelImpl._setOriginalCompanyId = false;

		dlFileShortcutModelImpl._setModifiedDate = false;

		dlFileShortcutModelImpl._originalFolderId = dlFileShortcutModelImpl._folderId;

		dlFileShortcutModelImpl._setOriginalFolderId = false;

		dlFileShortcutModelImpl._originalToFileEntryId = dlFileShortcutModelImpl._toFileEntryId;

		dlFileShortcutModelImpl._setOriginalToFileEntryId = false;

		dlFileShortcutModelImpl._originalActive = dlFileShortcutModelImpl._active;

		dlFileShortcutModelImpl._setOriginalActive = false;

		dlFileShortcutModelImpl._originalStatus = dlFileShortcutModelImpl._status;

		dlFileShortcutModelImpl._setOriginalStatus = false;

		dlFileShortcutModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DLFileShortcut> toCacheModel() {
		DLFileShortcutCacheModel dlFileShortcutCacheModel = new DLFileShortcutCacheModel();

		dlFileShortcutCacheModel.uuid = getUuid();

		String uuid = dlFileShortcutCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			dlFileShortcutCacheModel.uuid = null;
		}

		dlFileShortcutCacheModel.fileShortcutId = getFileShortcutId();

		dlFileShortcutCacheModel.groupId = getGroupId();

		dlFileShortcutCacheModel.companyId = getCompanyId();

		dlFileShortcutCacheModel.userId = getUserId();

		dlFileShortcutCacheModel.userName = getUserName();

		String userName = dlFileShortcutCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			dlFileShortcutCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			dlFileShortcutCacheModel.createDate = createDate.getTime();
		}
		else {
			dlFileShortcutCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			dlFileShortcutCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			dlFileShortcutCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		dlFileShortcutCacheModel.repositoryId = getRepositoryId();

		dlFileShortcutCacheModel.folderId = getFolderId();

		dlFileShortcutCacheModel.toFileEntryId = getToFileEntryId();

		dlFileShortcutCacheModel.treePath = getTreePath();

		String treePath = dlFileShortcutCacheModel.treePath;

		if ((treePath != null) && (treePath.length() == 0)) {
			dlFileShortcutCacheModel.treePath = null;
		}

		dlFileShortcutCacheModel.active = isActive();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			dlFileShortcutCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			dlFileShortcutCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		dlFileShortcutCacheModel.status = getStatus();

		dlFileShortcutCacheModel.statusByUserId = getStatusByUserId();

		dlFileShortcutCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = dlFileShortcutCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			dlFileShortcutCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			dlFileShortcutCacheModel.statusDate = statusDate.getTime();
		}
		else {
			dlFileShortcutCacheModel.statusDate = Long.MIN_VALUE;
		}

		return dlFileShortcutCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DLFileShortcut, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((4 * attributeGetterFunctions.size()) +
				2);

		sb.append("{");

		for (Map.Entry<String, Function<DLFileShortcut, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<DLFileShortcut, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DLFileShortcut)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<DLFileShortcut, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((5 * attributeGetterFunctions.size()) +
				4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DLFileShortcut, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<DLFileShortcut, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DLFileShortcut)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DLFileShortcut.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DLFileShortcut.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _fileShortcutId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _repositoryId;
	private long _folderId;
	private long _originalFolderId;
	private boolean _setOriginalFolderId;
	private long _toFileEntryId;
	private long _originalToFileEntryId;
	private boolean _setOriginalToFileEntryId;
	private String _treePath;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private Date _lastPublishDate;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private long _columnBitmask;
	private DLFileShortcut _escapedModel;
}