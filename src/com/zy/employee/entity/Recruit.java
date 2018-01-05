package com.zy.employee.entity;

public class Recruit{

	private int id;
	
	private String companyDescription;//��˾����
	
	private String jobReq;//ְλ����
	
	private String workHours;//����ʱ��
	
	private String benefits;//��������
	
	private String address;//��˾��ַ
	
	private String tel;//��˾�绰
	
	private String title;//��һ����Ŀ�ı���

	public Recruit(String companyDescription, String jobReq, String workHours, String benefits, String address,
			String tel, String title) {
		super();
		this.companyDescription = companyDescription;
		this.jobReq = jobReq;
		this.workHours = workHours;
		this.benefits = benefits;
		this.address = address;
		this.tel = tel;
		this.title = title;
	}

	public Recruit(int id, String companyDescription, String jobReq, String workHours, String benefits, String address,
			String tel, String title) {
		super();
		this.id = id;
		this.companyDescription = companyDescription;
		this.jobReq = jobReq;
		this.workHours = workHours;
		this.benefits = benefits;
		this.address = address;
		this.tel = tel;
		this.title = title;
	}

	public Recruit() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getJobReq() {
		return jobReq;
	}

	public void setJobReq(String jobReq) {
		this.jobReq = jobReq;
	}

	public String getWorkHours() {
		return workHours;
	}

	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Recruit [id=" + id + ", companyDescription=" + companyDescription + ", jobReq=" + jobReq
				+ ", workHours=" + workHours + ", benefits=" + benefits + ", address=" + address + ", tel=" + tel
				+ ", title=" + title + "]";
	}
}