package com.zy.employee.entity;
public class Train{

	private int id;
	
	private String title;//��ѵ����
	
	private String createTime;//����ѵ�Ĵ���ʱ��
	
	private String context;//��ѵ����
	
	private int uid;
	
	private int status;//0 δ�鿴  1�Ѳ鿴 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Train(int id, String title, String createTime, String context, int uid, int status) {
		super();
		this.id = id;
		this.title = title;
		this.createTime = createTime;
		this.context = context;
		this.uid = uid;
		this.status = status;
	}

	public Train(String title, String createTime, String context, int uid, int status) {
		super();
		this.title = title;
		this.createTime = createTime;
		this.context = context;
		this.uid = uid;
		this.status = status;
	}

	public Train() {
		super();
	}
}