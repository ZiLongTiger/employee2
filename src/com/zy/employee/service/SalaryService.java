package com.zy.employee.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Salary;

public interface SalaryService {

	public List<Salary>getAllSalary();//查询所有发工资记录
	
	public List<Salary>getEmployeeSalary(int uid);//发工资记录
	
	public Salary getSalaryByMonth(String month);//按月份查询工资
	
	public Salary getSalaryByMonthAndUid(@Param(value="uid")int uid,@Param(value="month")String month);
	
	public Salary getSalaryByMonthAndUidAndStatus(@Param(value="uid")int uid,@Param(value="month")String month,@Param(value="status")int status);

	public int insertSalary(Salary salary);
	
	public int updateSalary(Salary salary);
}
