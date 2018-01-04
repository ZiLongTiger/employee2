package com.zy.employee.entity;

public class Curriculumvitae{

	private int id;
	
	private String realName;//真实姓名
	
	private String gender;//性别
	
	private int age;//年龄
	
	private String education;//学历
	
	private String phone;//联系方式
	
	private String email;//电子邮箱
	
	private String jobPostion;//应聘职位
	
	private String politics;//政治面貌
	
	private String experience;//工作经验
	
	private String expectedSalary;//期望薪水
	
	private String hobby;//兴趣爱好
	
	private String evaluation;//自我评价
	
	private User user;//关联的
	
	private String title;

	public Curriculumvitae(String realName, String gender, int age, String education, String phone, String email,
			String jobPostion, String politics, String experience, String expectedSalary, String hobby,
			String evaluation, User user, String title) {
		super();
		this.realName = realName;
		this.gender = gender;
		this.age = age;
		this.education = education;
		this.phone = phone;
		this.email = email;
		this.jobPostion = jobPostion;
		this.politics = politics;
		this.experience = experience;
		this.expectedSalary = expectedSalary;
		this.hobby = hobby;
		this.evaluation = evaluation;
		this.user = user;
		this.title = title;
	}

	public Curriculumvitae(int id, String realName, String gender, int age, String education, String phone,
			String email, String jobPostion, String politics, String experience, String expectedSalary, String hobby,
			String evaluation, User user, String title) {
		super();
		this.id = id;
		this.realName = realName;
		this.gender = gender;
		this.age = age;
		this.education = education;
		this.phone = phone;
		this.email = email;
		this.jobPostion = jobPostion;
		this.politics = politics;
		this.experience = experience;
		this.expectedSalary = expectedSalary;
		this.hobby = hobby;
		this.evaluation = evaluation;
		this.user = user;
		this.title = title;
	}

	public Curriculumvitae() {
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
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

	public String getJobPostion() {
		return jobPostion;
	}

	public void setJobPostion(String jobPostion) {
		this.jobPostion = jobPostion;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Curriculumvitae [id=" + id + ", realName=" + realName + ", gender=" + gender + ", age=" + age
				+ ", education=" + education + ", phone=" + phone + ", email=" + email + ", jobPostion=" + jobPostion
				+ ", politics=" + politics + ", experience=" + experience + ", expectedSalary=" + expectedSalary
				+ ", hobby=" + hobby + ", evaluation=" + evaluation + ", user=" + user + ", title=" + title + "]";
	}
}