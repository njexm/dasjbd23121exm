package com.proem.exm.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.order.CustomerDao;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.order.CustomerService;

@Service("customerServiceImpl")
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public void updateObj(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
	
}
