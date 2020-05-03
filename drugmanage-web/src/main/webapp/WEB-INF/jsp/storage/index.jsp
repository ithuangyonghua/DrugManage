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
						- 入库审核</a>
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
										placeholder="请输入供应商名称">
								</div>
							</div>
							<button type="button" id="searchBtn" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<!-- <button type="button" id="deleteBouth" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button> -->
						<!-- <button type="button" class="btn btn-primary"
							style="float: right;" onclick="insertRole()">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button> -->
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
						    <form id="RoleForm">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<!-- <th width="30"><input type="checkbox" id="boxAll"></th> -->
										<th >供应商</th>
										<th>审核状态</th>
										<th>支付状态</th>
										<th>申请人</th>
										<th >创建时间</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody id="supplierData">

								</tbody>
								<tfoot>
									<tr>
										<td colspan="8" align="center">
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
						url : "${APP_PATH}/storage/pageQuery",
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
												function(i, storage) {
													var status ="未审核";
													var zfstatus = "未支付";
													if(storage.status != 0){
														status = "已审核";
													}
													if(storage.monystatus != 0){
														zfstatus = "已支付";
													}
													tableData += '	<tr> ';
													tableData += '      <td>'
															+ (i+1) + '</td>';
													/* tableData += '	  <td><input type="checkbox" name="id" value="'+storage.id+'"></td>';  */
												
													tableData += '      <td>'
															+ storage.suppliername /* 供应商  */
															+ '</td>';
													tableData += '      <td>' 
													         +  status
																+ '</td>';
													tableData += '      <td>' 
												         +  zfstatus
															+ '</td>';
												    tableData += '      <td>'
																+ storage.loginacct /* 申请人 */
																+ '</td>';
													tableData += '      <td>'
																		+ storage.createTime /* 创建时间 */
																		+ '</td>';
													tableData += '      <td>';
											 		tableData += '	      <button type="button"  onclick="deatil(\''+storage.id+'\')" class="btn btn btn-success btn-xs"><i class=" glyphicon glyphicon-pencil">查看明细</i></button>';
											 		if(storage.status == 0){
											 		     tableData += '		  <button type="button" onclick="updateStatusId(\''+storage.id+'\',\''+storage.suppliername+'\')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-remove">审核</i></button>';
											 		    tableData += '		  <button type="button" onclick="deleteStorageId(\''+storage.id+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove">删除</i></button>';
											 		}else{
											 			 tableData += '		  <button type="button" disabled="disabled" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-remove">审核</i></button>';
											 		}
											 		if(storage.status!=0&& storage.monystatus == 0){
											 			tableData += '		  <button type="button" onclick="accounts(\''+storage.id+'\')"  class="btn btn-warning btn-xs"><i class=" glyphicon glyphicon-remove">结账</i></button>';
											 		}
											 		
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
								layer.msg("入库分页查询失败", {
									time : 1000,
									icon : 5,
									shift : 6
								}, function() {
								});
							}
						}
					});
		};
		//明细 
		function deatil(id){
			window.location.href ="${APP_PATH}/storage/detail?id="+id;
		}
		//结账
		function accounts(id){
			window.location.href ="${APP_PATH}/storage/accounts?id="+id;
		}
		//审核
		function updateStatusId(id,suppliername){
			layer.confirm("修改【"+suppliername+"】审核状态,是否继续",  {icon: 3, title:'提示'}, function(cindex){
				   $.ajax({
					   type:"POST",
					   url:"${APP_PATH}/storage/updateStatusId",
					   data:{
						   "id":id
					   },
					   success:function(result){
						   layer.close(cindex);
						   if(result.success==true){
							   layer.msg("审核状态信息修改成功", {
									time : 1000,
									icon : 6
								}, function() {
									window.location.href="${APP_PATH}/storage/index"
								});
							   
						   }else{
							   layer.msg("审核状态信息修改失败", {
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
		
		//删除
		function deleteStorageId(rid){
			layer.confirm("删除库存信息,是否继续",  {icon: 3, title:'提示'}, function(cindex){
				   $.ajax({
					   type:"POST",
					   url:"${APP_PATH}/storage/deleteStorageById",
					   data:{
						   "id":rid
					   },
					   success:function(result){
						   layer.close(cindex);
						   if(result.success==true){
							   window.location.href="${APP_PATH}/storage/index";
						   }else{
							   layer.msg("入库信息删除失败", {
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
				 layer.msg("请选择要删除的入库信息", {
						time : 1000,
						icon : 5,
						shift : 6
					}, function() {
					});
				 return;
			}
			layer.confirm("删除选择的入库信息,是否继续",  {icon: 3, title:'提示'}, function(cindex){
				//第一个代表的是确定按钮
				$.ajax({
					type:"POST",
					url:"${APP_PATH}/storage/deleteStorageBouth",
					data:$("#RoleForm").serialize(),
					success:function(result){
						if(result.success==true){
							 window.location.href="${APP_PATH}/storage/index"
						}else{
							layer.msg("入库信息删除失败", {
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
