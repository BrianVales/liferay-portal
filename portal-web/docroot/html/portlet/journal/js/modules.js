;(function() {
	var PATH_PORTLET = Liferay.AUI.getPortletRootPath();

	AUI().applyConfig(
		{
			groups: {
				journal: {
					base: PATH_PORTLET + '/journal/js/',
					modules: {
						'liferay-portlet-journal': {
							path: 'main.js',
							requires: [
								'aui-base',
								'aui-dialog-iframe-deprecated',
								'aui-tooltip',
								'liferay-portlet-base',
								'liferay-util-window'
							]
						},
						'liferay-journal-content': {
							path: 'content.js',
							requires: [
								'aui-base',
								'liferay-portlet-base'
							]
						},
						'liferay-journal-navigation': {
							path: 'navigation.js',
							requires: [
								'liferay-app-view-move',
								'liferay-app-view-select',
								'liferay-portlet-base'
							]
						}
					},
					root: PATH_PORTLET + '/journal/js/'
				}
			}
		}
	);
})();