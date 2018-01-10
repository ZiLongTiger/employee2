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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	var url = "${pageContext.request.contextPath}/user/checkMessage.do";
	$.ajax({
		  type: "post",
		  url:url,
		  dataType: "text",
		  success:function(msg){
			  if(msg == "success"){
				  alert("您有一份面试通知请在查看消息中查看");
			  }
		  }
		});
	$("a[name='message']").click(function(){
		$("#bg").hide();
		var url = "${pageContext.request.contextPath}/user/message.do";
		$.ajax({
			  type: "post",
			  url:url,
			  dataType: "json",
			  success:function(msg){
				 if(msg.length > 0){
					 $("#divTable").show();
					 $("table tr:gt(0)").empty();
					 var trs = [];
					 $(msg).each(function(){
						 var delivery = this.delivery;//投递时间
						 var confirm = this.confirm;//面试时间
						 var statics = (this.statics==0?"未查看":"已查看");//简历是否被查看
						 
						 var tds = [];
						 tds.push("<td>" + delivery + "</td>");
						 tds.push("<td>" + statics + "</td>");
						 tds.push("<td>" + confirm + "</td>");
						 tds.push("<td>按时面试</td>");
						 var tr = "<tr>" + tds.join("") + "</tr>";
						 trs.push(tr);
					 });
					 $("table").append(trs.join(""));
				 }else{
					 alert("还没有面试通知");
				 }
			  }
			});
	});
	
});
</script>
</head>
<body>
<%@ include file="head.jsp" %>
<div id="main">
<a href="${pageContext.request.contextPath}/user/create.do">创建简历</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/user/showSelf.do">查看个人简历</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/user/goRecruid.do">查看招聘信息</a>
<a href="javascript:void(0)" name="message">查看消息</a>
<div id="bg">
	<img alt="背景" src="${pageContext.request.contextPath}/images/bg.jpg" style="width:1000px;heigth:400px">
</div>
</div>
<div id="divTable" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center'>
		<caption><h4>面试通知</h4></caption>
		<tr>
			<th width="200px">投递时间</th>
			<th width="100px">是否查看</th>
			<th width="200px">面试时间</th>
			<th width="100px">是否面试</th>
		</tr>
	</table>
</div>
<%@ include file="foot.jsp" %>
</body>
</html>