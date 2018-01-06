package com.zy.employee.entity;
public class Offer{

	private int id;
	
	private int curId;//简历id
	
	private int statics;//0表示改简历未查看  1表示已查看
	
	private String delivery;//投递时间
	
	private int interview;//0表示未面试 1表示面试通过   2面试失败 3简历看不过去
	
	private String confirm;//通知其面试时间
	
	private int uid;

	public Offer(int id, int curId, int statics, String delivery, int interview, String confirm, int uid) {
		super();
		this.id = id;
		this.curId = curId;
		this.statics = statics;
		this.delivery = delivery;
		this.interview = interview;
		this.confirm = confirm;
		this.uid = uid;
	}

	public Offer(int curId, int statics, String delivery, int interview, String confirm, int uid) {
		super();
		this.curId = curId;
		this.statics = statics;
		this.delivery = delivery;
		this.interview = interview;
		this.confirm = confirm;
		this.uid = uid;
	}

	public Offer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurId() {
		return curId;
	}

	public void setCurId(int curId) {
		this.curId = curId;
	}

	public int getStatics() {
		return statics;
	}

	public void setStatics(int statics) {
		this.statics = statics;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public int getInterview() {
		return interview;
	}

	public void setInterview(int interview) {
		this.interview = interview;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", curId=" + curId + ", statics=" + statics + ", delivery=" + delivery
				+ ", interview=" + interview + ", confirm=" + confirm + ", uid=" + uid + "]";
	}
}