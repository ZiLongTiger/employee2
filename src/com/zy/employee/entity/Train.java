package com.zy.employee.entity;

public class Train{

	private int id;
	
	private String title;
	
	private String createTime;
	
	private String context;

	public Train() {
		super();
	}

	public Train(int id, String title, String createTime, String context) {
		super();
		this.id = id;
		this.title = title;
		this.createTime = createTime;
		this.context = context;
	}

	public Train(String title, String createTime, String context) {
		super();
		this.title = title;
		this.createTime = createTime;
		this.context = context;
	}

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

	@Override
	public String toString() {
		return "Train [id=" + id + ", title=" + title + ", createTime=" + createTime + ", context=" + context + "]";
	}
}