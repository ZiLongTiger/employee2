package com.zy.employee.service;

import com.zy.employee.entity.Train;

public interface TrainService {

	public int insertTrain(Train train);
	
	public int updateTrain(Train train);
	
	public int deleteTrain(Train train);
	
	public Train getById(int id);
	
	public Train getByTitle(String title);
}
