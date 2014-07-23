package com.sms.taglibs;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import com.sms.model.ContentBox;

public class ContentView extends TagSupport {
	private static final long serialVersionUID = 7723305003197784247L;
	private ContentBox contentBox;

	public ContentView() {
		super();
	}

	/**
	 * doStartTag is called by the JSP container when the tag is encountered
	 */
	public int doStartTag() {
		StringBuffer htmlTextBuffer = new StringBuffer("");
//		htmlTextBuffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+Settings.APP_CONTEXT+"/css/style.css\">");
//		htmlTextBuffer.append("<img src=\""+Settings.APP_LOGO_URL+"\" style=\"float:left\" width=\"95px\" height=\"79px\">");

		if(contentBox.getActive() == 1) {
			htmlTextBuffer.append("<h2><u>"+contentBox.getTitle()+"</u></h2>");
			htmlTextBuffer.append("<p>"+contentBox.getContentTxt()+"</p>");
		}

		try {
			pageContext.getOut().println(htmlTextBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public void setContentBox(ContentBox contentBox) {
		this.contentBox = contentBox;
	}
}