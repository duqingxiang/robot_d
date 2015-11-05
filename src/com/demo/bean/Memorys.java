package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class Memorys {

	private long id ;
	private int type ;
	private String tag ;
	private String content ;
	private String photoUrl ;
	private Date createdDate ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public static Memorys convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		Memorys mem = new Memorys();
		mem.setId(id);
		if(map.get("type") != null) mem.setType(StringUtil.toInteger(map.get("type").toString(),0));
		if(map.get("tag") != null) mem.setTag(map.get("tag").toString());
		if(map.get("content") != null) mem.setContent(map.get("content").toString());
		if(map.get("photo_url") != null) mem.setPhotoUrl(map.get("photo_url").toString());
		if(map.get("created_date") != null) mem.setCreatedDate((Date)map.get("created_date"));
		return mem ;
	}
	
	public static List<Memorys> convertList(List<Map<String,Object>> mapList){
		if(CollectionUtils.isEmpty(mapList))
			return null ;
		
		List<Memorys> list = new ArrayList<Memorys>();
		for(Map<String,Object> map : mapList){
			Memorys mem = Memorys.convert(map);
			list.add(mem);
		}
		
		return list;
	}
	
}
