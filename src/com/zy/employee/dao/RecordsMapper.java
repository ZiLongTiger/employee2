package com.zy.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Records;

public interface RecordsMapper {

	public List<Records> getAllRecords();
	
	public Records getByRecordsId(int id);
	
	public List<Records> getByRecordsUid(int uid);
	
	public Records getByRecordsUidAndDeleStatus(@Param(value="status")int status,@Param(value="uid")int uid);
	
	public int updateRecords(Records records);
	
	public int insertRecords(Records records);
}
