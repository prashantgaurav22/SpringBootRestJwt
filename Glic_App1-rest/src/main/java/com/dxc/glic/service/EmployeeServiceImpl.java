package com.dxc.glic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.glic.entity.Department;
import com.dxc.glic.entity.Employee;
import com.dxc.glic.exception.EmployeeException;
import com.dxc.glic.repository.DepartmentRepository;
import com.dxc.glic.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	public static Integer ids;

	public static Integer empid;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private DepartmentRepository deptRepo;


	@Transactional
	@Override
	public Employee add(Employee employee) throws EmployeeException {
		
		if (!(employee.getEmployeeId() != null  && employee.getFirstName() != "" 
				&& employee.getLastName() != "" && employee.getEmail() != ""))
		{
			throw new EmployeeException(
					"All Fields are Mandatory!");
		}
		else if(employee.getEmployeeId()<0 || employee.getEmployeeId()==null)
		{
			throw new EmployeeException(
					"Employee Id should Mandatory & should be greater than 0!");
		}
		
	
	Integer abc = employee.getDepartment().getDepartmentId();
	if(!deptRepo.existsById(abc))
		{
			throw new EmployeeException(
					"Department Id is Incorrect!");
		}
	
		

		else if (employee != null) {
			if (employeeRepo.existsById(employee.getEmployeeId())) {
				throw new EmployeeException(
						"An Employee with the Employee Id " + employee.getEmployeeId() + " already exists!");
			}

			employeeRepo.save(employee);
			ids = employee.getEmployeeId();

		}
		return employee;
	}

	@Transactional
	@Override
	public Employee getEmpId(int employeeId)  throws EmployeeException{
		if (!employeeRepo.existsById(employeeId)) {
			throw new EmployeeException(
					"An Employee with the Employee Id " + employeeId+ " doesnot exists!");
		}
		
		
		empid = employeeId;
		return employeeRepo.findById(employeeId).orElse(null);
	}

	@Transactional
	@Override
	public List<Employee> getAllEmps() {
		return employeeRepo.findAll();
	}

	@Override
	public void deleteById(int employeeId) {
		 employeeRepo.deleteById(employeeId);
		
	}

	@Override
	public void validate(Employee emp) throws EmployeeException {
		if (

				 (emp.getEmployeeId() == null || emp.getEmployeeId() == 0)

				 ||

				 (emp.getFirstName() == null || emp.getFirstName() == "")

				 ||

				 (emp.getLastName() == null || emp.getLastName() == "") 
				 ||

				 (emp.getEmail() == null || emp.getEmail() == "")

				 )
		{
			throw new EmployeeException(
					"All Fields are Mandatory!");
					}
	
		else if(emp.getDepartment()==null) {
		throw new EmployeeException("Department is Mandatory");
	}
	
	else if(emp.getDepartment().getDepartmentId()==null) {
		throw new EmployeeException(
				"Department Id is Null!");
	}
		
	}

}
