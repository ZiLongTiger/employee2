package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.BonusMapper;
import com.zy.employee.entity.Bonus;
import com.zy.employee.service.BonusService;

@Service("bonusService")
public class BonusServiceImpl implements BonusService {

	@Autowired
	private BonusMapper bonusMapper;
	
	@Override
	public List<Bonus> getAllBonus() {
		return bonusMapper.getAllBonus();
	}

	@Override
	public Bonus getByBonusId(int id) {
		return bonusMapper.getByBonusId(id);
	}

	@Override
	public int updateBonus(Bonus bonus) {
		return bonusMapper.updateBonus(bonus);
	}

	@Override
	public int saveBonus(Bonus bonus) {
		return bonusMapper.saveBonus(bonus);
	}

	@Override
	public List<Bonus> getByBonusUidAndReward(int uid, String reward) {
		return bonusMapper.getByBonusUidAndReward(uid, reward);
	}

	@Override
	public List<Bonus> getBonusByUid(int uid) {
		return bonusMapper.getBonusByUid(uid);
	}

}
