package com.zy.employee.entity;

public class Employee{

	private int id;
	
	private String realName;
	
	private String phone;
	
	private String email;
	
	private int depId;
	
	private int posId;
	
	private int uid;
	
	private int currId;

	public Employee(int id, String realName, String phone, String email, int depId, int posId, int uid, int currId) {
		super();
		this.id = id;
		this.realName = realName;
		this.phone = phone;
		this.email = email;
		this.depId = depId;
		this.posId = posId;
		this.uid = uid;
		this.currId = currId;
	}

	public Employee(String realName, String phone, String email, int depId, int posId, int uid, int currId) {
		super();
		this.realName = realName;
		this.phone = phone;
		this.email = email;
		this.depId = depId;
		this.posId = posId;
		this.uid = uid;
		this.currId = currId;
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getPosId() {
		return posId;
	}

	public void setPosId(int posId) {
		this.posId = posId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCurrId() {
		return currId;
	}

	public void setCurrId(int currId) {
		this.currId = currId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", realName=" + realName + ", phone=" + phone + ", email=" + email + ", depId="
				+ depId + ", posId=" + posId + ", uid=" + uid + ", currId=" + currId + "]";
	}
}