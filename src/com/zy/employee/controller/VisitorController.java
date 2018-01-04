package com.zy.employee.controller;

import java.io.IOException;
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
import com.zy.employee.entity.Postion;
import com.zy.employee.entity.User;
import com.zy.employee.service.CurriculumvitaeService;
import com.zy.employee.service.DepartmentService;
import com.zy.employee.service.PostionService;
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
	
	private static User login_user = null;
	
	@RequestMapping("userGo.do")
	public String goUserLogin() {
		return "User/login";
	}
	
	@RequestMapping("login.do")
	@ResponseBody
	public String goAdminLogin(HttpServletRequest req) {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.login(name, password);
		if(user != null && user.getRole() == 0) {
			login_user = user;
			return "success";
		}
		return "error";
	}
	
	@RequestMapping("goMainPage.do")
	public String goMainPage(Model model) {
		System.out.println("goMainPage"+login_user);
		model.addAttribute("user", login_user);
		return "User/mainPage";
	}
	
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
	
	@RequestMapping("changePostion.do")
	@ResponseBody
	public String changPostion(HttpServletRequest req) throws IOException {
		System.out.println("changePostion"+login_user);
		int depId = Integer.parseInt(req.getParameter("depId"));
		List<Postion> list = postionService.getByDepId(depId);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	
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
	
	@RequestMapping("showSelf.do")
	public String showSelfCurriculumvitae(Model model) {
		System.out.println("showSelf"+login_user);
		List<Curriculumvitae> list = curriculumvitaeService.getByUid(login_user.getId());
		model.addAttribute("SelfCurriculumvitae", list);
		return "User/selfCurriculumvitae";
	}
}
