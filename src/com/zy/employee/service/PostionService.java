package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Postion;

public interface PostionService {

	public int insertPostion(Postion postion);
	
	public int updatePostion(Postion postion);
	
	public List<Postion> getByDepId(int depId);
	
	public Postion getById(int id);
}
