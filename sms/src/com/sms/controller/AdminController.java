package com.sms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sms.dao.GenericDAO;
import com.sms.dao.SchoolDAO;
import com.sms.dao.UserDAO;
import com.sms.model.School;
import com.sms.model.UserInfo;

@SuppressWarnings("all")
@Controller
public class AdminController {
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private SchoolDAO schoolDAO;
	
	@Autowired
	private GenericDAO genericDAO;
	
	private ModelAndView mav;
	public AdminController(){
		mav = new ModelAndView();
	}
	
	private UserInfo initUserInfo(HttpServletRequest request){
		UserInfo userInfo;
		
		if(null == request.getSession().getAttribute("userInfo")){
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			userInfo = userDao.getUserInfo(username, "DONOTUSETHISPASSWORD");
			School school = schoolDAO.getSchool(userInfo.getSchoolId());
			
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("schoolId", school.getSchoolId());
			request.getSession().setAttribute("school", school);
			request.getSession().setAttribute("userInfo", userInfo);
		} else {
			userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		}
		
		return userInfo;
	}
	
	@RequestMapping(value="/admin/onLoginSuccess.htm")
	public ModelAndView onLoginSuccess(HttpServletRequest request){
		UserInfo userInfo = initUserInfo(request);
		
		mav.addObject("username", userInfo.getUsername());
		mav.addObject("schoolId", userInfo.getSchoolId());
		
		mav.setViewName("welcome");
		return mav;
	}
	
	@RequestMapping(value="/admin/onChangePassword.htm")
	public ModelAndView changePassword(HttpServletRequest request){
		UserInfo userInfo = initUserInfo(request);
		
		String oldPassword = request.getParameter("password");
		String newpassword = request.getParameter("newpassword");
		
		request.setAttribute("msgType", "Error");
		request.setAttribute("mode", "CHANGE_PASSWORD");
		
		if(oldPassword != null && userInfo != null && oldPassword.equals(userInfo.getPassword())){
			userInfo.setPassword(newpassword);
			if(genericDAO.updateObject(userInfo)){
				request.setAttribute("msgType", "Success");
				request.setAttribute("msg", "Password updated successfully");
			}else{
				request.setAttribute("msg", "Unable to update. Please try again");
			}
		}else{
			request.setAttribute("msg", "Invalid current password");
		}
		
		mav.setViewName("forgotChangePassword");
		return mav;
	}
	
	@RequestMapping(value="/admin/toChangePassword.htm")
	public ModelAndView toChangePassword(HttpServletRequest request){
		initUserInfo(request);
		
		request.setAttribute("mode", "CHANGE_PASSWORD");
		
		mav.setViewName("forgotChangePassword");
		return mav;
	}
	
	@RequestMapping(value="/admin/toEditProfile.htm")
	public ModelAndView toEditProfile(HttpServletRequest request){
		UserInfo userInfo = initUserInfo(request);
		School school = (School) request.getSession().getAttribute("school");
		
		request.setAttribute("mode", "EDIT_PROFILE");
		request.setAttribute("USER_INFO", userInfo);
		request.setAttribute("SCHOOL_INFO", school);
		
		mav.setViewName("addEditUser");
		return mav;
	}
		
	@RequestMapping(value="/admin/onUpdateProfile.htm")
	public ModelAndView onUpdateProfile(HttpServletRequest request){
		mav.setViewName("forward:/login/onSaveUser.htm");
		return mav;
	}
}