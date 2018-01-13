package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.TrainMapper;
import com.zy.employee.entity.Train;
import com.zy.employee.service.TrainService;

@Service("trainService")
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainMapper trainMapper;
	
	@Override
	public int insertTrain(Train train) {
		return trainMapper.insertTrain(train);
	}

	@Override
	public int updateTrain(Train train) {
		return trainMapper.updateTrain(train);
	}

	@Override
	public int deleteTrain(int id) {
		return trainMapper.deleteTrain(id);
	}

	@Override
	public Train getById(int id) {
		return trainMapper.getById(id);
	}

	@Override
	public Train getByUId(int uid) {
		return trainMapper.getByUId(uid);
	}

	@Override
	public List<Train> getAllTrain() {
		return trainMapper.getAllTrain();
	}

	@Override
	public Train getByTitle(String title) {
		return trainMapper.getByTitle(title);
	}
}
