package com.sms.dao;

import com.sms.model.UserInfo;

public interface UserDAO {
	public UserInfo getUserInfoByEmail(String email);
	public UserInfo getUserInfo(String username, String password);
}