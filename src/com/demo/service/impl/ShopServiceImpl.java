package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.bean.RobotShopGood;
import com.demo.bean.RobotShopOrder;
import com.demo.bean.RobotUser;
import com.demo.dao.ShopDao;
import com.demo.service.DemoService;
import com.demo.service.ShopService;
import com.demo.util.DoubleUtil;
import com.demo.util.Logs;
import com.demo.util.StringUtil;
@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private DemoService demoService;
	
	public long insertRobotShopGood(RobotShopGood good) {
		return shopDao.insertRobotShopGood(good);
	}
	
	public long updateRobotShopGoodSimple(RobotShopGood good) {
		return shopDao.updateRobotShopGoodSimple(good);
	}
	
	public long updateRobotShopGoodStatus(long id,int status) {
		return shopDao.updateRobotShopGoodStatus(id, status);
	}

	public RobotShopGood getRobotShopGoodById(long id) {
		return RobotShopGood.convert(shopDao.getRobotShopGoodById(id));
	}

	public List<RobotShopGood> getRobotShopGoods(String key, int status,int type,
			int page, int pageSize) {
		return RobotShopGood.convertToList(shopDao.getRobotShopGoods(key, status,type, page, pageSize));
	}

	public int getRobotShopGoodsCount(String key, int status) {
		List<Map<String,Object>> list = shopDao.getRobotShopGoodsCount(key, status);
		if (CollectionUtils.isEmpty(list))
			return 0 ;
		int count = 0 ;
		if (list.get(0) != null && list.get(0).get("num") != null) {
			count = StringUtil.toInteger(list.get(0).get("num").toString(), 0);
		}
		return count;
	}
	
	public List<RobotShopGood> getRobotShopGoodsByIds(String ids) {
		return RobotShopGood.convertToList(shopDao.getRobotShopGoodsByIds(ids));
	}
	
	public long updateRobotShopGoodToCount(long id ,int type) {
		return shopDao.updateRobotShopGoodToCount(id, type) ;
	}
	
	public long updateRobotShopGoodToCountBuy(RobotShopOrder order){
		StringBuffer log = new StringBuffer(" ShopServiceImpl  updateRobotShopGoodToCountBuy");
		if (order == null) {
			Logs.geterrorLogger().error(log.append(" order is null   update faild").toString());
			return -1;
		}
		
		if (StringUtil.isEmpty(order.getContent()) || order.getContent().split(";").length <= 0) {
			Logs.geterrorLogger().error(log.append("  content is null   update faild").toString());
			return -1;
		}
		
		//获取下单的商品
		String [] contentArray = order.getContent().split(";");
		for (String goodContent : contentArray) {
			if (goodContent.indexOf("=") == -1)
				continue;
			String []goods = goodContent.split("=");
			long good_id = StringUtil.toLong(goods[0], 0L);
			log.append("\n good_id:"+ good_id);
			
			RobotShopGood good = this.getRobotShopGoodById(good_id);
			if (good == null || good.getGoodStatus() == -2) {
				log.append(" good is null or good is off ");
				continue;
			}
			long res = this.updateRobotShopGoodToCount(good_id, 1);
			if (res > 0)
				log.append(" update success !");
		}
		
		return 0 ;
	}
	
	
	public RobotShopOrder createRobotShopOrder(RobotShopOrder order){
		StringBuffer log = new StringBuffer(" ShopServiceImpl  createRobotShopOrder");
		if (order == null) {
			Logs.geterrorLogger().error(log.append(" order is null   created faild").toString());
			return null;
		}
		
		//判断用户是否存在，用户是否为正常用户
		RobotUser user = demoService.getRobotUserById(order.getUserId());
		log.append(" userid:"+order.getUserId());
		if (user == null || user.getIsActive() == -1) {
			Logs.geterrorLogger().error(log.append(" user is null or user is delete    created faild").toString());
			return null;
		}
		
		log.append(" content :"+order.getContent());
		if (StringUtil.isEmpty(order.getContent()) || order.getContent().split(";").length <= 0) {
			Logs.geterrorLogger().error(log.append("  content is null   created faild").toString());
			return null;
		}
		
		
		double price = 0 ;
		//获取下单的商品
		String [] contentArray = order.getContent().split(";");
		for (String goodContent : contentArray) {
			if (goodContent.indexOf("=") == -1)
				continue;
			String []goods = goodContent.split("=");
			long good_id = StringUtil.toLong(goods[0], 0L);
			int good_amount = StringUtil.toInteger(goods[1], 1);
			
			log.append("\n good_id:"+ good_id +" good_amount:"+good_amount);
			
			RobotShopGood good = this.getRobotShopGoodById(good_id);
			if (good == null || good.getGoodStatus() == -2) {
				log.append(" good is null or good is off ");
				continue;
			}
			double good_price = good.getIsDiscount() == 1 ? good.getGoodDisPrice() : good.getGoodPrice();
			price = DoubleUtil.add(price, DoubleUtil.multiply(good_price, good_amount));
			
		}
		
		log.append(" /n price:"+price);
		if (price == 0) {
			Logs.geterrorLogger().error(log.append(" price is 0  created faild"));
			return null;
		}
		order.setPrice(price);
		
		long id = shopDao.insertRobotShopOrder(order);
		if (id <= 0) {
			Logs.geterrorLogger().error(log.append(" system error  created faild"));
			return null;
		}
		order.setId(id);
		return order;
	}

	public RobotShopOrder getRobotShopOrderById(long id) {
		return RobotShopOrder.convert(shopDao.getRobotShopOrderById(id));
	}

	public long updateRobotShopOrderStatus(long id, int type) {
		return shopDao.updateRobotShopOrderStatus(id, type);
	}
	public long updateRobotShopOrderStatusOrderid(long id,int status,String order_id){
		return shopDao.updateRobotShopOrderStatusOrderid(id, status, order_id);
	}
	public List<RobotShopOrder> getRobotShopOrder(long userid ,int status,int page ,int pageLength){
		return RobotShopOrder.convertToList(shopDao.getRobotShopOrder(userid, status, page, pageLength));
	}
	public long updateRobotShopOrderToAutosend(long id){
		return shopDao.updateRobotShopOrderToAutosend(id);
	}
	public JSONArray getRobotShopOrderDetail(String content){
		if (StringUtil.isEmpty(content) || content.split(";").length <= 0) {
			return null;
		}
		
		JSONArray array = new JSONArray();
		//获取下单的商品
		String [] contentArray = content.split(";");
		for (String goodContent : contentArray) {
			if (goodContent.indexOf("=") == -1)
				continue;
			String []goods = goodContent.split("=");
			long good_id = StringUtil.toLong(goods[0], 0L);
			int good_amount = StringUtil.toInteger(goods[1], 1);
			
			RobotShopGood good = this.getRobotShopGoodById(good_id);
			if (good == null || good.getGoodStatus() == -2) {
				continue;
			}
			
			JSONObject json = new JSONObject();
			double good_price = good.getIsDiscount() == 1 ? good.getGoodDisPrice() : good.getGoodPrice();
			
			json.put("id", good.getId());
			json.put("name", good.getGoodName());
			json.put("price", good_price);
			json.put("amount", good_amount);
			array.add(json);
		}
		
		return array;
	}
}
