package com.zy.employee.dao;

import java.util.List;

import com.zy.employee.entity.Offer;

public interface OfferMapper {
	
	public List<Offer>getAllOffer();

	public Offer getByuId(int uid);//根据用户id查询投递信息
	
	public Offer getBycurId(int curId);//根据简历id查询投递信息
	
	public Offer getById(int id);//根据id查询投递信息
	
	public int insertOffer(Offer offer);//添加id查询投递信息
	
	public int updateOffer(Offer offer);//判断是否面试通过 告诉用户面试时间 简历是否查看过
	
	public int deleteOffer(int id);//删除
}
