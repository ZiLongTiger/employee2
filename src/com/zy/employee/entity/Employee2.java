package com.zy.employee.entity;

public class Employee2{

	private int id;
	
	private String email;
	
	private String phone;
	
	private Department dept;//Ա������
	
	private Postion postion;//Ա��ְλ
	
	private User user;//Ա���˺�
	
	private Curriculumvitae curriculumvitae;//Ա������

	public String record;//��ְ����
	
	public int status;//Ա��״̬

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Postion getPostion() {
		return postion;
	}

	public void setPostion(Postion postion) {
		this.postion = postion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Curriculumvitae getCurriculumvitae() {
		return curriculumvitae;
	}

	public void setCurriculumvitae(Curriculumvitae curriculumvitae) {
		this.curriculumvitae = curriculumvitae;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Employee2(int id, String email, String phone, Department dept, Postion postion, User user,
			Curriculumvitae curriculumvitae, String record, int status) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.dept = dept;
		this.postion = postion;
		this.user = user;
		this.curriculumvitae = curriculumvitae;
		this.record = record;
		this.status = status;
	}
}