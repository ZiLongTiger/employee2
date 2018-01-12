<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mianPage.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	var url = "${pageContext.request.contextPath}/admin/checkMessage.do";
	$.ajax({
		  type: "post",
		  url:url,
		  dataType: "text",
		  success:function(msg){
			  if(msg == "success"){
				  alert("您有一份面试消息请在查通知管理查看");
			  }
		  }
		});
	
	$("a[name='message']").click(function(){
		$("#show").empty();
		var url = "${pageContext.request.contextPath}/admin/message.do";
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
						 var realName = this.curriculumvitae.realName;
						 var confirm = this.offer.confirm;//面试时间
						 var id = this.curriculumvitae.id;
						 var offerId = this.offer.id;
						 var tds = [];
						 tds.push("<td>" + realName + "</td>");
						 tds.push("<td>" + confirm + "</td>");
						 tds.push("<td><input type='hidden' value='"+id+"'><input type='hidden' value='"+offerId+"'><a name='showCrru'>查看详情</a></td>");
						 var tr = "<tr>" + tds.join("") + "</tr>";
						 trs.push(tr);
					 });
					 $("table").append(trs.join(""));
					 
					 $("a[name='showCrru']").click(function(){
						$("#divTable").hide();
						
					 	var url = "${pageContext.request.contextPath}/admin/queryCurrById.do";
					 	var id = $(this).prev().prev().val();
					 	var offerId = $(this).prev().val();
					 	$.ajax({
					 		   type: "POST",
					 		   url: url,
					 		   data: {id:id , offerId:offerId},
					 		   dataType:"json",
					 		   success: function(msg){
					 			  $("#crr").html("<table border='1px' cellpadding='5px' cellspacing='0px' width='600px' align='center'>"
					 						+ "<td align='right'>姓名</td>"+
					 							"<td>"+msg[0].realName+"</td><td align='right'>性别</td><td>"+msg[0].gender+"</td>"+
					 						"</tr><tr><td align='right'>兴趣爱好</td><td>"+msg[0].hobby+"</td>"+"<td align='right'>年龄</td>"
					 						+"<td>"+msg[0].age+"</td></tr><tr><td align='right'>联系方式</td><td>"+msg[0].phone+"</td>"+
					 						"<td align='right'>邮箱</td><td>"+msg[0].email+"</td></tr><tr><td align='right'>教育背景</td>"+
					 						"<td>"+msg[0].education+"</td><td align='right'>政治面貌</td><td>"+msg[0].politics+"</td></tr><tr>"+
					 						"<td align='right'>应聘岗位</td><td>"+msg[0].jobPostion+"</td><td align='right'>工作经验</td>"+
					 						"<td>"+msg[0].experience+"</td></tr><tr><td align='right'>期望薪资</td><td colspan='3'>"+msg[0].expectedSalary+"</td>"+
					 						"</tr><tr><td align='right' valign='middle'>自我描述</td><td colspan='3'>"+
					 						 msg[0].evaluation+ "</td></tr><tr><td colspan='4' align='center'><a name='mesg'>录用</a>&nbsp;&nbsp;&nbsp;<a name='mesg2'>不录用</a></td></tr></table>");
					 			  
					 			  $("a[name='mesg']").click(function(){
					 				  var url = "${pageContext.request.contextPath}/admin/saveEmployee.do";
					 				  $.ajax({
					 					   type: "POST",
					 					   url: url,
					 					   data: {id:id , offerId:offerId},
					 					   success: function(msg){
					 					    if(msg == "success"){
					 					    	alert("录用成功");
					 					    	$("#crr").hide();
					 					    }else if(msg == "no"){
					 					    	alert("请勿重复操作");
					 					    }else{
					 					    	alert("录用失败");
					 					    }
					 					   }
					 					});
					 				  
					 			  });
					 			  
					 			  $("a[name='mesg2']").click(function(){
					 				 var url = "${pageContext.request.contextPath}/admin/notSaveEmployee.do";
					 				  $.ajax({
					 					   type: "POST",
					 					   url: url,
					 					   data: {id:id , offerId:offerId},
					 					   success: function(msg){
					 					    if(msg == "success"){
					 					    	alert("请求已处理");
					 					    }else if(msg == "no"){
					 					    	alert("请勿重复操作");
					 					    }
					 					   }
					 					});
					 			  });
					 		   }
					 	});
					 });
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
<div id="main2">
	<a href="javascript:void(0)" name="message">通知管理</a>
</div>

<div id="divTable" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center'>
		<tr>
			<th width="200px">面试者</th>
			<th width="200px">面试时间</th>
			<th width="100px">操作</th>
		</tr>
	</table>
</div>

<div id="crr"></div>
<%@ include file="foot.jsp" %>
</body>
</html>