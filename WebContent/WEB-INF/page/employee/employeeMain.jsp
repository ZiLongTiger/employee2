<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		$("#showSelfRecords").hide();
		$("#btns").hide();
		$("#divSalary").hide();
		$("#showSelfBouns").hide();
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
		$("#divSalary").hide();
		$("#showSelfRecords").hide();
		$("#showSelfBouns").hide();
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
			    	 $("#divSalary").hide();
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
			    		    		    	 $("#showEmployee").append("<span>"+emplName+"</span><br/>");
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
	//打卡
	$("a[name='card']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showEmployee").empty();
		$("#showSelf").empty();
		$("#showSelfRecords").hide();
		$("#btns").show();
		$("#divSalary").hide();
		$("#showSelfBouns").hide();
		$.ajax({
			 type: "POST",
			 url: "${pageContext.request.contextPath}/user/goCheckClockIn.do",
			 success:function(msg){
				 if(msg == "yes"){
					 $("input[name='clockIn']").attr("disabled",true); 
				 }
			 }
		});
	});
	$("input[name='clockIn']").click(function(){
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var seconds = date.getSeconds();
		//上班打卡时间
		var workShift = year+"-"+month+"-"+day + " "+ hour +":"+minute+":"+seconds;
		//早上9点上班
		if(hour-9>3){
			alert("你上班打卡也太晚了吧");
			var types = "旷工";
			clockIn(workShift,types);
		}else if(hour-9 == 0){
			if(minute-0==0){
				alert("打卡成功，祝你工作顺利。");
				var types = "下班正常打卡";
				clockIn(workShift,types);
			}else if(minute-0 > 0){
				alert("迟到打卡成功，祝你工作顺利。");
				var types = "迟到打卡";
				clockIn(workShift,types);
			}
			
		}else if(hour-9 > 0 && hour-9 < 3){
			alert("迟到打卡成功，祝你工作顺利。");
			var types = "迟到打卡";
			clockIn(workShift,types);
		}else{
			alert("新的一天，早起的鸟儿有虫吃");
			var types = "上班正常打卡";
			clockIn(workShift,types);
		}
		
	});
	
	$("input[name='clockOut']").click(function(){
		$.ajax({
			 type: "POST",
			 url: "${pageContext.request.contextPath}/user/goCheckClockIn.do",
			 success:function(msg){
				 if(msg == "no"){
					 alert("请先上班打卡");
					 return; 
				 }
				
			 }
		});
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var seconds = date.getSeconds();
		//下班打卡时间
		var colsingTime = year+"-"+month+"-"+day + " "+ hour +":"+minute+":"+seconds;
		//晚上6点下班
		if(18-hour>3){
			if(confirm("你确定这么早就下班吗？")){
				var types = "旷工";
				clockOut(colsingTime,types);
			}
			return false;
		}else if(18-hour > 0){
			if(confirm("你确定要早退吗？")){
				var types = "下班早退";
				clockOut(colsingTime,types);
			}
			return false;
		}else{
			alert("辛苦一天了，愿你晚上有个好梦。");
			var types = "下班正常打卡";
			clockOut(colsingTime,types);
		}
	});
	
	$("a[name='showReCords']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showEmployee").empty();
		$("#showSelf").empty();
		$("#divSalary").hide();
		$("#showSelfBouns").hide();
		var url = "${pageContext.request.contextPath}/user/showSelfRecords.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   success: function(msg){
				  $("table[name='showRec'] tr:gt(0)").empty();
				  var trs = [];
				  $(msg).each(function(){
					     var clockIn = this.clockIn;
		    			 var clockOut = this.clockOut;
		    			 var types = this.types;
		    			 var tds = [];
						 tds.push("<td>" + clockIn + "</td>");
						 tds.push("<td>" + (clockOut == null ?"未打卡":clockOut) + "</td>");
						 tds.push("<td>" + types + "</td>");
						 var tr = "<tr>" + tds.join("") + "</tr>";
						 trs.push(tr);
				  });
				  $("table[name='showRec']").append(trs.join(""));
		    	  $("#showSelfRecords").show();
			   }
			});
	});
	//查看已发放的工资
	$("a[name='showSalary']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showEmployee").empty();
		$("#btns").hide();
		$("#showSelf").empty();
		$("#showSelfRecords").hide();
		$("#showSelfBouns").hide();
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var month= year+"-"+month;
		var url = "${pageContext.request.contextPath}/user/showSelefSalary.do";
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			success:function(msg){
				if(msg[0] == "no"){
					alert("你还没有薪资记录");
				}else{
					$("table[name='three'] tr:gt(0)").empty();
					var trs = [];
					$(msg).each(function(){
						var tds = [];
						tds.push("<td>" + this.month+ "</td>");
						tds.push("<td>" + this.total+ "</td>");
						tds.push("<td>" + this.baseWage + "</td>");
						tds.push("<td>" + (this.total-this.baseWage+this.late+this.early+this.absenteeism-this.evection) + "</td>");
						tds.push("<td>" + (this.late==0?"0":"-"+this.late) + "</td>");
						tds.push("<td>" + (this.early==0?"0":"-"+this.early) + "</td>");
						tds.push("<td>" + (this.absenteeism==0?"0":"-"+this.absenteeism) + "</td>");
						tds.push("<td>" + this.evection + "</td>");
						tds.push("<td><a name='salaryHasPro'>工资异议</a></td>");
						var tr = "<tr>" + tds.join("") + "</tr>";
						trs.push(tr);
					});
					$("table[name='three']").append(trs.join(""));
		    		$("#divSalary").show();
		    		
		    		$("a[name='salaryHasPro']").click(function(){
		    			$("#salaryPro").show();
		    			var time = $(this).parent().prev().prev().prev().prev().prev().prev().prev().prev().text();
		    		
		    			$("input[name='suerThis']").click(function(){
			    			var url="${pageContext.request.contextPath}/user/checkSalary.do";
			    			var introduct = $("#introduct").val();
			    			if(!introduct){
			    				alert("请输入你的异议理由。");
			    				return;
			    			}
			    			$.ajax({
			    				type:"post",
			    				url:url,
			    				data:{introduct:introduct,time:time},
			    				dataType:"text",
			    				success:function(msg){
			    					if(msg == "repeat"){
			    						alert("你已经异议过了，请静候佳音。");
			    					}else if(msg == "success"){
			    						alert("你的异议请求已发送。");
			    						$("#salaryPro").hide();
			    					}else{
			    						alert("你的异议请求发送失败。");
			    					}
			    				}
			    			});
			    		});
		    		});
				}
			}
		});
	});
	
	$("input[name='coloseThis']").click(function(){
		$("#salaryPro").hide();
	});
	
	//查看我的奖惩
	$("a[name='showBouns']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showEmployee").empty();
		$("#showSelfRecords").hide();
		$("#btns").hide();
		$("#divSalary").hide();
		var url = "${pageContext.request.contextPath}/user/showSelfBouns.do";
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			success:function(msg){
				if(msg[0] == "no"){
					alert("你还没有相关的奖惩记录。");
				}else{
					$("table[name='showBouns'] tr:gt(0)").empty();
					var trs = [];
					$(msg).each(function(){
						var createTime = this.createTime;
						var bonus = this.bonus;
						var introduce = this.introduce;
						var deletestatus = this.deletestatus;
						var balance = this.balance;
						var tds = [];
						tds.push("<td>" + createTime + "</td>");
						tds.push("<td>" + introduce + "</td>");
						tds.push("<td>" + bonus + "</td>");
						tds.push("<td>" + (deletestatus==0?"待处理":"已处理") + "</td>");
						tds.push("<td>" + (balance==1?"奖励":balance==2?"惩罚":"") + "</td>");
						var tr = "<tr>" + tds.join("") + "</tr>";
						trs.push(tr);
					});
					$("table[name='showBouns']").append(trs.join(""));
		    		$("#showSelfBouns").show();
				}
			}
		});
	});
});

function clockOut(time,types){
	var url = "${pageContext.request.contextPath}/user/clockOut.do";
	$.ajax({
		   type: "POST",
		   url: url,
		   data: {time:time,types:types},
		   success: function(msg){
			   if(msg == "success"){
			    	 $("input[name='clockIn']").attr("disabled",false); 
			   }else if(msg == "no"){
				   alert("别闹你还没打上班卡呢");
			   }
		   }
		});
}

function clockIn(time,types){
	var url = "${pageContext.request.contextPath}/user/clockIn.do";
	$.ajax({
		   type: "POST",
		   url: url,
		   data: {time:time,types:types},
		   success: function(msg){
		     if(msg == "success"){
		    	 $("input[name='clockIn']").attr("disabled",true); 
		     }
		   }
		});
}
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
<a href="javascript:void(0)" name="showReCords">考勤记录</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:void(0)" name="messageTrain">培训通知</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:void(0)" name="showSalary">薪资</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:void(0)" name="showBouns">查看奖惩详情</a>
</div>
<div id="showSelfRecords" style="display: none;">
	<table border="1px" cellpadding="5px" cellspacing="0px" align="center" name="showRec">
		<tr>
			<th width="150px">上班时间</th>
			<th width="150px">下班时间</th>
			<th width="150px">打卡类型</th>
		</tr>
	</table>
</div>

<div id="showSelfBouns" style="display: none;">
	<table border="1px" cellpadding="5px" cellspacing="0px" align="center" name="showBouns">
		<tr>
		 	<th width="150px">奖惩时间</th>
			<th width="150px">奖惩原因</th>
			<th width="150px">奖惩金额</th>
			<th width="150px">是否处理</th>
			<th width="150px">奖惩类型</th>
		</tr>
	</table>
</div>

<div id="divSalary" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center' name="three">
		<tr>
			<th width="120px">工资发放时间</th>
			<th width="70px">总工资</th>
			<th width="70px">基本薪资</th>
			<th width="70px">奖惩总计</th>
			<th width="70px">员工迟到</th>
			<th width="70px">员工早退</th>
			<th width="70px">员工旷工</th>
			<th width="70px">社保</th>
			<th width="70px">操作</th>
		</tr>
	</table>
	
	<div id="salaryPro" style="display: none;">
		<table border="1px" cellpadding="5px" cellspacing="0px" align="center" width="400px">
			<tr>
				<td align="right">请输入异议理由：</td>
				<td>
					<textarea rows="5" cols="20" id="introduct" style="resize: none"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="suerThis" value="确认"/>&nbsp;&nbsp;&nbsp;
					<input type="button" name="coloseThis" value="关闭"/>
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="showOne"></div>
<div id="show"></div>
<div id="showEmployee"></div>
<div id="showSelf">
</div>
<div id="showRes6">
<c:if test=""></c:if>
</div>

<%@ include file="foot.jsp" %>
</body>
</html>