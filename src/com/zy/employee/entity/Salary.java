package com.zy.employee.entity;

public class Salary{

	private int id;
	
	private double late;//迟到
	
	private double evection;//出差
	
	private int bonusId;//奖金
	
	private double early;//早退
	
	private double absenteeism;//旷工
	
	private int uid;
	
	private double total;//实际应发工资
	
	private double baseWage;//基本工资
	
	private String month;

	public Salary(int id, double late, double evection, int bonusId, double early, double absenteeism, int uid,
			double total, double baseWage, String month) {
		super();
		this.id = id;
		this.late = late;
		this.evection = evection;
		this.bonusId = bonusId;
		this.early = early;
		this.absenteeism = absenteeism;
		this.uid = uid;
		this.total = total;
		this.baseWage = baseWage;
		this.month = month;
	}

	public Salary(double late, double evection, int bonusId, double early, double absenteeism, int uid, double total,
			double baseWage, String month) {
		super();
		this.late = late;
		this.evection = evection;
		this.bonusId = bonusId;
		this.early = early;
		this.absenteeism = absenteeism;
		this.uid = uid;
		this.total = total;
		this.baseWage = baseWage;
		this.month = month;
	}

	public Salary() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLate() {
		return late;
	}

	public void setLate(double late) {
		this.late = late;
	}

	public double getEvection() {
		return evection;
	}

	public void setEvection(double evection) {
		this.evection = evection;
	}

	public int getBonusId() {
		return bonusId;
	}

	public void setBonusId(int bonusId) {
		this.bonusId = bonusId;
	}

	public double getEarly() {
		return early;
	}

	public void setEarly(double early) {
		this.early = early;
	}

	public double getAbsenteeism() {
		return absenteeism;
	}

	public void setAbsenteeism(double absenteeism) {
		this.absenteeism = absenteeism;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getBaseWage() {
		return baseWage;
	}

	public void setBaseWage(double baseWage) {
		this.baseWage = baseWage;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", late=" + late + ", evection=" + evection + ", bonusId=" + bonusId + ", early="
				+ early + ", absenteeism=" + absenteeism + ", uid=" + uid + ", total=" + total + ", baseWage="
				+ baseWage + ", month=" + month + "]";
	}
}