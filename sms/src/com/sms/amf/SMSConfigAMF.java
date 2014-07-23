package com.sms.amf;

import java.util.List;
import java.util.Map;

import org.springframework.flex.security.AuthenticationResultUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sms.dao.ContentBoxDAO;
import com.sms.dao.GenericDAO;
import com.sms.dao.TabDAO;
import com.sms.model.ContentBox;
import com.sms.model.Tab;
import com.sms.utils.IDGenerator;

import flex.messaging.FlexContext;

public class SMSConfigAMF {
	public List<Tab> updateTab(Tab tab) {
		GenericDAO<Tab> genericDAO = (GenericDAO<Tab>) getWebApplicationContext().getBean(GenericDAO.class);
		
		genericDAO.updateObject(tab);
		
		return getAllTab(tab.getSchoolId());
	}
	
	public List<Tab> insertTab(Tab tab) {
		GenericDAO<Tab> genericDAO = (GenericDAO<Tab>) getWebApplicationContext().getBean(GenericDAO.class);
		IDGenerator iDGenerator = (IDGenerator) getWebApplicationContext().getBean(IDGenerator.class);
		
		tab.setTabId(iDGenerator.generateTabId());
		genericDAO.insertObject(tab);
		
		return getAllTab(tab.getSchoolId());
	}
	
	public List<Tab> deleteTab(Tab tab) {
		GenericDAO<Tab> genericDAO = (GenericDAO<Tab>) getWebApplicationContext().getBean(GenericDAO.class);;
		
		genericDAO.deleteObject(tab);
		
		return getAllTab(tab.getSchoolId());
	}
	
	public List<Tab> getAllTab(String schoolId) {
		TabDAO tabDAO = (TabDAO) getWebApplicationContext().getBean(TabDAO.class);

		return tabDAO.getAllTab(schoolId);
	}
	
	public List<ContentBox> updateContent(ContentBox contentBox) {
		GenericDAO<ContentBox> genericDAO = (GenericDAO<ContentBox>) getWebApplicationContext().getBean(GenericDAO.class);
		
		genericDAO.updateObject(contentBox);
		
		return getAllContent(contentBox.getSchoolId(), contentBox.getTabId());
	}
	
	public List<ContentBox> insertContent(ContentBox contentBox) {
		GenericDAO<ContentBox> genericDAO = (GenericDAO<ContentBox>) getWebApplicationContext().getBean(GenericDAO.class);
		IDGenerator iDGenerator = (IDGenerator) getWebApplicationContext().getBean(IDGenerator.class);
		
		contentBox.setTabId(iDGenerator.generateContentBoxId());
		genericDAO.insertObject(contentBox);
		
		return getAllContent(contentBox.getSchoolId(), contentBox.getTabId());
	}
	
	public List<ContentBox> deleteContent(ContentBox contentBox) {
		GenericDAO<ContentBox> genericDAO = (GenericDAO<ContentBox>) getWebApplicationContext().getBean(GenericDAO.class);
		
		genericDAO.deleteObject(contentBox);
		
		return getAllContent(contentBox.getSchoolId(), contentBox.getTabId());
	}
	
	public List<ContentBox> getAllContent(String schoolId, String tabId) {
		ContentBoxDAO tabDAO = (ContentBoxDAO) getWebApplicationContext().getBean(ContentBoxDAO.class);

		return tabDAO.getAllContentBox(schoolId, tabId);
	}
	
	public Map<String, Object> getAuthentication() {
		return AuthenticationResultUtils.getAuthenticationResult();
	}
	
	private WebApplicationContext getWebApplicationContext(){
		return WebApplicationContextUtils.getWebApplicationContext(FlexContext.getServletContext());
	}
}