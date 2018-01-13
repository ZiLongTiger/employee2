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
					 $("table[name='one'] tr:gt(0)").empty();
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
					 $("table[name='one']").append(trs.join(""));
					 
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
	
	$("a[name='checkSalayBouns']").click(function(){
		$("#salaryPro").hide();
		$("#divTrain").hide();
		var url = "${pageContext.request.contextPath}/admin/checkSalayBouns.do";
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			success:function(msg){
				if(msg[0] == "no"){
					alert("没有异议信息。");
				}else{
					$("table[name='two'] tr:gt(0)").empty();
					var trs = [];
					$(msg).each(function(){
						var employee = this.employee;
						var bonus = this.bonus;
						 var tds = [];
						 tds.push("<td><input type='hidden' value='"+bonus.id+"'/><input type='hidden' value='"+employee.id+"'><input type='hidden' value='"+bonus.reward+"'><a name='showSalary'>" 
								 + employee.realName + "</a></td>");
						 tds.push("<td>" + bonus.introduce + "</td>");
						 tds.push("<td>" + bonus.reward + "</td>");
						 var tr = "<tr>" + tds.join("") + "</tr>";
						 trs.push(tr);
					});
					$("table[name='two']").append(trs.join(""));
		    		$("#tableId").show();
					
					$("a[name='showSalary']").click(function(){
						var url = "${pageContext.request.contextPath}/admin/showEmploySalary.do";
						var employeeId = $(this).prev().prev().val();
						var month = $(this).prev().val();
						var bonusId = $(this).prev().prev().prev().val();
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
										tds.push("<td><a name='no'>驳回</a>&nbsp;&nbsp;<a name='yes'>奖</a>&nbsp;&nbsp;<a name='non'>罚</a></td>");
										var tr = "<tr>" + tds.join("") + "</tr>";
										trs.push(tr);
									});
									$("table[name='three']").append(trs.join(""));
						    		$("#divSalary").show();
						    		var date = new Date();
						    		var year = date.getFullYear();
						    		var months = date.getMonth()+1;
						    		var month = year+"-"+months;
						    		//赏
						    		$("a[name='yes']").click(function(){
						    			$("#salaryPro").show();
						    			$("#salaryPro2").hide();
						    			$("input[name='suerThis']").click(function(){
						    				var bonus = $("input[name='salary']").val();
							    			var introduct = $("#introduct").val();
							    			if(!bonus){
							    				alert("请输入奖励金额");
							    				return;
							    			}
							    			if(!introduct){
							    				alert("请输入奖励理由。");
							    				return;
							    			}
						    				var url = "${pageContext.request.contextPath}/admin/isBonus.do";
							    			$.ajax({
							    				type:"POST",
							    				url:url,
							    				data:{bonusId:bonusId,bonus:bonus,introduct:introduct,month:month},
							    				success:function(msg){
							    					if(msg == "success"){
							    						alert("奖励成功。");
							    						$("#salaryPro").hide();
							    					}else{
							    						alert("奖励失败。");
							    					}
							    				}
							    			});
						    			});
						    		});
						    		//罚
						    		$("a[name='non']").click(function(){
						    			$("#salaryPro").hide();
						    			$("#salaryPro2").show();
						    			$("input[name='suerThis2']").click(function(){
						    				var bonus = $("input[name='salary2']").val();
							    			var introduct = $("#introduct2").val();
							    			if(!bonus){
							    				alert("请输入惩罚金额");
							    				return;
							    			}
							    			if(!introduct){
							    				alert("请输入惩罚理由。");
							    				return;
							    			}
						    				var url = "${pageContext.request.contextPath}/admin/isNotBonus.do";
							    			$.ajax({
							    				type:"POST",
							    				url:url,
							    				data:{bonusId:bonusId,bonus:bonus,introduct:introduct,month:month},
							    				success:function(msg){
							    					if(msg == "success"){
							    						alert("惩罚成功。");
							    						$("#salaryPro2").hide();
							    					}else{
							    						alert("惩罚失败。");
							    					}
							    				}
							    			});
						    			});
						    			
						    		});
						    		//驳回
						    		$("a[name='no']").click(function(){
						    			$("#salaryPro").hide();
						    			$("#salaryPro2").hide();
						    			$.ajax({
						    				type:"POST",
						    				data:{bonusId:bonusId},
						    				url:"${pageContext.request.contextPath}/admin/noBonus.do",
						    				success:function(msg){
						    					if(msg == "success"){
						    						alert("驳回成功");
						    						window.location.reload();
						    					}else if(msg == "repeat"){
						    						alert("请勿重复操作");
						    					}else{
						    						alert("驳回失败");
						    					}
						    				}
						    			});
						    		});
								}
							}
						});
					});
				}
			}
		});
	});
	
	$("input[name='coloseThis']").click(function(){
		$("#salaryPro").hide();
	});
	
	$("input[name='coloseThis2']").click(function(){
		$("#salaryPro2").hide();
	});
	
	$("a[name='employeeTrain']").click(function(){
		$("#tableId").hide();
		var url = "${pageContext.request.contextPath}/admin/showEmployeeAll.do";
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			success:function(msg){
				if(msg[0] == "no"){
					alert("还没有员工信息。");
				}else{
					$("table[name='showEmployee'] tr:gt(0)").empty();
					var trs = [];
					$(msg).each(function(){
						var realName = this.realName;
						var email = this.email;
						var phone = this.phone;
						var dept = this.dept;
						var postion = this.postion;
						var record = this.record;
						var status = this.status;
						var tds = [];
						tds.push("<td>" + realName + "</td>");
						tds.push("<td>" + phone + "</td>");
						tds.push("<td>" + email + "</td>");
						tds.push("<td>" + dept.depName + "</td>");
						tds.push("<td>" + postion.posName + "</td>");
						tds.push("<td>" + record + "</td>");
						tds.push("<td>" + (status == 0?"试用期":status == 0?"正式员工":"") + "</td>");
						tds.push("<td><a name='isTarin'>通知培训</a></td>");
						var tr = "<tr>" + tds.join("") + "</tr>";
						trs.push(tr);
					});
					$("table[name='showEmployee']").append(trs.join(""));
					$("#divTrain").show();
					$("a[name='isTarin']").click(function(){
						$("#insertTrain").show();
						$("input[name='trainTitle']").change(checkTrainTile);
					});
				}
			}
		});
	});
});

checkTrainTile = function(){
	var title = $("input[name='trainTitle']").val();
	var url = "${pageContext.request.contextPath}/admin/checkTrainTile.do";
	$.ajax({
		type:"POST",
		data:{title:title},
		url:url,
		success:function(msg){
			if(msg == "error"){
				 $("#errorMsg").text("培训标题已存在");
				 $("input[name='suerTrain']").attr("disabled",true);
				 return;
			}else if(msg == "success"){
				
				$("#errorMsg0").text("");
				$("#errorMsg0_1").text("该培训标题可用");
				$("input[name='suerTrain']").attr("disabled",false);
			}
		}
	});
}
</script>
</head>
<body>
<%@ include file="head.jsp" %>
<div id="main2">
	<a href="javascript:void(0)" name="message">面试通知</a>&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0)" name="employeeTrain">员工培训</a>&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0)" name="checkSalayBouns">工资异议</a>
</div>

<div id="tableId" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center' name="two">
		<tr>
			<th width="200px">员工姓名</th>
			<th width="200px">异议理由</th>
			<th width="200px">异议月份</th>
		</tr>
	</table>
	
	<div id="divSalary" style="display: none;">
		<table border='1px' cellpadding='5px' cellspacing='0px' align='center' name="three">
			<caption><h3>员工:<span id="emName3"></span>异议月份的薪资详情</h3></caption>
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
	
	<div id="salaryPro" style="display: none;">
		<table border="1px" cellpadding="5px" cellspacing="0px" align="center" width="400px">
			
			<tr>
				<td align="right">请输入奖励金额：</td>
				<td>
					<input type="number" name="salary"/>
				</td>
			</tr>
			
			
			<tr>
				<td align="right">请输入奖励理由：</td>
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
	
	<div id="salaryPro2" style="display: none;">
		<table border="1px" cellpadding="5px" cellspacing="0px" align="center" width="400px">
			
			<tr>
				<td align="right">请输入惩罚金额：</td>
				<td>
					<input type="number" name="salary2"/>
				</td>
			</tr>
			
			
			<tr>
				<td align="right">请输入惩罚理由：</td>
				<td>
					<textarea rows="5" cols="20" id="introduct2" style="resize: none"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="suerThis2" value="确认"/>&nbsp;&nbsp;&nbsp;
					<input type="button" name="coloseThis2" value="关闭"/>
				</td>
			</tr>
		</table>
	</div>
</div>

<div id="divTable" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center' name="one">
		<tr>
			<th width="200px">面试者</th>
			<th width="200px">面试时间</th>
			<th width="100px">操作</th>
		</tr>
	</table>
</div>

<div id="divTrain" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center' name="showEmployee">
		<tr>
			<th width="70px">员工姓名</th>
			<th width="12px">联系方式</th>
			<th width="120px">EMAIL</th>
			<th width="70px">所在部门</th>
			<th width="70px">任职岗位</th>
			<th width="150px">就职时间</th>
			<th width="70px">员工状态</th>
			<th width="70px">操作</th>
		</tr>
	</table>
	<div id="insertTrain" style="display: none;">
		<table border='1px' cellpadding='5px' cellspacing='0px' align='center' width="600px">
			<tr>
				<td align="right">培训标题：</td>
				<td><input type="text" value="请输入培训标题" onfocus="if (this.value == '请输入培训标题') {this.value = '';this.style.color = 'black';}" onblur="if (this.value == '' || this == 0) {this.style.color = 'gray';this.value = '请输入培训标题';}" 
						style="color:gray; margin:5px 0px;" name="trainTitle"/>
						<span id="errorMsg0" style="color:red; font-size: 20;"></span>
						<span id="errorMsg0_1" style="color:green; font-size: 20;"></span>
				</td>
			</tr>
			
			<tr>
				<td align="right">培训内容：</td>
				<td><textarea rows="5" cols="20" id="introduct2" style="resize: none"></textarea></td>
			</tr>
			
			<tr>
				<td align="center" colspan="2">
					<input type="button" name="suerTrain" value="确认" />
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="crr"></div>
<%@ include file="foot.jsp" %>
</body>
</html>