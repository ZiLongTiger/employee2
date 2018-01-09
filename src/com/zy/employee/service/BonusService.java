package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Bonus;

public interface BonusService {

	public List<Bonus> getAllBonus();
	
	public Bonus getByBonusId(int id);
	
	public Bonus getByBonusUid(int uid);
	
	public int updateBonus(Bonus bonus);
	
	public int saveBonus(Bonus bonus);
}
