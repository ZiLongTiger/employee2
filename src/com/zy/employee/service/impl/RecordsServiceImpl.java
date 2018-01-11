package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.RecordsMapper;
import com.zy.employee.entity.Records;
import com.zy.employee.service.RecordsService;

@Service("recordsService")
public class RecordsServiceImpl implements RecordsService {

	@Autowired
	private RecordsMapper recordsMapper;
	
	@Override
	public List<Records> getAllRecords() {
		return recordsMapper.getAllRecords();
	}

	@Override
	public Records getByRecordsId(int id) {
		return recordsMapper.getByRecordsId(id);
	}


	@Override
	public int updateRecords(Records records) {
		return recordsMapper.updateRecords(records);
	}

	@Override
	public int insertRecords(Records records) {
		return recordsMapper.insertRecords(records);
	}

	@Override
	public List<Records> getByRecordsUid(int uid) {
		return recordsMapper.getByRecordsUid(uid);
	}

	@Override
	public Records getByRecordsUidAndDeleStatus(int status, int uid) {
		return recordsMapper.getByRecordsUidAndDeleStatus(status, uid);
	}
}
