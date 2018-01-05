<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自己的简历</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$("a[name='showSelfCurriculumvitae']").click(function(){
		var url = "${pageContext.request.contextPath}/user/queryByTitle2.do";
		var title = $(this).text();
		var show = $("#show");
		$.ajax({
		   type: "POST",
		   url: url,
		   data: {title:title},
		   dataType:"json",
		   success: function(msg){
			   show.html("<table border='1px' cellpadding='5px' cellspacing='0px' width='600px' align='center'>"
						+ "<caption>title："+msg[0].title+"</caption><tr>	<td align='right'>姓名</td>"+
							"<td>"+msg[0].realName+"</td><td align='right'>性别</td><td>"+msg[0].gender+"</td>"+
						"</tr><tr><td align='right'>兴趣爱好</td><td>"+msg[0].hobby+"</td>"+"<td align='right'>年龄</td>"
						+"<td>"+msg[0].age+"</td></tr><tr><td align='right'>联系方式</td><td>"+msg[0].phone+"</td>"+
						"<td align='right'>邮箱</td><td>"+msg[0].email+"</td></tr><tr><td align='right'>教育背景</td>"+
						"<td>"+msg[0].education+"</td><td align='right'>政治面貌</td><td>"+msg[0].politics+"</td></tr><tr>"+
						"<td align='right'>应聘岗位</td><td>"+msg[0].jobPostion+"</td><td align='right'>工作经验</td>"+
						"<td>"+msg[0].experience+"</td></tr><tr><td align='right'>期望薪资</td><td colspan='3'>"+msg[0].expectedSalary+"</td>"+
						"</tr><tr><td align='right' valign='middle'>自我描述</td><td colspan='3'>"+
						 msg[0].evaluation+ "</td></tr><tr><td colspan='4' align='center'><a href=${pageContext.request.contextPath}/user/querySingle.do?id="+msg[0].id+">修改</a></td></tr></table>");
		   }
		});
	});
	
	
	$("a[name='send']").click(function(){
		var req = "${pageContext.request.contextPath}/user/sendCurriculumvitae.do";
		var id = $(this).prev().val();
		$.ajax({
			   type: "POST",
			   url: req,
			   data: {id:id},
			   success: function(msg){
				 if(msg == "success"){
					 alert("投递成功");
				 }else if(msg == "error1"){
					 alert("你已经投递过了，请不要重复投递");
				 }
			   }
			});
	});
});
</script>
</head>
<body>
<c:if test="${empty SelfCurriculumvitae}">
	<h2>你还没有简历信息，是否<a href="${pageContext.request.contextPath}/user/create.do">立即创建</a></h2>
</c:if>
<div>

	<c:forEach items="${SelfCurriculumvitae}" var="Curriculumvitae">
		<span>
			<a name="showSelfCurriculumvitae" href="javascript:void(0)">${Curriculumvitae.title}</a>
			&nbsp;&nbsp;&nbsp;
			<c:if test="${msg =='go'}">
				<input type="hidden" name="id" value="${Curriculumvitae.id}"/>
				<a href="javascript:void(0)" name="send">投递</a>
			</c:if>
		</span>
		<br/><br/>
	</c:forEach>
</div>
<div id="show">
</div>
</body>
</html>