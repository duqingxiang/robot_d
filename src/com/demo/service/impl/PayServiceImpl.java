package com.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.impl.PayDao;
import com.demo.service.PayService;
@Service
public class PayServiceImpl implements PayService {

	@Autowired
	private PayDao payDao;
	
}
