package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.bean.RobotPhotos;
import com.demo.bean.RobotPosition;
import com.demo.bean.RobotResource;
import com.demo.util.Db;

public interface DemoDao {

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
	 * @param type
	 * @return
	 */
	public List<Map<String,Object>> getBrowseRecordStatistics(int type);
	
	/**
	 * 根据email查询用户
	 * @param email
	 * @return
	 */
	public List<Map<String,Object>> getRobotUserFromEmail(String email);
	/**
	 * 根据cookie获取用户
	 * @param cookie
	 * @return
	 */
	public List<Map<String,Object>> getRobotUserFromCookies(String cookie);
	
	public List<Map<String,Object>> getRobotUserByUsername(String username ,String password);
	
	public Map<String,Object> getRobotUserById(long userid);
	
	public long updateRobotUserLoginCookies(long userid,String cookies);
	
	/**
	 * 注册使用插入用户
	 * @param email
	 * @param password
	 * @param cookies
	 * @return
	 */
	public long insertRegRobotUser(String email,String password,String cookies,String nickName);
	
	/**
	 * 插入资源
	 * @param res
	 * @return
	 */
	public long insertRobotResource(RobotResource res);
	
	/**
	 * 资源查询方法
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> getRobotResourceByType(long userid,int type,int page,int pageSize);
	public Map<String,Object> getRobotResourceById(long id);
	public List<Map<String,Object>> getRobotResources(long userid,int page,int pageSize);
	public long deleteRobotResourceById(long id);
	
	/**
	 * 插入和查询坐标
	 * @param pos
	 * @return
	 */
	public long insertRobotPosition(RobotPosition pos);
	public List<Map<String,Object>> getRobotPostionByYears(long userid,int type,String years);
	public List<Map<String,Object>> getRobotPostionAll(long userid);
	public Map<String,Object> getRobotPositionById(long id);
	public long updateRobotPositionImgPath(long id,String path);
	public long deleteRobotPositionById(long id);
	
	
	/**
	 * 相册相关
	 */
	public long insertRobotPhotos(RobotPhotos pho);
	public Map<String,Object> getRobotPhotosById(long id) ;
	public List<Map<String,Object>> getRobotPhotosByPosition(long userid,long positionId) ;
	public long deleteRobotPhotosById(long id);
	
	/**
	 * 关于统计相关的
	 */
	public long insertStatisticsFood(String phone ,String content ,String ip);
	public List<Map<String,Object>> getStatisticsFoodByPhone(String phone);
}
