protected function onCreateContent():void {
	var contentPanel:CreateContent = CreateContent(PopUpManager.createPopUp(this, CreateContent,true));
	contentPanel.contentBox = new ContentBox();
	contentPanel.contentBox.schoolId = schoolId;
	contentPanel.contentBox.tabId = selectedTabId;
	contentPanel.maxOrder = contentGridData.length;
	contentPanel.title = "Create Content";
	
	PopUpManager.centerPopUp(contentPanel);
}

public function onSaveContent(contentBox:ContentBox, isUpdate: Boolean = false):void {
	showProgress();

	if(isUpdate) {
		smsAMFObj.updateContent(contentBox);
	} else {
		smsAMFObj.insertContent(contentBox);
	}
}

public function onEditContent(rowData:Object):void {
	var contentPanel:CreateContent = CreateContent(PopUpManager.createPopUp(this, CreateContent,true));
	contentPanel.contentBox = ContentBox(rowData);
	contentPanel.contentBox.schoolId = schoolId;
	contentPanel.contentBox.tabId = selectedTabId;
	contentPanel.maxOrder = contentGridData.length;
	contentPanel.title = "Edit Content";
	
	PopUpManager.centerPopUp(contentPanel);
}

public function onDeleteContent(rowData:Object):void {
	showProgress();
	
	smsAMFObj.deleteContent(ContentBox(rowData));
}

public function onEnableDisableContent(rowData:Object):void {
	showProgress();

	var contentBox:ContentBox = ContentBox(rowData);
	contentBox.active = 1 - contentBox.active;
	smsAMFObj.updateContent(contentBox);
}

protected function contentResultHandler(event:ResultEvent):void {
	contentGridData = event.result as ArrayCollection;
	if(configPanel.selectedIndex == 0){
		configPanel.selectedIndex = 1;
	}

	hideProgress();
}