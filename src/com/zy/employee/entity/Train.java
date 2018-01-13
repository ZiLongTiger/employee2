package com.zy.employee.entity;
public class Train{

	private int id;
	
	private String title;//培训标题
	
	private String createTime;//该培训的创建时间
	
	private String context;//培训内容
	
	private int uid;
	
	private int status;//0 未查看  1已查看 

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