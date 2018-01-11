package com.zy.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Salary;

public interface SalaryMapper {

	public List<Salary>getAllSalary();//查询所有发工资记录
	
	public Salary getSalaryByMonth(String month);//按月份查询工资
	
	public Salary getSalaryByMonthAndUid(@Param(value="uid")int uid,@Param(value="month")String month);

	public int insertSalary(Salary salary);
	
	public int updateSalary(Salary salary);
}
