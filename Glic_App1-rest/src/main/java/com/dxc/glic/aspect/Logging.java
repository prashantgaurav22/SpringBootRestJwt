package com.dxc.glic.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dxc.glic.entity.Transaction;
import com.dxc.glic.service.EmployeeServiceImpl;
import com.dxc.glic.service.TransactionServiceImpl;

@CrossOrigin
@Component
@Aspect
@Configuration
@EnableAspectJAutoProxy
public class Logging {

	@Autowired
	TransactionServiceImpl tranSer;

	@Autowired
	EmployeeServiceImpl empSer;


	@Before("execution(* com.dxc.glic.api.GlicApi.addEmployee(..))")
	public void crossCutFunction1(JoinPoint jp) {
		System.out.println("Add New is Excecute: " + jp.getSignature().getName());

	}

	@AfterReturning("execution(* com.dxc.glic.api.GlicApi.addEmployee(..))")
	public void crossCutFunction2(JoinPoint jp) {
		System.out.println("Add New is Done: " + jp.getSignature().getName());
		Transaction trans = new Transaction();
		if(empSer.ids!=null)
		{
			trans.setEmployeeId(empSer.ids);
			trans.setTransactionType("Registration");
			trans.setTimeStamp(LocalDateTime.now());
			tranSer.addTrans(trans);
		}
		
	}

	@Before("execution(* com.dxc.glic.api.GlicApi.getEmpById(..))")
	public void crossCutFunction3(JoinPoint jp) {
		System.out.println("View is Excecute: " + jp.getSignature().getName());

	}

	@AfterReturning("execution(* com.dxc.glic.api.GlicApi.getEmpById(..))")
	public void crossCutFunction4(JoinPoint jp) {
		System.out.println("View is Done: " + jp.getSignature().getName());

		Transaction trans2 = new Transaction();
		if(empSer.empid!=null)
		{
			trans2.setEmployeeId(empSer.empid);
			trans2.setTransactionType("View");
			trans2.setTimeStamp(LocalDateTime.now());
			tranSer.addTrans(trans2);
		}
		
	}

}
