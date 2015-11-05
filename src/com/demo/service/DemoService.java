package com.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.demo.bean.RobotPhotos;
import com.demo.bean.RobotPosition;
import com.demo.bean.RobotResource;
import com.demo.bean.RobotUser;
import com.demo.bean.UserBean;
import com.demo.util.DateUtil;

public interface DemoService {
	/**
	 * 插入浏览记录
	 * @param ipAddress
	 * @param url
	 * @param sessionId
	 * @return
	 */
	public long insertBrowseRecord(String ipAddress,String url,String sessionId);
	/**
	 * 浏览统计
	 * @param type 0:全部  1:ip统计  2:sessionid统计
	 * @return
	 */
	public long getBrowseRecordStatistics(int type);
	
	public RobotUser getRobotUserFromCookies(String cookie);
	
	public RobotUser loginRobotUser(String username ,String password);
	
	public RobotUser getRobotUserByUsername(String username ,String password);
	
	public List<RobotUser> getRobotUserFromEmail(String email);
	
	public boolean isUserExist(String email);
	
	public RobotUser registerRobotUser(String email,String password,String nickName);
	
	public RobotUser getRobotUserById(long userid);
	
	
	/**
	 * 资源相关
	 * @param res
	 * @return
	 */
	public long insertRobotResource(RobotResource res);
	
	public List<RobotResource> getRobotResourceByType(long userid,int type,int page,int pageSize);
	
	public RobotResource getRobotResourceById(long id) ;
	
	public List<RobotResource> getRobotResources(long userid,int page,int pageSize);
	public long deleteRobotResourceById(long id);
	
	/**
	 * 坐标相关
	 * @param pos
	 * @return
	 */
	public long insertRobotPosition(RobotPosition pos);
	public List<RobotPosition> getRobotPostionByYears(long userid,int type,String years);
	public List<RobotPosition> getRobotPostionAll(long userid);
	public RobotPosition getRobotPositionById(long id);
	public long updateRobotPositionImgPath(long id,String path);
	public long deleteRobotPositionById(long id);
	public long editPositionImgPath(long resource_id,long position_id);
	
	/**
	 * 相册相关
	 * @param pho
	 * @return
	 */
	public long insertRobotPhotos(RobotPhotos pho);
	public RobotPhotos getRobotPhotosById(long id) ;
	public List<RobotPhotos> getRobotPhotosByPosition(long userid,long positionId) ;
	public long deleteRobotPhotosById(long id);
	
	/**
	 * 统计相关
	 */
	public long insertStatisticsFood(String phone ,String content ,String ip);
	public boolean isStatisticsFoodExist(String phone);
}
