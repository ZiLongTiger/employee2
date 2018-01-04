package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zy.employee.dao.CurriculumvitaeMapper;


//����ʱ����SpringIOC����
@RunWith(SpringJUnit4ClassRunner.class) 
//����Spring�������ļ�
@ContextConfiguration({"classpath:Spring*.xml"})
public class MainTest2 {

	@Autowired
	private CurriculumvitaeMapper CurriculumvitaeMapper;
	
	@Test
	public void testQueryByUid() {
		System.out.println(CurriculumvitaeMapper.getByUid(1));
	}
}
