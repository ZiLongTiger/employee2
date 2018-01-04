package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.CurriculumvitaeMapper;
import com.zy.employee.entity.Curriculumvitae;
import com.zy.employee.service.CurriculumvitaeService;

@Service("curriculumvitaeService")
public class CurriculumvitaeServiceImpl implements CurriculumvitaeService {

	@Autowired
	private CurriculumvitaeMapper curriculumvitaeMapper;
	
	@Override
	public int saveCurriculumvitae(Curriculumvitae curriculumvitae) {
		return curriculumvitaeMapper.saveCurriculumvitae(curriculumvitae);
	}

	@Override
	public int updateCurriculumvitae(Curriculumvitae curriculumvitae) {
		return curriculumvitaeMapper.updateCurriculumvitae(curriculumvitae);
	}

	@Override
	public List<Curriculumvitae> getByUid(int uid) {
		return curriculumvitaeMapper.getByUid(uid);
	}

	@Override
	public int deleteCurriculumvitaeById(int id) {
		return curriculumvitaeMapper.deleteCurriculumvitaeById(id);
	}

}
