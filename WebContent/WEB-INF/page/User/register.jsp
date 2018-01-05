<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	
	$("input[name='userName']").change(checkedName);
	$("#btnRegister").click(checkedRegister);
});
checkedName = function(){
	var name = $("input[name='userName']").val();
	$.ajax({
		url: "${pageContext.request.contextPath}/user/registerCheckName.do",
	    type: "POST",
	    data: {name:name},
	    success: function(data){
		   if(data == "error"){
			   $("#errorMsg0").text("用户已存在");
			   $("#btnRegister").attr("disabled",true);
			   return;
		   }else if(data == "success"){
			   $("#errorMsg0").text("");
			   $("#errorMsg0_1").text("该用户名可用");
			   $("#btnRegister").attr("disabled",false);
		   }
	    }
	});
}
checkedRegister = function(){
	var name = $("input[name='userName']").val();
	var password = $("input[name='password']").val();
	var regPassword = $("input[name='regPassword']").val();
	
	if(!name || name == "请输入用户名"){
		 $("#errorMsg0").text("请输入用户名");
		 return;
	}
	
	if(!password){
		 $("#errorMsg1").text("请输入密码");
		 return;
	}
	
	if(!regPassword){
		 $("#errorMsg2").text("请输入确认密码");
		 return;
	}
	
	if(password != regPassword){
		 $("#errorMsg2").text("两次输入的密码不一样");
		 return;
	}
	
	$.ajax({
		url: "${pageContext.request.contextPath}/user/register.do",
	    type: "POST",
	    data: {name:name,password:password},
	    success: function(data){
		   if(data == "error"){
			   $("#errorMsg").text("用户已存在");
			   return;
		   }else  if(data == "error2"){
			   $("#errorMsg").text("注册失败");
			   return;
		   }else{
			   window.location = "${pageContext.request.contextPath}/user/userGo.do";
		   }
	    }
	});
}

</script>
</head>
<body>
		<table cellpadding="5px" cellspacing="0px" align="center">
			<tr>
				<td align="right">请输入用户名：</td>
				<td><input type="text" value="请输入用户名" onfocus="if (this.value == '请输入用户名') {this.value = '';this.style.color = 'black';}" onblur="if (this.value == '' || this == 0) {this.style.color = 'gray';this.value = '请输入用户名';}" style="color:gray; margin:5px 0px;" name="userName"/>
					<span id="errorMsg0" style="color:red; font-size: 20;"></span>
					<span id="errorMsg0_1" style="color:green; font-size: 20;"></span>
				</td>
				
			</tr>
			
			<tr>
				<td align="right">请输入注册密码：</td>
				<td><input type="password" name="password"/><span id="errorMsg1" style="color:red; font-size: 20;"></span></td>
			</tr>
			
			<tr>
				<td align="right">请再次输入注册密码：</td>
				<td><input type="password" name="regPassword"/><span id="errorMsg2" style="color:red; font-size: 20;"></span></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" id="btnRegister" value="注册"/><br/>
					
					<span id="errorMsg" style="color:red; font-size: 20;"></span>
				</td>
			</tr>
		</table>
</body>
</html>