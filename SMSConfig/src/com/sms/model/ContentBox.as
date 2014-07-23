package com.sms.model{		
	[RemoteClass(alias="com.sms.model.ContentBox")]
	public class ContentBox{
		private var _contentBoxId:String;
		private var _tabId:String;
		private var _schoolId:String;
		private var _title:String;
		private var _contentTxt:String;
		private var _imageId:String;
		private var _image:Boolean;
		private var _order:int;
		private var _active:int;
		
		public function ContentBox(){}
		
		public function get contentBoxId():String{
			return _contentBoxId;
		}
		public function set contentBoxId(value:String):void{
			_contentBoxId = value;
		}
		
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
		
		public function get title():String{
			return _title;
		}
		public function set title(value:String):void{
			_title = value;
		}
		
		public function get contentTxt():String{
			return _contentTxt;
		}
		public function set contentTxt(value:String):void{
			_contentTxt = value;
		}
		
		public function get imageId():String{
			return _imageId;
		}
		public function set imageId(value:String):void{
			_imageId = value;
		}
		
		public function get image():Boolean{
			return _image;
		}
		public function set image(value:Boolean):void{
			_image = value;
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