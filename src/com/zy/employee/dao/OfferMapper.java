package com.zy.employee.dao;

import com.zy.employee.entity.Offer;

public interface OfferMapper {

	public Offer getByuId(int uid);//�����û�id��ѯͶ����Ϣ
	
	public Offer getBycurId(int curId);//���ݼ���id��ѯͶ����Ϣ
	
	public Offer getById(int id);//����id��ѯͶ����Ϣ
	
	public int insertOffer(Offer offer);//����id��ѯͶ����Ϣ
	
	public int updateOffer(Offer offer);//�ж��Ƿ�����ͨ�� �����û�����ʱ�� �����Ƿ�鿴��
}