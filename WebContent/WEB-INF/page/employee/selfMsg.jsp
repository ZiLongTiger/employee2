<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工修改个人信息</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user/updateEmployee.do" method="post">
		<table border="0px" cellpadding="5px" cellspacing="0px" width="600px" align="center">
			<tr>
				<input type="hidden" value="${empl.id}" name="id"/>
				<td align="right">姓名：</td>
				<td><input type="text" name="realName" value="${empl.curriculumvitae.realName}" readonly="readonly"/></td>
				<td align="right">性别：</td>
				<td><input type="text" name="gender" value="${empl.curriculumvitae.gender}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right">联系方式：</td>
				<td><input type="text" name="phone" value="${empl.phone}" required="required"/></td>
				<td align="right">邮箱：</td>
				<td><input type="email" name="email" value="${empl.email}" required="required"/></td>
			</tr>
			<tr>
				<td align="right">所在部门：</td>
				<td><input type="text" name="depName" value="${empl.dept.depName}" readonly="readonly"/></td>
				<td align="right">就职岗位：</td>
				<td><input type="text" name="posName" value="${empl.postion.posName}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right">就职状态：</td>
				<td><input type="text" name="status" value="${empl.status == 0?'试用期 ':empl.status == 1?'正式员工':'离职员工'}" readonly="readonly"/></td>
				<td align="right">录用时间：</td>
				<td><input type="text" name="record" value="${empl.record}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="修改"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>