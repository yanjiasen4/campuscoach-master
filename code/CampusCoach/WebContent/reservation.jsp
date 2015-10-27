<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			    <li role="presentation"><a href="showusercourses.action">${sessionScope.user}</a></li>
			    <li role="presentation"><a href="logout.action">注销</a></li>
			    <%  } %>
				<li role="presentation"><a href="register.jsp">注册</a></li>
				</ul>
		</div>
	</nav>
	
	<div class="jumbotron">
      <div class="container">
        <h1>校园教练</h1>
        <p>你身边的运动达人</p>
      </div>
    </div>
	
	<div class="header">
	  <h2>为苦苦寻找教练的学员解决困难吧！</h2>
	</div>
	
	<div class="container-fluid res-box">
		<div class="row">
			<s:iterator value="reservations" var="it">
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div class="res-list"  data-toggle="modal" data-target="#<s:property value="#it.reservationID"/>">
						<div class="restitle">
							<h3><s:property value="#it.reservationName"/></h3>
							<p><s:property value="#it.introduction"/></p>
						</div>
						<div class="ressports">
							<span><s:property value="#it.sportsName"/></span>
						</div>
						<div class="resprice">
							<span><s:property value="#it.price"/>￥/小时</span>
						</div>
						<div class="receive">
							<button type="button" class="btn btn-default" value="<s:property value="#it.reservationID"/>" onclick="receive(this)">申请</button>
						</div>
					</div>
				</div>
			</s:iterator>
	    </div>
	</div>
	
	<s:iterator value="reservations" var="it">
	<div class="modal fade" id="<s:property value="#it.reservationID"/>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel"><s:property value="it.reservationName"/></h4>
				</div>
			<div class="modal-body">
				<div class="res-detail">
					<div class="res-title">
						<span>运动类型：<s:property value="#it.sportsName"/></span>
					</div>
					<div class="res-title">
						<span>空闲时间：<s:property value="#it.time"/></span>
					</div>
					<div class="res-title">
						<span>预期价格：<s:property value="#it.place"/></span>
					</div>
					<div class="res-title">
						<span>联系电话<s:property value="#it.phoneNumber"/></span>
					</div>
					<div class="res-content">
						<p><s:property value="#it.introduction"/></p>
					</div>
				</div>
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" value="<s:property value="#it.reservationID"/>" onclick="receive(this)">确认</button>
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

  </body>
  
  <script>
  function receive(name) {
		window.location.href = "receive?ReservationID="+name.value;
	}
  </script>
</html>