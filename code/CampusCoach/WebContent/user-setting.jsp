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
    <link href="style/css/normalize.css" rel="stylesheet">
    <link href="style/css/default.css" rel="stylesheet">
    <link href="style/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />	
	
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
    	<li><a href="showusercourses">培训班记录</a></li>
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
	
	<div class="containter upload">
	<div class="htmleaf-container">
        
        <%if(request.getSession().getAttribute("info") != null) {%>
      	<div class="container">
      		<div class="page-header">
      			<h3>${sessionScope.user}您好，您预留的手机号为${sessionScope.phoneNumber}</h3>
      			<hr>
      			<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#inputInfo" aria-expanded="false" aria-controls="inputInfo">修改信息</button>
      		</div>
      	</div>
      	<% } else {%>
      	<div class="container" id="inputInfo">
      		<div class="page-header">
      		<h2>完善个人信息</h2>
      		</div>
      		<form class="form-course" action="updatelearnerinfo" method="post">
			<label for="inputType" class="sr-only">真实姓名</label>
			<input type="text" name="realName" id="inputType" class="form-control" placeholder="真实姓名" data-toggle="popover" data-trigger="focus" data-placement="top" title="真实姓名" 
			data-content="请输入您真实姓名，以供报名或发布愉悦" required autofocus>
			<label for="inputType" class="sr-only">电话号码</label>
			<input type="text" name="phoneNumber" id="inputType" class="form-control" placeholder="电话号码" data-toggle="popover" data-trigger="focus" data-placement="top" title="请确保您输入了11位数字的电话号码" 
			data-content="请输入您手机号，以供教练或管理员联系" required autofocus pattern="[0-9]{11}">
			<input class="btn btn-lg btn-primary btn-block" type="submit"></input>
			</form>
      	</div>
     
      	<%} %>
      	<div class="collapse" id="inputInfo">
      	<div class="container">
      		<form class="form-course" action="updatelearnerinfo" method="post">
			<label for="inputType" class="sr-only">真实姓名</label>
			<input type="text" name="realName" id="inputType" class="form-control" placeholder="真实姓名" data-toggle="popover" data-trigger="focus" data-placement="top" title="真实姓名" 
			data-content="请输入您真实姓名，以供报名或发布愉悦" required autofocus>
			<label for="inputType" class="sr-only">电话号码</label>
			<input type="text" name="phoneNumber" id="inputType" class="form-control" placeholder="电话号码" data-toggle="popover" data-trigger="focus" data-placement="top" title="请确保您输入了11位数字的电话号码" 
			data-content="请输入您手机号，以供教练或管理员联系" required autofocus pattern="[0-9]{11}">
			<input class="btn btn-lg btn-primary btn-block" type="submit"></input>
			</form>
      	</div>
      	</div>
      	
      	<div class="container kv-main">
            <div class="page-header">
            <h2>上传你的头像 <small></h2>
            </div>
            <form enctype="multipart/form-data" action="uploadavatar" method="post">
                <input id="file-0a" class="file" type="file" name="file" data-min-file-count="1">
                <br>
                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="reset" class="btn btn-default">Reset</button>
            </form>
        </div>   
      	
	</div>
	</div>
	<br>
	<br>
	
	<%if(request.getParameter("success")!=null){ %>
	<div class="alert alert-success" role="alert">更新个人信息成功！</div>
	<%} %>
	
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
	
	<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script>
    <script src="style/js/fileinput.js" type="text/javascript"></script>
    <script src="style/js/fileinput_locale_zh.js" type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" type="text/javascript"></script>
    <script>
	    $("#file-0").fileinput({
	        'allowedFileExtensions' : ['jpg', 'png','gif'],
	    });
	    $("#file-1").fileinput({
	        uploadUrl: '#', // you must set a valid URL here else you will get an error
	        allowedFileExtensions : ['jpg', 'png','gif'],
	        overwriteInitial: false,
	        maxFileSize: 1000,
	        maxFilesNum: 10,
	        //allowedFileTypes: ['image', 'video', 'flash'],
	        slugCallback: function(filename) {
	            return filename.replace('(', '_').replace(']', '_');
	        }
		});
	    /*
	    $(".file").on('fileselect', function(event, n, l) {
	        alert('File Selected. Name: ' + l + ', Num: ' + n);
	    });
	    */
		$("#file-3").fileinput({
			showUpload: false,
			showCaption: false,
			browseClass: "btn btn-primary btn-lg",
			fileType: "any",
	        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
		});
		$("#file-4").fileinput({
			uploadExtraData: {kvId: '10'}
		});
	    $(".btn-warning").on('click', function() {
	        if ($('#file-4').attr('disabled')) {
	            $('#file-4').fileinput('enable');
	        } else {
	            $('#file-4').fileinput('disable');
	        }
	    });    
	    $(".btn-info").on('click', function() {
	        $('#file-4').fileinput('refresh', {previewClass:'bg-info'});
	    });
	    /*
	    $('#file-4').on('fileselectnone', function() {
	        alert('Huh! You selected no files.');
	    });
	    $('#file-4').on('filebrowse', function() {
	        alert('File browse clicked for #file-4');
	    });
	    */
	    $(document).ready(function() {
	        $("#test-upload").fileinput({
	            'showPreview' : false,
	            'allowedFileExtensions' : ['jpg', 'png','gif'],
	            'elErrorContainer': '#errorBlock'
	        });
	        /*
	        $("#test-upload").on('fileloaded', function(event, file, previewId, index) {
	            alert('i = ' + index + ', id = ' + previewId + ', file = ' + file.name);
	        });
	        */
	    });
		</script>
	
	</script>
	</body>
</html>
