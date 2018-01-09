<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Curriculumvitae.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[name='title']").change(function(){
			var title = $(this).val();
			if(!title){
				 $("#errorMsg").text("标题不能为空");
			     $("#btnSubmit").attr("disabled",true);
			     return;
			}
			$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath}/admin/queryByTitle.do",
				   data: {title:title},
				   success: function(msg){
					   if(msg == "error"){
						   $("#errorMsg").text("标题已存在");
						   $("#btnSubmit").attr("disabled",true);
						   return;
					   }else if(msg == "success"){
						   $("#btnSubmit").attr("disabled",false);
					   }
				   }
					
				});
		});
	});
</script>
</head>
<body>
<%@ include file="head.jsp" %>
<c:if test="${msg == 'save'}">
<div id="frm1">
<form action="${pageContext.request.contextPath}/admin/saveRecruit.do" method="post">
<table cellpadding="5px" width="600px" cellspacing="0px" border="1px" align="center">
	<caption>
		title:<input type="text" name="title" value="未命名的招聘信息" onfocus="if (this.value == '未命名的招聘信息') {this.value = '';this.style.color = 'black';}" onblur="if (this.value == '' || this == 0) {this.style.color = 'gray';this.value = '未命名的招聘信息';}"/>
			<br/>
			<span id="errorMsg" style="color:red; font-size: 20;"></span>
	</caption>
	<tr>
		<td align="right" valign="middle">公司描述:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="companyDescription" required="required"></textarea>
			<!-- <input type="text" name="companyDescription"/> -->
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">职位需求:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="jobReq" requied="requied"></textarea>
			<!-- <input type="text" name="jobReq"/> -->
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">工作时间:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="workHours" requied="requied"></textarea>
		<!-- 	<input type="text" name="workHours"/> -->
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">福利待遇:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="benefits" required="required"></textarea>
		<!-- 	<input type="text" name="benefits"/> -->
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">公司地址:</td>
		<td>
			<input type="text" name="address" required="required"/>
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">公司电话:</td>
		<td>
			<input type="text" name="tel" required="required"/>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="发布"/>
		</td>
	</tr>
</table>
</form>
</div>
</c:if>
<c:if test="${msg == 'update'}">
<div id="frm">
<form action="${pageContext.request.contextPath}/admin/updateRecruit.do" method="post">
<table cellpadding="5px" width="600px" cellspacing="0px" border="1px" align="center">
	<caption>
		<input type="hidden" value="${data.id}" name="id" />
		title:<input type="text" value="${data.title}" name="title" />
		<br/>
		<span id="errorMsg" style="color:red; font-size: 20;"></span>
	</caption>
	<tr>
		<td align="right" valign="middle">公司描述:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="companyDescription" required="required">${data.companyDescription}</textarea>
			<%-- <input type="text" value="${data.companyDescription}" name="companyDescription"/> --%>
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">职位需求:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="jobReq" required="required">${data.jobReq}</textarea>
			<%-- <input type="text" value="${data.jobReq}" name="jobReq"/> --%>
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">工作时间:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="workHours" required="required">${data.workHours}</textarea>
			<%-- <input type="text" value="${data.workHours}" name="workHours"/> --%>
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">福利待遇:</td>
		<td>
			<textarea rows="4" cols="40" style="resize: none" name="benefits" required="required">${data.benefits}</textarea>
		<%-- 	<input type="text" value="${data.benefits}" name="benefits"/> --%>
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">公司地址:</td>
		<td>
			<input type="text" value="${data.address}" name="address" required="required"/>
		</td>
	</tr>
	
	<tr>
		<td align="right" valign="middle">公司电话:</td>
		<td>
			<input type="text" value="${data.tel}" name="tel" required="required"/>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="修改并发布"/>
		</td>
	</tr>
</table>
</form>
</div>
</c:if>
<%@ include file="foot.jsp" %>
</body>
</html>