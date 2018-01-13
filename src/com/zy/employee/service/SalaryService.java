package com.zy.employee.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Salary;

public interface SalaryService {

	public List<Salary>getAllSalary();//��ѯ���з����ʼ�¼
	
	public List<Salary>getEmployeeSalary(int uid);//�����ʼ�¼
	
	public Salary getSalaryByMonth(String month);//���·ݲ�ѯ����
	
	public Salary getSalaryByMonthAndUid(@Param(value="uid")int uid,@Param(value="month")String month);
	
	public Salary getSalaryByMonthAndUidAndStatus(@Param(value="uid")int uid,@Param(value="month")String month,@Param(value="status")int status);

	public int insertSalary(Salary salary);
	
	public int updateSalary(Salary salary);
}
