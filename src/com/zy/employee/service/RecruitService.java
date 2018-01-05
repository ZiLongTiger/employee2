package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Recruit;

public interface RecruitService {

	public List<Recruit> getAllRecruit();//查看所有招聘信息
	
	public int insertRecruit(Recruit recruit);//新增招聘信息
	
	public int updateRecruit(Recruit recruit);//修改招聘信息
	
	public int deleteRecruit(int id);//删除招聘信息
	
	public Recruit getById(int id);//根据id查看招聘信息
	
	public Recruit getByTitle(String title);//根据标题查看招聘信息
}
