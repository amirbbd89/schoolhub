package com.sms.dao;

import java.util.List;

import com.sms.model.Tab;

public interface TabDAO {
	Tab getTab(String tabId);
	List<Tab> getAllTab(String schoolId);
}