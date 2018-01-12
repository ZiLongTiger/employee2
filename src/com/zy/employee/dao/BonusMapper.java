package com.zy.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Bonus;

public interface BonusMapper {

	public List<Bonus> getAllBonus();
	
	public Bonus getByBonusId(int id);
	
	public List<Bonus> getByBonusUidAndReward(@Param(value="uid")int uid,@Param(value="reward")String reward);
	
	public int updateBonus(Bonus bonus);
	
	public int saveBonus(Bonus bonus);
}
