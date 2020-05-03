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
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">药品管理 - 药品添加</a></div>
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
				<form role="form" id="DrugForm">
				  <!-- <div class="form-group">
					<label for="exampleInputPassword1">药品编号</label>
					<input type="text"  class="form-control" id="drugid" placeholder="请输入药品编号">
				  </div> -->
				  <div class="form-group">
					<label for="exampleInputPassword1">药品名称</label>
					<input type="text" class="form-control" id="drugname" placeholder="请输入药品名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">药品类别</label>
					<select class="form-control" id="drugcategory">
						  <option value="" selected="selected">全部</option>
						  <c:forEach items="${drugCategoryAll}" var="drug">
						   <option value="${drug.dcategoryid}">${drug.name}</option>
						  </c:forEach>
						</select>
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">价格</label>
					<input type="text" class="form-control" id="price" placeholder="请输入价格">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">药品用途</label>
					<input type="text" class="form-control" id="yt" placeholder="请输入药品用途">
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
				<h4>测试标题11111</h4>
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
			    	//$("#PermissionForm");
			    	$("#DrugForm")[0].reset();
			    });
			    $("#addBtn").click(function(){
			    	 var loadingIndex;
			    	/*  var drugid = $("#drugid").val();
			    	 if(drugid==""){
			    		 layer.msg("药品编号不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    		 });
			    		 return;
			    	 } */
			    	 var drugname = $("#drugname").val();
			    	 if(drugname==""){
			    		 layer.msg("药品名称不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    		 });
			    		 return;
			    	 }
			    	 var drugcategory = $("#drugcategory :selected").val();
			    	 if(drugcategory==""){
			    		 layer.msg("药品类别不能为空,请选择", {time:1000, icon:5, shift:6}, function(){
			    		 });
			    		 return;
			    	 }
			    	 var price = $("#price").val();
			    	 if(price==""){
			    		 layer.msg("价格不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    		 });
			    		 return;
			    	 }
			    	
			    	 var  yt= $("#yt").val();
			    	 if( yt==""){
			    		 layer.msg("药品用途不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    		 });
			    		 return;
			    	 }
			    		  	 
			    	 $.ajax({
			    		 type:"POST",
			    		 url:"${APP_PATH}/drug/insert",
			    		 data:{
			    			 /* "drugid":drugid, */
			    			 "drugname":drugname,
			    			 "drugcategory":drugcategory,
			    			 "price":price,
			    			 "yt":yt
			    		 },
			    		 beforeSend:function(){//代表发送数据前怎么怎么做
			    			 loadingIndex = layer.msg('数据处理中', {icon: 16});
			    		 },
			    		 success:function(result){
			    			 layer.close(loadingIndex);
			    			 if(result.success==true){
			    				 layer.msg("药品信息保存成功", {time:1000, icon:6, shift:6}, function(){
			    					 window.location.href="${APP_PATH}/drug/index"
					    		 });
			    			 }else{
			    				 layer.msg("药品信息保存失败,请重新输入", {time:1000, icon:5, shift:6}, function(){
					    			 //alert("回调方法");
					    			 $("#DrugForm")[0].reset();
					    		 });
			    			 }
			    		 }
			    	 });
			    });
            });
        </script>
  </body>
</html>
