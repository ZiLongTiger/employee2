package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.PostionMapper;
import com.zy.employee.entity.Postion;
import com.zy.employee.service.PostionService;

@Service("postionService")
public class PostionServiceImpl implements PostionService {

	@Autowired
	private PostionMapper postionMapper;
	
	@Override
	public int insertPostion(Postion postion) {
		return postionMapper.insertPostion(postion);
	}

	@Override
	public int updatePostion(Postion postion) {
		return postionMapper.updatePostion(postion);
	}

	@Override
	public List<Postion> getByDepId(int depId) {
		return postionMapper.getByDepId(depId);
	}

	@Override
	public Postion getById(int id) {
		return postionMapper.getById(id);
	}

	@Override
	public int deleteById(int id) {
		return postionMapper.deleteById(id);
	}
}
