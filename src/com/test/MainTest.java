package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zy.employee.dao.DepartmentMapper;
import com.zy.employee.dao.PostionMapper;
import com.zy.employee.dao.UserMapper;
import com.zy.employee.entity.Department;
import com.zy.employee.entity.User;

//启动时加载SpringIOC容器
@RunWith(SpringJUnit4ClassRunner.class) 
//引入Spring的配置文件
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
	public void getPostionById() {
		System.out.println(postionMapper.getById(1));
	}
	
	@Test
	public void getAllDep() {
		System.out.println(departmentMapper.getAllDepartment());
	}
	@Test
	public void queryDepById() {
		Department dep = departmentMapper.getByDepId(7);
		dep.setDepLock(0);
		dep.setDepName("赖皮部");
		System.out.println(dep);
		int update = departmentMapper.updateDepartment(dep);
		System.out.println(departmentMapper.getByDepId(7));
		
	}
	
	@Test
	public void getUser() {
		System.out.println(userMapper.getUser("Tom", "123123"));
	}
	
	@Test
	public void saveUser() {
		System.out.println(userMapper.insertUser(new User("阿媚", "21312312", 0, 0)));
	}
}
