<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ment.css">

</head>
<body>
<%@ include file="head.jsp" %>
<c:if test="${msg == 'saveDept'}">
<div id="one">
<form action="${pageContext.request.contextPath}/admin/saveDept.do" method="get">
请输入部门名称：<input type="text" name="deptName" required="required"/><br/><br/>
请输入基本工资:<input type="num" name="baseWage" required="required"/><br/><br/>
<input type="submit" value="新增部门">
</form>
</div>
</c:if>

<c:if test="${msg == 'updateDept'}">
<div id="one">
<form action="${pageContext.request.contextPath}/admin/updateDept.do" method="get">
<input type="hidden" value="${dept.id}" name="id"/>
请输入部门名称：<input type="text" value="${dept.depName}" name="deptName" required="required"/><br/><br/>
请输入基本工资:<input type="num" value="${dept.baseWage}" name="baseWage" required="required"/><br/><br/>
<input type="submit" value="修改部门">
</form>
</div>
</c:if>

<c:if test="${msg=='addPostion'}">
<div id="one">
<form action="${pageContext.request.contextPath}/admin/savePostion.do" method="get">
<input type="hidden" name="depId" value="${department.id}"/>
部门名称：<input type="text" value="${department.depName}" readonly="readonly"/><br/><br/>
添加职位:<input type="text" name="posName" required="required" /><br/><br/>
<input type="submit" value="添加职位名">
</form>
</div>
</c:if>

<c:if test="${msg=='updatePostion'}">
<div id="one">
<form action="${pageContext.request.contextPath}/admin/updatePostion.do" method="get">
<input type="hidden" name="posId" value="${postion.id}"/>
部门名称：<input type="text" value="${department.depName}" readonly="readonly"/><br/><br/>
添加职位:<input type="text" name="posName" value="${postion.posName}" required="required" /><br/><br/>
<input type="submit" value="修改职位名">
</form>
</div>
</c:if>
<%@ include file="foot.jsp" %>
</body>
</html>