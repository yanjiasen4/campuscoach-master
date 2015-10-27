package com.action;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.manager.LearnerManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAvatarAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8677673256561492336L;
	
	private String uploadDir;   
	private LearnerManager learnerManager;
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	public LearnerManager getLearnerManager() {
		return learnerManager;
	}
	public void setLearnerManager(LearnerManager learnerManager) {
		this.learnerManager = learnerManager;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public String execute() throws FileNotFoundException, IOException{
		ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        String username = (String) session.get("user");
        int learnerID = (Integer) session.get("id");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); 
        java.util.Date date=new java.util.Date();  
        String str=format.format(date); 
        String newFileName = username + '-' + str +  fileFileName.substring(fileFileName.indexOf('.'));
		String savePath = uploadFile(newFileName,learnerID);
		learnerManager.updateAvatar(learnerID, savePath);
		session.put("avatar", savePath); // 将头像路径放入session中以便上传头像后，刷新时更新头像
		return SUCCESS;
	}
	
	public String uploadFile(String newFileName, int learnerID) throws FileNotFoundException, IOException {
		String realPath = ServletActionContext.getServletContext().getRealPath("upload");
		File uploadFile = new File(realPath);
		//判断上传文件的保存目录是否存在
		if (!uploadFile.exists() && !uploadFile.isDirectory()) {
			System.out.println(realPath+"目录不存在，需要创建");
			//创建目录
			uploadFile.mkdir();
		}
		String userStr = ((Integer)learnerID).toString();
		File userDir = new File(uploadFile.getPath()+'/'+userStr);
		if(!userDir.exists() && !userDir.isDirectory()) {
			System.out.println(uploadFile.getPath()+"用户目录不存在，需要创建");
			userDir.mkdir();
		}
		String savePath = userDir + "/" + newFileName;
	    FileInputStream input = new FileInputStream(file);  
		FileOutputStream out = new FileOutputStream(savePath);  
		try {  
		    byte[] b =new byte[1024];  
		    int i = 0;  
		    while ((i=input.read(b))>0) {  
		        out.write(b, 0, i);  
		    }  
	    } catch (Exception e) {  
		    e.printStackTrace();  
		} finally {  
		    //关闭输入、输出流  
		  input.close();  
		  out.close();  
		  //删除临时文件  
		  file.delete(); 
	    }   
		System.out.println("upload"+"/"+((Integer)learnerID).toString() + "/" + newFileName);
		return "upload"+"/"+((Integer)learnerID).toString() + "/" + newFileName; 
	}

}