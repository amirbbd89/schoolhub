package com.sms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.gridfs.GridFSDBFile;
import com.sms.dao.GenericDAO;
import com.sms.dao.SchoolDAO;
import com.sms.dao.UserDAO;
import com.sms.model.ContentBox;
import com.sms.model.School;
import com.sms.model.Tab;
import com.sms.model.UserInfo;
import com.sms.services.Mailer;
import com.sms.utils.IDGenerator;

@SuppressWarnings("all")
@Controller
public class LoginController{
	@Autowired
	private SchoolDAO schoolDAO;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private GenericDAO genericDAO;
	
	@Autowired
	private IDGenerator iDGenerator;
	
	@Autowired
	private Mailer mailer;
	
	private ModelAndView mav; 
	public LoginController(){
		mav = new ModelAndView();
	}
	
	@RequestMapping(value="/login/onLoginFailed.htm")
	public ModelAndView onLoginFailed(HttpServletRequest request){
		request.setAttribute("msgType", "Error");
		if(request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION") instanceof DisabledException){
			request.setAttribute("msg", "! Login Failed : Account "+request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME")+" is disabled");
		}else{
			request.setAttribute("msg", "! Login Failed : Invalid credentials");
		}
		
		mav.setViewName("loginView");
		return mav;
	}
	
	@RequestMapping(value="/login/onLogoutSuccess.htm")
	public ModelAndView onLogoutSuccess(HttpServletRequest request){
		request.setAttribute("msgType", "Success");
		request.setAttribute("msg", "Logged Out Successfully.");
		
		mav.setViewName("loginView");
		return mav;
	}
	
	@RequestMapping(value="/login/toSignUp.htm")
	public ModelAndView toSignUp(HttpServletRequest request){
		request.setAttribute("mode", "SIGN_UP");
		
		mav.setViewName("addEditUser");
		return mav;
	}
	
	@RequestMapping(value="/login/onSaveProfile.htm")
	public ModelAndView onSaveUser(HttpServletRequest request){
		School school = new School();
		school.setSchoolId(iDGenerator.generateSchoolId());
		UserInfo userInfo = new UserInfo();
		userInfo.setEnabled(true);
		userInfo.setSchoolId(school.getSchoolId());
		String mode = "SIGN_UP";
		
		school.setSchoolName(request.getParameter("schoolName"));
		school.setAddress(request.getParameter("address"));
		school.setCity(request.getParameter("city"));
		school.setState(request.getParameter("state"));						
		school.setPincode(request.getParameter("pincode"));
		school.setCountry(request.getParameter("country"));
		userInfo.setPassword(request.getParameter("password"));
		school.setAdminEmailId(request.getParameter("adminEmailId"));
		userInfo.setUsername(school.getAdminEmailId());
		school.setPhoneNumber(request.getParameter("phoneNumber"));
		mode = request.getParameter("mode");
		
		String statusMsg = "";
		boolean isDone = genericDAO.updateObject(school);
		isDone = isDone && genericDAO.updateObject(userInfo);
		
		if(isDone){
			request.setAttribute("msgType", "Success");
			
			if(mode.equals("EDIT_PROFILE")){
				statusMsg = "School profile updated successfully.";
				mav.setViewName("welcome");
			}else{
				Tab tab = new Tab("ht_"+school.getSchoolId(), school.getSchoolId(), "Home", 0, 1);
				isDone = isDone && genericDAO.insertObject(tab);
				
				ContentBox contentBox = new ContentBox(iDGenerator.generateContentBoxId(), school.getSchoolId(), tab.getTabId(), "Welcome to Home Page","Welcome to Home Page", 0, 1, false, null);
				isDone = isDone && genericDAO.insertObject(contentBox);
								
				statusMsg = "School profile created successfully.";	
				mav.setViewName("loginView");
			}
			
			String htmlMessage = "<h2>"+statusMsg+"</h2><br/>UserName: <b>"+userInfo.getUsername()+"</b><br/>Password: <b>"+userInfo.getPassword()+"</b><br/>";
			mailer.sendEmail(userInfo.getUsername(), "", "", "WELCOME TO SCHOOL MANAGEMENT SYSTEM", htmlMessage, null, null);
		} else {
			request.setAttribute("msgType", "Error");
			
			if(mode.equals("EDIT_PROFILE")){
				statusMsg = "Error while updating School profile.";
				mav.setViewName("welcome");
			}else{
				statusMsg = "Error creating School profile . Please try again after sometime.";
				mav.setViewName("loginView");
			}
		}
		
		request.setAttribute("msg", statusMsg);
		
		return mav;
	}
	
	@RequestMapping(value="/login/toForgotPassword.htm")
	public ModelAndView toForgotPassword(HttpServletRequest request){
		request.setAttribute("mode", "FORGOT_PASSWORD");
		mav.setViewName("forgotChangePassword");
		return mav;
	}
	
	@RequestMapping(value="/login/sendPassword.htm")
	public ModelAndView sendPassword(HttpServletRequest request){
		String email = request.getParameter("email");
		UserInfo userInfo = userDao.getUserInfoByEmail(email);
		request.setAttribute("msgType", "Error");
		request.setAttribute("mode", "FORGOT_PASSWORD");
		mav.setViewName("forgotChangePassword");
		if(userInfo != null){
			boolean isMailSent = mailer.sendEmail(userInfo.getUsername(), "", "", "Passsword Recovery", "<b>Email:</b> "+userInfo.getUsername()+"<br/> <b>Username:</b> "+userInfo.getUsername()+"<br/> <b>Password:</b> "+ userInfo.getPassword(), null,"");
			if(isMailSent){
				request.setAttribute("msgType", "Success");
				request.setAttribute("msg", "Please check your Mailbox for password");
				mav.setViewName("loginView");
			}else{
				request.setAttribute("msg", "Server Error Please Try Again Later.");
			}
		}else{
			request.setAttribute("msg", "Invalid Email Id");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/login/enableAccount.htm")
	public void enableAccount(HttpServletRequest request, HttpServletResponse response){
		boolean isEnabled = false;
		
		try {
			UserInfo userInfo = userDao.getUserInfoByEmail(request.getParameter("EMAIL_ID").trim());
			if(null != userInfo){
				userInfo.setEnabled(true);
				isEnabled = genericDAO.updateObject(userInfo);
			}
		} catch (Exception e) {e.printStackTrace();}
		
		try {
			if(isEnabled) {
				response.getWriter().write("<h3 style='color:green'>Account activated successfully. Go to <a href='/login.jsp'>Login Page</a></h3>");
			} else {
				response.getWriter().write("<h2 style='color:red'>Page Expired</h2>");
			}
		} catch (Exception e) {e.printStackTrace();}
	}
}