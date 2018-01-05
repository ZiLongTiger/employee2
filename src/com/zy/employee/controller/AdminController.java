package com.zy.employee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.employee.entity.Department;
import com.zy.employee.entity.User;
import com.zy.employee.service.CurriculumvitaeService;
import com.zy.employee.service.DepartmentService;
import com.zy.employee.service.OfferService;
import com.zy.employee.service.PostionService;
import com.zy.employee.service.RecruitService;
import com.zy.employee.service.UserService;

@Controller("adminController")
@RequestMapping("admin")
public class AdminController {

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
	//用来储存登录账号
	private static User loginUser = null;
	
	@RequestMapping("adminGo.do")
	public String goPage() {
		return "admin/login";
	}
	
	//管理员登录
	@RequestMapping("login.do")
	@ResponseBody
	public String goAdminLogin(HttpServletRequest req) {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.login(name, password);
		if(user != null && user.getRole() == 1 && user.getUserLock() == 0) {
			//判断是不是有效的管理员
			loginUser = user;
			return "success";
		}else if(user != null && user.getRole() == 1 && user.getUserLock() == 1) {
			return "disappear";
		}
		return "error";
	}
	
	//跳转到主页面
	@RequestMapping("goMainPage.do")
	public String goMainPage(Model model) {
		model.addAttribute("user", loginUser);
		return "admin/adminMain";
	}
	
	@RequestMapping("showDepartMent.do")
	public String showDepartMent(Model model) {
		List<Department> list = departmentService.getAllDepartment();
		model.addAttribute("user", loginUser);
		model.addAttribute("list", list);
		return "admin/adminMain";
	}
}
