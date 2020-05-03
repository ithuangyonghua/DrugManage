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
						- 结账</a>
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
					<li class="active">结账</li>
				</ol>
				
				<div class="panel panel-default">
					<div class="hpanel">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-6">
									<h4>
										入库编号： <small id="id">${sdetailVoInfo[0].detailid}</small>
									</h4>
								</div>
							</div>
						</div>
						<div class="panel-body p-xl">
							<div class="m-t">
								<strong>药品明细</strong>
							</div>
							<div class="table-responsive m-t">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>药品名称</th>
											<th>数量</th>
											<th>单价</th>
											<th>小计</th>
										</tr>
									</thead>
									<tbody>
									<%-- ${sdetailVoInfo.drugInfo} --%>
									<c:forEach items="${sdetailVoInfo}" var="z" varStatus="s">
											<tr>
												<td>
													<div>
														${z.drugname}
													</div> 
												</td>
												 <td>${z.nownum}</td>
												 <td>￥${z.price}</td>
												 <td >￥<span class="price">${z.nownum*z.price}</span></td>
											</tr>
										</c:forEach> 
									</tbody>
								</table>
							</div>
							<div class="row m-t">
								<div class="col-md-4 col-md-offset-8">
									<table class="table table-striped text-right">
										<tbody>
											<tr>
												<td><strong>合计 :</strong></td>
												<td >￥<span id="result"></span></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							
							<div class="row m-t">
								<div class="col-md-6 ">
									<table class="table table-striped text-right">
										<tbody>
											<tr>
												<td><div class="form-group "><label>实际支付金额 :</label> </div></td>
												<td>
												  <div class="form-group col-md-12">
												    <input type="text" id="monery" class="form-control sum" placeholder="请输入实际支付金额">
											      </div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							
							<div class="row m-t">
								<div class="col-md-4 col-md-offset-8">
									<table class="table table-striped text-right">
										<tbody>
											<tr>
												<td><button type="button" id="saveAccount" class="btn btn-success"  style="float: right;" id="saveBtn">结账 </button></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
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
			var sum = 0;
			$(".price").each(function(i){
				sum += parseInt($(this).html());
			});
			$("#result").html(sum);
		});
		$("#saveAccount").click(function(){
			var monery = $("#monery").val();//实际支付的金额
			var result = $("#result").html();//需要支付的
			var id=$("#id").html();
			var reg = /^\d+$|^\d+[.]?\d+$/;
			if(monery==""){
				layer.msg("请输入实际支付的金额", {
					time : 1000,
					icon : 5,
					shift : 6
				}, function() {
				});
				return ;
			}
			if(!reg.test(monery)){
				layer.msg("含有非法字符,请输入正常的数", {
					time : 1000,
					icon : 5,
					shift : 6
				}, function() {
				});
				return ;
			}
			if(monery<0){
				layer.msg("实际支付的金额不能为负值", {
					time : 1000,
					icon : 5,
					shift : 6
				}, function() {
				});
				return;
			}
			if(parseInt(monery)<parseInt(result)){
				layer.msg("实际支付的金额不能小于总计", {
					time : 1000,
					icon : 5,
					shift : 6
				}, function() {
				});
				return;
			}
			$.ajax({
				type:"POST",
				url:"${APP_PATH}/storage/updateMoneryStatusId",
				data:{
					"id":id,
					"monery":monery
				},
				success:function(result){
					 if(result.success==true){
	                    	layer.msg("支付成功", {time:1000, icon:6, shift:6}, function(){
		    					 window.location.href="${APP_PATH}/storage/index"
				    		 });
	                    }else{
	                    	 layer.msg("支付失败,请重新支付", {time:1000, icon:5, shift:6}, function(){
				    			 //alert("回调方法");
				    		 });
	                    }
				}
			});
		});
	</script>
</body>
</html>
