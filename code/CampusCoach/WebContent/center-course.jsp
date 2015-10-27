<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="yanjiasen4">

    <title>校园教练</title>

    <!-- Bootstrap core CSS -->
    <link href="style/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles -->
    <link href="style/css/style.css" rel="stylesheet">
	
	<script src="style/js/jquery-2.1.1.min.js"></script>
	<script src="style/js/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
	
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<ul class="nav nav-pills">
            	<% if(request.getSession().getAttribute("user") == null) {
            	%>
			    <li role="presentation"><a href="login.jsp">登录</a></li>
			    <%  
			    } 
            	else {
			    %> 
			    <li role="presentation"><a class="nav-brand" href="showusercourses.action" style="padding:0px"><img width="50px" alt="校园教练" src="${sessionScope.avatar}"></a></li>
			    <li role="presentation" class="active"><a href="showusercourses">${sessionScope.user}</a></li>
			    <li role="presentation"><a href="logout.action">注销</a></li>
			    <%  } %>
				<li role="presentation"><a href="register.jsp">注册</a></li>
				</ul>
		</div>
	</nav>
	
	<div class="header">
	  <h1>我的运动中心</h1>
	  <nav>
  		<ul class="pager">
    	<li><a href="showusercourses.action">培训班记录</a></li>
    	<li><a href="showuserreservation.action">需求记录</a></li>
    	<% if(request.getSession().getAttribute("coachName") != null) {
            	%>
			    <li><a href="showcoachcourses.action">我的课程</a></li>
			    <%  
			    } 
    	%>
    	<li><a href="user-setting.jsp">个人设置</a></li>
  		</ul>
	  </nav>
	</div>
	
	<div class="container-fluid user-inform">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<h2 class="page-header">我创建的课程</h2>	
			</div>
			
			<div class="container-fluid res-box">
			<div class="row">
				<s:iterator value="signUpList" var="it">
					<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="res-list"  data-toggle="modal" data-target="#<s:property value="key.courseID"/>">
							<div class="restitle">
								<h3><s:property value="key.sportsName"/></h3>
								<p><s:property value="key.introduction"/></p>
							</div>
							<div class="ressports">
								<span><s:property value="key.time"/></span>
							</div>
							<div class="resprice">
								<span><s:property value="key.price"/>￥/小时</span>
							</div>
							<div class="receive">
								<button type="button" class="btn btn-default">详情</button>
							</div>
						</div>
					</div>
				</s:iterator>
	    	</div>
			</div>
			
		</div>
	</div>
	
	<s:iterator value="signUpList" var="it">
	<div class="modal fade" id="<s:property value="key.courseID"/>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					
				</div>
			<div class="modal-body">
				<div class="res-detail">
				<p><h3>报名学员</h3>
					<s:iterator value="#it.value" var="list">
						<s:property value="#list.realName"/>
						<s:property value="#list.phoneNumber"/>
					</s:iterator>
					<div class="res-content">
						<p><s:property value="#it.introduction"/></p>
					</div>
				</div>
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" name="<s:property value="#it.reservationID"/>" onclick="receive(this)">确认</button>
				</div>
			</div>
		</div>
	</div>
	</s:iterator>
	
	
	
	<nav class="navbar navbar-inverse navbar-fixed-bottom">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html">校园教练</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="create-res.jsp">发布预约</a></li>
            <li><a href="showcoach.action">发现教练</a></li>
            <li><a href="showreservation.action">查看预约</a></li>
			<%if(request.getSession().getAttribute("coachID") == null){ %>
			<li><a href="becomecoach.jsp">成为教练</a></li>
			<%}
            else {%>
            <li><a href="coachform.jsp">创建课程</a></li>
            <%} %>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	
	<!-- Use tooltip -->
	<script>
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	})
	</script>
	</body>
</html>
