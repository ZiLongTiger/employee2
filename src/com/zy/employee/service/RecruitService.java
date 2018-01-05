package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Recruit;

public interface RecruitService {

	public List<Recruit> getAllRecruit();//�鿴������Ƹ��Ϣ
	
	public int insertRecruit(Recruit recruit);//������Ƹ��Ϣ
	
	public int updateRecruit(Recruit recruit);//�޸���Ƹ��Ϣ
	
	public int deleteRecruit(int id);//ɾ����Ƹ��Ϣ
	
	public Recruit getById(int id);//����id�鿴��Ƹ��Ϣ
	
	public Recruit getByTitle(String title);//���ݱ���鿴��Ƹ��Ϣ
}
