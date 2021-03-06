<%--
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
--%>

<%@ include file="/document_library/init.jsp" %>

<liferay-util:dynamic-include key="com.liferay.document.library.web#/document_library/view_file_entry.jsp#pre" />

<%
String redirect = ParamUtil.getString(request, "redirect");

FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

long fileEntryId = fileEntry.getFileEntryId();

long folderId = fileEntry.getFolderId();

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	long parentFolderId = folderId;

	if (!DLFolderPermission.contains(permissionChecker, fileEntry.getGroupId(), parentFolderId, ActionKeys.VIEW)) {
		parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}

	portletURL.setParameter("mvcRenderCommandName", (parentFolderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) ? "/document_library/view" : "/document_library/view_folder");
	portletURL.setParameter("folderId", String.valueOf(parentFolderId));

	redirect = portletURL.toString();
}

Folder folder = fileEntry.getFolder();
FileVersion fileVersion = (FileVersion)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_VERSION);

boolean versionSpecific = false;

if (fileVersion != null) {
	versionSpecific = true;
}
else if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) {
	fileVersion = fileEntry.getLatestFileVersion();
}
else {
	fileVersion = fileEntry.getFileVersion();
}

com.liferay.portal.kernel.lock.Lock lock = fileEntry.getLock();

long assetClassPK = 0;

if (!fileVersion.isApproved() && !fileVersion.getVersion().equals(DLFileEntryConstants.VERSION_DEFAULT) && !fileEntry.isInTrash()) {
	assetClassPK = fileVersion.getFileVersionId();
}
else {
	assetClassPK = fileEntry.getFileEntryId();
}

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.fetchEntry(DLFileEntryConstants.getClassName(), assetClassPK);

request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, layoutAssetEntry);

DLPortletInstanceSettingsHelper dlPortletInstanceSettingsHelper = new DLPortletInstanceSettingsHelper(dlRequestHelper);
final DLViewFileVersionDisplayContext dlViewFileVersionDisplayContext = dlDisplayContextProvider.getDLViewFileVersionDisplayContext(request, response, fileVersion);

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);

	renderResponse.setTitle(fileVersion.getTitle());
}
%>

<c:if test="<%= portletTitleBasedNavigation %>">

	<%
	request.setAttribute("file_entry_upper_tbar.jsp-dlViewFileVersionDisplayContext", dlViewFileVersionDisplayContext);
	request.setAttribute("file_entry_upper_tbar.jsp-fileEntry", fileEntry);
	request.setAttribute("file_entry_upper_tbar.jsp-fileVersion", fileVersion);
	request.setAttribute("file_entry_upper_tbar.jsp-versionSpecific", versionSpecific);
	%>

	<liferay-util:include page="/document_library/file_entry_upper_tbar.jsp" servletContext="<%= application %>" />
</c:if>

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<portlet:actionURL name="/document_library/edit_file_entry" var="editFileEntry" />

	<aui:form action="<%= editFileEntry %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="fileEntryId" type="hidden" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
	</aui:form>

	<c:if test="<%= !portletTitleBasedNavigation && showHeader && (folder != null) %>">
		<liferay-ui:header
			backURL="<%= redirect %>"
			localizeTitle="<%= false %>"
			title="<%= fileVersion.getTitle() %>"
		/>
	</c:if>

	<c:if test="<%= !portletTitleBasedNavigation %>">
		<div class="btn-group">
			<liferay-frontend:management-bar-sidenav-toggler-button
				label="info"
			/>

			<c:if test="<%= dlPortletInstanceSettingsHelper.isShowActions() %>">

				<%
				for (ToolbarItem toolbarItem : dlViewFileVersionDisplayContext.getToolbarItems()) {
				%>

					<liferay-ui:toolbar-item
						toolbarItem="<%= toolbarItem %>"
					/>

				<%
				}
				%>

			</c:if>
		</div>
	</c:if>

	<liferay-frontend:sidebar-panel>

		<%
		request.setAttribute("info_panel.jsp-fileEntry", fileEntry);
		request.setAttribute("info_panel.jsp-fileVersion", fileVersion);
		%>

		<liferay-util:include page="/document_library/info_panel_file_entry.jsp" servletContext="<%= application %>" />
	</liferay-frontend:sidebar-panel>

	<div class="sidenav-content">
		<div class="alert alert-danger hide" id="<portlet:namespace />openMSOfficeError"></div>

		<c:if test="<%= (fileEntry.getLock() != null) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
			<c:choose>
				<c:when test="<%= fileEntry.hasLock() %>">
					<div class="alert alert-success">
						<c:choose>
							<c:when test="<%= lock.isNeverExpires() %>">
								<liferay-ui:message key="you-now-have-an-indefinite-lock-on-this-document" />
							</c:when>
							<c:otherwise>

								<%
								String lockExpirationTime = StringUtil.toLowerCase(LanguageUtil.getTimeDescription(request, DLFileEntryConstants.LOCK_EXPIRATION_TIME));
								%>

								<liferay-ui:message arguments="<%= lockExpirationTime %>" key="you-now-have-a-lock-on-this-document" translateArguments="<%= false %>" />
							</c:otherwise>
						</c:choose>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(PortalUtil.getUserName(lock.getUserId(), String.valueOf(lock.getUserId()))), dateFormatDateTime.format(lock.getCreateDate())} %>" key="you-cannot-modify-this-document-because-it-was-locked-by-x-on-x" translateArguments="<%= false %>" />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<div class="body-row">
			<c:if test="<%= PropsValues.DL_FILE_ENTRY_PREVIEW_ENABLED %>">

				<%
				PortalIncludeUtil.include(
					pageContext,
					new PortalIncludeUtil.HTMLRenderer() {

						@Override
						public void renderHTML(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
							dlViewFileVersionDisplayContext.renderPreview(request, response);
						}

					});
				%>

			</c:if>

			<c:if test="<%= showComments && fileEntry.isRepositoryCapabilityProvided(CommentCapability.class) %>">
				<liferay-ui:panel
					collapsible="<%= true %>"
					cssClass="lfr-document-library-comments panel-group"
					extended="<%= true %>"
					markupView="lexicon"
					persistState="<%= true %>"
					title="<%= dlViewFileVersionDisplayContext.getDiscussionLabel(locale) %>"
				>
					<liferay-comment:discussion
						className="<%= dlViewFileVersionDisplayContext.getDiscussionClassName() %>"
						classPK="<%= dlViewFileVersionDisplayContext.getDiscussionClassPK() %>"
						formName="fm2"
						ratingsEnabled="<%= dlPortletInstanceSettings.isEnableCommentRatings() %>"
						redirect="<%= currentURL %>"
						userId="<%= fileEntry.getUserId() %>"
					/>
				</liferay-ui:panel>
			</c:if>
		</div>
	</div>
</div>

<c:if test="<%= dlPortletInstanceSettingsHelper.isShowActions() && dlAdminDisplayContext.isVersioningStrategyOverridable() %>">

	<%
	request.setAttribute("edit_file_entry.jsp-checkedOut", fileEntry.isCheckedOut());
	%>

	<liferay-util:include page="/document_library/version_details.jsp" servletContext="<%= application %>" />
</c:if>

<aui:script>
	function <portlet:namespace />compare() {
		var rowIds = AUI.$('input[name=<portlet:namespace />rowIds]:checked');
		var sourceFileVersionId = AUI.$('input[name="<portlet:namespace />sourceFileVersionId"]');
		var targetFileVersionId = AUI.$('input[name="<portlet:namespace />targetFileVersionId"]');

		var rowIdsSize = rowIds.length;

		if (rowIdsSize == 1) {
			sourceFileVersionId.val(rowIds.eq(0).val());
		}
		else if (rowIdsSize == 2) {
			sourceFileVersionId.val(rowIds.eq(1).val());

			targetFileVersionId.val(rowIds.eq(0).val());
		}

		submitForm(document.<portlet:namespace />fm1);
	}

	function <portlet:namespace />initRowsChecked() {
		AUI.$('input[name=<portlet:namespace />rowIds]').each(
			function(index, item) {
				if (index >= 2) {
					item = AUI.$(item);

					item.prop('checked', false);
				}
			}
		);
	}

	function <portlet:namespace />updateRowsChecked(element) {
		var rowsChecked = AUI.$('input[name=<portlet:namespace />rowIds]:checked');

		if (rowsChecked.length > 2) {
			var index = 2;

			if (rowsChecked.eq(2).is(element)) {
				index = 1;
			}

			rowsChecked.eq(index).prop('checked', false);
		}
	}
</aui:script>

<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) && DLUtil.isOfficeExtension(fileVersion.getExtension()) && portletDisplay.isWebDAVEnabled() && BrowserSnifferUtil.isIeOnWin32(request) %>">
	<%@ include file="/document_library/action/open_document_js.jspf" %>
</c:if>

<aui:script sandbox="<%= true %>">
	<portlet:namespace />initRowsChecked();

	$('input[name=<portlet:namespace />rowIds]').on(
		'click',
		function(event) {
			<portlet:namespace />updateRowsChecked($(event.currentTarget));
		}
	);
</aui:script>

<%
boolean addPortletBreadcrumbEntries = ParamUtil.getBoolean(request, "addPortletBreadcrumbEntries", true);

if (addPortletBreadcrumbEntries) {
	DLBreadcrumbUtil.addPortletBreadcrumbEntries(fileEntry, request, renderResponse);
}
%>

<liferay-util:dynamic-include key="com.liferay.document.library.web#/document_library/view_file_entry.jsp#post" />