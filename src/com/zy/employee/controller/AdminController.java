package com.zy.employee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.employee.entity.User;
import com.zy.employee.service.UserService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("adminGo.do")
	public String goPage() {
		return "admin/login";
	}
	
	//����Ա��¼
	@RequestMapping("login.do")
	@ResponseBody
	public String goAdminLogin(HttpServletRequest req) {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.login(name, password);
		if(user != null && user.getRole() == 1 && user.getUserLock() == 0) {
			//�ж��ǲ�����Ч�Ĺ���Ա
			return "success";
		}else if(user != null && user.getRole() == 1 && user.getUserLock() == 1) {
			return "disappear";
		}
		return "error";
	}
}
