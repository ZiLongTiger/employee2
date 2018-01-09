package com.zy.employee.dao;

import java.util.List;

import com.zy.employee.entity.Records;

public interface RecordsMapper {

	public List<Records> getAllRecords();
	
	public Records getByRecordsId(int id);
	
	public Records getByRecordsUid(int uid);
	
	public int updateRecords(Records records);
	
	public int insertRecords(Records records);
}
