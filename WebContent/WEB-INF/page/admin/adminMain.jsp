<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$("a[name='showDept']").click(function(){
		$("#divTable").hide();
		$("#showCurr").empty();
		$("#showEmpl").empty();
		
		$("#mianshi").empty();
		var depName = $(this).text();
		var url = "${pageContext.request.contextPath}/admin/showDepartMent.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   success: function(msg){
				 if(!msg.length>0){
					 alert("暂时还没有部门信息，去试试新增部门吧");
					 return;
				 }
			     var show = $("#showOne");
			     show.empty();
			     show.append("部门：<br/>");
			   	 for(var i=0;i<msg.length;i++){
			   		show.append("<span><input type='hidden' value='"+msg[i].id+"'><a href='javascript:void(0)' name='showPostion'>"+msg[i].depName+"</a>&nbsp;&nbsp;&nbsp;"
			   		+"<a href='javascript:void(0)' name='delete'>解散部门</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/admin/queruyDeptById.do?id="+msg[i].id+"'>修改部门</a><span><br/><br/>");
			   	 }
			   	 
			   	$("a[name='showPostion']").click(function(){
			   		$("#showEmployee").empty();
					var depName = $(this).text();
					var id = $(this).prev().val();
					var url = "${pageContext.request.contextPath}/admin/showPostion.do";
					$.ajax({
						   type: "POST",
						   url: url,
						   data:{depName:depName},
						   dataType:"json",
						   success: function(msg){
							 if(msg.length>0){
								 var show = $("#show");
							     show.empty();
							     show.append("职位：<br/>");
							   	 for(var i=0;i<msg.length;i++){
							   		show.append("<span><input type='hidden' value='"+msg[i].id+"'/><input type='hidden' value='"+msg[i].department.id+"'/>"+
							   				"<a href='javascript:void(0)' name='showEmployee'>"+msg[i].posName+"</a>&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/admin/goSavePostion.do?depId="+msg[i].department.id+"'>新增职位</a>"
							   		+"&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/admin/querySingle.do?posId="+msg[i].id+"&depId="+msg[i].department.id+"'>修改职位</a>&nbsp;&nbsp;<input type='hidden' value='"+msg[i].id+"'/><a name='delpostion'>删除职位</a><br/><br/><span>");
							   	 }
							   	 $("a[name='showEmployee']").click(function(){
							   		var url="${pageContext.request.contextPath}/admin/showEmployee.do";
							   		var deptId = $(this).prev().val();
							   		var posId = $(this).prev().prev().val();
							   		$.ajax({
							   		   type: "POST",
							   		   url: url,
							   		   data: {deptId:deptId,posId:posId},
							   		   dataType: "json",
							   		   success: function(msg){
							   			if(msg.length > 0){
							   				$("#showEmployee").empty();
								   			$("#showEmployee").append("员工：<br/>");
								   			 $(msg).each(function(){
								   				 var id = this.id;
								   				 var name = this.realName;
								   				 var status = this.status;
								   				 var sta = (status == 0?"试用期员工":status == 1?"正式员工":"离职员工");
								   				 $("#showEmployee").append("<span>"+name+ " " + sta + "</span><br/>"); 
								   			 });
							   			}else{
							   				$("#showEmployee").empty();
								   			$("#showEmployee").append("<br>该部门的职位下还没有员工信息");
							   			}
							   		   }
							   		});
							   	 });
							   	$("a[name='delpostion']").click(function(){ 
							   		var posName = $(this).prev().prev().prev().prev().text();
							   		var id = $(this).prev().val();
							   		var url="${pageContext.request.contextPath}/admin/delPostion.do";
							   		var span = $(this).parent();
							   		if(confirm("你确定删除职位"+posName+"吗？")){
							   			$.ajax({
											   type: "POST",
											   url: url,
											   data:{id:id},
											   dataType:"text",
											   success:function(msg){
												   if(msg == "success"){
													   span.remove();
													   alert("删除成功");
													}else if(msg == "didnot"){
														alert("该职位下还有员工存在，暂不可删除");
													}else{
														alert("删除失败");
													}
											   }
							   			});
							   		}
							   		return false;
							   	});
							   	 
							 }else{
								 var show = $("#show");
								 show.empty();
							     show.append("<br/>这是一个新的部门，<br/>还没有安排职位信息<br/>，是否<a href='${pageContext.request.contextPath}/admin/goSavePostion.do?depId="+id+"'>新增职位</a>信息");
							 }
						   
						   }
						});
				});
			   	
			   	$("a[name='delete']").click(function(){
					var depName = $(this).prev().text();
					var span = $(this).parent();
					if(confirm("你确定解散部门"+depName+"吗？")){
						var url = "${pageContext.request.contextPath}/admin/deleteDept.do";
						$.ajax({
							   type: "POST",
							   url: url,
							   data:{depName:depName},
							   dataType:"text",
							   success: function(msg){
								   if(msg == "success"){
									   span.remove();
									   alert(depName + "已解散");
								   }else if(msg == "not"){
									   alert("该部门下还有员工存在，暂不可解散");
								   }else{
									   alert(depName + "还不能解散");
								   }
								   
							   }
							});
					}
					return false;
					
				});
			   }
			});
	});
	
	$("a[name='showOffer']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showEmployee").empty();
		$("#showCurr").empty();
		$("#mianshi").empty();
		$("#showEmpl").empty();
		var url = "${pageContext.request.contextPath}/admin/showOffer.do";
		$.ajax({
		   type: "POST",
		   url: url,
		   dataType:"json",
		   success: function(msg){
			 var show = $("#divTable");
			 show.show();
			 $("table[name='two'] tr:gt(0)").empty();
			 var trs = [];
			 $(msg).each(function(){
				 var id = this.id;
				 var cid = this.curId;
				 var uid = this.uid; //面试者
				 var delivery = this.delivery;//投递时间
				 var confirm = this.confirm;//面试时间
				 var statics = (this.statics==0?"未查看":"已查看");//简历是否被查看
			     var interview = (this.interview==0?"未面试":this.interview==1 ? "面试通过":this.interview==3 ? "已录用":"面试失败");//面试状态
				 var tds = [];
				 tds.push("<td>" + uid + "</td>");
				 tds.push("<td>" + delivery + "</td>");
				 tds.push("<td>" + statics + "</td>");
				 tds.push("<td>" + interview + "</td>");
				 tds.push("<td><input type='hidden' value='"+confirm+"'/><input type='hidden' value='"+id+"'/><input type='hidden' value='"+cid+"'/><a name='showCrues'>查看</a>&nbsp;&nbsp;&nbsp;<a name='deleteCrue'>删除</a></td>");
				 var tr = "<tr>" + tds.join("") + "</tr>";
				 trs.push(tr);
			 });
		   
		     $("table[name='two']").append(trs.join(""));
		     
		    $("a[name='deleteCrue']").click(function(){
		    	if(confirm("你确定删除这条应聘消息吗？")){
			    	var offerId = $(this).prev().prev().prev().val();
			    	var tr = $(this).parent().parent();
			    	 $.ajax({
			    	  type: "POST",
			    	  url: "${pageContext.request.contextPath}/admin/deleteOffer.do",
			    	  data: {offerId:offerId},
			    	  success:function(data){
			    		  if(data == "success"){
			    			  alert("删除成功");
			    			  tr.remove();
			    		  }else{
			    			  alert("删除失败");
			    		  }
			    	  }
			    	});
		    	 }
		    	return false;
		    });
		   
		     
		     $("a[name='showCrues']").click(function(){
		    	$("#divTable").hide();
		 		var url = "${pageContext.request.contextPath}/admin/queryCurrById.do";
		 		var id = $(this).prev().val();
		 		var offerId = $(this).prev().prev().val();
		 		var interview = $(this).parent().prev().text();
		 		var show = $("#showCurr");
		 		$.ajax({
		 		   type: "POST",
		 		   url: url,
		 		   data: {id:id , offerId:offerId},
		 		   dataType:"json",
		 		   success: function(msg){
		 			  
		 			   show.html("<table border='1px' cellpadding='5px' cellspacing='0px' width='600px' align='center'>"
		 						+ "<td align='right'>姓名</td>"+
		 							"<td>"+msg[0].realName+"</td><td align='right'>性别</td><td>"+msg[0].gender+"</td>"+
		 						"</tr><tr><td align='right'>兴趣爱好</td><td>"+msg[0].hobby+"</td>"+"<td align='right'>年龄</td>"
		 						+"<td>"+msg[0].age+"</td></tr><tr><td align='right'>联系方式</td><td>"+msg[0].phone+"</td>"+
		 						"<td align='right'>邮箱</td><td>"+msg[0].email+"</td></tr><tr><td align='right'>教育背景</td>"+
		 						"<td>"+msg[0].education+"</td><td align='right'>政治面貌</td><td>"+msg[0].politics+"</td></tr><tr>"+
		 						"<td align='right'>应聘岗位</td><td>"+msg[0].jobPostion+"</td><td align='right'>工作经验</td>"+
		 						"<td>"+msg[0].experience+"</td></tr><tr><td align='right'>期望薪资</td><td colspan='3'>"+msg[0].expectedSalary+"</td>"+
		 						"</tr><tr><td align='right' valign='middle'>自我描述</td><td colspan='3'>"+
		 						 msg[0].evaluation+ "</td></tr><tr><td colspan='4' align='center'><a name='mesg'>通知其面试</a></td></tr></table>");
		 		   		$("a[name='mesg']").click(function(){
			 		   		$.ajax({
			 		   		   type: "POST",
			 		   		   url: "${pageContext.request.contextPath}/admin/checkMessage.do",
			 		   		   success: function(msg){
			 		   		     if(msg == "error"){
			 		   		    	$("#mianshi").html("<table border='1px' cellpadding='5px' cellspacing='0px' width='600px' align='center'>"
				 		   					+"<tr><td colspan='4' align='center'><input type='datetime-local' name='date' requied='requied'>"
				 		   					+"<input type='button' name='dataSuer' value='确认面试时间'></td></tr></table>");
			 		   		    $("input[name='dataSuer']").click(function(){
			 		   				var date = $("input[name='date']").val();
			 		   				var dateTime = date.split("T")[0]+" "+date.split("T")[1];
			 		   				var date1 = new Date(dateTime);
			 		   				var date2 = new Date();
			 		   				if(date1 < date2){
			 		   					alert("您要求的面试时间难以执行。");
			 		   					return;
			 		   				}else if(date1>date2){
			 		   					var url="${pageContext.request.contextPath}/admin/giveAnInterview.do";
				 		   				$.ajax({
				 		   				  type: "post",
				 		   				  url: url,
				 		   				  data:{date1:dateTime,offerId:offerId},
				 		   				  dataType: "text",
				 		   				  success:function(msg){
				 		   					  if(msg == "success"){
				 		   						 alert("面试通知已发出"); 
				 		   						 $("#mianshi").hide();
				 		   					  }else{
				 		   						  alert("面试通知发送失败");
				 		   					  }
				 		   				  }
				 		   				});
			 		   				}
			 		   			});
			 		   		     }else if(msg == "success"){
				 		   		     alert("已通知其面试了。");
			 		   		    	 $("#mianshi").hide();
			 		   		     }
			 		   		   }
			 		   		});
		 		   			
		 		   			
		 		   		});
		 		   }
		 		});
		 	});
		  }
		});
	});
	
	$("a[name='delEmpl']").click(function(){
		var url = "${pageContext.request.contextPath}/admin/delEmpl.do";
		var id = $(this).prev().val();
		var tr = $(this).parent().parent();
		var emName = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
		if(confirm("你确定开除员工"+emName+"吗？")){
			$.ajax({
				   type: "POST",
				   url: url,
				   data: {id:id},
				   success: function(msg){
				     if(msg == "success"){
				    	 alert("员工"+emName+"已被开除,记得将他最近的工资结算清楚");
				    	 tr.remove();
				     }else{
				    	 alert("开除失败");
				     }
				   }
				});
		}
		return false;
		
	});
	
	//员工调动
	$("a[name='emplPosDep']").click(function(){
		$("#showRecords").hide();
		$("#divSalary").hide();
		var id = $(this).prev().prev().prev().prev().val();
		var url = "${pageContext.request.contextPath}/admin/emplGo.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   data: {id:id},
			   dataType:"json",
			   success: function(msg){
				   $(msg).each(function(){
					    var dept = this.depList;
					    var emp = this.employee;
					    $("#emName").text(emp.realName);
					    $("input[name='empId']").val(emp.id);
					    var depId = emp.depId;
					    var possId = emp.posId;
					    $("#departMent").empty();
					    $(dept).each(function(){
					    	var deptId = this.id;
					    	var depName = this.depName;
					    	if(depId == deptId){
					    		$("#departMent").append("<option value='"+deptId+"' selected='selected'>"+depName+"</option>");
					    	}else{
					    		$("#departMent").append("<option value='"+deptId+"'>"+depName+"</option>");
					    	}
					    	
					    });
				    	var pos = this.posList;
				    	$("#postion2").empty();
				    	$(pos).each(function(){
						    var posId = this.id;
						   
						    var posName = this.posName;
						    if(possId == posId){
					    		$("#postion2").append("<option value='"+posId+"' selected='selected'>"+posName+"</option>");
					    	}else{
					    		$("#postion2").append("<option value='"+posId+"'>"+posName+"</option>");
					    	}
						});
				   });
				   $("#dp").show();
			     
			   }
			});
		
	});
	
	$("#departMent").change(function(){
		var deptId = $(this).val();
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/user/changePostion.do",
			   data: {depId:deptId},
			   dataType:"json",
			   success: function(msg){
				 var postion = $("#postion2");
				 postion.empty();
				 for(var i = 0; i < msg.length; i++){
					 postion.append("<option value='"+msg[i].id+"'>"+msg[i].posName+"</option>");
				 }
			   }
			});
	});
	
	$("input[name='sure']").click(function(){
		var eid = $(this).prev().prev().prev().val();
		var depId = $(this).prev().prev().val();
		var posId = $(this).prev().val();
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/admin/empChangeDP.do",
			   data: {eid:eid,depId:depId,posId:posId},
			   success: function(msg){
				   if(msg == "success"){
					   alert("调动成功");
					   $("#dp").hide();
					   window.location = "${pageContext.request.contextPath}/admin/queryAllEmployee.do";
				   }
			   }
			});
	});
	//查看考勤
	$("a[name='emRecords']").click(function(){
		$("#dp").hide();
		$("#divSalary").hide();
		var employeeId = $(this).prev().prev().val();
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/admin/queryRecordsByEid.do",
			   data: {employeeId:employeeId},
			   dataType:"json",
			   success: function(msg){
			    	$(msg).each(function(){
			    		var name = this.name;
			    		$("#emName2").text(name);
			    		var recordList = this.recList;
			    		$("table[name='first'] tr:gt(0)").empty();
						var trs = [];
			    		$(recordList).each(function(){
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
			    		 $("table[name='first']").append(trs.join(""));
			    		 $("#showRecords").show();
			    	});
			   }
			});
	});
	
	//查看上一月员工工资详情
	$("a[name='salary']").click(function(){
		$("#showRecords").hide();
		var date = new Date();
		var year = date.getFullYear();
		var months = date.getMonth()+1;
		var day = date.getDate();
		var employeeId = $(this).prev().prev().prev().val();
		if(months-1==0){
			months=12;
			year-=1;
		}
		var month= year+"-"+months;
		var url = "${pageContext.request.contextPath}/admin/showEmploySalary.do";
		$.ajax({
			type:"POST",
			url:url,
			data:{employeeId:employeeId,month:month},
			dataType:"json",
			success:function(msg){
				if(msg[0] == "no"){
					alert("本月还没有该员工的工资信息。");
				}else{
					$("table[name='three'] tr:gt(0)").empty();
					var trs = [];
					$(msg).each(function(){
						var salary = this.salary;
						var employee = this.employee;
						var bounsList = this.list;
						var bouns = 0;
						$(bounsList).each(function(){
							if(this.balance == 1){
								bouns += this.bonus;
							}else if(this.balance == 2){
								bonus -= this.bonus;
							}
						});
						$("#emName3").text(employee.realName);
						var tds = [];
						tds.push("<td>" + salary.baseWage + "</td>");
						tds.push("<td>" + bouns + "</td>");
						tds.push("<td>" + (salary.late==0?"0":"-"+salary.late) + "</td>");
						tds.push("<td>" + (salary.early==0?"0":"-"+salary.early) + "</td>");
						tds.push("<td>" + (salary.absenteeism==0?"0":"-"+salary.absenteeism) + "</td>");
						tds.push("<td>" + salary.evection + "</td>");
						tds.push("<td>" + (salary.baseWage+bouns-salary.late-salary.early-salary.absenteeism+salary.evection)+ "</td>");
						tds.push("<td><input type='hidden' value='"+employee.id+"'/><a name='endSalary'>工资发放</a></td>");
						var tr = "<tr>" + tds.join("") + "</tr>";
						trs.push(tr);
					});
					$("table[name='three']").append(trs.join(""));
		    		$("#divSalary").show();
		    		$("a[name='endSalary']").click(function(){
		    			var empId = $(this).prev().val();
		    			var total = $(this).parent().prev().text();
		    			if(day < 12){
		    				alert("每个月的12号发放前一个月的工资，现在还没到工资方法的日期。");
		    			}else if(day>12){
		    				alert("每个月的12号发放前一个月的工资，已经过了发放工资方法的日期。");
		    			}else{
		    				//工资结算
		    				var url = "${pageContext.request.contextPath}/admin/endSalary.do";
		    				if(months-1==0){
			    				months=12;
			    				year-=1;
			    			}
		    				month = year+"-"+months;
		    				$.ajax({
		    					type:"POST",
		    					url:url,
		    					data:{empId:empId,month:month,total:total},
		    					success:function(msg){
		    						if(msg == "success"){
		    							alert("工资发放成功");
		    						}else if(msg == "success"){
		    							alert("工资发放失败");
		    						}else{
		    							alert("工资已结算，请勿重复发放");
		    						}
		    					}
		    				});
		    			}
		    		});
				}
			}
		});
	});
});
</script>
</head>
<body>
<%@ include file="head.jsp" %>
<div>
<div id="text">
<a href="javascript:void(0)" name="showDept">查看部门</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/admin/goSaveDept.do">新增部门</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/admin/goSaveRecruit.do">发布新招聘信息</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/admin/goRecruid.do">查看发布的招聘信息</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/admin/queryAllEmployee.do">员工管理</a>&nbsp;&nbsp;&nbsp;
<a href="javascript:void(0)" name="showOffer">应聘管理</a>
</div>

<div id="showOne"></div>
<div id="show"></div>
<div id="showEmployee">
</div>
<div id="showCurr"></div>
<div id="mianshi"></div>
<div id="showEmpl">
	<c:if test="${!empty data}">
		
		<table border='1px' cellpadding='5px' cellspacing='0px' align='center'>
			<tr>
				<th>员工姓名</th>
				<th>联系方式</th>
				<th>EMAIL</th>
				<th>所在部门</th>
				<th>任职岗位</th>
				<th>就职时间</th>
				<th>员工状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${data}" var="emp">
				<tr>
					<td width="75px">${emp.realName}</td>
					<td width="100px">${emp.phone}</td>
					<td width="100px">${emp.email}</td>
					<td width="75px">${emp.dept.depName}</td>
					<td width="100px">${emp.postion.posName}</td>
					<td width="150px">${emp.record}</td>
					<td width="80px">${emp.status==0?'试用期 ':emp.status == 1?'正式员工':'离职员工'}</td>
					<td width="260px">
						<input type="hidden" value="${emp.id}" />
						<a href="javascript:void(0)" name="delEmpl">开除员工</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" name="emRecords">考勤</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" name="salary">查看上一月员工工资</a>&nbsp;&nbsp;
				 		<a href="javascript:void(0)" name="emplPosDep">职位调动</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<div id="divSalary" style="display: none;">
			<table border='1px' cellpadding='5px' cellspacing='0px' align='center' name="three">
				<caption><h3>你要查看的员工是:<span id="emName3"></span></h3></caption>
				<tr>
					<th width="70px">基本薪资</th>
					<th width="70px">奖惩总计</th>
					<th width="70px">员工迟到</th>
					<th width="70px">员工早退</th>
					<th width="70px">员工旷工</th>
					<th width="70px">社保</th>
					<th width="70px">总工资</th>
					<th width="70px">操作</th>
				</tr>
			</table>
		</div>
		
		
		<div id="showRecords" style="display: none;">
			
			<table border='1px' ellpadding='5px' cellspacing='0px' align='center' width="720" name="first">
				<caption><h3>你查看的人员是:<span id="emName2"></span></h3></caption>
；				<tr>
					<th>上班时间</th>
					<th>下班时间</th>
					<th>打卡类型</th>
				</tr>
			</table>
		</div>
		
		<div id="dp" style="display: none;">
			<table border='1px' ellpadding='5px' cellspacing='0px' align='center' width="720">
				<caption><h3>你要调动的人员是:<span id="emName"></span></h3></caption>
				<tr>
					<td colspan="4" align="center">
						<input type="hidden" name="empId"/>
						<select name="departMent" id="departMent">
						</select>
						
						<select name="postion" id="postion2">
						</select>
						
						<input type="button" name="sure" value="确认调动"/>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
</div>

<div id="divTable" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center' name="two">
		<tr>
			<th width="50px">应聘者</th>
			<th width="200px">投递时间</th>
			<th width="100px">查看状态</th>
			<th width="100px">面试状态</th>
			<th width="100px">操作</th>
		</tr>
	</table>
</div>
</div>
<%@ include file="foot.jsp" %>
</body>
</html>