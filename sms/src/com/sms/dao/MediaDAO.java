package com.sms.dao;

import com.mongodb.gridfs.GridFSDBFile;

public interface MediaDAO {
	public boolean saveFile(String fileId, byte[] inputBytes);
	public GridFSDBFile getFile(String fileId);
}