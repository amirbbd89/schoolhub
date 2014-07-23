package com.sms.script
{
	import flash.display.Sprite;
	import mx.containers.VBox;
	import mx.controls.ProgressBar;
	import mx.core.FlexGlobals;
	import mx.managers.PopUpManager;
	
	public class Mask extends VBox {
		private static var mask:Mask;
		private var msg:String;
		
		public function Mask() {
			super();
		}
		
		public static function show(message:String="Please wait..", parent:Sprite=null):Mask {
			mask = new Mask();
			mask.msg = message;
			PopUpManager.addPopUp(mask, parent||Sprite(FlexGlobals.topLevelApplication), true);
			PopUpManager.centerPopUp(mask);
			mask.setFocus();
			return mask;	
		}
		
		public static function close():void {
			PopUpManager.removePopUp(mask);
		}
		
		override protected function createChildren():void {
			super.createChildren();
			this.setStyle("horizontalAlign","center");
			var pb:ProgressBar = new ProgressBar();
			pb.width=100;
			pb.label = msg;
			
			pb.indeterminate = true;
			pb.setStyle("fontSize",10);
			addChild(pb);
		}
	}
}