package com.zy.employee.service;

import java.util.List;


import com.zy.employee.entity.Records;

public interface RecordsService {

	public List<Records> getAllRecords();
	
	public Records getByRecordsId(int id);
	
	public List<Records> getByRecordsUid(int uid);
	
	public int updateRecords(Records records);
	
	public int insertRecords(Records records);
	
	public Records getByRecordsUidAndDeleStatus(int status,int uid);
}
