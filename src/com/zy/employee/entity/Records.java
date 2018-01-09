package com.zy.employee.entity;

public class Records{

	private int id;
	
	private String clockIn;//�ϰ��
	
	private String clockOut;//�°��
	
	private String types;//������
	
	private int uid;
	
	private int deletestatus;//ɾ��״̬ 0��ʾδɾ��  1��ʾ��ɾ��

	public Records(int id, String clockIn, String clockOut, String types, int uid, int deletestatus) {
		super();
		this.id = id;
		this.clockIn = clockIn;
		this.clockOut = clockOut;
		this.types = types;
		this.uid = uid;
		this.deletestatus = deletestatus;
	}

	public Records(String clockIn, String clockOut, String types, int uid, int deletestatus) {
		super();
		this.clockIn = clockIn;
		this.clockOut = clockOut;
		this.types = types;
		this.uid = uid;
		this.deletestatus = deletestatus;
	}

	public Records() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClockIn() {
		return clockIn;
	}

	public void setClockIn(String clockIn) {
		this.clockIn = clockIn;
	}

	public String getClockOut() {
		return clockOut;
	}

	public void setClockOut(String clockOut) {
		this.clockOut = clockOut;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
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

	@Override
	public String toString() {
		return "Records [id=" + id + ", clockIn=" + clockIn + ", clockOut=" + clockOut + ", types=" + types + ", uid="
				+ uid + ", deletestatus=" + deletestatus + "]";
	}
}