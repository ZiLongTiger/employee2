package com.zy.employee.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Salary;

public interface SalaryService {

	public List<Salary>getAllSalary();//��ѯ���з����ʼ�¼
	
	public Salary getSalaryByMonth(String month);//���·ݲ�ѯ����
	
	public List<Salary> getSalaryByMonthAndUid(@Param(value="uid")int uid,@Param(value="month")String month);

	public int insertSalary(Salary salary);
	
	public int updateSalary(Salary salary);
}