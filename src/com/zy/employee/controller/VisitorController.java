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
import com.zy.employee.entity.Employee;
import com.zy.employee.entity.Employee2;
import com.zy.employee.entity.Offer;
import com.zy.employee.entity.Postion;
import com.zy.employee.entity.Records;
import com.zy.employee.entity.Recruit;
import com.zy.employee.entity.Salary;
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
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RecordsService recordsService;
	
	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private BonusService bonusService;
	
	private static User login_user = null;
	
	@RequestMapping("checkLogin.do")
	@ResponseBody
	public String checkLogin(Model model) {
		if(login_user != null) {
			List<User>list = new ArrayList<User>();
			list.add(login_user);
			Object json = JSON.toJSON(list);
			return ""+json;
		}else {
			List<String>list = new ArrayList<String>();
			list.add("no");
			Object json = JSON.toJSON(list);
			return ""+json;
		}
	}
	
	//跳转到用户登录界面
	@RequestMapping("userGo.do")
	public String goUserLogin() {
		return "User/login";
	}
	//跳转到用户注册界面
	@RequestMapping("goRegister.do")
	public String goRegister() {
		return "User/register";
	}
	
	//普通用户登录
	@RequestMapping("login.do")
	@ResponseBody
	public String goUserLogin(HttpServletRequest req) {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.login(name, password);
		if(user != null && user.getRole() == 0 && user.getUserLock() == 0) {
			login_user = user;
			return "success";
		}else if(user != null && user.getRole() == 3 && user.getUserLock() == 0) {
			login_user = user;
			return "success2";
		}else if(user != null && user.getRole() == 0 && user.getUserLock() == 1) {
			return "disappear";//该用户的状态是删除了的  
		}else {
			return "error";
		}
	}
	//跳转到主页面
	@RequestMapping("goMainPage.do")
	public String goMainPage(Model model) {
		model.addAttribute("user", login_user);
		return "User/mainPage";
	}
	//跳转到新建简历的页面
	@RequestMapping("create.do")
	public String writeCurriculumvitae(Model model) {
		List<Department> departMent = departmentService.getAllDepartment();
		String msg= "create";
		model.addAttribute("msg", msg);
		model.addAttribute("user", login_user);
		model.addAttribute("departMent", departMent);
		return "User/Curriculumvitae";
	}
	//二级联动  对职位的做到动态修改效果
	@RequestMapping("changePostion.do")
	@ResponseBody
	public String changPostion(HttpServletRequest req) throws IOException {
		int depId = Integer.parseInt(req.getParameter("depId"));
		List<Postion> list = postionService.getByDepId(depId);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	//保存简历到数据库
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
	//查看自己的简历
	@RequestMapping("showSelf.do")
	public String showSelfCurriculumvitae(Model model) {
		List<Curriculumvitae> list = curriculumvitaeService.getByUid(login_user.getId());
		model.addAttribute("SelfCurriculumvitae", list);
		return "User/selfCurriculumvitae";
	}
	//用户注册
	@RequestMapping("register.do")
	@ResponseBody
	public String saveUser(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		User user = userService.getUserByName(name);
		if(user != null) {//如果该用户已存在则不能继续注册
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
	//注册时对用户名的判断
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
	//创建简历的标题 不能相同
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
	//根据简历标题 查询到具体的简历信息
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
	//修改自己不满意的简历
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
	//查看简历是否投递 ，如果投递则不可再次修改
	@RequestMapping("checkCurr.do")
	@ResponseBody
	public String check(HttpServletRequest req) {
		int id = Integer.valueOf(req.getParameter("id"));
		Offer offer = offerService.getBycurId(id);
		if(offer!=null) {
			return "no";
		}
		return "ok";
	}
	
	//修改简历
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
	//跳转到招聘信息网页
	@RequestMapping("goRecruid.do")
	public String goRecruid(Model model) {
		List<Recruit> listRecruid = recruitService.getAllRecruit();
		model.addAttribute("user", login_user);
		model.addAttribute("data", listRecruid);
		return "recruid/showRecruid";
	}
	//根据招聘信息的标题查看并决定是否投递
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
	
	//查看自己的简历
	@RequestMapping("goShowSelf.do")
	public String goShowSelfCurriculumvitae(Model model) {
		System.out.println("showSelf"+login_user);
		String msg = "go";
		List<Curriculumvitae> list = curriculumvitaeService.getByUid(login_user.getId());
		model.addAttribute("SelfCurriculumvitae", list);
		model.addAttribute("msg", msg);
		return "User/selfCurriculumvitae";
	}
	
	//投递简历
	@RequestMapping("sendCurriculumvitae.do")
	@ResponseBody
	public String goSendCurriculumvitae(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		int curId = Integer.valueOf(req.getParameter("id"));
		System.out.println(curId);
		Curriculumvitae curr = curriculumvitaeService.getById(curId);
		Offer offer = offerService.getByuId(curr.getUser().getId());	
		if(offer != null) {//如果已经投递过来 则不能继续投递
			return "error1";
		}
		String delivery = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		int res = offerService.insertOffer(new Offer(curId, 0, delivery, 0, null, curr.getUser().getId()));
		if(res > 0) {
			return "success";
		}
		return "error";
	}
	
	//检查是否有面试通知
	@RequestMapping("checkMessage.do")
	@ResponseBody
	public String cheackMessage() {
		Offer offer = offerService.getByuId(login_user.getId());
		if(offer.getInterview() == 4 || offer.getInterview() == 3) {
			return "error";
		}
		if(offer.getConfirm() != null) {
			System.out.println(offer.getConfirm());
			return "success";
		}
		return "error";
	}
	//查看面试消息
	@RequestMapping("message.do")
	@ResponseBody
	public String message() {
		Offer offer = offerService.getByuId(login_user.getId());
		if(offer.getConfirm() == null) {
			List<String>list = new ArrayList<String>();
			list.add("no");
			Object json = JSON.toJSON(list);
			return ""+json;
		}
		List<Offer>list = new ArrayList<Offer>();
		list.add(offer);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	//跳转到员工主界面
	@RequestMapping("goEmployeePage.do")
	public String goEmployeePage() {
		return "employee/employeeMain";
	}
	//查看个人信息
	@RequestMapping("showSelfMsg.do")
	@ResponseBody
	public String showSelfMsg() {
		Employee employee = employeeService.getByEmployeeByUid(login_user.getId());
		Postion postion = postionService.getById(employee.getPosId());
		Department dept = departmentService.getByDepId(employee.getDepId());
		Curriculumvitae curriculumvitae = curriculumvitaeService.getById(employee.getCurrId());
		Employee2 em2 = new Employee2(employee.getId(),employee.getEmail(),employee.getPhone(), dept, postion, login_user, curriculumvitae, employee.getRecord(), employee.getStatus());
		List<Employee2> list = new ArrayList<Employee2>();
		list.add(em2);
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	//修改个人信息页面跳转
	@RequestMapping("goUpdateEmpl.do")
	public String goUpdateEmpl(Model model) {
		Employee employee = employeeService.getByEmployeeByUid(login_user.getId());
		Postion postion = postionService.getById(employee.getPosId());
		Department dept = departmentService.getByDepId(employee.getDepId());
		Curriculumvitae curriculumvitae = curriculumvitaeService.getById(employee.getCurrId());
		Employee2 em2 = new Employee2(employee.getId(),employee.getEmail(),employee.getPhone(), dept, postion, login_user, curriculumvitae, employee.getRecord(), employee.getStatus());
		model.addAttribute("empl", em2);
		return "employee/selfMsg";
	}
	
	//员工修改个人信息
	@RequestMapping("updateEmployee.do")
	public String updateEmployee(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		Employee employee = employeeService.getByEmployeeId(id);
		employee.setPhone(phone);
		employee.setEmail(email);
		employeeService.updateEmployee(employee);
		
		return "redirect:goEmployeePage.do";
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
	//检验用户是否已经打过上班卡
	@RequestMapping("goCheckClockIn.do")
	@ResponseBody
	public String checkClockIn() {
		Records records = recordsService.getByRecordsUidAndDeleStatus(0, login_user.getId());
		if(records == null) {
			return "no";
		}else {
			return "yes";
		}
	}
	
	//上班打卡
	@RequestMapping("clockIn.do")
	@ResponseBody
	public String clockIn(HttpServletRequest req) {
		String clockIn = req.getParameter("time");
		String types = req.getParameter("types");
		String month = clockIn.split("-")[0]+"-"+clockIn.split("-")[1];
		Records records = new Records(clockIn, null, types, login_user.getId(), 0);
		int res = recordsService.insertRecords(records);
		if(res>0) {
			if(types.equals("迟到打卡")) {
				Salary salary = salaryService.getSalaryByMonthAndUidAndStatus(login_user.getId(), month,0);
				if(salary != null) {
					salary.setLate(salary.getLate()+50);
					salaryService.updateSalary(salary);
				}else {
					Employee employee = employeeService.getByEmployeeByUid(login_user.getId());
					Department department = departmentService.getByDepId(employee.getDepId());
					Salary salary2 = new Salary(50, 0, 0, 0, 0, login_user.getId(), 0, department.getBaseWage(), month,0);
					salaryService.insertSalary(salary2);
				}
			}
			if(types.equals("旷工")) {
				Salary salary = salaryService.getSalaryByMonthAndUidAndStatus(login_user.getId(), month,0);
				if(salary != null) {
					salary.setAbsenteeism(salary.getAbsenteeism()+100);
					salaryService.updateSalary(salary);
				}else {
					Employee employee = employeeService.getByEmployeeByUid(login_user.getId());
					Department department = departmentService.getByDepId(employee.getDepId());
					Salary salary2 = new Salary(0, 0, 0, 0, 100, login_user.getId(), 0, department.getBaseWage(), month ,0);
					salaryService.insertSalary(salary2);
				}
			}
			return "success";
		}
		return "error";
	}
	//下班打卡
	@RequestMapping("clockOut.do")
	@ResponseBody
	public String clockOut(HttpServletRequest req) {
		String clockOut = req.getParameter("time");
		String types = req.getParameter("types");
		String month = clockOut.split("-")[0]+"-"+clockOut.split("-")[1];
		Records records = recordsService.getByRecordsUidAndDeleStatus(0, login_user.getId());
		records.setClockOut(clockOut);
		records.setTypes(records.getTypes()+","+types);
		records.setDeletestatus(1);
		int res = recordsService.updateRecords(records);
		if(res>0) {
			if(types.equals("下班早退")) {
				Salary salary = salaryService.getSalaryByMonthAndUidAndStatus(login_user.getId(), month,0);
				if(salary != null) {
					salary.setEarly(salary.getEarly()+50);
					salaryService.updateSalary(salary);
				}else {
					Employee employee = employeeService.getByEmployeeByUid(login_user.getId());
					Department department = departmentService.getByDepId(employee.getDepId());
					Salary salary2 = new Salary(0, 0, 0, 50, 0, login_user.getId(), 0, department.getBaseWage(), month,0);
					salaryService.insertSalary(salary2);
				}
			}
			if(types.equals("旷工")) {
				Salary salary = salaryService.getSalaryByMonthAndUidAndStatus(login_user.getId(), month,0);
				if(salary != null) {
					salary.setAbsenteeism(salary.getAbsenteeism()+100);
					salaryService.updateSalary(salary);
				}else {
					Employee employee = employeeService.getByEmployeeByUid(login_user.getId());
					Department department = departmentService.getByDepId(employee.getDepId());
					Salary salary2 = new Salary(0, 0, 0, 0, 100, login_user.getId(), 0, department.getBaseWage(), month,0);
					salaryService.insertSalary(salary2);
				}
			}
			return "success";
		}
		return "error";
	}
	
	//查看自己的打卡记录
	@RequestMapping("showSelfRecords.do")
	@ResponseBody
	public String showSelfRecords() {
		System.out.println(login_user);
		List<Records> list = recordsService.getByRecordsUid(login_user.getId());
		Object json = JSON.toJSON(list);
		return ""+json;
	}
	
	//用户退出
	@RequestMapping("exit.do")
	public String exit() {
		login_user = null;
		return "redirect:userGo.do";
	}
}
