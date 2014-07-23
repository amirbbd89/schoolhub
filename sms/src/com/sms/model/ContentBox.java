package com.sms.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contentBoxes")
@XmlRootElement(name="contentBoxes")
public class ContentBox implements Comparable<ContentBox>{
	@Id
	private String contentBoxId;
	private String tabId;
	private String schoolId;
	private String title;
	private String contentTxt;
	private int order;
	private int active;
	private String imageId;
	private boolean image;
	
	public ContentBox(){}
	
	public ContentBox(String contentBoxId, String schoolId, String tabId, String title, String contentTxt, int order, int active, boolean image, String imageId) {
		super();
		this.contentBoxId = contentBoxId;
		this.tabId = tabId;
		this.schoolId = schoolId;
		this.title = title;
		this.contentTxt = contentTxt;
		this.order = order;
		this.setActive(active);
		this.imageId = imageId;
		this.image = image;
	}
	
	public String getContentBoxId() {
		return contentBoxId;
	}
	public void setContentBoxId(String contentBoxId) {
		this.contentBoxId = contentBoxId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentTxt() {
		return contentTxt;
	}
	public void setContentTxt(String contentTxt) {
		this.contentTxt = contentTxt;
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
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public boolean getImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	
	@Override
	public int compareTo(ContentBox contentBox) {
		if(contentBox.order > order){
			return -1;
		} else if(contentBox.order > order) {
			return 1;
		}
		
		return 0;
	}
}