package com.dxc.glic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.glic.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	@Query("SELECT t FROM Transaction t where t.employeeId=:empId and t.transactionType='Registration'")
	Transaction getByDateTime(int empId);

}