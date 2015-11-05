package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import com.demo.bean.WeixinUser;
import com.demo.dao.WeixinDao;
import com.demo.service.WeixinService;
import com.demo.util.Logs;
import com.demo.util.StringUtil;
import com.demo.util.WeixinConstants;

public class WeixinServiceImpl implements WeixinService {

	private WeixinDao weixinDao;
	public void setWeixinDao(WeixinDao weixinDao){
		this.weixinDao = weixinDao;
	}
	public WeixinUser getWeixinUserByFromid(String fromid){
		WeixinUser user = null;
		List<Map<String,Object>> list = weixinDao.getWeixinUserByFromid(fromid, 0);
		if(list!=null&&list.size()>0){
			user = WeixinUser.convert(list.get(0));
		}
		return user;
	}
	
	public long insertWeixinUser(String fromid){
		return weixinDao.insertWeixinUser(fromid);
	}
	
	public String updateWeixinUserInfo(String content,long userid){
		String result = "";
		String name = "";
		long phone =0;
		if((content.indexOf(";")!=-1||content.indexOf("；")!=-1)&&content.indexOf(":")!=-1){
			String [] array = content.split(";");
			if(array.length==1){
				array = content.split("；");
			}
			if(array.length==1){
				result="更新失败！";
				return result;
			}
			for(int i = 0 ; i<array.length ; i++){
				String[] attrs = array[i].split(":");
				if("name".equals(attrs[0])){
					name = attrs[1];
				}else if("phone".equals(attrs[0])){
					phone = StringUtil.toLong(attrs[1], 0l);
				}
			}
			Logs.geterrorLogger().error("---用户更新--->name"+name+"--phone:"+phone+"--id"+userid);
			long res = weixinDao.updateWeixinUserInfo(name, phone, userid);
			if(res>0){
				result="更新成功！";
			}else{
				result="更新失败！";
			}
		}else{
			result =WeixinConstants.WEIXIN_USERINFO_ERROR;
		}
		
		return result;
	}
	
	public String joinGroup(String content, String fromUserId) {
		// TODO Auto-generated method stub
		return null;
	}

}
