package com.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.demo.bean.RobotShopGood;
import com.demo.bean.RobotShopOrder;
import com.demo.dao.ShopDao;
import com.demo.util.Db;
import com.demo.util.StringUtil;
@Repository
public class ShopDaoImpl implements ShopDao{

	
	public long insertRobotShopGood(RobotShopGood good) {
		String sql = "insert into robot_shop_good (owner_id,good_price,good_dis_price,"
				+ "good_name,good_tag,good_pic_url,good_content,good_type,good_status,"
				+ "is_discount,is_out) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		return Db.executeUpdate(sql, new Object[]{good.getOwnerId(),good.getGoodPrice(),good.getGoodDisPrice()
							,good.getGoodName(),good.getGoodTag(),good.getGoodPicUrl(),good.getGoodContent()
							,good.getGoodType(),good.getGoodStatus(),good.getIsDiscount(),good.getIsOut()});
	}
	
	public long updateRobotShopGoodSimple(RobotShopGood good) {
		
		String sql = "update robot_shop_good set good_price = ? ,good_dis_price = ? ,good_name = ?, "
				 + "good_tag = ?, good_pic_url = ?, good_content = ?,good_type = ? ,is_discount = ? where id = ?";
		return Db.executeUpdate(sql, new Object[]{good.getGoodPrice(),good.getGoodDisPrice(),good.getGoodName()
							,good.getGoodTag(),good.getGoodPicUrl(),good.getGoodContent(),good.getGoodType(),good.getIsDiscount(),good.getId()});
	}
	
	public long updateRobotShopGoodStatus(long id,int status){
		String sql = "update robot_shop_good set good_status = ? where id = ?";
		return Db.executeUpdate(sql, new Object[]{status,id});
	}
	
	public Map<String,Object> getRobotShopGoodById(long id) {
		String sql = "select * from robot_shop_good where id= ?";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{id});
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}
	
	public List<Map<String,Object>> getRobotShopGoods(String key,int status,int type ,int page,int pageSize){
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select * from robot_shop_good where 1=1");
		
		if (!StringUtil.isEmpty(key)){
			sql.append(" and (good_tag like '%"+key+"%' or good_name like '%"+key+"%')");
		}
		
		if (status != -999) {
			sql.append(" and good_status = ? ");
			param.add(status);
		}
		
		if (type != -1) {
			sql.append(" and good_type = ? ");
			param.add(type);
		}
		
		sql.append(" and good_status !=-2 order by id desc limit ?,?");
		param.add(page);
		param.add(pageSize);
		return Db.executeQuery(sql.toString(), param.toArray());
	}
	
	public List<Map<String,Object>> getRobotShopGoodsCount(String key,int status){
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select count(*) num from robot_shop_good where 1=1");
		
		if (!StringUtil.isEmpty(key)){
			sql.append(" and good_tag like %"+key+"%");
		}
		
		if (status != 0) {
			sql.append(" and good_status = ? ");
			param.add(status);
		}
		
		return Db.executeQuery(sql.toString(), param.toArray());
	}
	
	public List<Map<String,Object>> getRobotShopGoodsByIds(String ids){
		String sql = "select * from robot_shop_good where 1=? and id in ("+ids+")";
		return Db.executeQuery(sql, new Object[]{1});
	}
	
	public long updateRobotShopGoodToCount(long id ,int type){
		String field = "count_view";
		if (type == 1) {
			field = "count_buy";
		}
		String sql = "update robot_shop_good set "+field+" = "+field+"+ 1 where id = ?";
		return Db.executeUpdate(sql, new Object[]{id}) ;
	}
	
	public long insertRobotShopOrder(RobotShopOrder order){
		String sql = "insert into robot_shop_order (pay_order_id,user_id,type,price,content,"
				+"received_name,received_phone,received_address,received_remarks,status,modify_date,created_date)"
				+"VALUES (?,?,?,?,?,?,?,?,?,?,now(),now());";
		
		return Db.executeUpdate(sql, new Object[]{order.getPayOrderid(),order.getUserId(),order.getType(),order.getPrice(),order.getContent(),
						order.getReceivedName(),order.getReceivedPhone(),order.getReceivedAddress(),order.getReceivedRemarks(),order.getStatus()}) ;
	}
	
	public Map<String,Object> getRobotShopOrderById(long id){
		String sql = "select * from robot_shop_order where id = ?";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{id});
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}
	
	public long updateRobotShopOrderStatus(long id ,int type){
		StringBuffer sql = new StringBuffer("update robot_shop_order set status = ? ");
		
		if (type == 1) {
			sql.append(" , pay_date = now() ");
		} else if (type == 2) {
			sql.append(" , out_date = now() ");
		} else if (type == 3) {
			sql.append(" , finsh_date = now()");
		}
		
		sql.append(" ,modify_date = now()  where id = ?");
		return Db.executeUpdate(sql.toString(), new Object[]{type,id}) ;
	}
	
	public long updateRobotShopOrderStatusOrderid(long id,int status,String order_id){
		StringBuffer sql = new StringBuffer("update robot_shop_order set status = ? ,pay_order_id = ?");
		if (status == 1) {
			sql.append(" , pay_date = now() ");
		} else if (status == 2) {
			sql.append(" , out_date = now() ");
		} else if (status == 3) {
			sql.append(" , finsh_date = now()");
		}
		sql.append(" ,modify_date = now()  where id = ?");
		return Db.executeUpdate(sql.toString(), new Object[]{status,order_id,id}) ;
	}
	
	public long updateRobotShopOrderToAutosend(long id){
		String sql = "update robot_shop_order set auto_send = 1 where id = ?";
		return Db.executeUpdate(sql, new Object[]{id});
	}
	
	public List<Map<String,Object>> getRobotShopOrder(long userid ,int status,int page ,int pageLength){
		StringBuffer sql = new StringBuffer(" select * from robot_shop_order where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (userid != 0) {
			sql.append(" and user_id = ? ");
			params.add(userid);
		}
		
		if (status != -999) {
			sql.append(" and status = ? ");
			params.add(status);
		}
		sql.append(" order by id desc limit ?,?");
		params.add(page);
		params.add(pageLength);
		return Db.executeQuery(sql.toString(), params.toArray());
	}
	
}
