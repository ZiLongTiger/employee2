package com.zy.employee.entity;

public class Bonus{

	private int id;
	
	private double bonus;//奖金,罚款
	
	private String createTime;//创建时间
	
	private String introduce;//说明
	
	private String reward;//奖励，3异议,罚款 时间
	
	private int uid;
	
	private int deletestatus;//删除状态 0表示未删除  1表示已删除
	
	private int balance;//将罚标志  1奖  2罚  3异议

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Bonus(int id, double bonus, String createTime, String introduce, String reward, int uid, int deletestatus,
			int balance) {
		super();
		this.id = id;
		this.bonus = bonus;
		this.createTime = createTime;
		this.introduce = introduce;
		this.reward = reward;
		this.uid = uid;
		this.deletestatus = deletestatus;
		this.balance = balance;
	}

	public Bonus(double bonus, String createTime, String introduce, String reward, int uid, int deletestatus,
			int balance) {
		super();
		this.bonus = bonus;
		this.createTime = createTime;
		this.introduce = introduce;
		this.reward = reward;
		this.uid = uid;
		this.deletestatus = deletestatus;
		this.balance = balance;
	}

	public Bonus() {
		super();
	}
}