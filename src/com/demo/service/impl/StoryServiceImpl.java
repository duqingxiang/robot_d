package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import com.demo.bean.Memorys;
import com.demo.dao.StoryDao;
import com.demo.service.StoryService;

public class StoryServiceImpl implements StoryService{

	private StoryDao storyDao ;

	public void setStoryDao(StoryDao storyDao) {
		this.storyDao = storyDao;
	}
	public List<Memorys> getMemorysList(int type ,int startIndex ,int pageLength){
		List<Map<String,Object>> list = storyDao.getMemorysList(type,startIndex, pageLength);
		return Memorys.convertList(list) ;
	}
}
