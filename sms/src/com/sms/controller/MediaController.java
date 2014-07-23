package com.sms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.gridfs.GridFSDBFile;
import com.sms.dao.MediaDAO;

@SuppressWarnings("all")
@Controller
public class MediaController {
	@Autowired
	private MediaDAO mediaDAO;
	
	@RequestMapping(value="/image/saveFile.htm")
	public void saveFile(HttpServletRequest request, HttpServletResponse response){
		try {
			String fileId = request.getParameter("fileId");
			List<FileItem> files = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			for(FileItem file : files){				
				if(!file.isFormField()){
					if(null != file.get()){
						response.getWriter().write(mediaDAO.saveFile(fileId, file.get())+"");
						break;
					}
				}
			}
		} catch (Exception e) {}
	}
	
	@RequestMapping(value="/image/showImage.htm")
	public void showImage(HttpServletRequest request, HttpServletResponse response){
		try {
			String imageId = request.getParameter("imageId");
			GridFSDBFile imageFile = mediaDAO.getFile(imageId);
				
			if(null != imageFile){
				response.setContentType("image/jpeg");
				imageFile.writeTo(response.getOutputStream());
			}
		} catch (Exception e) {}
	}
}