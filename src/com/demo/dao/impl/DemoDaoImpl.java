package com.demo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.bean.RobotPhotos;
import com.demo.bean.RobotPosition;
import com.demo.bean.RobotResource;
import com.demo.dao.DemoDao;
import com.demo.util.Db;

public class DemoDaoImpl implements DemoDao {

	public long insertBrowseRecord(String ipAddress,String url,String sessionId){
		String sql = "insert into test (ip_address,url,session_id) values(?,?,?)";
		return Db.executeUpdate(sql,new Object[]{ipAddress,url.toString(),sessionId});
	}
	
	public List<Map<String,Object>> getBrowseRecordStatistics(int type){
		String field = "*";
		switch (type) {
			case 0:field="*" ;break;
			case 1:field="DISTINCT(ip_address)";break;
			case 2:field="DISTINCT(session_id)";break;
		}
		String sql = "select count("+field+") count from test where 1=?";
		return Db.executeQuery(sql, new Object[]{1}) ;
	}
	public List<Map<String,Object>> getRobotUserFromEmail(String email){
		String sql = "select * from robot_user where email = ?";
		return Db.executeQuery(sql, new Object[]{email}) ;
	}
	public List<Map<String,Object>> getRobotUserFromCookies(String cookie){
		String sql = "select * from robot_user where login_cookies = ? limit 1";
		return Db.executeQuery(sql, new Object[]{cookie}) ;
	}
	public List<Map<String,Object>> getRobotUserByUsername(String username ,String password){
		String sql = "select * from robot_user where email=? and login_pwd=?";
		return Db.executeQuery(sql, new Object[]{username,password}) ;
	}
	public Map<String,Object> getRobotUserById(long userid) {
		String sql = "select * from robot_user where user_id = ?";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{userid});
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}
	public long updateRobotUserLoginCookies(long userid,String cookies){
		String sql = "update robot_user set login_cookies = ?,modify_date=now() where user_id = ?";
		return Db.executeUpdate(sql, new Object[]{cookies,userid}) ;
	}

	public long insertRegRobotUser(String email,String password,String cookies,String nickName){
		String sql = "insert into robot_user (email,nick_name,login_pwd,login_cookies,created_date,modify_date) VALUES (?,?,?,?,now(),now())";
		return Db.executeUpdate(sql, new Object[]{email,nickName,password,cookies}) ;
	}
	
	public long insertRobotResource(RobotResource res){
		String sql = "insert into robot_resources (user_id,name,type,real_path,net_path,mini_path) VALUES (?,?,?,?,?,?)";
		return  Db.executeUpdate(sql, new Object[]{res.getUserId(),res.getName(),res.getType(),res.getRealPath(),res.getNetPath(),res.getMiniPath()});
	}
	
	public List<Map<String,Object>> getRobotResourceByType(long userid,int type,int page,int pageSize) {
		String sql = "select * from robot_resources where user_id = ? and type = ? order by id desc limit ?,?";
		return Db.executeQuery(sql, new Object[]{userid,type,page,pageSize});
	}
	public Map<String,Object> getRobotResourceById(long id) {
		String sql = "select * from robot_resources where id = ?";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{id});
		if (CollectionUtils.isEmpty(list))
			return null ;
		return list.get(0);
	}
	public List<Map<String,Object>> getRobotResources(long userid,int page,int pageSize){
		String sql = "select * from robot_resources where user_id = ? order by id desc limit ?,?";
		return Db.executeQuery(sql, new Object[]{userid,page,pageSize});
	}
	
	public long deleteRobotResourceById(long id) {
		String sql = "delete from robot_resources where id = ?";
		return Db.executeUpdate(sql, new Object[]{id});
	}
	
	public long insertRobotPosition(RobotPosition pos) {
		String sql = "insert into robot_position (user_id,type,years,position_x,position_y,img_path,content,tags) VALUES (?,?,?,?,?,?,?,?);";
		return Db.executeUpdate(sql, new Object[]{pos.getUserId(),pos.getType(),pos.getYears(),pos.getPositionX(),pos.getPositionY(),
												pos.getImgPath(),pos.getContent(),pos.getTags()}) ;
	}
	
	public List<Map<String,Object>> getRobotPostionByYears(long userid,int type,String years){
		String sql = "select * from robot_position where user_id=? and type = ? and years = ? order by id desc";
		return Db.executeQuery(sql, new Object[]{userid,type,years}) ;
	}
	public List<Map<String,Object>> getRobotPostionAll(long userid){
		String sql = "select * from robot_position where user_id = ? order by id desc";
		return Db.executeQuery(sql, new Object[]{userid}) ;
	}
	
	public Map<String,Object> getRobotPositionById(long id){
		String sql = "select * from robot_position where id = ?";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{id});
		if (CollectionUtils.isEmpty(list))
			return null ;
		return list.get(0);
	}
	
	public long updateRobotPositionImgPath(long id,String path){
		String sql = "update robot_position set img_path =? where id = ?";
		return Db.executeUpdate(sql, new Object[]{path,id});
	}
	
	public long deleteRobotPositionById(long id){
		String sql = "delete from robot_position where id = ?";
		return Db.executeUpdate(sql, new Object[]{id});
	}
	
	
	public long insertRobotPhotos(RobotPhotos pho){
		String sql= "insert into robot_photos (user_id,position_id,net_path,mini_path) values (?,?,?,?)";
		return Db.executeUpdate(sql, new Object[]{pho.getUserId(),pho.getPositionId(),pho.getNetPath(),pho.getMiniPath()}) ;
	}
	
	public Map<String,Object> getRobotPhotosById(long id) {
		String sql = "select * from robot_photos where id = ?";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{id});
		if (CollectionUtils.isEmpty(list))
			return null ;
		return list.get(0);
	}
	
	public List<Map<String,Object>> getRobotPhotosByPosition(long userid,long positionId) {
		String sql = "select * from robot_photos where user_id=? and position_id = ? order by id desc";
		return Db.executeQuery(sql, new Object[]{userid,positionId});
	}
	
	public long deleteRobotPhotosById(long id){
		String sql = "delete from robot_photos where id = ?";
		return Db.executeUpdate(sql, new Object[]{id});
	}
	
	
	public long insertStatisticsFood(String phone ,String content ,String ip){
		String sql = "insert into statistics_food (ip_address,phone,content) VALUES (?,?,?)";
		return Db.executeUpdate(sql, new Object[]{ip,phone,content});
	}
	
	public List<Map<String,Object>> getStatisticsFoodByPhone(String phone){
		String sql = "select * from statistics_food where phone = ?";
		return Db.executeQuery(sql, new Object[]{phone});
	}
	
}
