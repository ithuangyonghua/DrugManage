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
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">药品管理 - 供应商添加</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${loginUser.username}  <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<!-- <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li> -->
						<li><a href="${APP_PATH}/logout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
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
				  <li class="active">添加</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form"  id="addForm">
				  <div class="form-group">
					<label for="exampleInputPassword1">药品名称</label>
					<select class="form-control" id="drugid">
						  <option value="" selected="selected">全部</option>
						  <c:forEach items="${drugInfo}" var="drug">
						   <option value="${drug.drugid}">${drug.drugname}</option>
						  </c:forEach>
						</select>
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">库存数量</label>
					<input type="text" class="form-control" id="inventorySum"   placeholder="请输入库存数量">
				  </div>
				  <button type="button" id="addBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button type="button"   id="resetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
			    	$("#addForm")[0].reset();
			    });
			    $("#addBtn").click(function(){
			    	 var loadingIndex;
			    	 var idx = 0;
			    	 var drugid = $("#drugid :selected").val();
			    	 if(drugid==""){
			    		 layer.msg("药品不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    		 });
			    		 return;
			    	 }
			    	 var inventorySum = $("#inventorySum").val();
			    	 if(inventorySum==""){
			    		 layer.msg("库存数量不能为空,请输入", {time:1000, icon:5, shift:6}, function(){
			    		 });
			    		 return;
			    	 }
			    	 $.ajax({
			    		 type:"POST",
			    		 url:"${APP_PATH}/inventory/insert",
			    		 data:{
			    			 "drugid":drugid,
			    			 "inventorySum":inventorySum
			    		 },
			    		 beforeSend:function(){//代表发送数据前怎么怎么做
			    			 loadingIndex = layer.msg('数据处理中', {icon: 16});
			    		 },
			    		 success:function(result){
			    			 layer.close(loadingIndex);
			    			 if(result.success){
			    				 layer.msg("库存信息保存成功", {time:1000, icon:6, shift:6}, function(){
			    					 window.location.href="${APP_PATH}/inventory/index"
					    		 });
			    			 }else{
			    				 layer.msg("库存信息保存失败,请重新输入", {time:1000, icon:5, shift:6}, function(){
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
