package com.zy.employee.dao;

import com.zy.employee.entity.Train;

public interface TrainMapper {

	public int insertTrain(Train train);
	
	public int updateTrain(Train train);
	
	public int deleteTrain(Train train);
	
	public Train getById(int id);
	
	public Train getByTitle(String title);
}
