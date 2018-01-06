<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mianPage.css">
</head>
<body>
<%@ include file="head.jsp" %>
<div id="main">
<a href="${pageContext.request.contextPath}/user/create.do">创建简历</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/user/showSelf.do">查看个人简历</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/user/goRecruid.do">查看招聘信息</a>

<div>
	<img/>
</div>
</div>
<%@ include file="foot.jsp" %>
</body>
</html>