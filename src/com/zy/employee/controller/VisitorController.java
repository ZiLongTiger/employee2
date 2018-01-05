package com.zy.employee.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zy.employee.entity.Curriculumvitae;
import com.zy.employee.entity.Department;
import com.zy.employee.entity.Offer;
import com.zy.employee.entity.Postion;
import com.zy.employee.entity.Recruit;
import com.zy.employee.entity.User;
import com.zy.employee.service.CurriculumvitaeService;
import com.zy.employee.service.DepartmentService;
import com.zy.employee.service.OfferService;
import com.zy.employee.service.PostionService;
import com.zy.employee.service.RecruitService;
import com.zy.employee.service.UserService;

@Controller("visitorController")
@RequestMapping("user")
public class VisitorController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CurriculumvitaeService curriculumvitaeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PostionService postionService;
	
	@Autowired
	private RecruitService recruitService;
	
	@Autowired
	private OfferService offerService;
	
	private static User login_user = null;
	//��ת���û���¼����
	@RequestMapping("userGo.do")
	public String goUserLogin() {
		return "User/login";
	}
	//��ת���û�ע�����
	@RequestMapping("goRegister.do")
	public String goRegister() {
		return "User/register";
	}
	
	//��ͨ�û���¼
	@RequestMapping("login.do")
	@ResponseBody
	public String goUserLogin(HttpServletRequest req) {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.login(name, password);
		if(user != null && user.getRole() == 0 && user.getUserLock() == 0) {
			login_user = user;
			return "success";
		}else if(user != null && user.getRole() == 2 && user.getUserLock() == 0) {
			login_user = user;
			return "success2";
		}else if(user != null && user.getRole() == 0 && user.getUserLock() == 1) {
			return "disappear";//���û���״̬��ɾ���˵�  
		}else {
			return "error";
		}
	}
	//��ת����ҳ��
	@RequestMapping("goMainPage.do")
	public String goMainPage(Model model) {
		System.out.println("goMainPage"+login_user);
		model.addAttribute("user", login_user);
		return "User/mainPage";
	}
	//��ת���½�������ҳ��
	@RequestMapping("create.do")
	public String writeCurriculumvitae(Model model) {
		System.out.println("create"+login_user);
		List<Department> departMent = departmentService.getAllDepartment();
		String msg= "create";
		model.addAttribute("msg", msg);
		model.addAttribute("user", login_user);
		model.addAttribute("departMent", departMent);
		return "User/Curriculumvitae";
	}
	//��������  ��ְλ��������̬�޸�Ч��
	@RequestMapping("changePostion.do")
	@ResponseBody
	public String changPostion(HttpServletRequest req) throws IOException {
		System.out.println("changePostion"+login_user);
		int depId = Integer.parseInt(req.getParameter("depId"));
		List<Postion> list = postionService.getByDepId(depId);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	//������������ݿ�
	@RequestMapping("saveCurriculumvitae.do")
	public String saveCurriculumvitae(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
//		System.out.println("saveCurriculumvitae"+login_user);
//		Integer useId = Integer.valueOf(req.getParameter("userId"));
//		User user = userService.getUserById(useId);
		String name = req.getParameter("userName");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		Integer age = Integer.valueOf(req.getParameter("age"));
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String education = req.getParameter("education");
		String politics = req.getParameter("politics");
		String depId = req.getParameter("departMent");
		Department dep = departmentService.getByDepId(Integer.valueOf(depId));
		String postion = req.getParameter("postion");
		String jobPostion = dep.getDepName()+","+postion;
		String experience = req.getParameter("experience");
		String expectedSalary = req.getParameter("expectedSalary");
		String evaluation = req.getParameter("evaluation");
		String title = req.getParameter("title");
		System.out.println(title);
		Curriculumvitae curriculumvitae = new Curriculumvitae(name, gender, age, education, phone, email, jobPostion, politics, experience, expectedSalary, hobby, evaluation, login_user,title);
		curriculumvitaeService.saveCurriculumvitae(curriculumvitae);
		return "redirect:goMainPage.do";
	}
	//�鿴�Լ��ļ���
	@RequestMapping("showSelf.do")
	public String showSelfCurriculumvitae(Model model) {
		System.out.println("showSelf"+login_user);
		List<Curriculumvitae> list = curriculumvitaeService.getByUid(login_user.getId());
		model.addAttribute("SelfCurriculumvitae", list);
		return "User/selfCurriculumvitae";
	}
	//�û�ע��
	@RequestMapping("register.do")
	@ResponseBody
	public String saveUser(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.getUserByName(name);
		if(user != null) {//������û��Ѵ������ܼ���ע��
			return "error";
		}else {
			int res = userService.regiseter(new User(name, password, 0, 0));
			if(res > 0) {
				return "success";
			}else {
				return "error2";
			}
		}
	}
	//ע��ʱ���û������ж�
	@RequestMapping("registerCheckName.do")
	@ResponseBody
	public String registerCheckName(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		User user = userService.getUserByName(name);
		if(user != null) {
			return "error";
		}
		return "success";
	}
	//���������ı��� ������ͬ
	@RequestMapping("queryByTitle.do")
	@ResponseBody
	public String queryByTitle(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		Curriculumvitae curriculumvitae = curriculumvitaeService.getByTitle(title);
		if(curriculumvitae != null) {
			return "error";
		}
		return "success";
	}
	//���ݼ������� ��ѯ������ļ�����Ϣ
	@RequestMapping("queryByTitle2.do")
	@ResponseBody
	public String queryByTitle2(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		Curriculumvitae curriculumvitae = curriculumvitaeService.getByTitle(title);
		List<Curriculumvitae> list = new ArrayList<Curriculumvitae>();
		list.add(curriculumvitae);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	//�޸��Լ�������ļ���
	@RequestMapping("querySingle.do")
	public String querySingle(HttpServletRequest req,Model model) throws IOException {
		req.setCharacterEncoding("utf-8");
		int id = Integer.valueOf(req.getParameter("id"));
		String msg = "update";
		List<Department> departMent = departmentService.getAllDepartment();
		Curriculumvitae curriculumvitae = curriculumvitaeService.getById(id);
		String depName = curriculumvitae.getJobPostion().split(",")[0];
		Department dep = departmentService.getByDepName(depName);
		List<Postion> list = postionService.getByDepId(dep.getId());
		model.addAttribute("msg", msg);
		model.addAttribute("curriculumvitae", curriculumvitae);
		model.addAttribute("departMent", departMent);
		model.addAttribute("postion", list);
		return "User/Curriculumvitae";
	}
	
	//�޸ļ���
	@RequestMapping("updateCurriculumvitae.do")
	public String updateCurriculumvitae(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		Integer id = Integer.valueOf(req.getParameter("cid"));
		Curriculumvitae curriculumvitae = curriculumvitaeService.getById(id);
		String name = req.getParameter("userName");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		Integer age = Integer.valueOf(req.getParameter("age"));
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String education = req.getParameter("education");
		String politics = req.getParameter("politics");
		String depId = req.getParameter("departMent");
		Department dep = departmentService.getByDepId(Integer.valueOf(depId));
		String postion = req.getParameter("postion");
		String jobPostion = dep.getDepName()+","+postion;
		String experience = req.getParameter("experience");
		String expectedSalary = req.getParameter("expectedSalary");
		String evaluation = req.getParameter("evaluation");
		String title = req.getParameter("title");
		
		curriculumvitae.setRealName(name);
		curriculumvitae.setGender(gender);
		curriculumvitae.setAge(age);
		curriculumvitae.setHobby(hobby);
		curriculumvitae.setEducation(education);
		curriculumvitae.setJobPostion(jobPostion);
		curriculumvitae.setEmail(email);
		curriculumvitae.setPhone(phone);
		curriculumvitae.setExpectedSalary(expectedSalary);
		curriculumvitae.setExperience(experience);
		curriculumvitae.setPolitics(politics);
		curriculumvitae.setEvaluation(evaluation);
		curriculumvitae.setTitle(title);
		curriculumvitaeService.updateCurriculumvitae(curriculumvitae);
		return "redirect:goMainPage.do";
	}
	//��ת����Ƹ��Ϣ��ҳ
	@RequestMapping("goRecruid.do")
	public String goRecruid(Model model) {
		List<Recruit> listRecruid = recruitService.getAllRecruit();
		model.addAttribute("user", login_user);
		model.addAttribute("data", listRecruid);
		return "recruid/showRecruid";
	}
	//������Ƹ��Ϣ�ı���鿴�������Ƿ�Ͷ��
	@RequestMapping("queryByRecruidTitle.do")
	@ResponseBody
	public String queryByTitleRecruid(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		List<Recruit> list = new ArrayList<Recruit>();
		Recruit recruit = recruitService.getByTitle(title);
		list.add(recruit);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	
	//�鿴�Լ��ļ���
	@RequestMapping("goShowSelf.do")
	public String goShowSelfCurriculumvitae(Model model) {
		System.out.println("showSelf"+login_user);
		String msg = "go";
		List<Curriculumvitae> list = curriculumvitaeService.getByUid(login_user.getId());
		model.addAttribute("SelfCurriculumvitae", list);
		model.addAttribute("msg", msg);
		return "User/selfCurriculumvitae";
	}
	
	//Ͷ�ݼ���
	@RequestMapping("sendCurriculumvitae.do")
	@ResponseBody
	public String goSendCurriculumvitae(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		int curId = Integer.valueOf(req.getParameter("id"));
		System.out.println(curId);
		Curriculumvitae curr = curriculumvitaeService.getById(curId);
		Offer offer = offerService.getByuId(curr.getUser().getId());	
		if(offer != null) {//����Ѿ�Ͷ�ݹ��� ���ܼ���Ͷ��
			return "error1";
		}
		String delivery = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		int res = offerService.insertOffer(new Offer(curId, 0, delivery, 0, null, curr.getUser().getId()));
		if(res > 0) {
			return "success";
		}
		return "error";
	}
}
