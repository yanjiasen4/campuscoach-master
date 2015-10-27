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
	
	<div class="container content">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="pannel" id="filter">
					<div class="btn-group" role="group" aria-label="...">
						<s:iterator value="sportsList" var="it">
							<button class="btn btn-primary active"><s:property value="#it"/></button>
						</s:iterator>
					</div>
				</div>
			</div>
			
			<s:iterator value="courses" var="course">
				<div class="col-sm-12 col-md-6 col-lg-4 " id="<s:property value="#course.sportsName"/>">
					<div class="box">
						<div class="iconpic">
							<img src="img/1.jpg" width="150px" height="150px">
						</div>
						<div class="caption">
							<div class="line">
								<div class="f">
									<h4><s:property value="#course.sportsName"/>教练：<s:property value="#course.coachName"/></h4>	
								</div>
								<div class="b">
									<button type="button" class="btn btn-primary" value="<s:property value="#course.courseID"/>" onclick="order(this)">参加</button>
								</div>
							</div>
							<div class="line">
								<div class="f">
									
									<p>价格：<s:property value="#course.price"/></p>
								</div>
								<div class="b">
									<button type="button" class="btn btn-primary" value="<s:property value="#course.courseID"/>" data-toggle="modal" data-target="#<s:property value="#course.courseID"/>">详情</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:iterator>
			
			<div class="col-sm-12 col-md-12 col-lg-12" id="conment">
				<div id="disqus_thread" class="disqus_div"></div>
				<script type="text/javascript">
    		/* * * CONFIGURATION VARIABLES * * */
   			var disqus_shortname = 'campuscoach';
   			var disqus_identifier = '<s:property value='#course.id'/>';
   				
    
    		/* * * DON'T EDIT BELOW THIS LINE * * */
    		(function() {
        		var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
        		dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
        		(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
    			})();
    			
    		var disqus_config = function() {
    			this.page.remote_auth_s3 = '${sessionScope.SSOCode}';
    			this.page.api_key = 'Q1SyX1WCgjuosUWoitWc0ijJzHpqxmC2UagcmRZc0hm1h05Zfam3qYhcnhO8Y3gm';
    		}
			</script>
			<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a></noscript>
			</div>
			
			
		</div>
	</div>
	
	<s:iterator value="courses" var="course">
	<div class="modal fade" id="<s:property value="#course.courseID"/>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">教练详情</h4>
					<div style="clear:both"></div>
				</div>
			<div class="modal-body">
				<div class="detail-head">
					<p>教练: <s:property value="#course.coachName"/></p>
					<p>时间: <s:property value="#course.time"/></p>
					<p>价格: <s:property value="#course.price"/></p>
					<p>人数: <s:property value="#course.maxNum"/></p>
					<p>地点: <s:property value="#course.place"/></p>
				</div>
				<hr>
				<p><s:property value="#course.introduction"/></p>
				
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" value="<s:property value="#course.courseID"/>" onclick="order(this)">报名</button>
				</div>
			</div>
		</div>
	</div>
	</s:iterator>
	
	<div class="positionfix alert alert-danger" id="alert" role="alert" style="display:hidden">请前往个人中心完善信息！</div>
	<%if(request.getParameter("login")!=null){%>
	<div class="positionfix alert alert-success" id="login" role="alert">登录成功！</div>
	<%} %>

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
	<script type="text/javascript">
	$(function () {
        $("#filter button").click(function () {
			if($(this).hasClass("active")) {
				$(this).removeClass("active");
				hideDivsById();
			}
			else {
				$(this).addClass("active");
				showDivsById();
			}
        });
    });
	
	function showDivsById(){
		t = document.getElementsByTagName('div');
		$("#filter button[class='btn btn-primary active']").each(function () {
			for(var i = 0; i < t.length; i++) {
				if(t[i].id==$(this).html()) {
					if(t[i].style.display=="none"){
						t[i].style.display="block";
					}
				}
			}
		});
	}
	
	function hideDivsById(){
		t = document.getElementsByTagName('div');
		$("#filter button[class='btn btn-primary']").each(function () {
			for(var i = 0; i < t.length; i++) {
				if(t[i].id==$(this).html()) {
					if(t[i].style.display!="none"){
						t[i].style.display="none";
					}
				}
			}
		});
	}
	
	function login() {
		//alert(<%request.getParameter("login");%>);
		<%if(request.getParameter("login")!=null){%>
			t = document.getElementById("login");
			alert(t);
			t.style.display = "block";
		<%}%>
	}
	
	function order(name) {
		<%if(request.getSession().getAttribute("info")==null){%>
			t = document.getElementById("alert");
			
			t.style.display = "block";
		<%} else {%>
		window.location.href = "signup?courseID="+name.value;
		<%}%>
	}
	
	function detail(name) {
		window.location.href = "course/" + name.value + ".html";
	}
	</script>
</html>