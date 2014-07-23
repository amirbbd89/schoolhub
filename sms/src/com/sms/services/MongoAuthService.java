package com.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sms.dao.UserDAO;
import com.sms.model.UserInfo;

@Service(value="mongoAuthService")
public class MongoAuthService implements UserDetailsService {
	@Autowired
	UserDAO userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		try {
			UserInfo userInfo = userDao.getUserInfo(username, "DONOTUSETHISPASSWORD");
			
			if(null != userInfo){
				return new User(username, userInfo.getPassword(), userInfo.isEnabled(), true, true, true, AuthorityUtils.createAuthorityList(new String[]{"AD"}));
			}
		} catch (Exception e) {
		}

		throw new UsernameNotFoundException("Invalid username/password.");
	}
}