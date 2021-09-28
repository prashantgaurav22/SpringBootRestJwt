package com.dxc.glic.service;

import com.dxc.glic.entity.Transaction;

public interface TransactionService {

	Transaction getTransId(int transId);// search
	
	void addTrans(Transaction obj);// add
	
	Transaction getByDateTime(int empId);
	
	

}
