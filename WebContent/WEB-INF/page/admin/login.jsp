<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
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
				
				
				<input type="checkbox" id="cbAuto" style="margin:5px 0px;"/>
				30天内自动登录
				<br/>

				<input type="submit" id="btnLogin" value="登录" style="margin:5px 0px;"/>
				<br/>
				<br/>
				
				<span id="errorMsg" style="color:red; font-size: 20;"></span>
			</fieldset>
		</div>
</body>
</html>