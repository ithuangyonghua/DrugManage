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

<link rel="stylesheet"
	href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/main.css">
<link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="user.html">药品管理
						- 入库申请</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<div class="btn-group">
							<button type="button"
								class="btn btn-default btn-success dropdown-toggle"
								data-toggle="dropdown">
								<i class="glyphicon glyphicon-user"></i> ${loginUser.username} <span
									class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${APP_PATH}/logout"><i
										class="glyphicon glyphicon-off"></i> 退出系统</a></li>
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
					<%@include file="/WEB-INF/jsp/common/menue.jsp"%>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">数据列表</a></li>
					<li class="active">新增</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body ">

						<form role="form" class="panel-heading" id="ContextForm">
							<div class="form-row col-md-12">
								<div class="form-group">
									<label for="inputAddress">供应商</label> <select
										class="form-control" id="supplier">
										<option value="" selected="selected">请选择供应商</option>
										<c:forEach items="${supplierInfo}" var="supplier">
											<option value="${supplier.supplierid}">${supplier.suppliername}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-4">
									<select class="form-control drugCate"
										onchange="preferedBrowser(this)">
										<option value="" selected="selected">请选择类别</option>
										<c:forEach items="${drugCategoryAll}" var="drugCate">
											<option value="${drugCate.dcategoryid}">${drugCate.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group col-md-4">
									<select class="form-control supplier">
										<option value="" selected="selected">请选择药品</option>
									</select>
								</div>
								<div class="form-group col-md-3">
									<input type="text" class="form-control sum" placeholder="请输入数量">
								</div>

								<div class="form-group col-md-1">
									<button type="button" onclick="delRow(this)"
										class="btn  btn-danger">
										<i class="glyphicon glyphicon-remove"></i> 删除
									</button>
								</div>
							</div>
						</form>
						<div class="form-group col-md-2">
							<button type="button" class="btn btn-primary "
								style="float: right;" id="addRow">
								<i class="glyphicon glyphicon-plus"></i>新增
							</button>
						</div>
						&nbsp;
						<div class="form-group col-md-2">
							<button type="button" class="btn btn-success"
								style="float: right;" id="saveBtn">
								<i class="glyphicon glyphicon-plus"></i> 保存
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
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
	<script src="${APP_PATH}/bootstrap/js/bootstrap-select.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
			$("#addRow")
					.click(
							function() {
								var Context = "";
								Context += '	<div class="form-row">';
								Context += '		   <div class="form-group col-md-4">';
								Context += '			  <select class="form-control drugCate"   onchange="preferedBrowser(this)" >';
								Context += '				     <option value="" selected="selected">请选择类别</option>';
								Context += '					  <c:forEach items="${drugCategoryAll}" var="drugCate">';
								Context += '					    <option value="${drugCate.dcategoryid}">${drugCate.name}</option>';
								Context += '					  </c:forEach>';
								Context += '			    </select>';
								Context += '		  </div>';
								Context += '		  <div class="form-group col-md-4">';
								Context += '			  <select class="form-control supplier"  class="supplier">';
								Context += '				     <option value="" selected="selected" >请选择药品</option>';
								Context += '			    </select>';
								Context += '		  </div>';
								Context += '		   <div class="form-group col-md-3">';
								Context += '		     <input type="text"  class="form-control sum" placeholder="请输入数量">';
								Context += '		  </div>';
								Context += '		  <div class="form-group col-md-1">';
								Context += '			    <button type="button" onclick="delRow(this)" class="btn  btn-danger"><i class="glyphicon glyphicon-remove"></i> 删除</button>';
								Context += '		  </div>';
								Context += '	</div>';
								$("#ContextForm").append(Context);
							});

		});
		//二级联动实现
		function preferedBrowser(obj) {
			var supplierVal = $(obj).children('option:selected').val() //使用this
			if (supplierVal == "") {
				supplierVal = -1;
			}
			$.ajax({
				type : "POST",
				url : "${APP_PATH}/drug/queryById/" + supplierVal,
				success : function(result) {
					if (result.success == true) {
						$(obj).parent().next().find("select").html("");
						$(obj).parent().next().find("select").append(
								new Option("请选择药品", ""));
						for ( var i in result.data) {
							var drugid = result.data[i].drugid;
							var drugname = result.data[i].drugname;
							$(obj).parent().next().find("select").append(
									new Option(drugname, drugid));
						}
					}
				}
			});
		}
		//删除
		function delRow(obj) {
			$(obj).parent().parent().remove();
		}
		//保存
        var flag = false;
		$("#saveBtn").click(function() {
			var storageVo = {};
			var sdetails = new Array();
			var item;
			var drugCate = $(".drugCate :selected");
			var supplier = $(".supplier :selected");
			var sum = $(".sum");
			var supplierValue = $("#supplier :selected").val();
			if (supplierValue == "") {
				layer.msg("供应商不能为空", {
					time : 1000,
					icon : 5,
					shift : 6
				}, function() {
				});
				return false;
			}
			storageVo.sid = supplierValue;
			$.each(drugCate, function(i, item) {
				if ($(drugCate[i]).val() == "") {
					flag = true;
					layer.msg("类别信息不能为空", {
						time : 1000,
						icon : 5,
						shift : 6
					}, function() {
					});
					console.log($(drugCate[i]).val());
					return false;
				}
				if ($(supplier[i]).val() == "") {
					flag = true;
					layer.msg("药品信息不能为空", {
						time : 1000,
						icon : 5,
						shift : 6
					}, function() {
					});
					return false;
				}
				if ($(sum[i]).val() == "") {
					flag = true;
					layer.msg("数量不能为空", {
						time : 1000,
						icon : 5,
						shift : 6
					}, function() {
					});
					return false;
				}
				if ($(sum[i]).val() < 0) {
					flag = true;
					layer.msg("数量不能为负值", {
						time : 1000,
						icon : 5,
						shift : 6
					}, function() {
					});
					return false;
				}
			});
			if(flag){
				return;
			}
			$.each(drugCate, function(i, item) {
				  sdetails.push({
					"drugid" : $(supplier[i]).val(),
					"nownum" : $(sum[i]).val()
				});  
				/* item.drugid =  $(supplier[i]).val();
				item.sum= $(sum[i]).val();
				sdetails.push(item);  */
			});
			storageVo.sdetails = sdetails;
			console.log(storageVo);
				$.ajax({
					type : "POST",
					url : "${APP_PATH}/storage/save",
					data : JSON.stringify(storageVo),//将对象序列化成JSON字符串  
					dataType : "json",
					contentType : "application/json",
					success : function(result) {
	                    if(result.success==true){
	                    	layer.msg("库存信息保存成功", {time:1000, icon:6, shift:6}, function(){
		    					 window.location.href="${APP_PATH}/drug/index"
				    		 });
	                    }else{
	                    	 layer.msg("入库信息保存失败,请重新输入", {time:1000, icon:5, shift:6}, function(){
				    			 //alert("回调方法");
				    		 });
	                    }
					}
				});
		});
	</script>
</body>
</html>
