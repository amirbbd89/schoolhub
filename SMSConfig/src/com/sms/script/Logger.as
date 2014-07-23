package com.sms.script
{
	import mx.core.FlexGlobals;
	import mx.rpc.Fault;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;

	public class Logger
	{
		private var logRemoteObj:RemoteObject;
		public function Logger(){
			logRemoteObj = new RemoteObject("loggerAMF");
			logRemoteObj.endpoint = FlexGlobals.topLevelApplication.parameters.appContext + "/messagebroker/amf";
			logRemoteObj.addEventListener(ResultEvent.RESULT, onSuccess);
			logRemoteObj.addEventListener(FaultEvent.FAULT, onFault);
		}

		private function onFault(event:FaultEvent):void {
			trace(event.fault.faultString);
		}

		private function onSuccess(event:ResultEvent):void {
			trace(event.result as String);
		}

		private function log(msg:String, type:String):void {
			try {
				logRemoteObj.writeLog(msg,type);
			} catch(err:Error) {}
		}

		public static function info(msg:String):void {
			new Logger().log(msg, "INFO");
		}

		public static function error(fault:Fault):void {
			var msg:String = fault.faultCode +" :: "+ fault.faultDetail +" :: "+ fault.faultString;
			new Logger().log(msg, "Error");
		}

		public static function debug(msg:String):void {
			new Logger().log(msg, "DEBUG");
		}
	}
}