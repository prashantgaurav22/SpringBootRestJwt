package com.dxc.glic.api;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.dxc.glic.entity.Department;
import com.dxc.glic.entity.Employee;
import com.dxc.glic.entity.Transaction;
import com.dxc.glic.exception.EmployeeException;
import com.dxc.glic.service.DepartmentService;
import com.dxc.glic.service.EmployeeService;
import com.dxc.glic.service.TransactionServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/emps")
public class GlicApi {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private TransactionServiceImpl trs ;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmps() {
	
		return new ResponseEntity<List<Employee>>(empService.getAllEmps(), HttpStatus.OK);
	}

	@GetMapping("/{empId}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("empId") int employeeId) throws EmployeeException {
		ResponseEntity<Employee> response = null;

		
		Employee emp = empService.getEmpId(employeeId);
			
		Transaction td = trs.getByDateTime(employeeId);
	
		
		emp.setTimeStamp(td.getTimeStamp());

		
/*
		TransactionServiceImpl trs = new TransactionServiceImpl();
		dep.setTimeStamp(Timestamp.valueOf(trs.findTime(employeeId)));
	*/	
		
		
		
		
		if (emp == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(emp, HttpStatus.OK);
		}

		return response;
	}

	
	@PostMapping
	public ResponseEntity<String> addEmployee( @RequestBody Employee emp ) throws EmployeeException {
		ResponseEntity<String> response = null;
      
		empService.validate(emp);
		
	//	dep.setTimeStamp(LocalDateTime.now());
	
		//int dpid =emp.getEmployeeId(employeeId);  //Here get the depId of particular EmployeeId
		
			
		
			 
			empService.add(emp);
			response = new ResponseEntity<>("Register Successfull",HttpStatus.OK);
		

		return response;
	}

	

	@DeleteMapping("/{empId}")
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable("empId") int employeeId) throws EmployeeException {
		empService.deleteById(employeeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
