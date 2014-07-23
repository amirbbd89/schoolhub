package com.sms.taglibs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import com.sms.model.School;
import com.sms.model.Tab;
import com.sms.utils.Settings;

public class SiteHeaderFooter extends TagSupport {
	private static final long serialVersionUID = 7521214713555219036L;
	private String schoolId;
	private String tabId;
	
	public SiteHeaderFooter() {
		super();
	}
	
	/**
	 * doStartTag is called by the JSP container when the tag is encountered
	 */
    public int doStartTag() {
    	School school = (School) pageContext.getRequest().getAttribute("school");
    	List<Tab> tabList = (ArrayList<Tab>) pageContext.getRequest().getAttribute("tabList");
    	Collections.sort(tabList);
    	
    	StringBuffer htmlTextBuffer = new StringBuffer("<link rel=\"stylesheet\" type=\"text/css\" href=\""+Settings.APP_CONTEXT+"/css/style.css\">");
    	
    	htmlTextBuffer.append("<div class=\"headerDiv\">");
    	
    	htmlTextBuffer.append("<img src=\""+Settings.APP_CONTEXT+Settings.APP_LOGO_URL+"\" style=\"float:left\" width=\"95px\" height=\"79px\">");
    	htmlTextBuffer.append("<h3>&nbsp;&nbsp;&nbsp;"+school.getSchoolName()+"</h3>"
    						+ school.getAddress() + ", " + school.getCity() + ", " + school.getState()
    						+ "-" + school.getPincode() + ". Phone #: " + school.getPhoneNumber());
    	
    	htmlTextBuffer.append("</div>");
    	
		htmlTextBuffer.append("<div class=\"titleBarDiv\" style=\"float:left;\">");
		
		for(Tab tab : tabList){
			if(tab.getTabId().equals(tabId)){
				htmlTextBuffer.append("&nbsp;&nbsp;&nbsp;<b>"+tab.getMenuLable()+"</b>");
			} else if(tab.getActive() == 1) {
				htmlTextBuffer.append("&nbsp;&nbsp;&nbsp;<a class=\"whiteLink\" href=\""+Settings.APP_CONTEXT+"/view/"+schoolId+"/"+tab.getTabId()+".htm\">"+tab.getMenuLable()+"</a>");
    		}
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

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
}