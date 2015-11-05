package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.bean.RobotPhotos;
import com.demo.bean.RobotPosition;
import com.demo.bean.RobotResource;
import com.demo.bean.RobotUser;
import com.demo.service.DemoService;
import com.demo.test.Test;
import com.demo.util.Constants;
import com.demo.util.DateUtil;
import com.demo.util.HttpSessionUtil;
import com.demo.util.ImgCompress;
import com.demo.util.Logs;
import com.demo.util.StringUtil;
import com.demo.util.TypeUtil;
import com.demo.util.XMemcachedUtil;

public class DemoController extends MultiActionController {

	private DemoService demoService;
	
	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}
	
	
	public String  test(HttpServletRequest request,HttpServletResponse response){
		StringBuffer url = request.getRequestURL();
		String ipAddress = request.getRemoteAddr();
		String sessionId = request.getSession().getId();
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		//插入记录
		demoService.insertBrowseRecord(ipAddress, url.toString(), sessionId);
		
		long browseCount = demoService.getBrowseRecordStatistics(0);
		long ipCount = demoService.getBrowseRecordStatistics(1);
		long sessionidCount = demoService.getBrowseRecordStatistics(2);
		
		request.setAttribute("browseCount", browseCount);
		request.setAttribute("ipCount", ipCount);
		request.setAttribute("sessionidCount", sessionidCount);
		request.setAttribute("user", user);
		return "test";
	}
	
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(!StringUtil.isEmail(username) || StringUtil.isEmpty(password)){
			out.print(-1);
			return null ;
		}
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		
		if(user != null){
			//已登录
			out.print(1);
			return null ;
		}
		
		user = demoService.loginRobotUser(username, password);
		
		if(user.getIsActive() == 1){
			out.print(-2);
			return null ;
		}
		
		//设置cookie,缓存
		HttpSessionUtil.putUserBean(request, response, user);
		out.print(1);
		return null;
	}
	
	public String register(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		String username = request.getParameter("reg_username");
		String password = request.getParameter("reg_password");
		String password2 = request.getParameter("reg_password2");
		String nickName = request.getParameter("nick_name");
		if(!StringUtil.isEmail(username) || StringUtil.isEmpty(password) ||StringUtil.isEmpty(password2) ){
			out.print(-1);
			return null ;
		}
		
		if(!password.equals(password2)){
			out.print(-2);
			return null ;
		}
		
		boolean isExist = demoService.isUserExist(username);
		if(isExist){
			out.print(-3);
			return null ;
		}
		
		RobotUser user = demoService.registerRobotUser(username, password,nickName);
		if(user == null){
			out.print(-4);
			return null;
		}
		
		//设置cookie
		HttpSessionUtil.putUserBean(request, response, user);
		out.print(1);
		return null ;
	}
	
	public String logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		
		if (user != null) {
			HttpSessionUtil.removeUserBean(request, response, user);
		}
		out.print(1);
		return null ;
	}
	
	public String  about(HttpServletRequest request,HttpServletResponse response){
		
		return "login" ;
	}
	
	public String uploadManager(HttpServletRequest request,HttpServletResponse response){
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if(user == null){
			return test(request,response) ;
		}
		
		int page = StringUtil.toInteger(request.getParameter("page"), 0);
		List<RobotResource> resources = demoService.getRobotResources(user.getUserId(),(page*20), 20);
		
		List<RobotPosition> positions = demoService.getRobotPostionAll(user.getUserId());
		
		request.setAttribute("positions", positions);
		request.setAttribute("resources", resources);
		request.setAttribute("page", page);
		return "upload" ;
	}
	
	public String  timeline(HttpServletRequest request,HttpServletResponse response){
		
		return "/story/timeline" ;
	}
	
	
	public String deleteFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if(user == null ){
			out.print(-1);
			return null;
		}
		
		long id = StringUtil.toLong(request.getParameter("id"),0L);
		if (id == 0) {
			out.print(-2);
			return null;
		}
		
		RobotResource res = demoService.getRobotResourceById(id);
		if (res == null) {
			out.print(-3);
			return null;
		}
		String p = res.getRealPath();
		Logs.geterrorLogger().error("deleteFile filePath:-->"+p);
		File file = new File(p);
		if ( !file.exists() ) {
			out.print(-4);
			return null;
		}
		String mp = file.getParent()+"/mini/mini_"+file.getName();
		Logs.geterrorLogger().error("deleteFile miniFilePath:-->"+mp);
		File miniFile = new File(mp);
		
		if ( !miniFile.exists() ) {
			out.print(-5);
			return null;
		}
		
		file.delete();
		miniFile.delete();
		
		long re = demoService.deleteRobotResourceById(id);
		if (re <=0) {
			out.print(-6);
			return null;
		}
		out.print(1);
		return null;
	}
	
	public String uploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if(user == null){
			out.print(-1);
			return null;
		}
		DiskFileItemFactory fac = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(fac);  
        upload.setHeaderEncoding("utf-8");  
        List fileList = null;  
        try {  
            fileList = upload.parseRequest(request);  
        } catch (FileUploadException ex) {  
            return null;  
        }  
        Iterator<FileItem> it = fileList.iterator();  
        String name = "";  
        String extName = "";
        String path = Constants.UPLOAD_REAL_PATH;
        String netPath = Constants.UPLOAD_NET_PATH;
        String miniPath = Constants.UPLOAD_MINI_PATH;
        String finalName = "";
        while (it.hasNext()) {  
            FileItem item = it.next();  
            if (!item.isFormField()) {  
                name = item.getName();  
                long size = item.getSize();  
                String type = item.getContentType();  
                System.out.println(size + " " + type);  
                if (name == null || name.trim().equals("")) {  
                    continue;  
                }  
      
                // 扩展名格式：  
                if (name.lastIndexOf(".") >= 0) {  
                    extName = name.substring(name.lastIndexOf("."));  
                }  
                
                File file = null;  
                do {  
                    // 生成文件名：  
                    name = UUID.randomUUID().toString();  
                    finalName = name + extName;
                    path = path + finalName ;
                    netPath = netPath + finalName;
                    miniPath = miniPath +"mini_"+ finalName;
                    file = new File(path);
                } while (file.exists());  
      
                File saveFile = new File(path);  
                try {  
                    item.write(saveFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                
                ImgCompress imgCom = new ImgCompress(path,Constants.UPLOAD_REAL_PATH+"/mini/mini_"+finalName);  
                imgCom.resizeFix(100, 100);  
                
                //插入资源列表
                RobotResource res = new RobotResource();
                res.setName(finalName);
                res.setType(TypeUtil.getType(finalName));
                res.setRealPath(path);
                res.setNetPath(netPath);
                res.setMiniPath(miniPath);
                res.setUserId(user.getUserId());
                demoService.insertRobotResource(res);
                
            }  
        } 

        JSONObject json = new JSONObject();
        json.put("name", finalName);
        json.put("path", path);
        json.put("netPath", netPath);
		out.print(json.toJSONString());
		return null ;
	}
	
	public String  index(HttpServletRequest request,HttpServletResponse response){
		
		return "test";
	}
	
	
	public String  map(HttpServletRequest request,HttpServletResponse response){
		
		return "map" ;
	}
	
	public String insertPosition(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if(user == null){
			out.print(-1);
			return null;
		}
		
		String positionX = request.getParameter("positionX");
		String positionY = request.getParameter("positionY");
		String tags = request.getParameter("tag");
		String content = request.getParameter("content");
		int type = user.getUserId() >10 ? 0 : 1;
		
		if (StringUtil.isEmpty(positionX) || StringUtil.isEmpty(positionY)) {
			out.print(-2);
			return null;
		}
		
		RobotPosition pos = new RobotPosition();
		pos.setType(type);
		pos.setPositionX(positionX);
		pos.setPositionY(positionY);
		pos.setTags(tags);
		pos.setContent(content);
		pos.setUserId(user.getUserId());
		long res = demoService.insertRobotPosition(pos);
		if (res <=0) {
			out.print(-3);
			return null;
		}
		out.print(1);
		return null;
	}
	
	public String getPositions(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		
		
		String years = request.getParameter("years");
		int type = user.getUserId() > 10 ? 0 : 1;
		
		if (StringUtil.isEmpty(years)) {
			years = DateUtil.dateUtil2String(new Date(), "yyyy");
		}
		
		
		List<RobotPosition> list = demoService.getRobotPostionByYears(user.getUserId(),type, years);
		int suc = CollectionUtils.isEmpty(list) ? 0 : 1;
		JSONObject json = new JSONObject();
		json.put("success", suc);
		json.put("positionList", JSON.toJSONString(list));
		
		out.print(json.toJSONString());
		return null;
	}
	
	public String editPositions(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		
		
		long res_id = StringUtil.toLong(request.getParameter("resource_id"), 0L);
		long pos_id = StringUtil.toLong(request.getParameter("position_id"), 0L);
		
		if (res_id == 0 || pos_id == 0) {
			out.print(-2);
			return null;
		}
		
		long res = demoService.editPositionImgPath(res_id, pos_id);
		out.print(res);
		return null;
	}
	
	public String positionManager(HttpServletRequest request,HttpServletResponse response){
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			return test(request, response);
		}
		List<RobotPosition> positions = demoService.getRobotPostionAll(user.getUserId());
		List<RobotResource> resources = demoService.getRobotResources(user.getUserId(),0, 50);
		
		request.setAttribute("positions", positions);
		request.setAttribute("resources", resources);
		
		return "managerPosition";
	}
	
	public String deletePosition(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		if (id == 0) {
			out.print(-2);
			return null ;
		}
		
		RobotPosition position = demoService.getRobotPositionById(id);
		if (position == null) {
			out.print(-3);
			return null ;
		}
		
		long res = demoService.deleteRobotPositionById(id);
		if (res <=0) {
			out.print(-4);
			return null;
		}
		out.print(1);
		return null;
	}
	
	public String photo(HttpServletRequest request,HttpServletResponse response){
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			return test(request, response);
		}
		
		long positionId = StringUtil.toLong(request.getParameter("posid"), 0L);
		if (positionId == 0) {
			return test(request, response);
		}
		
		List<RobotPhotos> photos = demoService.getRobotPhotosByPosition(user.getUserId(),positionId);
		if (CollectionUtils.isEmpty(photos))
			return "redirect:/error.do?param=1";
		
		JSONArray photosJSON = new JSONArray();
		int count = 1;
		for (RobotPhotos pho : photos) {
			JSONObject json = new JSONObject();
			json.put("picPos", count);
			json.put("pid", pho.getId());
			json.put("bigPic", pho.getNetPath());
			json.put("thumbPic", pho.getMiniPath());
			photosJSON.add(json);
			count ++;
		}
		request.setAttribute("picListJson", photosJSON.toJSONString());
		request.setAttribute("photos", photos);
		return "/photo/photo";
	}
	
	public String photoManager(HttpServletRequest request,HttpServletResponse response){
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			return test(request, response);
		}
		long position_id = StringUtil.toLong(request.getParameter("posid"), 0L);
		if (position_id == 0)//跳回上传管理
			return test(request, response);
		
		RobotPosition position = demoService.getRobotPositionById(position_id);
		
		if (position == null)//坐标id 错误，返回上传管理
			return test(request, response);
		
		List<RobotPhotos> photos = demoService.getRobotPhotosByPosition(user.getUserId(),position_id);
		List<RobotResource> resources = demoService.getRobotResources(user.getUserId(),0, 50);
		
		request.setAttribute("position", position);
		request.setAttribute("photos", photos);
		request.setAttribute("resources", resources);
		return "managerPhotos";
	}
	
	
	public String addPhotos(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		
		long position_id = StringUtil.toLong(request.getParameter("position_id"), 0L);
		String ids = request.getParameter("ids");
		if (position_id == 0 || StringUtil.isEmpty(ids)) {
			out.print(-2);
			return null;
		}
		
		RobotPosition position = demoService.getRobotPositionById(position_id);
		if (position == null) {
			out.print(-3);
			return null;
		}
		
		String [] idArray = ids.split(",");
		
		for (String id : idArray) {
			long res_id = StringUtil.toLong(id, 0L);
			if (res_id == 0)
				continue;
			
			RobotResource res = demoService.getRobotResourceById(res_id);
			
			if (res == null) 
				continue;
			
			RobotPhotos pho = new RobotPhotos();
			pho.setPositionId(position_id);
			pho.setNetPath(res.getNetPath());
			pho.setMiniPath(res.getMiniPath());
			pho.setUserId(user.getUserId());
			demoService.insertRobotPhotos(pho);
		}
		
		out.print(1);
		return null ;
	}
	
	public String deletePhotos(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null ) {
			out.print(-1);
			return null;
		}
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		if (id == 0) {
			out.print(-2);
			return null ;
		}
		
		RobotPhotos pho = demoService.getRobotPhotosById(id);
		if (pho == null) {
			out.print(-3);
			return null;
		}
		
		long res = demoService.deleteRobotPhotosById(id);
		if (res <=0) {
			out.print(-4);
			return null;
		}
		
		out.print(1);
		return null ;
	}
	
	public String duqingxiang (HttpServletRequest request,HttpServletResponse response) {
		
		return "myself";
	}
	
	public String t (HttpServletRequest request,HttpServletResponse response) {
		System.out.println("------->");
		return "/admin/admin";
	}

	
	public String hongbao (HttpServletRequest request,HttpServletResponse response) {
		System.out.println("------->");
		return "hongbao";
	}
	public String caihongbao (HttpServletRequest request,HttpServletResponse response)  throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String url = request.getParameter("url");
		String phone = request.getParameter("phone");
		int max = StringUtil.toInteger(request.getParameter("max"), 0);
		
		JSONObject res = new JSONObject();
		
		if (StringUtil.isEmpty(url)) {
			res.put("code", -1);
			out.print(res.toJSONString());
			return null;
		}
		if (StringUtil.isEmpty(phone)) {
			res.put("code", -2);
			out.print(res.toJSONString());
			return null;
		}
		if (max == 0) {
			res.put("code", -3);
			out.print(res.toJSONString());
			return null;
		}
		
		JSONObject json = Test.caihongbao(url, max, phone);
		if (json == null) {
			res.put("code", -4);
			out.print(res.toJSONString());
			return null;
		}
		if ("success".equals(json.get("status").toString())) {
			json.put("code", 1);
		} else {
			json.put("code", 2);
		}
		out.print(json.toJSONString());
		return null;
	}
	
	public String analysis (HttpServletRequest request,HttpServletResponse response) {
		
		return "analysis";
	}
	
	public String upload_test (HttpServletRequest request,HttpServletResponse response) {
		
		return "upload_uy";
	}
	
	public String upload_service (HttpServletRequest request,HttpServletResponse response)  throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if(user == null){
			out.print(-1);
			return null;
		}
		
		
		DiskFileItemFactory fac = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(fac);  
        upload.setHeaderEncoding("utf-8");  
        List fileList = null;  
        try {  
            fileList = upload.parseRequest(request);  
        } catch (FileUploadException ex) {  
            return null;  
        }  
        Iterator<FileItem> it = fileList.iterator();  
        String name = "";  
        String extName = "";
        String path = Constants.UPLOAD_REAL_PATH;
        String netPath = Constants.UPLOAD_NET_PATH;
        String miniPath = Constants.UPLOAD_MINI_PATH;
        String finalName = "";
        while (it.hasNext()) {  
            FileItem item = it.next();  
            if (!item.isFormField()) {  
                name = item.getName();  
                long size = item.getSize();  
                String type = item.getContentType();  
                System.out.println(size + " " + type);  
                if (name == null || name.trim().equals("")) {  
                    continue;  
                }  
      
                // 扩展名格式：  
                if (name.lastIndexOf(".") >= 0) {  
                    extName = name.substring(name.lastIndexOf("."));  
                }  
                
                File file = null;  
                do {  
                    // 生成文件名：  
                    name = UUID.randomUUID().toString();  
                    finalName = name + extName;
                    path = path + finalName ;
                    netPath = netPath + finalName;
                    miniPath = miniPath +"mini_"+ finalName;
                    file = new File(path);
                } while (file.exists());  
      
                File saveFile = new File(path);  
                try {  
                    item.write(saveFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                
                ImgCompress imgCom = new ImgCompress(path,Constants.UPLOAD_REAL_PATH+"/mini/mini_"+finalName);  
                imgCom.resizeFix(100, 100);  
                
                //插入资源列表
                RobotResource res = new RobotResource();
                res.setName(finalName);
                res.setType(TypeUtil.getType(finalName));
                res.setRealPath(path);
                res.setNetPath(netPath);
                res.setMiniPath(miniPath);
                res.setUserId(user.getUserId());
                demoService.insertRobotResource(res);
                
            }  
        } 

        JSONObject json = new JSONObject();
        json.put("name", finalName);
        json.put("path", path);
        json.put("netPath", netPath);
		json.put("status", "ok");
		out.print(json.toJSONString());
		return null;
	}
	
	public String submitAnalysis (HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String content = request.getParameter("content");
		String phone = request.getParameter("phone");
		String ipAddress = request.getRemoteAddr();
		if (StringUtil.isEmpty(phone)) {
			out.print("还没填手机号哦！~");
		}
		if (StringUtil.isEmpty(content)) {
			out.print("还没有填写内容哟！~");
			return null;
		}
		
		boolean exist = demoService.isStatisticsFoodExist(phone);
		if (exist) {
			out.print("这个手机号已经提交过哦！~");
			return null;
		}
		
		long res = demoService.insertStatisticsFood(phone, content, ipAddress);
		if (res <= 0) {
			out.print("系统出现异常了。。。。");
			return null;
		}
		
		String cacheStr = (String)XMemcachedUtil.get("analysisList");
		List<String> list = JSON.parseArray(cacheStr, String.class);
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<String>();
		}
		list.add(content);
		//超时时间设置为90天
		XMemcachedUtil.put("analysisList", JSON.toJSONString(list));
		out.println("感谢您提交的调查内容，非常感谢，么么哒！");
		return null;
	}
	public String showAnalysis (HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String str = request.getParameter("key");
		if (!"DUdu123456".equals(str)) {
			out.print("密码错误");
			return null;
		}
		
		String cacheStr = (String)XMemcachedUtil.get("analysisList");
		List<String> list = JSON.parseArray(cacheStr, String.class);
		if (CollectionUtils.isEmpty(list)) {
			out.print("还没有投票内容呢！~");
			return null;
		}
		StringBuffer content = new StringBuffer();

		int i = 1 ;
		for (String s : list) {
			content.append("<p>"+i+"、"+s+"</p>");
			i++;
		}
		out.println(content.toString());
		return null;
	}
	
}
