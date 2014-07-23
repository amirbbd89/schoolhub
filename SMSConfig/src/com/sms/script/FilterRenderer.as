package
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.containers.Panel;
	import mx.controls.AdvancedDataGrid;
	import mx.controls.Button;
	import mx.controls.DataGrid;
	import mx.controls.Label;
	import mx.controls.LinkButton;
	import mx.controls.TextInput;
	import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
	import mx.controls.advancedDataGridClasses.AdvancedDataGridHeaderRenderer;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.events.FlexMouseEvent;
	import mx.managers.PopUpManager;
	
	public class FilterRenderer extends AdvancedDataGridHeaderRenderer
	{
		private var hBox:HBox;
		private var filterTXT:TextInput;
		private var img:LinkButton;
		private var filterTXTshown:Boolean;
		private var panel:Panel;
		
		[Embed(source="assets/icons/filter.png")]private var filterIcon:Class;
		
		public function FilterRenderer() {
			super();
			filterTXTshown = false;
			img = new LinkButton();
			img.setStyle("icon",filterIcon);
			img.addEventListener(MouseEvent.CLICK, filterButtonClickHandler);
			img.addEventListener(MouseEvent.MOUSE_OVER, stopPropagationHandler);
			img.addEventListener(MouseEvent.MOUSE_DOWN, stopPropagationHandler);
			
			filterTXT = new TextInput();
			filterTXT.name = "filterText";
			filterTXT.addEventListener(KeyboardEvent.KEY_UP, function():void{
				setSearchKey((data as AdvancedDataGridColumn).dataField,filterTXT.text);
				((owner as AdvancedDataGrid).dataProvider as ArrayCollection).filterFunction=filterFirst;
				((owner as AdvancedDataGrid).dataProvider as ArrayCollection).refresh();
			});
			filterTXT.percentWidth = 100;
			
			panel = new Panel();
			panel.width = 150;
			panel.title = "Search";
			panel.addChild(filterTXT);
			
			hBox = new HBox();
			hBox.styleName = this.styleName;
			hBox.setStyle("verticalAlign","center");
		}
		
		public function setSearchKey(fid:String,val:String):void{
			for each(var ob:Object in parentApplication.filterVar){
				if(String(ob.fid)==fid){
					ob.val=val;
				}
			}
		}
		
		protected function filterButtonClickHandler(event:MouseEvent):void {
			if(filterTXTshown){
				filterTXTshown = false;
				hideTextInputPopUp();
			}else{
				filterTXTshown = true;
				showTextInputPopUp();
			}
		}
		
		protected function showTextInputPopUp():void {
			PopUpManager.addPopUp(panel, this, false);
			panel.addEventListener(FlexMouseEvent.MOUSE_DOWN_OUTSIDE,modalClose);
			var editorPos:Point = localToGlobal(new Point(0,height));
			panel.x = editorPos.x;
			panel.y = editorPos.y;
			panel.isPopUp=false;
			filterTXT.setFocus();
		}
		
		protected function modalClose(e:FlexMouseEvent):void {
			filterTXTshown = false;
			panel.removeEventListener(FlexMouseEvent.MOUSE_DOWN_OUTSIDE,modalClose);
			hideTextInputPopUp();
		}
		
		protected function hideTextInputPopUp():void {
			PopUpManager.removePopUp(panel);
		}
		
		private function filterFirst(item:Object):Boolean{
			var bool:Boolean=true;
			if(String(item.allowDelete)=="false"){
				bool=true;	
			}else{
				for each(var ob:Object in parentApplication.filterVar){
					if(!item[String(ob.fid)].match(new RegExp(String(ob.val), 'i'))){
						bool=false;
					}
				}
			}
			return bool;
		}
		
		protected function stopPropagationHandler(event:Event):void {
			event.stopPropagation();
		}
		
		override protected function createChildren():void {
			super.createChildren();
			addChild(hBox);
			removeChild(label as DisplayObject);
			hBox.addChild(img);
			hBox.addChild(label as DisplayObject);
		}
		
		override protected function commitProperties():void {
			super.commitProperties();
			if(filterTXTshown) {
				img.toolTip = parentApplication.getConstants("FILETR_DONE");
			} else {
				img.toolTip = parentApplication.getConstants("DO_FILTER");
			}
		}
		
		private function sizeRenderer():void {
			img.x = 0 ;
			hBox.setActualSize( hBox.getExplicitOrMeasuredWidth() ,getExplicitOrMeasuredHeight());
			measuredHeight = measuredMinHeight = hBox.getExplicitOrMeasuredHeight();
			measuredWidth = measuredMinWidth = hBox.getExplicitOrMeasuredWidth();
		}
		
		override protected function measure():void {
			super.measure();
			sizeRenderer();
		}
		
		override protected function updateDisplayList(unscaledWidth:Number,unscaledHeight:Number):void {		
			super.updateDisplayList(unscaledWidth,unscaledHeight) ;						
			sizeRenderer();
		}
	}
}