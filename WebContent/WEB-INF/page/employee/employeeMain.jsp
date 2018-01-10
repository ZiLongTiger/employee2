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
	$("a[name='selfMsg']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showEmployee").empty();
		$("#btns").hide();
		var url = "${pageContext.request.contextPath}/user/showSelfMsg.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   success: function(msg){
			     $(msg).each(function(){
			    	 var name = this.curriculumvitae.realName;
			    	 var phone = this.phone;
			    	 var email = this.email;
			    	 var gender = this.curriculumvitae.gender;
			    	 var depName = this.dept.depName;
			    	 var posName = this.postion.posName;
			    	 var statis = (this.status == 0?"试用期 ":this.status == 1?"正式员工":"离职员工");
			    	 var record = this.record;
			    	 $("#showSelf").html("<table border='1px' cellpadding='5px' cellspacing='0px' width='600px' align='center'>"+
			    			 "<tr><td align='right'>姓名：</td><td>"+name+"</td><td align='right'>性别：</td><td>"+gender+"</td></tr>"+
			    			 "<tr><td align='right'>联系方式：</td><td>"+phone+"</td><td align='right'>邮箱：</td><td>"+email+"</td></tr>"+
			    			 "<tr><td align='right'>所在部门：</td><td>"+depName+"</td><td align='right'>就职岗位：</td><td>"+posName+"</td></tr>"+
			    			 "<tr><td align='right'>就职状态：</td><td>"+statis+"</td><td align='right'>录用时间：</td><td>"+record+"</td></tr>"+
			    			 "<tr><td colspan='4' align='center'><a href='${pageContext.request.contextPath}/user/goUpdateEmpl.do'>修改个人信息</a></td></tr></table>");
			     });
			   }
			});
	});
	
	$("a[name='showDepAndPos']").click(function(){
		$("#showSelf").empty();
		$("#btns").hide();
		var url="${pageContext.request.contextPath}/user/showDepartMent.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   dataType: "json",
			   success: function(msg){
			     if(!msg.length > 0){
			    	 alert("还没有部门信息，敬请期待");
			    	 return;
			     }
			     $("#showOne").empty();
			     $("#showOne").append("部门：<br/>");
			     $(msg).each(function(){
			    	 var depName = this.depName;
			    	 $("#showOne").append("<a name='showPostion'>"+depName+"</a><br/>");
			     });
			     
			     $("a[name='showPostion']").click(function(){
			    	 var url="${pageContext.request.contextPath}/user/showPostion.do";
			    	 $("#showEmployee").empty();
			    	 var depName = $(this).text();
			    	 $.ajax({
			    		   type: "POST",
			    		   url: url,
			    		   data: {depName:depName},
			    		   dataType:"json",
			    		   success: function(msg){
			    			 if(!msg.length>0){
			    				 alert("该部门下还没有职位，敬请期待");
			    				 return;
			    			 }  
			    		     $("#show").empty();
			    		     $("#show").append("职位：<br/>");
			    		     $(msg).each(function(){
			    		    	 var posName = this.posName;
			    		    	 var posId = this.id;
			    		    	 var deptId = this.department.id;
			    		    	 var depName = this.department.depName;
			    		    	 $("#show").append("<span><input type='hidden' value='"+depName+"'/><input type='hidden' value='"+posId+"'/>"+
			    		    	"<input type='hidden' value='"+deptId+"'/><a name='showEmployee'>"+posName+"</a></span><br/>");
			    		     });
			    		     
			    		     $("a[name='showEmployee']").click(function(){
			    		    	 var url="${pageContext.request.contextPath}/admin/showEmployee.do";
						   		 var deptId = $(this).prev().val();
						   		 var posId = $(this).prev().prev().val();
						   		 var depName = $(this).prev().prev().prev().val();
						   		 var posName = $(this).text();
			    		    	 $.ajax({
			    		    		   type: "POST",
			    		    		   url: url,
			    		    		   data: {deptId:deptId,posId:posId},
			    		    		   dataType:"json",
			    		    		   success: function(msg){
			    		    		     if(!msg.length>0){
			    		    		    	 alert("你选择的"+depName+"的"+posName+"，暂未招到人");
			    		    		    	 return;
			    		    		     }
			    		    		     $("#showEmployee").empty();
								   		 $("#showEmployee").append("员工：<br/>");
			    		    		     $(msg).each(function(){
			    		    		    	 var emplName = this.realName;
			    		    		    	/*  var status = (this.status == 0?"试用期员工":this.status == 1?"正式员工":"离职员工"); */
			    		    		    	 $("#showEmployee").append("<span>"+emplName+"</span>");
			    		    		     });
			    		    		   }
			    		    		});
			    		     });
			    		   }
			    		});
			     });
			   }
			});
	});
	
	$("a[name='card']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showEmployee").empty();
		$("#showSelf").empty();
		$("#btns").show();
	});
	$("input[name='clockIn']").click(function(){
		Date date = new Date();
		var workShift = "09:00:00";
		
	});
});
</script>
</head>
<body>
<%@ include file="head.jsp" %>
<div id="main">
<ul id="daka">
	<li><a href="javascript:void(0)" name="card">打卡</a>
		<ul id="btns" style="display: none">
			<li><input type="button" name="clockIn" value="上班打卡"/></li>
			<li><input type="button" name="clockOut" value="下班打卡"/></li>
		</ul>
	</li>
</ul>&nbsp;&nbsp;
<a href="javascript:void(0)" name="selfMsg">查看个人信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:void(0)" name="showDepAndPos">部门职位</a>&nbsp;&nbsp;&nbsp;&nbsp;
<!-- <a href="">培训</a>&nbsp; -->
<a href="查看个人信息">薪资</a>
</div>
<div id="showOne"></div>
<div id="show"></div>
<div id="showEmployee"></div>
<div id="showSelf">
</div>
<%@ include file="foot.jsp" %>
</body>
</html>