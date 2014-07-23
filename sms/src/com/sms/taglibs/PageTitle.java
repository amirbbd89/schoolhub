package com.sms.taglibs;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import com.sms.utils.Settings;

public class PageTitle extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String pageTitle;
	public PageTitle() {
		super();
		setPageTitle("");
	}
	
    public int doStartTag() {
    	StringBuffer htmlTextBuffer = new StringBuffer("<TITLE>");
    	
    	if(!"".equals(pageTitle)){
    		htmlTextBuffer.append(pageTitle+":");
    	}
    	
    	htmlTextBuffer.append(Settings.APP_TITLE+"</TITLE>");
    	htmlTextBuffer.append("<link rel=\"shortcut icon\" type=\"image/ico\" href=\""+Settings.APP_CONTEXT+Settings.APP_FAVICON_URL+"\">");
    	htmlTextBuffer.append("<link rel=\"icon\" type=\"image/ico\" href=\""+Settings.APP_CONTEXT+Settings.APP_FAVICON_URL+"\">");
    	
    	try {
    		pageContext.getOut().println(htmlTextBuffer.toString());
		} catch (IOException e) {e.printStackTrace();}
    	return SKIP_BODY;
    }

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
}