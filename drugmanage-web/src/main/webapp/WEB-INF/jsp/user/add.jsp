<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">药品管理 - 用户添加</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${loginUser.username}  <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="${APP_PATH}/logout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@include file="/WEB-INF/jsp/common/menue.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" id="ResetForm">
				  <div class="form-group">
					<label for="exampleInputPassword1">登陆账号</label>
					<input type="text"  class="form-control" id="loginacct" placeholder="请输入登陆账号">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">用户名称</label>
					<input type="text" class="form-control" id="username" placeholder="请输入用户名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">邮箱地址</label>
					<input type="email" class="form-control" id="email" placeholder="请输入邮箱地址">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <button type="button" id="addBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button type="button" id="resetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    $("#resetBtn").click(function(){
			    	//$("#loginacct").val("${userInfo.loginacct}");
			    	//$("#username").val("${userInfo.username}");
			    	//$("#email").val("${userInfo.email}");
			    	$("#ResetForm")[0].reset();
			    });
			    $("#addBtn").click(function(){
			    	 var loadingIndex;
			    	 var loginacct = $("#loginacct").val();
			    	 var username = $("#username").val();
			    	 var email = $("#email").val();
			    	 if(loginacct==""){
			    		 layer.msg("登陆的账号不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    			 //alert("回调方法");
			    		 });
			    		 return;
			    	 }
			    	 if(username==""){
			    		 layer.msg("用户名称不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    			 //alert("回调方法");
			    		 });
			    		 return;
			    	 }
			    	 if(email==""){
			    		 layer.msg("邮箱地址不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    			 //alert("回调方法");
			    		 });
			    		 return;
			    	 }
			    	 var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
			    	 if(!reg.test(email)){
			    		 layer.msg("邮箱格式不正确,请输入", {time:1000, icon:5, shift:6}, function(){
			    			 //alert("回调方法");
			    		 });
			    		 return ;
			    	 }
			    	 $.ajax({
			    		 type:"POST",
			    		 url:"${APP_PATH}/user/insert",
			    		 data:{
			    			 "loginacct":loginacct,
			    			 "username":username,
			    			 "email":email
			    		 },
			    		 beforeSend:function(){//代表发送数据前怎么怎么做
			    			 loadingIndex = layer.msg('数据处理中', {icon: 16});
			    		 },
			    		 success:function(result){
			    			 layer.close(loadingIndex);
			    			 if(result.success){
			    				 layer.msg("用户信息保存成功", {time:1000, icon:6, shift:6}, function(){
			    					 window.location.href="${APP_PATH}/user/index"
					    		 });
			    			 }else{
			    				 layer.msg("用户信息保存失败,请重新输入", {time:1000, icon:5, shift:6}, function(){
					    			 //alert("回调方法");
					    		 });
			    			 }
			    		 }
			    	 });
			    });
            });
        </script>
  </body>
</html>
