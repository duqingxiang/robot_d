package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotShopOrder {

	private long id;
	private String payOrderid;
	private long userId;
	private int type;
	private double price;
	private String content ;
	private String receivedName;
	private String receivedPhone;
	private String receivedAddress;
	private String receivedRemarks;
	private int status;
	private int autoSend;
	private Date payDate;
	private Date outDate;
	private Date finishDate;
	private Date modifyDate;
	private Date createdDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPayOrderid() {
		return payOrderid;
	}
	public void setPayOrderid(String payOrderid) {
		this.payOrderid = payOrderid;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceivedName() {
		return receivedName;
	}
	public void setReceivedName(String receivedName) {
		this.receivedName = receivedName;
	}
	public String getReceivedPhone() {
		return receivedPhone;
	}
	public void setReceivedPhone(String receivedPhone) {
		this.receivedPhone = receivedPhone;
	}
	public String getReceivedAddress() {
		return receivedAddress;
	}
	public void setReceivedAddress(String receivedAddress) {
		this.receivedAddress = receivedAddress;
	}
	public String getReceivedRemarks() {
		return receivedRemarks;
	}
	public void setReceivedRemarks(String receivedRemarks) {
		this.receivedRemarks = receivedRemarks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAutoSend() {
		return autoSend;
	}
	public void setAutoSend(int autoSend) {
		this.autoSend = autoSend;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
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
	
	public static RobotShopOrder convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotShopOrder order = new RobotShopOrder();
		order.setId(id);
		if (map.get("pay_order_id") != null) order.setPayOrderid(map.get("pay_order_id").toString());
		if (map.get("user_id") != null) order.setUserId(StringUtil.toLong(map.get("user_id").toString(), 0L));
		if(map.get("type") != null) order.setType(StringUtil.toInteger(map.get("type").toString(),0));
		if (map.get("price") != null) order.setPrice(StringUtil.toDouble(map.get("price").toString(), 0D));
		if(map.get("content") != null) order.setContent(map.get("content").toString());
		if(map.get("received_name") != null) order.setReceivedName(map.get("received_name").toString());
		if(map.get("received_phone") != null) order.setReceivedPhone(map.get("received_phone").toString());
		if(map.get("received_address") != null) order.setReceivedAddress(map.get("received_address").toString());
		if(map.get("received_remarks") != null) order.setReceivedRemarks(map.get("received_remarks").toString());
		if(map.get("status") != null) order.setStatus(StringUtil.toInteger(map.get("status").toString(),0));
		if(map.get("auto_send") != null) order.setAutoSend(StringUtil.toInteger(map.get("auto_send").toString(),0));
		if(map.get("pay_date") != null) order.setPayDate((Date)map.get("pay_date"));
		if(map.get("out_date") != null) order.setOutDate((Date)map.get("out_date"));
		if(map.get("finsh_date") != null) order.setFinishDate((Date)map.get("finsh_date"));
		if(map.get("modify_date") != null) order.setModifyDate((Date)map.get("modify_date"));
		if(map.get("created_date") != null) order.setCreatedDate((Date)map.get("created_date"));
		
		return order ;
	}
	public static List<RobotShopOrder> convertToList(List<Map<String,Object>> list){
		if (CollectionUtils.isEmpty(list))
			return null ;
		List<RobotShopOrder> resultList = new ArrayList<RobotShopOrder>();
		for (Map<String,Object> map : list) {
			RobotShopOrder order = convert(map);
			resultList.add(order);
		}
		return resultList ;
	}	
	
}
