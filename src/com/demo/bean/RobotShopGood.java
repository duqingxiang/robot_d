package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotShopGood {

	private long id;
	private long ownerId;
	private double goodPrice;
	private double goodDisPrice;
	private String goodName;
	private String goodTag;
	private String goodPicUrl;
	private String goodContent;
	private int goodType;
	private int goodStatus;
	private long countView;
	private long countBuy;
	private int isDiscount;
	private int isOut;
	private Date modifyDate;
	private Date createdDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public double getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}
	public double getGoodDisPrice() {
		return goodDisPrice;
	}
	public void setGoodDisPrice(double goodDisPrice) {
		this.goodDisPrice = goodDisPrice;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGoodTag() {
		return goodTag;
	}
	public void setGoodTag(String goodTag) {
		this.goodTag = goodTag;
	}
	
	public String getGoodPicUrl() {
		return goodPicUrl;
	}
	public void setGoodPicUrl(String goodPicUrl) {
		this.goodPicUrl = goodPicUrl;
	}
	public String getGoodContent() {
		return goodContent;
	}
	public void setGoodContent(String goodContent) {
		this.goodContent = goodContent;
	}
	public int getGoodType() {
		return goodType;
	}
	public void setGoodType(int goodType) {
		this.goodType = goodType;
	}
	public int getGoodStatus() {
		return goodStatus;
	}
	public void setGoodStatus(int goodStatus) {
		this.goodStatus = goodStatus;
	}
	public long getCountView() {
		return countView;
	}
	public void setCountView(long countView) {
		this.countView = countView;
	}
	public long getCountBuy() {
		return countBuy;
	}
	public void setCountBuy(long countBuy) {
		this.countBuy = countBuy;
	}
	public int getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}
	public int getIsOut() {
		return isOut;
	}
	public void setIsOut(int isOut) {
		this.isOut = isOut;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public static RobotShopGood convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotShopGood good = new RobotShopGood();
		good.setId(id);
		if (map.get("owner_id") != null) good.setOwnerId(StringUtil.toLong(map.get("owner_id").toString(), 0L));
		if (map.get("good_price") != null) good.setGoodPrice(StringUtil.toDouble(map.get("good_price").toString(), 0D));
		if (map.get("good_dis_price") != null) good.setGoodDisPrice(StringUtil.toDouble(map.get("good_dis_price").toString(), 0D));
		if(map.get("good_name") != null) good.setGoodName(map.get("good_name").toString());
		if(map.get("good_tag") != null) good.setGoodTag(map.get("good_tag").toString());
		if(map.get("good_pic_url") != null) good.setGoodPicUrl(map.get("good_pic_url").toString());
		if(map.get("good_content") != null) good.setGoodContent(map.get("good_content").toString());
		if(map.get("good_type") != null) good.setGoodType(StringUtil.toInteger(map.get("good_type").toString(),0));
		if(map.get("good_status") != null) good.setGoodStatus(StringUtil.toInteger(map.get("good_status").toString(),0));
		if(map.get("count_view") != null) good.setCountView(StringUtil.toLong(map.get("count_view").toString(), 0L));
		if(map.get("count_buy") != null) good.setCountBuy(StringUtil.toLong(map.get("count_buy").toString(), 0L));
		if(map.get("is_discount") != null) good.setIsDiscount(StringUtil.toInteger(map.get("is_discount").toString(),0));
		if(map.get("is_out") != null) good.setIsOut(StringUtil.toInteger(map.get("is_out").toString(),0));
		if(map.get("modify_date") != null) good.setModifyDate((Date)map.get("modify_date"));
		if(map.get("created_date") != null) good.setCreatedDate((Date)map.get("created_date"));
		
		return good ;
	}
	
	public static List<RobotShopGood> convertToList(List<Map<String,Object>> list){
		if (CollectionUtils.isEmpty(list))
			return null ;
		List<RobotShopGood> resultList = new ArrayList<RobotShopGood>();
		for (Map<String,Object> map : list) {
			RobotShopGood good = convert(map);
			resultList.add(good);
		}
		return resultList ;
	}
}
