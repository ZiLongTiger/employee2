package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Curriculumvitae;

public interface CurriculumvitaeService {

	public int saveCurriculumvitae(Curriculumvitae curriculumvitae);//��Ӽ���
	
	public int updateCurriculumvitae(Curriculumvitae curriculumvitae);//�޸ļ���
	
	public List<Curriculumvitae> getByUid(int uid);//���ݵ�¼���û���������
	
	public int deleteCurriculumvitaeById(int id);
	
	public Curriculumvitae getByTitle(String title);//���ݵ�¼���û����������ļ����ı����ѯ
	
	public Curriculumvitae getById(int id);//���ݱ�Ų�ѯ������Ϣ
}
