package com.dxc.glic.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable {

	@Id
	@Column(name = "Transaction_Timestamp")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime timeStamp;

	@Column(name = "Employee_ID")
	private Integer employeeId;

	@Column(name = "Transaction_Type", nullable = true)
	private String transactionType;

	
	public Transaction() {
		// left unimplemented
	}


	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	

}
