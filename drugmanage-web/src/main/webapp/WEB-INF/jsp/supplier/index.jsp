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
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}
</style>
</head>

<body>
    <%--  <%@include file="/WEB-INF/jsp/common/top.jsp" %> --%>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">药品管理
						- 供应商管理</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<div class="btn-group">
							<button type="button"
								class="btn btn-default btn-success dropdown-toggle"
								data-toggle="dropdown">
								<i class="glyphicon glyphicon-user"></i> ${loginUser.username}<span
									class="caret"></span>
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
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input class="form-control has-success"  id="searchText" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" id="searchBtn" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" id="deleteBouth" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="insertRole()">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
						    <form id="RoleForm">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox" id="boxAll"></th>
										<th>供货商名称</th>
										<th>供货商负责人</th>
										<th>供货商联系电话</th>
										<th>供货商地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody id="supplierData">

								</tbody>
								<tfoot>
									<tr>
										<td colspan="7" align="center">
											<ul class="pagination">
											</ul>
										</td>
									</tr>

								</tfoot>
							</table>
							</form>
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
	<script type="text/javascript">
	    var searchFlag =true;
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
			//分页查询
			pageQuery(1);
			
		});
		$("#searchBtn").click(function(){
			if($("#searchText").val()==""){
				searchFlag=true;//是模糊查询
			}else{
				searchFlag=false;//不是模糊查询
			}
			pageQuery(1);
		}); 
		 $("tbody .btn-success").click(function() {
			window.location.href = "assignPermission.html";
		}); 
		//加载
		function pageQuery(pageno) {
			var loadingIndex;
			var jsonData={"pageno" : pageno,"pagesize" : 10}
			if(!searchFlag){
				jsonData.searchText=$("#searchText").val();
			}
			$
					.ajax({
						type : "POST",
						url : "${APP_PATH}/supplier/pageQuery",
						data : jsonData,
						beforeSend : function() {//代表发送数据前怎么怎么做
							loadingIndex = layer.msg('数据处理中', {
								icon : 16
							});
						},
						success : function(result) {
							if (result.success == true) {
								layer.close(loadingIndex);
								 var tableData = "";
								var PageContext = "";
								var supplierData = result.data.datas;
								var pageData = result.data;
								$
										.each(
												supplierData,
												function(i, supplier) {
													tableData += '	<tr> ';
													tableData += '      <td>'
															+ (i+1) + '</td>';
													tableData += '	  <td><input type="checkbox" name="id" value="'+supplier.supplierid+'"></td>'; 
													tableData += '      <td>'
															+ supplier.suppliername
															+ '</td>';
													tableData += '      <td>'
																+ supplier.fzr
																+ '</td>';
												    tableData += '      <td>'
																+ supplier.phone
																+ '</td>';
													tableData += '      <td>'
																		+ supplier.address
																		+ '</td>';
													tableData += '      <td>';
												 	/* tableData += '        <button type="button" onclick="goAssignRole('+supplier.supplierid+')" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';  */
											 		tableData += '	      <button type="button"  onclick="edit('+supplier.supplierid+')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
													tableData += '		  <button type="button" onclick="deleteRoleById('+supplier.supplierid+',\''+supplier.suppliername+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
													tableData += '	  </td>';
													tableData += '    </tr>';
												});
								//上一页
								if (pageno > 1) {
									PageContext += '<li><a href="#" onclick="pageQuery('
											+ (pageno - 1) + ')">上一页</a></li>';
								}
								for (var i = 1; i <= pageData.totalno; i++) {
									if (pageno == i) {
										PageContext += '<li class="active"><a href="#" >'
												+ i + '</a></li>';
									} else {
										PageContext += '<li><a href="#"onclick="pageQuery('
												+ i + ')">' + i + '</a></li>';
									}
								}
								//下一页
								if (pageno < pageData.totalno) {//下一页的判断
									PageContext += '<li><a href="#" onclick="pageQuery('
											+ (pageno + 1) + ')">下一页</a></li>';
								}
								$("#supplierData").html(tableData);
								$(".pagination").html(PageContext); 
							} else {
								layer.msg("角色分页查询失败", {
									time : 1000,
									icon : 5,
									shift : 6
								}, function() {
								});
							}
						}
					});
		};
		//新增
		function insertRole(){
			window.location.href="${APP_PATH}/supplier/insertSupplier";
		}
		
		
		//修改 
		function edit(rid){
			window.location.href="${APP_PATH}/supplier/edit?id="+rid;
		}
		
		//删除
		function deleteRoleById(rid,rname){
			layer.confirm("删除【"+rname+"】信息,是否继续",  {icon: 3, title:'提示'}, function(cindex){
				   $.ajax({
					   type:"POST",
					   url:"${APP_PATH}/supplier/deleteSupplierById",
					   data:{
						   "id":rid
					   },
					   success:function(result){
						   if(result.success==true){
							   window.location.href="${APP_PATH}/supplier/index"
						   }else{
							   layer.msg("角色信息删除失败", {
									time : 1000,
									icon : 5,
									shift : 6
								}, function() {
								});
						   }
					   }
				   });
			}, function(cindex){
			    layer.close(cindex);
			});
		}
		//全选
		$("#boxAll").click(function(){
			var boxcheck = this.checked;
			//alert(boxcheck);
			$("#supplierData :checkbox").each(function(){
				this.checked=boxcheck;
			});
		});
		//删除
		$("#deleteBouth").click(function(){
			//获取选中的checkbox
			var checks = $("#supplierData :checked");
			if(checks.length==0){
				 layer.msg("请选择要删除的角色信息", {
						time : 1000,
						icon : 5,
						shift : 6
					}, function() {
					});
				 return;
			}
			layer.confirm("删除选择的用户信息,是否继续",  {icon: 3, title:'提示'}, function(cindex){
				//第一个代表的是确定按钮
				$.ajax({
					type:"POST",
					url:"${APP_PATH}/supplier/deleteRoleBouth",
					data:$("#RoleForm").serialize(),
					success:function(result){
						if(result.success==true){
							 window.location.href="${APP_PATH}/supplier/index"
						}else{
							layer.msg("角色信息删除失败", {
								time : 1000,
								icon : 5,
								shift : 6
							}, function() {
							});
						}
					}
				});
			}, function(cindex){
				//第二个代表的是取消按钮
			    layer.close(cindex);
			});
			
			
		});
	</script>
</body>
</html>
