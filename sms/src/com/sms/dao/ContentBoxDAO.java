package com.sms.dao;

import java.util.List;

import com.sms.model.ContentBox;

public interface ContentBoxDAO {
	ContentBox getContentBox(String contentBoxId);
	List<ContentBox> getAllContentBox(String schoolId, String tabId);
}