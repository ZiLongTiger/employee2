<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$("a[name='showPostion']").click(function(){
		var url = "${pageContext.request.contextPath}/admin/showPostion.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   success: function(msg){
			     var show = $("#show");
			   	 show.html();
			   }
			});
	});
});
</script>
</head>
<body>
<c:if test="${user != null && user != ''}">欢迎${user.name}的到来</c:if><br><br>
<a href="${pageContext.request.contextPath}/admin/showDepartMent.do">查看部门</a>
<c:forEach items="${list}" var="dept">
	<a href="javascript:void(0)" name="showPostion">${dept.depName}</a><br/><br/>
</c:forEach>
<div id="show"></div>
</body>
</html>