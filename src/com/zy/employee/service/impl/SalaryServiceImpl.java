package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.SalaryMapper;
import com.zy.employee.entity.Salary;
import com.zy.employee.service.SalaryService;

@Service("salaryService")
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryMapper salaryMapper;
	
	@Override
	public List<Salary> getAllSalary() {
		return salaryMapper.getAllSalary();
	}

	@Override
	public Salary getSalaryByMonth(String month) {
		return salaryMapper.getSalaryByMonth(month);
	}

	@Override
	public Salary getSalaryByMonthAndUid(int uid, String month) {
		return salaryMapper.getSalaryByMonthAndUid(uid, month);
	}

	@Override
	public int insertSalary(Salary salary) {
		return salaryMapper.insertSalary(salary);
	}

	@Override
	public int updateSalary(Salary salary) {
		return salaryMapper.updateSalary(salary);
	}

	@Override
	public Salary getSalaryByMonthAndUidAndStatus(int uid, String month, int status) {
		return salaryMapper.getSalaryByMonthAndUidAndStatus(uid, month, status);
	}
}
