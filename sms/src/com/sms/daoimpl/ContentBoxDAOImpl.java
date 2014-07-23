package com.sms.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sms.dao.ContentBoxDAO;
import com.sms.model.ContentBox;

@Service
public class ContentBoxDAOImpl implements ContentBoxDAO {
	@Autowired
	MongoOperations mongoOperations;

	@Override
	public ContentBox getContentBox(String contentBoxId) {
		try {
			return mongoOperations.findOne(new Query(Criteria.where("contentBoxId").is(contentBoxId)), ContentBox.class);
		} catch (Exception e) {}
		
		return null;
	}

	@Override
	public List<ContentBox> getAllContentBox(String schoolId, String tabId) {
		try {
			return mongoOperations.find(new Query(Criteria.where("schoolId").is(schoolId).and("tabId").is(tabId)), ContentBox.class);
		} catch (Exception e) {}
		
		return null;
	}
}