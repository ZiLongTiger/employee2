package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.RecruitMapper;
import com.zy.employee.entity.Recruit;
import com.zy.employee.service.RecruitService;

@Service("recruitService")
public class RecruitServiceImpl implements RecruitService {

	@Autowired
	private RecruitMapper recruitMapper;
	
	@Override
	public List<Recruit> getAllRecruit() {
		return recruitMapper.getAllRecruit();
	}

	@Override
	public int insertRecruit(Recruit recruit) {
		return recruitMapper.insertRecruit(recruit);
	}

	@Override
	public int updateRecruit(Recruit recruit) {
		return recruitMapper.updateRecruit(recruit);
	}

	@Override
	public int deleteRecruit(int id) {
		return recruitMapper.deleteRecruit(id);
	}

	@Override
	public Recruit getById(int id) {
		return recruitMapper.getById(id);
	}

	@Override
	public Recruit getByTitle(String title) {
		return recruitMapper.getByTitle(title);
	}
}
