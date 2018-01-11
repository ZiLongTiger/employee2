package com.zy.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Salary;

public interface SalaryMapper {

	public List<Salary>getAllSalary();//��ѯ���з����ʼ�¼
	
	public Salary getSalaryByMonth(String month);//���·ݲ�ѯ����
	
	public Salary getSalaryByMonthAndUid(@Param(value="uid")int uid,@Param(value="month")String month);

	public int insertSalary(Salary salary);
	
	public int updateSalary(Salary salary);
}
