package com.sms.utils;

public class Settings {
	public static final String APP_CONTEXT;
	public static final String APP_HOST_URL;
	
	public static final String APP_FAVICON_URL = "/images/icon/favicon.ico";
	public static final String APP_LOGO_URL = "/images/logo.jpg";
	public static final String APP_TITLE = "SCHOOL HUB";
	public static final String DEFAULT_PAGE_TITLE = "LOGIN PAGE";
	public static final String APP_ADMIN_MAILID = "admin@schoolmanagementsystem.aws.af.cm";
	public static final String APP_ADMIN_MAILBOX_TITLE = "SCHOOL HUB ADMIN";
	
	static {
		if(null == System.getenv("VCAP_SERVICES")){
			APP_CONTEXT = "/sms";
			APP_HOST_URL = "http://localhost:8080/sms";
		} else {
			APP_CONTEXT = "";
			APP_HOST_URL = "http://schoolmanagementsystem.aws.af.cm";
		}
	}
}