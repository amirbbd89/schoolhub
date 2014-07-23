package com.sms.model{
	[RemoteClass(alias="com.sms.model.Tab")]
	public class Tab{
		private var _tabId:String;
		private var _schoolId:String;
		private var _menuLable:String;
		private var _order:int;
		private var _active:int;
		
		public function Tab(){}
		
		public function get tabId():String{
			return _tabId;
		}
		public function set tabId(value:String):void{
			_tabId = value;
		}
		public function get schoolId():String{
			return _schoolId;
		}
		public function set schoolId(value:String):void{
			_schoolId = value;
		}
		public function get menuLable():String{
			return _menuLable;
		}
		public function set menuLable(value:String):void{
			_menuLable = value;
		}
		public function get order():int{
			return _order;
		}
		public function set order(value:int):void{
			_order = value;
		}
		public function get active():int{
			return _active;
		}
		public function set active(value:int):void{
			_active = value;
		}
	}
}