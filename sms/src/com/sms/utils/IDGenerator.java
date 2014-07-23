package com.sms.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sms.dao.ContentBoxDAO;
import com.sms.dao.SchoolDAO;
import com.sms.dao.TabDAO;
import com.sms.model.ContentBox;
import com.sms.model.School;
import com.sms.model.Tab;

@Component
public class IDGenerator {
	@Autowired
	SchoolDAO schoolDAO;
	
	@Autowired
	TabDAO tabDAO;
	
	@Autowired
	ContentBoxDAO contentBoxDAO;
	
	private final String DATA = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNM";
	
	public String generateSchoolId(){
		String schoolId="";
		School school = new School();
		
		while(school != null){
			schoolId = getRandomString(4);
			school = schoolDAO.getSchool(schoolId);
		}
		
		return schoolId;
	}
	
	public String generateTabId(){
		String tabId="";
		Tab tab = new Tab();
		
		while(tab != null){
			tabId = getRandomString(7);
			tab = tabDAO.getTab(tabId);
		}
		
		return tabId;
	}
	
	public String generateContentBoxId(){
		String contentBoxId="";
		ContentBox contentBox = new ContentBox();
		
		while(contentBox != null){
			contentBoxId = getRandomString(9);
			contentBox = contentBoxDAO.getContentBox(contentBoxId);
		}
		
		return contentBoxId;
	}
	
	private String getRandomString(int numberOfDigots){
		StringBuffer str = new StringBuffer();
		Random random = new Random();
		for(int i = 0; i < numberOfDigots; i++){
			int n = Math.abs(random.nextInt()) % DATA.length();
			str.append(DATA.charAt(n));
		}
		
		return str.toString();
	}
}