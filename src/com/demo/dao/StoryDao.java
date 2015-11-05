package com.demo.dao;

import java.util.List;
import java.util.Map;

public interface StoryDao {
	public List<Map<String,Object>> getMemorysList(int type ,int startIndex ,int pageLength);
}
