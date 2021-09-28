package com.dxc.glic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.glic.entity.Department;
import com.dxc.glic.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository deptRepo;

	@Override
	public Department getDeptId(int deptId) {
		return deptRepo.findById(deptId).orElse(null);
	}

}
