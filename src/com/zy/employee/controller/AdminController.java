package com.zy.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
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
	
	@RequestMapping("checkLogin.do")
	@ResponseBody
	public String checkLogin(Model model) {
		if(loginUser != null) {
			List<User>list = new ArrayList<User>();
			list.add(loginUser);
			Object json = JSON.toJSON(list);
			return ""+json;
		}
		return "false";
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
	
	//展示部门
	@RequestMapping("showDepartMent.do")
	@ResponseBody
	public String showDepartMent(Model model) {
		List<Department> list = departmentService.getAllDepartment();
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	
	//展示职位
	@RequestMapping("showPostion.do")
	@ResponseBody
	public String showPostion(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String depName = req.getParameter("depName");
		Department dept = departmentService.getByDepName(depName);
		List<Postion> list = postionService.getByDepId(dept.getId());
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	//跳到新增部门页面
	@RequestMapping("goSaveDept.do")
	public String goSaveDept(Model model) {
		String msg = "saveDept";
		model.addAttribute("msg", msg);
		return "admin/departMent";
	}
	
	//查找要修改的部门
	@RequestMapping("queruyDeptById.do")
	public String querySingleDept(HttpServletRequest req,Model model) throws IOException {
		req.setCharacterEncoding("utf-8");
		String di = req.getParameter("id");
		Department dept = departmentService.getByDepId(Integer.parseInt(di));
		String msg = "updateDept";
		model.addAttribute("msg", msg);
		model.addAttribute("dept", dept);
		return "admin/departMent";
	}
	
	
	//修改部门
	@RequestMapping("updateDept.do")
	public String saveDept(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String di = req.getParameter("id");
		Department dept = departmentService.getByDepId(Integer.parseInt(di));
		String deptName = req.getParameter("deptName");
		String baseWage = req.getParameter("baseWage");
		dept.setDepName(deptName);
		dept.setBaseWage(Double.parseDouble(baseWage));
		departmentService.updateDepartment(dept);
		return "redirect:goMainPage.do";
	}
	
	//修改部门
	@RequestMapping("saveDept.do")
	public String updateDept(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String deptName = req.getParameter("deptName");
		String baseWage = req.getParameter("baseWage");
		departmentService.insertDepartment(new Department(1, deptName, Double.parseDouble(baseWage)));
		return "redirect:goMainPage.do";
	}
	
	//删除部门
	@RequestMapping("deleteDept.do")
	@ResponseBody
	public String deleteDept(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String depName = req.getParameter("depName");
		System.out.println(depName);
		Department dept = departmentService.getByDepName(depName);
		//如果部门底下还有员工 则不能解散
		dept.setDepLock(0);
		int res = departmentService.updateDepartment(dept);
		if(res > 0) {
			 return "success";
		}
		return "error";
	}
	
	//查看有的应聘情况
	@RequestMapping("showOffer.do")
	@ResponseBody
	public String showOffer() {
		List<Offer> list = offerService.getAllOffer();
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	//管理员查看简历
	@RequestMapping("queryCurrById.do")
	@ResponseBody
	public String queryCurrById(HttpServletRequest req) {
		int id = Integer.valueOf(req .getParameter("id"));
		Curriculumvitae curr = curriculumvitaeService.getById(id);
		int offerId = Integer.valueOf(req.getParameter("offerId"));
		Offer offer = offerService.getById(offerId);
		offer.setStatics(1);
		offerService.updateOffer(offer);
		List<Curriculumvitae>list = new ArrayList<Curriculumvitae>();
		list.add(curr);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	
	//管理员删除不满意的投递
	@RequestMapping("deleteOffer.do")
	@ResponseBody
	public String deleteOffer(HttpServletRequest req) {
		int offerId = Integer.valueOf(req .getParameter("offerId"));
		Offer offer = offerService.getById(offerId);
		offer.setInterview(4);
		int res = offerService.updateOffer(offer);
		if(res > 0) {
			return "success";
		}
		return "error";
	}
	
	//给投递者面试的机会
	@RequestMapping("giveAnInterview.do")
	@ResponseBody
	public String giveAnInterview(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String date1 = req.getParameter("date1");
		int offerId = Integer.valueOf(req .getParameter("offerId"));
		Offer offer = offerService.getById(offerId);
		offer.setConfirm(date1);
		int res = offerService.updateOffer(offer);
		if(res > 0) {
			return "success";
		}
		return "error";
	}
	
	//发布新的招聘信息
	@RequestMapping("goSaveRecruit.do")
	public String goSaveRecruit(Model model) {
		String msg = "save";
		model.addAttribute("msg", msg);
		return "admin/Recruit";
	}
	//保存新的招聘信息
	@RequestMapping("saveRecruit.do")
	public String addRecruit(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String companyDescription = req.getParameter("companyDescription").trim();
		String jobReq = req.getParameter("jobReq").trim();
		System.out.println(jobReq);
		String workHours = req.getParameter("workHours").trim();
	
		String benefits = req.getParameter("benefits").trim();
		String address = req.getParameter("address").trim();
		String tel = req.getParameter("tel").trim();
		String title = req.getParameter("title").trim();
		Recruit recruit = new Recruit(companyDescription, jobReq, workHours, benefits, address, tel, title);
		recruitService.insertRecruit(recruit);
		return "redirect:goRecruid.do";
	}
	//转到修改招聘信息的界面
	@RequestMapping("querySingleRecruit.do")
	public String goPageRecruit(HttpServletRequest req,Model model) {
		int id = Integer.valueOf(req.getParameter("id"));
		Recruit recruit = recruitService.getById(id);
		String msg = "update";
		model.addAttribute("data", recruit);
		model.addAttribute("msg", msg);
		return "admin/Recruit";
	}
	
	//跳转到新增职位信息页面
	@RequestMapping("goSavePostion.do")
	public String goSavePostion(HttpServletRequest req,Model model) {
		int id = Integer.valueOf(req.getParameter("depId"));
		Department department = departmentService.getByDepId(id);
		String msg = "addPostion";
		model.addAttribute("department", department);
		model.addAttribute("msg", msg);
		return "admin/departMent";
	}
	//跳转到修改职位信息页面
	@RequestMapping("querySingle.do")
	public String goUpdatePostion(HttpServletRequest req,Model model) {
		int posId = Integer.valueOf(req.getParameter("posId"));
		int id = Integer.valueOf(req.getParameter("depId"));
		Department department = departmentService.getByDepId(id);
		Postion postion = postionService.getById(posId);
		String msg = "updatePostion";
		model.addAttribute("department", department);
		model.addAttribute("postion", postion);
		model.addAttribute("msg", msg);
		return "admin/departMent";
	}
	//新增职位
	@RequestMapping("savePostion.do")
	public String savePostion(HttpServletRequest req)  throws IOException {
		req.setCharacterEncoding("utf-8");
		int depId = Integer.parseInt(req.getParameter("depId"));
		Department department = departmentService.getByDepId(depId);
		String posName = req.getParameter("posName");
		postionService.insertPostion(new Postion(0, posName, department));
		return "redirect:goMainPage.do";
	}
	//修改职位名
	@RequestMapping("updatePostion.do")
	public String updatePostion(HttpServletRequest req)  throws IOException {
		req.setCharacterEncoding("utf-8");
		Postion postion = postionService.getById(Integer.parseInt(req.getParameter("posId")));
		String posName = req.getParameter("posName");
		postion.setPosName(posName);
		postionService.updatePostion(postion);
		return "redirect:goMainPage.do";
	}
	
	//删除职位
	@RequestMapping("delPostion.do")
	@ResponseBody
	public String delPostion(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		//如果有员工则不能删除
		int res = postionService.deleteById(Integer.valueOf(req.getParameter("id")));
		if(res>0) {
			return "success";
		}
		return "error";
	}
	
	//保存新的招聘信息
	@RequestMapping("updateRecruit.do")
	public String updateRecruit(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		int id = Integer.valueOf(req.getParameter("id"));
		Recruit recruit = recruitService.getById(id);
		String companyDescription = req.getParameter("companyDescription").trim();
		String jobReq = req.getParameter("jobReq").trim();
		String workHours = req.getParameter("workHours").trim();
		String benefits = req.getParameter("benefits").trim();
		String address = req.getParameter("address").trim();
		String tel = req.getParameter("tel").trim();
		String title = req.getParameter("title").trim();
		
		recruit.setCompanyDescription(companyDescription);
		recruit.setJobReq(jobReq);
		recruit.setWorkHours(workHours);
		recruit.setBenefits(benefits);
		recruit.setAddress(address);
		recruit.setTitle(title);
		recruit.setTel(tel);
		
		recruitService.updateRecruit(recruit);
		return "redirect:goRecruid.do";
	}
	
	//查看发布过的招聘信息
	@RequestMapping("goRecruid.do")
	public String goRecruid(Model model) {
		List<Recruit> listRecruid = recruitService.getAllRecruit();
		model.addAttribute("data", listRecruid);
		return "admin/showRecruid";
	}
	
	//删除过时的招聘简历
	@RequestMapping("deleteRecruid.do")
	public String deleteRecruid(HttpServletRequest req) {
		int id = Integer.valueOf(req.getParameter("id"));
		recruitService.deleteRecruit(id);
		return "redirect:goRecruid.do";
	}
	
	@RequestMapping("queryByTitle.do")
	@ResponseBody
	public String queryRecruidByTitle(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		Recruit recruit = recruitService.getByTitle(title);
		if(recruit != null) {
			return "error";
		}
		return "success";
	}
}
