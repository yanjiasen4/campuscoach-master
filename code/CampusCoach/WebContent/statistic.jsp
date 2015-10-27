<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8">
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
			    <li role="presentation"><a class="nav-avatar" href="showusercourses.action"><img alt="校园教练" src="${sessionScope.avatar}"></a></li>
			    <li role="presentation"><a href="showusercourses">${sessionScope.user}</a></li>
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
	  <h2>开始选择你需要的教练/培训班吧！</h2>
	</div>
	
	
	<nav class="navbar navbar-inverse navbar-fixed-bottom">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"! aria-expanded="false" aria-controls="navbar">
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
	</body>
	<script>
		document.write("<p>浏览器: ")
		document.write(navigator.appCodeName + "</p>")
		document.write("<p>浏览器版本: ")
		document.write(navigator.appVersion + "</p>")
		document.write("<p>平台:")
		document.write(navigator.platform + "</p>")
	</script>
</html>