<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">

	$(function() {
		 $.ajax({
			   url: "${pageContext.request.contextPath}/admin/checkLogin.do",
			   type: "POST",
			   data: {param:"login"},
			   dataType:"json",
			   success: function(returnData){
				   if(returnData != "false"){
					   var msg = "欢迎您：" + returnData[0].name + " " + (returnData[0].role == 0 ? "用户" : returnData[0].role == 1 ?"管理员 ":"y工");
						
						$("#spLoginMsg").text(msg);
						$("#divLoginT").show();
						$("#divLoginF").hide();
				   }
			   }
		 });
	});
</script>

<div id="header">	
	<div id="header_menu">
		<!-- <div id="header_menu_left">
			<a href="">首页</a>
		</div> -->
	
		<div id="header_menu_right">
			<div id="divLoginF">
				<a href="javascript:void(0)">注册</a>
				
				<span>|</span>
				
		        <a href="${pageContext.request.contextPath}/admin/adminGo.do">登录</a>
			</div>
			
			<div id="divLoginT" style="display:none;">
				<span id="spLoginMsg"></span>
				
				<span>|</span>
				
		        <a href="javascript:void(0)">退出</a>
		        
		        <span>|</span>
				
		        <a href="memberCenter.jsp">用户中心</a>
			</div>
	        
	        <span>|</span>
	        
	        <a>帮助中心</a>
		</div>
	</div>

	<div id="header_middle">
		<div id="header_middle_left">
			<marquee id="marquee" behavior="alternate" direction="right">
				<img src='${pageContext.request.contextPath}/images/web/logo.jpg' height="75" onMouseOut="document.getElementById('marquee').start()" onMouseOver="document.getElementById('marquee').stop()"/>
			</marquee>	
		</div>
	</div>

	<div id="header_bottommenu">
		<a href="">首页</a>
		
		<span>|</span>
		
		<a href="javascript:void(0)">创建简历</a>
	</div>
</div>