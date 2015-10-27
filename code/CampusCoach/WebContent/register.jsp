<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
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
				<li role="presentation" class="active"><a href="register.jsp">注册</a></li>
				</ul>
		</div>
	</nav>
	
	<div class="header">
	  <h2>注册</h2>
	</div>
	
	<div class="container">
		<form class="form-course" action="registerform" method="post">
			<label for="inputType" class="sr-only">账号</label>
			<input type="text" name="learner.username" id="inputType" class="form-control" placeholder="账号" data-toggle="popover" data-trigger="focus" data-placement="top" title="账号" 
			data-content="请输入您的账号" required autofocus pattern="[0-9a-zA-Z]{6,20}">
			<label for="inputType" class="sr-only">密码</label>
			<input type="password" name="learner.password" id="inputType" class="form-control" placeholder="密码" data-toggle="popover" data-trigger="focus" data-placement="top" title="密码" 
			data-content="请输入您的密码" required autofocus pattern="[0-9a-zA-Z]{6,20}">
			<input class="btn btn-lg btn-primary btn-block" type="submit"></input>
			<%
            if(request.getParameter("errNo") != null){
            String errID = request.getParameter("errNo");
            if (errID.equals("1")) 
            { System.out.println(request.getParameter("errNo"));
            %>
            <p><font color="red">该账号已被注册!</font></p>
            <%}}
			%>
           
		</form>
	</div>
	
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
	<script>
	$(function () {
		$('[data-toggle="popover"]').popover()
	})
	</script>
	</body>
</html>
