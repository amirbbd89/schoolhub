package com.sms.taglibs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import com.sms.utils.Settings;

public class HeaderFooter extends TagSupport {
	private static final long serialVersionUID = 7521214713555219036L;
	
	private Boolean isUserNameAndLogoutReq;
	private Boolean isBackButtonReq;
	private String backUrl;
	private String pageTitle;
	private String schoolId;
	
	public HeaderFooter() {
		super();
		setIsUserNameAndLogoutReq(false);
		setPageTitle(Settings.DEFAULT_PAGE_TITLE);
		setIsBackButtonReq(false);
		setBackUrl("");
	}
	
	/**
	 * doStartTag is called by the JSP container when the tag is encountered
	 */
    public int doStartTag() {
    	StringBuffer htmlTextBuffer = new StringBuffer("<link rel=\"stylesheet\" type=\"text/css\" href=\""+Settings.APP_CONTEXT+"/css/style.css\">");
    	
    	htmlTextBuffer.append("<div class=\"headerDiv\">");
    	
    	if(null == schoolId){
    		htmlTextBuffer.append("<img src=\""+Settings.APP_CONTEXT+Settings.APP_LOGO_URL+"\" style=\"float:left\" width=\"95px\" height=\"79px\">");
    	}
    	
    	htmlTextBuffer.append("<h1>&nbsp;&nbsp;&nbsp;"+Settings.APP_TITLE+"</h1></div>");
    	htmlTextBuffer.append("<div class=\"titleBarDiv\"><div style=\"float:left;\">&nbsp;&nbsp;&nbsp;<b>"+pageTitle+"</b></div>");
    	
    	if(isBackButtonReq || isUserNameAndLogoutReq){
    		htmlTextBuffer.append("<div style=\"float:right;\">");

    		if(isUserNameAndLogoutReq){
    			htmlTextBuffer.append("<b>Welcome "+((HttpServletRequest)pageContext.getRequest()).getSession(true).getAttribute("username")+"</b>");
    			htmlTextBuffer.append("&nbsp;&nbsp;<a class=\"whiteLink\" href=\""+Settings.APP_CONTEXT+"/admin/toEditProfile.htm\">Edit Profile</a>&nbsp;&nbsp;&nbsp;");
    			htmlTextBuffer.append("&nbsp;&nbsp;<a class=\"whiteLink\" href=\""+Settings.APP_CONTEXT+"/admin/toChangePassword.htm\">Change Password</a>&nbsp;&nbsp;&nbsp;");
    			htmlTextBuffer.append("&nbsp;&nbsp;<a class=\"whiteLink\" href=\""+Settings.APP_CONTEXT+"/j_spring_security_logout\">Logout</a>&nbsp;&nbsp;&nbsp;");
    		}
    		
    		if(isBackButtonReq){
    			htmlTextBuffer.append("&nbsp;&nbsp;<a class=\"whiteLink\" href=\""+Settings.APP_CONTEXT+backUrl+"\">Back</a>&nbsp;&nbsp;&nbsp;");
    		}
    		
    		htmlTextBuffer.append("</div>");
    	}
    	
    	htmlTextBuffer.append("</div>");
    	
    	htmlTextBuffer.append("<div class=\"footerDiv\"><div style=\"float:left;\">&nbsp;&nbsp;&nbsp;@Designed & Developed By Mohammad Aamir & Mohammad Javed Iqubal</div><div style=\"float:right;\">Email: <a  class=\"whiteLink\" href=\"mailto:amirbbd89@gmail.com\">amirbbd89@gmail.com</a> | <a class=\"whiteLink\" href=\"mailto:equbal.javed@gmail.com\">equbal.javed@gmail.com</a>&nbsp;&nbsp;&nbsp;</div></div>");
    	try {
    		pageContext.getOut().println(htmlTextBuffer.toString());
		} catch (IOException e) {e.printStackTrace();}
    	return SKIP_BODY;
    }

	/**
     * doEndTag is called by the JSP container when the tag is closed
     */
	public int doEndTag() {
		return EVAL_PAGE;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public void setIsUserNameAndLogoutReq(Boolean isUserNameAndLogoutReq) {
		this.isUserNameAndLogoutReq = isUserNameAndLogoutReq;
	}

	public void setIsBackButtonReq(Boolean isBackButtonReq) {
		this.isBackButtonReq = isBackButtonReq;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
}