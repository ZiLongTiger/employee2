<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/showRecruid.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$("a[name='showRecruid']").click(function(){
		var url = "${pageContext.request.contextPath}/user/queryByRecruidTitle.do";
		var title = $(this).text();
		var show = $("#show");
		$.ajax({
		   type: "POST",
		   url: url,
		   data: {title:title},
		   dataType:"json",
		   success: function(msg){
			   show.html("<table border='0px' cellpadding='5px' cellspacing='0px' width='600px' align='center'><tr>"+
					   "<td>公司介绍：<br/>"+msg[0].companyDescription+"</td><tr><td>岗位要求：<br/>"+msg[0].jobReq+"</td><tr><td>工作时间：<br/>"+msg[0].workHours
					   +"</td><tr><td>福利待遇：<br/>"+msg[0].benefits+"</td><tr><td>公司地址及电话：<br/>"+msg[0].address+"<br/><br/>"+msg[0].tel+
					   "</td><tr><td><a href='${pageContext.request.contextPath}/admin/querySingleRecruit.do?id="+msg[0].id+"'>修改招聘信息</a></td><tr></tr></table>");
		   }
		});
	});
	
});
</script>

</head>
<body>
<%@ include file="head.jsp" %>
<div id="main">
	<c:forEach items="${data}" var="recruid">
		<a name="showRecruid" href="javascript:void(0)">${recruid.title}</a>
		&nbsp;&nbsp;&nbsp;
		<a name="showRecruid" href="${pageContext.request.contextPath}/admin/deleteRecruid.do?id=${recruid.id}">删除</a>
		<br/><br/>
	</c:forEach>
</div>
<div id="show">
</div>
<%@ include file="foot.jsp" %>
</body>
</html>