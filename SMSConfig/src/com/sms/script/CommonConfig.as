import com.sms.components.CreateTab;
import com.sms.components.CreateContent;

import com.sms.script.Logger;
import com.sms.script.Mask;
import com.sms.model.Tab;
import com.sms.model.ContentBox;

import mx.core.FlexGlobals;
import mx.managers.PopUpManager;
import mx.collections.ArrayCollection;
import mx.controls.Alert;

import mx.rpc.events.ResultEvent;
import mx.events.FlexEvent;
import mx.rpc.events.FaultEvent;


[Bindable]private var tabGridData:ArrayCollection;
[Bindable]private var contentGridData:ArrayCollection;
[Bindable]private var appContext:String;

[Embed(source="com/sms/assets/images/ok-icon.png")] public var activeIcon:Class;
[Embed(source="com/sms/assets/images/restrict.png")] public var deactiveIcon:Class;

private var schoolId:String;
private var username:String;
private var selectedTabId:String;
protected function onApplicationComplete(event:FlexEvent):void {
	schoolId = FlexGlobals.topLevelApplication.parameters.schoolId;
	username = FlexGlobals.topLevelApplication.parameters.username;
	appContext = FlexGlobals.topLevelApplication.parameters.appContext;
	
	tabGridData = new ArrayCollection();
	contentGridData = new ArrayCollection();

	smsAMFObj.getAllTab(schoolId);
	showProgress();
}

protected function faultHandler(event:FaultEvent):void {
	hideProgress();
	Logger.error(event.fault);
}

private function showMessage(message:String):void {
	Alert.show(message);
}

private function showProgress(message:String="Please wait..", parent:Sprite=null):void {
	Mask.show(message, parent);
}

private function hideProgress():void {
	Mask.close();
}