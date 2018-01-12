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
import com.zy.employee.entity.Bonus;
import com.zy.employee.entity.Curriculumvitae;
import com.zy.employee.entity.Department;
import com.zy.employee.entity.EmDepPos;
import com.zy.employee.entity.Employee;
import com.zy.employee.entity.Employee2;
import com.zy.employee.entity.EmployeeRecords;
import com.zy.employee.entity.ManagerOffer;
import com.zy.employee.entity.Offer;
import com.zy.employee.entity.Postion;
import com.zy.employee.entity.Records;
import com.zy.employee.entity.Recruit;
import com.zy.employee.entity.Salary;
import com.zy.employee.entity.SalaryEnd;
import com.zy.employee.entity.User;
import com.zy.employee.service.BonusService;
import com.zy.employee.service.CurriculumvitaeService;
import com.zy.employee.service.DepartmentService;
import com.zy.employee.service.EmployeeService;
import com.zy.employee.service.OfferService;
import com.zy.employee.service.PostionService;
import com.zy.employee.service.RecordsService;
import com.zy.employee.service.RecruitService;
import com.zy.employee.service.SalaryService;
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
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RecordsService recordsService;
	
	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private BonusService bonusService;
	
	//用来储存登录账号
	private static User loginUser = null;
	
	@RequestMapping("adminGo.do")
	public String goPage() {
		return "admin/login";
	}
	
	//用户退出
	@RequestMapping("exit.do")
	public String exitAdmin() {
		loginUser = null;
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
		}else {
			List<String>list = new ArrayList<String>();
			list.add("no");
			Object json = JSON.toJSON(list);
			return ""+json;
		}
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
		}else if(user != null && user.getRole() == 2 && user.getUserLock() == 0) {
			//判断是不是manager
			loginUser = user;
			return "success2";
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
		List<Employee> list = employeeService.getByEmployeeDeptId(dept.getId());
		if(list.size() > 0) {
			return "not";
		}
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
		int posId = Integer.valueOf(req.getParameter("id"));
		//如果有员工则不能删除
		List<Employee> list = employeeService.getByEmployeePosId(posId);
		if(list.size() > 0) {
			return "didnot";
		}
		int res = postionService.deleteById(posId);
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
	
	//跳转到manager界面
	@RequestMapping("goManagerMainPage.do")
	public String goManagerMainPage() {
		return "manager/main";
	}
	
	//检查是否有面试通知
	@RequestMapping("checkMessage.do")
	@ResponseBody
	public String cheackMessage() {
		List<Offer> offer = offerService.getAllOffer();
		if(offer.size() > 0) {
			for (Offer offer2 : offer) {
				if(offer2.getConfirm() != null || offer2.getConfirm() != null) {
					if(offer2.getInterview() == 0) {
						return "success";
					}
				}
			}
		}
		return "error";
	}
	//查看面试消息
	@RequestMapping("message.do")
	@ResponseBody
	public String message() {
		List<Offer> offer = offerService.getAllOffer();
		List<ManagerOffer> list = new ArrayList<ManagerOffer>();
		ManagerOffer managerOffer = null;
		if(offer.size() > 0) {
			for (Offer offer2 : offer) {
				if(offer2.getConfirm() != null || offer2.getConfirm() != null) {
					if(offer2.getInterview() == 0) {
						Curriculumvitae curriculumvitae = curriculumvitaeService.getById(offer2.getCurId());
						managerOffer = new ManagerOffer(offer2.getId(), curriculumvitae, offer2);
						list.add(managerOffer);
					}
				}
			}
		}
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	
	//通过职位查询员工
	@RequestMapping("showEmployee.do")
	@ResponseBody
	public String showEmployee(HttpServletRequest req) {
		int deptId = Integer.parseInt(req.getParameter("deptId"));
		int posId = Integer.parseInt(req.getParameter("posId"));
		List<Employee> list = employeeService.getByEmployeePosIdAndDeptId(posId,deptId);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	
	//添加员工
	@RequestMapping("saveEmployee.do")
	@ResponseBody
	public String saveEmployee(HttpServletRequest req) {
		int curId = Integer.parseInt(req.getParameter("id"));
		int offerId = Integer.parseInt(req.getParameter("offerId"));
		Curriculumvitae curriculumvitae = curriculumvitaeService.getById(curId);
		int uid = curriculumvitae.getUser().getId();
		String realName = curriculumvitae.getRealName();
		String phone = curriculumvitae.getPhone();
		String email = curriculumvitae.getEmail();
		int posId = 0;
		String depName = curriculumvitae.getJobPostion().split(",")[0];
		System.out.println(depName);
		Department dept = departmentService.getByDepName(depName);
		
		List<Postion> list = postionService.getByDepId(dept.getId());
		for (Postion postion : list) {
			if(postion.getPosName().equals(curriculumvitae.getJobPostion().split(",")[1])) {
				posId = postion.getId();
			}
		}
		String record = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		int res = employeeService.insertEmployee(new Employee(realName, phone, email, dept.getId(), posId,
				curriculumvitae.getUser().getId(), curId, record, 0));
		Offer offer = offerService.getById(offerId);
		if(offer.getStatics() == 2) {
			return "no";
		}
		if(res > 0) {
			offer.setStatics(2);
			offer.setInterview(3);
			offerService.updateOffer(offer);
			User user = userService.getUserById(uid);
			user.setRole(3);
			userService.updateUser(user);
			return "success";
		}
		return "error";
	}
	//不录用该员工
	@RequestMapping("notSaveEmployee.do")
	@ResponseBody
	public String notSaveEmployee(HttpServletRequest req) {
		int offerId = Integer.parseInt(req.getParameter("offerId"));
		Offer offer = offerService.getById(offerId);
		if(offer.getStatics() == 2) {
			return "no";
		}
		offer.setInterview(2);
		int res = offerService.updateOffer(offer);
		if(res > 0) {
			return "success";
		}
		return "error";
	}
	
	@RequestMapping("queryAllEmployee.do")
	public String queryAllEmployee(Model model) {
		List<Employee> list = employeeService.getAllEmployee();
		List<Employee2> listEmpl = new ArrayList<Employee2>();
		for (Employee employee : list) {
			Postion postion = postionService.getById(employee.getPosId());
			Department dept = departmentService.getByDepId(employee.getDepId());
			User user = userService.getUserById(employee.getUid());
			Employee2 emp = new Employee2(employee.getId(),employee.getRealName(), employee.getEmail(), employee.getPhone(),
					dept, postion, user, null, employee.getRecord(), employee.getStatus());
			listEmpl.add(emp);
		}
		model.addAttribute("data", listEmpl);
		return "admin/adminMain";
	}
	//开除员工
	@RequestMapping("delEmpl.do")
	@ResponseBody
	public String delEmpl(HttpServletRequest req) {
		int id = Integer.valueOf(req.getParameter("id"));
		Employee employee = employeeService.getByEmployeeId(id);
		employee.setStatus(3);
		int res = employeeService.updateEmployee(employee);
		if(res>0) {
			User user = userService.getUserById(employee.getUid());
			System.out.println(user);
			user.setRole(0);
			userService.updateUser(user);
			return "success";
		}
		return "error";
	}
	
	//员工调动
	@RequestMapping("emplGo.do")
	@ResponseBody
	public String emplDepPos(HttpServletRequest req) {
		int id = Integer.valueOf(req.getParameter("id"));
		Employee employee = employeeService.getByEmployeeId(id);
		List<Department> depl = departmentService.getAllDepartment();
		List<Postion> posl = postionService.getByDepId(employee.getDepId());
		EmDepPos emDepPos = new  EmDepPos(employee,depl,posl);
		List<EmDepPos>list = new ArrayList<EmDepPos>();
		list.add(emDepPos);
		Object json = JSON.toJSON(list);
		return "" + json;
	}
	
	@RequestMapping("empChangeDP.do")
	@ResponseBody
	public String empChangeDP(HttpServletRequest req) {
		int depId = Integer.valueOf(req.getParameter("depId"));
		int posId = Integer.valueOf(req.getParameter("posId"));
		int eid = Integer.valueOf(req.getParameter("eid"));
		Employee employee = employeeService.getByEmployeeId(eid);
		employee.setDepId(depId);
		employee.setPosId(posId);
		int res = employeeService.updateEmployee(employee);
		if(res > 0) {
			return "success";
		}
		return "error";
	}
	
	//查看单个员工的考情记录
	@RequestMapping("queryRecordsByEid.do")
	@ResponseBody
	public String queryRecordsByEid(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("employeeId"));
		Employee emp = employeeService.getByEmployeeId(id);
		List<Records> list = recordsService.getByRecordsUid(emp.getUid());
		List<EmployeeRecords> rlist = new ArrayList<EmployeeRecords>();
		EmployeeRecords EmpR = new EmployeeRecords(emp.getRealName(), list);
		rlist.add(EmpR);
		Object json = JSON.toJSON(rlist);
		return ""+json;
	}
	
	//查看员工本月的薪水
	@RequestMapping("showEmploySalary.do")
	@ResponseBody
	public String showEmploySalary(HttpServletRequest req) {
		int employeeId = Integer.parseInt(req.getParameter("employeeId"));
		String month = req.getParameter("month");
		Employee emp = employeeService.getByEmployeeId(employeeId);
		Salary salary = salaryService.getSalaryByMonthAndUid(emp.getUid(), month);
		if(salary != null) {
			List<SalaryEnd>list = new ArrayList<SalaryEnd>();
			List<Bonus> list2 = bonusService.getByBonusUidAndReward(emp.getUid(), month);
			SalaryEnd salaryEnd = new SalaryEnd(salary, emp, list2);
			list.add(salaryEnd);
			Object json = JSON.toJSON(list);
			return ""+json;
		}else {
			List<String>list = new ArrayList<String>();
			list.add("no");
			Object json = JSON.toJSON(list);
			return ""+json;
		}
	}
	
	//工资发放
	@RequestMapping("endSalary.do")
	@ResponseBody
	public String salaryGo(HttpServletRequest req) {
		return "";
	}
	
}
