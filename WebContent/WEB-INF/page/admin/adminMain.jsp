<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminMain.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$("a[name='showPostion']").click(function(){
		var depName = $(this).text();
		var url = "${pageContext.request.contextPath}/admin/showPostion.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   data:{depName:depName},
			   dataType:"json",
			   success: function(msg){
			     var show = $("#show");
			     show.empty();
			     show.append("职位：<br/>");
			   	 for(var i=0;i<msg.length;i++){
			   		show.append("<a href='javascript:void(0)' name='showEmployee'>"+msg[i].posName+"</a><br/><br/>");
			   	 }
			   }
			});
	});
});
</script>
</head>
<body>
<%@ include file="head.jsp" %>
<c:if test="${user != null && user != ''}">欢迎“${user.name}”管理员的到来</c:if><br><br>
<div><a href="${pageContext.request.contextPath}/admin/showDepartMent.do">查看部门</a></div>
<c:if test="${!empty list}">
<div id="dept">
部门：<br>
<c:forEach items="${list}" var="dept">
	<a href="javascript:void(0)" name="showPostion">${dept.depName}</a>
	&nbsp;&nbsp;&nbsp;
	<a href="">解散部门</a>
	&nbsp;&nbsp;&nbsp;
	<a href="">修改部门</a>
	<br/><br/>
</c:forEach>
<a href="">新增部门</a>
</div>
</c:if>
<div id="show"></div>
<%@ include file="foot.jsp" %>
</body>
</html>