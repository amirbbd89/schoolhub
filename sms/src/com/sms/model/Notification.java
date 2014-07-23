package com.sms.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="notifications")
@XmlRootElement(name="notifications")
public class Notification implements Comparable<Notification>{
	@Id
	String notificationId;
	String msg;
	String linkTab;
	int order;
	Date startDate;
	Date endDate;
	
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLinkTab() {
		return linkTab;
	}
	public void setLinkTab(String linkTab) {
		this.linkTab = linkTab;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public int compareTo(Notification notification) {
		if(this.order < notification.order){
			return -1;
		} else if(notification.order < this.order){
			return 1;
		} else {
			return 0;
		}
	}
}