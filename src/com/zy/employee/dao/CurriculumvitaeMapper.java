package com.zy.employee.dao;

import java.util.List;

import com.zy.employee.entity.Curriculumvitae;

public interface CurriculumvitaeMapper {

	public int saveCurriculumvitae(Curriculumvitae curriculumvitae);//添加简历
	
	public int updateCurriculumvitae(Curriculumvitae curriculumvitae);//修改简历
	
	public List<Curriculumvitae> getByUid(int uid);//根据登录的用户导出简历
	
	public int deleteCurriculumvitaeById(int id);
}
