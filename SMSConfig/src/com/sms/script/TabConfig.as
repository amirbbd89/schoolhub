protected function onCreateTab():void {
	 var tabPanel:CreateTab = CreateTab(PopUpManager.createPopUp(this, CreateTab,true));
	 tabPanel.tab = new Tab();
	 tabPanel.tab.schoolId = schoolId;
	 tabPanel.maxOrder = tabGridData.length;
	 tabPanel.title = "Create Tab";
	 
	 PopUpManager.centerPopUp(tabPanel);
}

public function onSaveTab(tab:Tab, isUpdate: Boolean = false):void {
	showProgress();
	
	if(isUpdate) {
		smsAMFObj.updateTab(tab);
	} else {
		smsAMFObj.insertTab(tab);
	}
}

public function onEditTab(rowData:Object):void {
	var tabPanel:CreateTab = CreateTab(PopUpManager.createPopUp(this, CreateTab,true));
	tabPanel.tab = Tab(rowData);
	tabPanel.tab.schoolId = schoolId;
	tabPanel.maxOrder = tabGridData.length;
	tabPanel.title = "Edit Tab";
	
	PopUpManager.centerPopUp(tabPanel);
}

public function onDeleteTab(rowData:Object):void {
	showProgress();
	
	smsAMFObj.deleteTab(Tab(rowData));
}

public function onEnableDisableTab(rowData:Object):void {
	showProgress();
	
	var tab:Tab = Tab(rowData);
	tab.active = 1 - tab.active;
	smsAMFObj.updateTab(tab);
}

public function onConfigTab(rowData:Object):void {
	showProgress();
	
	selectedTabId = Tab(rowData).tabId;
	smsAMFObj.getAllContent(schoolId, selectedTabId);
}

protected function tabResultHandler(event:ResultEvent):void {
	tabGridData = event.result as ArrayCollection;
	hideProgress();
}