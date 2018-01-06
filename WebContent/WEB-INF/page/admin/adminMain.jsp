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
		var depName = $(this).text();
		var url = "${pageContext.request.contextPath}/admin/showDepartMent.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   success: function(msg){
			     var show = $("#showOne");
			     show.empty();
			     show.append("部门：<br/>");
			   	 for(var i=0;i<msg.length;i++){
			   		show.append("<a href='javascript:void(0)' name='showPostion'>"+msg[i].depName+"</a>&nbsp;&nbsp;&nbsp;"
			   		+"<a href='javascript:void(0)' name='delete'>解散部门</a>&nbsp;&nbsp;&nbsp;<a href=>修改部门</a><br/><br/>");
			   	 }
			   	 
			   	$("a[name='showPostion']").click(function(){
					var depName = $(this).text();
					var url = "${pageContext.request.contextPath}/admin/showPostion.do";
					$.ajax({
						   type: "POST",
						   url: url,
						   data:{depName:depName},
						   dataType:"json",
						   success: function(msg){
						     var show = $("#show");
						     show.empty();
						     show.append("职位：<br/>");
						   	 for(var i=0;i<msg.length;i++){
						   		show.append("<a href='javascript:void(0)' name='showEmployee'>"+msg[i].posName+"</a><br/><br/>");
						   	 }
						   }
						});
				});
			   	
			   	$("a[name='delete']").click(function(){
					var depName = $(this).text();
					var url = "${pageContext.request.contextPath}/admin/deleteDept.do";
					$.ajax({
						   type: "POST",
						   url: url,
						   dataType:"json",
						   success: function(msg){
						     var show = $("#show");
						     show.empty();
						     show.append("职位：<br/>");
						   	 for(var i=0;i<msg.length;i++){
						   		show.append("<a href='javascript:void(0)' name='showEmployee'>"+msg[i].posName+"</a><br/><br/>");
						   	 }
						   }
						});
				});
			   }
			});
	});
	
	$("a[name='showOffer']").click(function(){
		$("#show").empty();
		$("#showOne").empty();
		$("#showCurr").empty();
		var url = "${pageContext.request.contextPath}/admin/showOffer.do";
		$.ajax({
		   type: "POST",
		   url: url,
		   dataType:"json",
		   success: function(msg){
			 var show = $("#divTable");
			 show.show();
			 $("table tr:gt(0)").empty();
			 var trs = [];
			 $(msg).each(function(){
				 var id = this.id;
				 var cid = this.curId;
				 var uid = this.uid; //面试者
				 var delivery = this.delivery;//投递时间
				 var statics = (this.statics==0?"未查看":"已查看");//简历是否被查看
			     var interview = (this.interview==0?"未面试":this.interview==1 ? "面试通过":"面试失败");//面试状态
				 var tds = [];
				 tds.push("<td>" + uid + "</td>");
				 tds.push("<td>" + delivery + "</td>");
				 tds.push("<td>" + statics + "</td>");
				 tds.push("<td>" + interview + "</td>");
				 tds.push("<td><input type='hidden' value='"+id+"'/><input type='hidden' value='"+cid+"'/><a name='showCrues'>查看</a>&nbsp;&nbsp;&nbsp;<a name='deleteCrue'>删除</a></td>");
				 var tr = "<tr>" + tds.join("") + "</tr>";
				 trs.push(tr);
			 });
		   
		     $("table").append(trs.join(""));
		     
		    $("a[name='deleteCrue']").click(function(){
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
		    });
		     
		     $("a[name='showCrues']").click(function(){
		    	$("#divTable").hide();
		 		var url = "${pageContext.request.contextPath}/admin/queryCurrById.do";
		 		var id = $(this).prev().val();
		 		var offerId = $(this).prev().prev().val();
		 		
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
		 		   }
		 		});
		 	});
		  }
		});
	});
});
</script>
</head>
<body>
<%@ include file="head.jsp" %>
<div id="text">
<a href="javascript:void(0)" name="showDept">查看部门</a>&nbsp;&nbsp;&nbsp;
<a href="">新增部门</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/admin/goSaveRecruit.do">发布新招聘信息</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/admin/goRecruid.do">查看发布的招聘信息</a>&nbsp;&nbsp;&nbsp;
<a href="javascript:void(0)" name="showOffer">应聘管理</a>
</div>
<div id="showOne"></div>
<div id="show"></div>
<div id="showCurr"></div>
<div id="divTable" style="display: none;">
	<table border='1px' cellpadding='5px' cellspacing='0px' align='center'>
		<tr>
			<th width="50px">应聘者</th>
			<th width="200px">投递时间</th>
			<th width="100px">查看状态</th>
			<th width="100px">面试状态</th>
			<th width="100px">操作</th>
		</tr>
	</table>
</div>
<%@ include file="foot.jsp" %>
</body>
</html>