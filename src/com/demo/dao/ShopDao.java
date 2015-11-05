package com.demo.dao;

import java.util.List;
import java.util.Map;

import com.demo.bean.RobotShopGood;
import com.demo.bean.RobotShopOrder;

public interface ShopDao {

	/**
	 * 插入商品
	 * @param good
	 * @return
	 */
	public long insertRobotShopGood(RobotShopGood good);
	
	/**
	 * 更新产品信息
	 * @param good
	 * @return
	 */
	public long updateRobotShopGoodSimple(RobotShopGood good);
	
	/**
	 * 修改商品状态
	 * @param id
	 * @param status
	 * @return
	 */
	public long updateRobotShopGoodStatus(long id,int status);
	/**
	 * 根据id获取商品
	 * @param id
	 * @return
	 */
	public Map<String,Object> getRobotShopGoodById(long id);
	
	/**
	 * 获取商品列表
	 * @param key
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> getRobotShopGoods(String key,int status ,int type,int page,int pageSize);
	
	/**
	 * 获取商品总数
	 * @param key
	 * @param status
	 * @return
	 */
	public List<Map<String,Object>> getRobotShopGoodsCount(String key,int status);
	
	/**
	 * 通过id获取多个商品
	 * @param ids
	 * @return
	 */
	public List<Map<String,Object>> getRobotShopGoodsByIds(String ids);
	
	/**
	 * 更新商品统计次数，一次一个
	 * @param id
	 * @param type
	 * @return
	 */
	public long updateRobotShopGoodToCount(long id ,int type);
	
	/**
	 * 插入商品订单
	 * @param order
	 * @return
	 */
	public long insertRobotShopOrder(RobotShopOrder order);
	
	/**
	 * 查询订单
	 * @param id
	 * @return
	 */
	public Map<String,Object> getRobotShopOrderById(long id);
	
	/**
	 * 更新订单状态
	 * @param id
	 * @param type
	 * @return
	 */
	public long updateRobotShopOrderStatus(long id ,int type);
	public long updateRobotShopOrderStatusOrderid(long id,int status,String order_id);
	
	/**
	 * 更新自动发货状态
	 * @param id
	 * @return
	 */
	public long updateRobotShopOrderToAutosend(long id);
	/**
	 * 查询订单
	 * @param userid  0：全部
	 * @param status  -999：全部
	 * @param page
	 * @param pageLength
	 * @return
	 */
	public List<Map<String,Object>> getRobotShopOrder(long userid ,int status,int page ,int pageLength);
}
