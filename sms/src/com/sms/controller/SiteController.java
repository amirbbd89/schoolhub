package com.sms.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sms.dao.ContentBoxDAO;
import com.sms.dao.SchoolDAO;
import com.sms.dao.TabDAO;
import com.sms.model.ContentBox;

@SuppressWarnings("all")
@Controller
public class SiteController {
	@Autowired
	private SchoolDAO schoolDAO;
	
	@Autowired
	private TabDAO tabDAO;
	
	@Autowired
	ContentBoxDAO contentBoxDAO;
	
	private ModelAndView mav; 
	public SiteController(){
		mav = new ModelAndView();
	}
	
	@RequestMapping(value="/view/{schoolId}/home.htm")
	public ModelAndView showHomePage(@PathVariable String schoolId){
		return showPage(schoolId, "ht_"+schoolId);
	}
	
	@RequestMapping(value="/view/{schoolId}/{tabId}.htm")
	public ModelAndView showPage(@PathVariable String schoolId, @PathVariable String tabId) {
		List<ContentBox> contentBoxList = contentBoxDAO.getAllContentBox(schoolId, tabId);
		Collections.sort(contentBoxList);
		
		mav.addObject("schoolId", schoolId);
		mav.addObject("tabId", tabId);
		mav.addObject("school", schoolDAO.getSchool(schoolId));
		mav.addObject("tab", tabDAO.getTab(tabId));
		mav.addObject("tabList", tabDAO.getAllTab(schoolId));
		mav.addObject("contentBoxList", contentBoxList);
		mav.setViewName("viewPage");
		return mav;
	}
}