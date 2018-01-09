<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<c:if test="${msg == 'saveDept'}">
<form action="${pageContext.request.contextPath}/admin/saveDept.do" method="get">
请输入部门名称：<input type="text" name="deptName" requied="requied"/><br/><br/>
请输入基本工资:<input type="num" name="baseWage" requied="requied"/><br/><br/>
<input type="submit" value="新增部门">
</form>
</c:if>

<c:if test="${msg == 'updateDept'}">
<form action="${pageContext.request.contextPath}/admin/updateDept.do" method="get">
<input type="hidden" value="${dept.id}" name="id"/>
请输入部门名称：<input type="text" value="${dept.depName}" name="deptName" requied="requied"/><br/><br/>
请输入基本工资:<input type="num" value="${dept.baseWage}" name="baseWage" requied="requied"/><br/><br/>
<input type="submit" value="修改部门">
</form>
</c:if>

<c:if test="${msg=='addPostion'}">
<form action="${pageContext.request.contextPath}/admin/savePostion.do" method="get">
<input type="hidden" name="depId" value="${department.id}"/>
部门名称：<input type="text" value="${department.depName}" readonly="readonly"/><br/><br/>
添加职位:<input type="text" name="posName" requied="requied" /><br/><br/>
<input type="submit" value="添加职位名">
</form>
</c:if>

<c:if test="${msg=='updatePostion'}">
<form action="${pageContext.request.contextPath}/admin/updatePostion.do" method="get">
<input type="hidden" name="posId" value="${postion.id}"/>
部门名称：<input type="text" value="${department.depName}" readonly="readonly"/><br/><br/>
添加职位:<input type="text" name="posName" value="${postion.posName}" requied="requied" /><br/><br/>
<input type="submit" value="修改职位名">
</form>
</c:if>
</body>
</html>