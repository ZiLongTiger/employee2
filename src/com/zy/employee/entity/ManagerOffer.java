package com.zy.employee.entity;

public class ManagerOffer {

	private int id;
	
	private Curriculumvitae curriculumvitae;
	
	private Offer offer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Curriculumvitae getCurriculumvitae() {
		return curriculumvitae;
	}

	public void setCurriculumvitae(Curriculumvitae curriculumvitae) {
		this.curriculumvitae = curriculumvitae;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public ManagerOffer(int id, Curriculumvitae curriculumvitae, Offer offer) {
		super();
		this.id = id;
		this.curriculumvitae = curriculumvitae;
		this.offer = offer;
	}
}
