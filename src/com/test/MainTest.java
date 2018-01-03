package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zy.employee.dao.DepartmentMapper;
import com.zy.employee.dao.PostionMapper;
import com.zy.employee.dao.UserMapper;
import com.zy.employee.entity.User;

//����ʱ����SpringIOC����
@RunWith(SpringJUnit4ClassRunner.class) 
//����Spring�������ļ�
@ContextConfiguration({"classpath:Spring*.xml"})
public class MainTest {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private PostionMapper postionMapper;
	
	@Test
	public void getPostByDepId() {
		System.out.println(postionMapper.getByDepId(1));
	}
	
	@Test
	public void getAllDep() {
		System.out.println(departmentMapper.getAllDepartment());
	}
	@Test
	public void queryDepById() {
		System.out.println(departmentMapper.getByDepId(1));
	}
	
	@Test
	public void getUser() {
		System.out.println(userMapper.getUser("Tom", "123123"));
	}
	
	@Test
	public void saveUser() {
		System.out.println(userMapper.insertUser(new User("����", "21312312", 0, 0)));
	}
}