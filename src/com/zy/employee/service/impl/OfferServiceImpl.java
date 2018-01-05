package com.zy.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.OfferMapper;
import com.zy.employee.entity.Offer;
import com.zy.employee.service.OfferService;

@Service("offerService")
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferMapper offerMapper;
	
	@Override
	public Offer getBycurId(int curId) {
		return offerMapper.getBycurId(curId);
	}

	@Override
	public Offer getById(int id) {
		return offerMapper.getById(id);
	}

	@Override
	public int insertOffer(Offer offer) {
		return offerMapper.insertOffer(offer);
	}

	@Override
	public int updateOffer(Offer offer) {
		return offerMapper.updateOffer(offer);
	}

	@Override
	public Offer getByuId(int uid) {
		return offerMapper.getByuId(uid);
	}
}
