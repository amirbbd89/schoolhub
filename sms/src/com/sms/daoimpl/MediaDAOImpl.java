package com.sms.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sms.dao.MediaDAO;

@Service
public class MediaDAOImpl implements MediaDAO {
	@Autowired
	MongoOperations mongoOperations;

	@Override
	public boolean saveFile(String fileId, byte[] inputBytes) {
		try {
			GridFS gridFS = new GridFS(mongoOperations.getCollection("userinfo").getDB());
			
			if(gridFS.find(fileId).size() > 0){
				gridFS.remove(fileId);
			}
			
			GridFSInputFile gfsFile = gridFS.createFile(inputBytes);
			gfsFile.setFilename(fileId);
			gfsFile.setId(fileId);
			gfsFile.save();

			return true;
		} catch (Exception e) {}
		return false;
	}

	@Override
	public GridFSDBFile getFile(String fileId) {
		try {
			return new GridFS(mongoOperations.getCollection("userinfo").getDB()).findOne(fileId);
		} catch (Exception e) {}
		
		return null;
	}
}