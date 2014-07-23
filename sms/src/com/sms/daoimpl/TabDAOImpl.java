package com.sms.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sms.dao.TabDAO;
import com.sms.model.Tab;

@Service
public class TabDAOImpl implements TabDAO {
	@Autowired
	MongoOperations mongoOperations;
	
	@Override
	public Tab getTab(String tabId) {
		try {
			return mongoOperations.findOne(new Query(Criteria.where("tabId").is(tabId)), Tab.class);
		} catch (Exception e) {}
		
		return null;
	}
	
	@Override
	public List<Tab> getAllTab(String schoolId) {
		try {
			return mongoOperations.find(new Query(Criteria.where("schoolId").is(schoolId)), Tab.class);
		} catch (Exception e) {}
		
		return null;
	}
}