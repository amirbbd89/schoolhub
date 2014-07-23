package com.sms.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sms.dao.SchoolDAO;
import com.sms.model.School;

@Service
public class SchoolDAOImpl implements SchoolDAO {
	@Autowired
	MongoOperations mongoOperations;
	
	@Override
	public School getSchool(String schoolId) {
		try {
			return mongoOperations.findOne(new Query(Criteria.where("schoolId").is(schoolId)), School.class);
		} catch (Exception e) {
		}
		
		return null;
	}
}