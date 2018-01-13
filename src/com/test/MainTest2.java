package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zy.employee.dao.BonusMapper;
import com.zy.employee.dao.CurriculumvitaeMapper;
import com.zy.employee.dao.OfferMapper;
import com.zy.employee.dao.RecruitMapper;


//����ʱ����SpringIOC����
@RunWith(SpringJUnit4ClassRunner.class) 
//����Spring�������ļ�
@ContextConfiguration({"classpath:Spring*.xml"})
public class MainTest2 {

	@Autowired
	private CurriculumvitaeMapper CurriculumvitaeMapper;
	
	@Autowired
	private RecruitMapper recruitMapper;
	
	@Autowired
	private OfferMapper offerMapper;
	
	@Autowired
	private BonusMapper bm;
	
	@Test
	public void textBm() {
		System.out.println(bm.getAllBonus());
	}
	
	
	@Test
	public void testQueryByUid() {
		System.out.println(CurriculumvitaeMapper.getByUid(1));
	}
	
	@Test
	public void testQueryAll() {
		System.out.println(recruitMapper.getAllRecruit());
	}
	
	@Test
	public void testQueryOffer() {
		System.out.println(offerMapper.getAllOffer());
	}
}
