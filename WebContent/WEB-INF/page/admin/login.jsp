<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"><base>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>

<script type="text/javascript">
$(function(){
	$("#btnLogin").click(login);
});

login = function(){
	var txtName = $("#txtName").val();
	var txtPwd = $("#txtPwd").val();
	if(!txtName || txtName == "管理员账号" || !txtPwd){
		$("#errorMsg").text("请输入正确的管理员账号或密码");
		return;
	}
	 $.ajax({
		   url: "${pageContext.request.contextPath}/admin/login.do",
		   type: "POST",
		   data: {name:txtName,password:txtPwd},
		   success: function(data){
			   if(data == "error"){
				   $("#errorMsg").text("请输入正确的管理员账号或密码");
			   }else if(data == "disappear"){
				   $("#errorMsg").text("无效的管理员账号");
			   }else if(data == "success"){
				   window.location = "";
			   }
		   }
		 });
}
</script>

<script type="text/javascript">
  $(document).ready(function () {
    if ($.cookie("rmbUser") == "true") {
    $("#remberPass").attr("checked", true);
    $("#txt_username").val($.cookie("username"));
    $("#txt_password").val($.cookie("password"));
    }
  });
 
  //记住用户名密码
  function Save() {
    if ($("#remberPass").attr("checked")) {
      var str_username = $("#txtName").val();
      var str_password = $("#txtPwd").val();
      $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
      $.cookie("username", str_username, { expires: 7 });
      $.cookie("password", str_password, { expires: 7 });
    }
    else {
      $.cookie("rmbUser", "false", { expire: -1 });
      $.cookie("username", "", { expires: -1 });
      $.cookie("password", "", { expires: -1 });
    }
  };
</script>
</head>
<body>
	<div id="main">
			<fieldset style="width:280px; float:right; margin-right:50px;">
				<legend>登录</legend>
				
				账&nbsp;&nbsp;&nbsp;&nbsp;号：
				<input type="text" id="txtName" value="管理员账号" onfocus="if (this.value == '管理员账号') {this.value = '';this.style.color = 'black';}" onblur="if (this.value == '' || this == 0) {this.style.color = 'gray';this.value = '管理员账号';}" style="width:200px; color:gray; margin:5px 0px;"/>
				<br/>  
				
				密&nbsp;&nbsp;&nbsp;&nbsp;码：
				<input type="password" id="txtPwd" style="width:200px; margin:5px 0px;"/>
				<br/>
				
				
				<input type="checkbox" id="remberPass"  checked="checked" style="margin:5px 0px;"/>
				记住密码
				<br/>

				<input type="submit" id="btnLogin" value="登录" style="margin:5px 0px;"/>
				<br/>
				<br/>
				
				<span id="errorMsg" style="color:red; font-size: 20;"></span>
			</fieldset>
		</div>
</body>
</html>