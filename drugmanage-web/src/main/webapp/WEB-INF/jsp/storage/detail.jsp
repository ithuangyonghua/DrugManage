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
						- 入库明细</a>
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
					<li class="active">明细列表</li>
				</ol>
				
				<div class="panel panel-default">
					<div class="hpanel">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-6">
									<h4>
										入库编号： <small>${sdetailVoInfo[0].detailid}</small>
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
	</script>
</body>
</html>
