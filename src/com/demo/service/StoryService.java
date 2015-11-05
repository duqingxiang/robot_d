package com.demo.service;

import java.util.List;

import com.demo.bean.Memorys;

public interface StoryService {

	public List<Memorys> getMemorysList(int type ,int startIndex ,int pageLength);
}
