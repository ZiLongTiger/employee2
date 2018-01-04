<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写简历</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$("#departMent").change(function(){
		var deptId = $(this).val();
		alert(deptId);
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/user/changePostion.do",
			   data: {depId:deptId},
			   dataType:"json",
			   success: function(msg){
				 var postion = $("#postion");
				 postion.empty();
				 postion.attr("disabled",false);
				 for(var i = 0; i < msg.length; i++){
					 postion.append("<option value='"+msg[i].posName+"'>"+msg[i].posName+"</option>");
				 }
			   }
			});
	});
});
</script>
</head>
<body>
<c:if test="${msg =='create'}">

	<form action="${pageContext.request.contextPath}/user/saveCurriculumvitae.do" method="post">
		<input type="hidden" value="${request.user.id}" name="userId"/>
		<table border="0px" cellpadding="5px" cellspacing="0px" width="600px" align="center">
			<caption>
				<h2>创建简历</h2>
				<input type="text" name="title" value="未命名的简历" onfocus="if (this.value == '未命名的简历') {this.value = '';this.style.color = 'black';}" onblur="if (this.value == '' || this == 0) {this.style.color = 'gray';this.value = '未命名的简历';}"/>
			</caption>
			<tr>
				<td align="right">姓名</td>
				<td><input type="text" name="userName" required="required" /></td>
				
				<td align="right">性别</td>
				<td>
					<input type="radio" value="男" name="gender" checked="checked"/>男
					<input type="radio" name="gender" value="女"/>女
				</td>
			</tr>
			
			<tr>
				<td align="right">兴趣爱好</td>
				<td><input type="text" name="hobby" /></td>
				
				<td align="right">年龄</td>
				<td><input type="number" name="age" required="required" /></td>
			</tr>
			
			<tr>
				<td align="right">联系方式</td>
				<td><input type="text" name="phone" required="required" /></td>
				
				<td align="right">邮箱</td>
				<td><input type="email" name="email" required="required" /></td>
			</tr>
			
			<tr>
				<td align="right">教育背景</td>
				<td><input type="text" name="education" required="required" /></td>
				
				<td align="right">政治面貌</td>
				<td><input type="text" name="politics" required="required" /></td>
			</tr> 
			
			<tr>
				<td align="right">应聘岗位</td>
				<td>
					<select name="departMent" id="departMent">
						<option value="0">--部门--</option>
						<c:forEach items="${requestScope.departMent}" var="dept">
							<option value="${dept.id}">${dept.depName}</option>
						</c:forEach>
					</select>
					
					<select name="postion" disabled="disabled" id="postion">
						<option value="0">--职位--</option>
					</select>
				</td>
				
				<td align="right">工作经验</td>
				<td><input type="text" name="experience" /></td>
			</tr>
			
			<tr>
				<td align="right">期望薪资</td>
				<td colspan="3"><input type="number" name="expectedSalary" required="required" /></td>
			</tr>
			
			<tr>
				<td align="right" valign="middle">自我描述</td>
				<td colspan="3">
					<textarea rows="4" cols="40" name="evaluation" style="resize: none"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="创建"/>
					<input type="reset" value="取消"/>
				</td>
			</tr>
		</table>
	</form>
</c:if>	

<c:if test="${msg =='update'}">
	<form action="${pageContext.request.contextPath}/user/saveCurriculumvitae.do" method="post">
		<table border="1px" cellpadding="5px" cellspacing="0px" width="600px" align="center">
			<caption><h2>创建简历</h2></caption>
			<tr>
				<td align="right">姓名</td>
				<td><input type="text" name="userName" required="required" /></td>
				
				<td align="right">性别</td>
				<td>
					<input type="radio" value="男" name="gender" checked="checked"/>男
					<input type="radio" name="gender" value="女"/>女
				</td>
			</tr>
			
			<tr>
				<td align="right">兴趣爱好</td>
				<td><input type="text" name="hobby" /></td>
				
				<td align="right">年龄</td>
				<td><input type="number" name="age" required="required" /></td>
			</tr>
			
			<tr>
				<td align="right">联系方式</td>
				<td><input type="text" name="phone" required="required" /></td>
				
				<td align="right">邮箱</td>
				<td><input type="email" name="email" required="required" /></td>
			</tr>
			
			<tr>
				<td align="right">教育背景</td>
				<td><input type="text" name="education" required="required" /></td>
				
				<td align="right">政治面貌</td>
				<td><input type="text" name="politics" required="required" /></td>
			</tr> 
			
			<tr>
				<td align="right">应聘岗位</td>
				<td>
					<select name="departMent" id="departMent">
						<option value="0">--部门--</option>
						<c:forEach items="${requestScope.departMent}" var="dept">
							<option value="${dept.depName}">${dept.depName}</option>
						</c:forEach>
					</select>
					
					<select name="postion" disabled="disabled" id="postion">
						<option value="0">--职位--</option>
					</select>
				</td>
				
				<td align="right">工作经验</td>
				<td><input type="text" name="experience" /></td>
			</tr>
			
			<tr>
				<td align="right">期望薪资</td>
				<td colspan="3"><input type="number" name="expectedSalary" required="required" /></td>
			</tr>
			
			<tr>
				<td align="right" valign="middle">自我描述</td>
				<td colspan="3">
					<textarea rows="4" cols="40" name="evaluation" style="resize: none"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="创建"/>
					<input type="reset" value="取消"/>
				</td>
			</tr>
		</table>
	</form>
</c:if>	
</body>
</html>