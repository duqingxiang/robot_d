package com.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.bean.RobotPhotos;
import com.demo.bean.RobotPosition;
import com.demo.bean.RobotResource;
import com.demo.bean.RobotUser;
import com.demo.dao.DemoDao;
import com.demo.service.DemoService;
import com.demo.util.DateUtil;
import com.demo.util.MD5Util;
import com.demo.util.StringUtil;

public class DemoServiceImpl implements DemoService {

	private DemoDao demoDao;
	
	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}
	public long insertBrowseRecord(String ipAddress,String url,String sessionId){
		return demoDao.insertBrowseRecord(ipAddress, url, sessionId);
	}
	public long getBrowseRecordStatistics(int type){
		List<Map<String,Object>> list = demoDao.getBrowseRecordStatistics(type);
		if(CollectionUtils.isEmpty(list))
			return 0 ;
		
		long result = list.get(0) != null ? StringUtil.toLong(list.get(0).get("count").toString(), 0L) : 0 ;
		return result ;
	}
	
	public RobotUser getRobotUserFromCookies(String cookie){
		List<Map<String,Object>> list = demoDao.getRobotUserFromCookies(cookie);
		if(CollectionUtils.isEmpty(list))
			return null ;
		return RobotUser.convert(list.get(0)) ;
	}
	
	public RobotUser getRobotUserById(long userid) {
		return RobotUser.convert(demoDao.getRobotUserById(userid));
	}
	public RobotUser loginRobotUser(String username ,String password){
		String md5Password = "";
		if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
			return null ;
		}
		md5Password = MD5Util.getMD5String(password);
		
		RobotUser user = getRobotUserByUsername(username,md5Password);
		
		if(user == null){
			return null;
		}
		if(user.getIsActive() == 0){
			String cookieStr = user.getUserId()+""+System.currentTimeMillis();
			String cookies = MD5Util.getMD5String(cookieStr);
			long res = demoDao.updateRobotUserLoginCookies(user.getUserId(), cookies);
			if(res < 0){
				return null ;
			}
			user.setModifyDate(new Date());
			user.setLoginCookie(cookies);
		}
		
		return user ;
	}
	
	public RobotUser getRobotUserByUsername(String username ,String password){
		List<Map<String,Object>> list = demoDao.getRobotUserByUsername(username, password);
		if(CollectionUtils.isEmpty(list))
			return null ;
		return RobotUser.convert(list.get(0)); 
	}
	
	public List<RobotUser> getRobotUserFromEmail(String email){
		List<Map<String,Object>> list = demoDao.getRobotUserFromEmail(email);
		if(CollectionUtils.isEmpty(list))
			return null ;
		
		List<RobotUser> userList= new ArrayList<RobotUser>();
		for(Map<String,Object> map : list){
			RobotUser user = RobotUser.convert(map);
			userList.add(user);
		}
		return userList ; 
	}
	
	public boolean isUserExist(String email){
		List<RobotUser> list = this.getRobotUserFromEmail(email);
		if(CollectionUtils.isEmpty(list))
			return false ;
		
		return true ;
	}
	
	public RobotUser registerRobotUser(String email,String password,String nickName){
		
		String md5Password = MD5Util.getMD5String(password);
		String cookieStr = email+""+System.currentTimeMillis();
		String md5Cookie = MD5Util.getMD5String(cookieStr);
		
		long res = demoDao.insertRegRobotUser(email, md5Password, md5Cookie,nickName);
		
		if(res <= 0)
			return null ;

		RobotUser user =this.getRobotUserFromCookies(md5Cookie);// new RobotUser();
		return user ;
	}
	
	
	//---------------上传相关---------------------
	
	public long insertRobotResource(RobotResource res){
		return demoDao.insertRobotResource(res);
	}
	
	public List<RobotResource> getRobotResourceByType(long userid,int type,int page,int pageSize){
		List<Map<String,Object>> list = demoDao.getRobotResourceByType( userid,type, page, pageSize);
		return RobotResource.convertToList(list);
	}
	
	public RobotResource getRobotResourceById(long id) {
		return RobotResource.convert(demoDao.getRobotResourceById(id));
	}
	
	public List<RobotResource> getRobotResources(long userid,int page,int pageSize) {
		List<Map<String,Object>> list = demoDao.getRobotResources(userid,page, pageSize);
		return RobotResource.convertToList(list);
	}
	
	public long deleteRobotResourceById(long id){
		return demoDao.deleteRobotResourceById(id);
	}
	
	
	//---------------坐标相关---------------------
	public long insertRobotPosition(RobotPosition pos){
		String years = DateUtil.dateUtil2String(new Date(), "yyyy");
		pos.setYears(years);
		pos.setImgPath("");
		return demoDao.insertRobotPosition(pos);
	}
	public List<RobotPosition> getRobotPostionByYears(long userid,int type,String years){
		return RobotPosition.convertToList(demoDao.getRobotPostionByYears(userid,type, years)) ;
	}
	public List<RobotPosition> getRobotPostionAll(long userid){
		return RobotPosition.convertToList(demoDao.getRobotPostionAll(userid)) ;
	}
	public RobotPosition getRobotPositionById(long id){
		return RobotPosition.convert(demoDao.getRobotPositionById(id));
	}
	public long updateRobotPositionImgPath(long id,String path){
		return demoDao.updateRobotPositionImgPath(id, path);
	}
	public long deleteRobotPositionById(long id){
		return demoDao.deleteRobotPositionById(id);
	}
	
	public long editPositionImgPath(long resource_id,long position_id){
		
		RobotResource res = this.getRobotResourceById(resource_id);
		if (res == null) 
			return -3;
		
		RobotPosition pos = this.getRobotPositionById(position_id);
		if (pos == null)
			return -4;
		
		
		long result = this.updateRobotPositionImgPath(pos.getId(), res.getMiniPath());
		if (result<=0)
			return -5;
					
		return 1 ;
	}
	
	
	public long insertRobotPhotos(RobotPhotos pho) {
		return demoDao.insertRobotPhotos(pho);
	}
	public RobotPhotos getRobotPhotosById(long id) {
		return RobotPhotos.convert(demoDao.getRobotPhotosById(id));
	}
	public List<RobotPhotos> getRobotPhotosByPosition(long userid,long positionId) {
		return RobotPhotos.convertToList(demoDao.getRobotPhotosByPosition(userid,positionId));
	}
	public long deleteRobotPhotosById(long id) {
		return demoDao.deleteRobotPhotosById(id);
	}
	
	public long insertStatisticsFood(String phone ,String content ,String ip){
		return demoDao.insertStatisticsFood(phone, content, ip);
	}
	public boolean isStatisticsFoodExist(String phone){
		List<Map<String,Object>> list = demoDao.getStatisticsFoodByPhone(phone);
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		return true;
	}
	
}
