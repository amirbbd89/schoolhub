package com.sms.model;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="tabs")
@XmlRootElement(name="tabs")
public class Tab implements Comparable<Tab>{
	@Id
	private String tabId;
	private String schoolId;
	private String menuLable;
	private int order;
	private int active;
	
	public Tab() {}
	
	public Tab(String tabId, String schoolId, String menuLable, int order, int active) {
		super();
		this.tabId = tabId;
		this.schoolId = schoolId;
		this.menuLable = menuLable;
		this.order = order;
		this.active = active;
	}
	
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getMenuLable() {
		return menuLable;
	}
	public void setMenuLable(String menuLable) {
		this.menuLable = menuLable;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public int compareTo(Tab tab) {
		if(tab.order > order){
			return -1;
		} else if(tab.order > order) {
			return 1;
		}
		
		return 0;
	}
}