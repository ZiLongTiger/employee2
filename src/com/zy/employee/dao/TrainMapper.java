package com.zy.employee.dao;

import java.util.List;

import com.zy.employee.entity.Train;

public interface TrainMapper {

	public int insertTrain(Train train);
	
	public int updateTrain(Train train);
	
	public int deleteTrain(int id);
	
	public Train getById(int id);
	
	public Train getByUId(int uid);
	
	public List<Train> getAllTrain();
	
	public Train getByTitle(String title);
}
