<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

	 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">药品管理
						- 药品信息</a>
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
									<input class="form-control has-success" type="text" id="queryID"
										placeholder="请输入药品编号">
								</div>
								<div class="input-group">
									<input class="form-control has-success" type="text" id="queryName"
										placeholder="请输入药品名称">
								</div>
								<div class="input-group">
									<select class="form-control" id="queryCategory">
									  <option value="" selected="selected">全部</option>
									  <c:forEach items="${drugCategoryAll}" var="drug">
									   <option value="${drug.dcategoryid}">${drug.name}</option>
									  </c:forEach>
									</select>
								</div>
							</div>
							<button id="queryBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" id="batchDelBtn" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='${APP_PATH}/drug/insertDrugCategory'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
						<form id="userForm">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox" id="BoxAll"></th>
										<th>药品编号</th>
										<th>药品名称</th>
										<th>药品类别</th>
										<th>价格</th>
										<th>用途</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								
								<tbody id="userData">

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
							</frorm>
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
	     var likeflag=true; 
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
			pageQuery(1);
			
			//模糊查询
	        $("#queryBtn").click(function(){
	        	pageQuery(1);
	        });
			
			
			//全选按钮
			$("#BoxAll").click(function(){
				var flag = this.checked;//获取选择状态
				
				$("#userData :checkbox").each(function(i,checkbox){
					this.checked=flag;
				});
			});
			
			//批量删除按钮
			$("#batchDelBtn").click(function(){
				var boxs = $("#userData :checked");
		        if(boxs.length==0){
		        	layer.msg("请选择需要删除的药品信息", {
						time : 1000,
						icon : 5,
						shift : 6
					}, function() {
					});
		        }else{
		        	var index;
					layer.confirm("删除选择的药品信息,是否继续",  {icon: 3, title:'提示'}, function(cindex){
						//第一个代表的是确定按钮
						$.ajax({
							type:"POST",
							url:"${APP_PATH}/drug/deleteDrugInfoBouth",
							data:$("#userForm").serialize(),
							success:function(result){
								if (result.success == true) {
									window.location.href="${APP_PATH}/drug/index";
								}else{
									layer.msg("药品信息删除失败", {
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
		        }
			});
		});
		$("tbody .btn-success").click(function() {
			window.location.href = "assignRole.html";
		});
		$("tbody .btn-primary").click(function() {
			window.location.href = "edit.html";
		});
		
		//内容加载
		function pageQuery(pageno) {
			var loadingIndex;
			var jsonData= {"pageno" : pageno,"pagesize" : 5};
			//var queryID = $("#queryID").val();//药品编号
			if($("#queryID").val()!=""){
				jsonData.queryID = $("#queryID").val();
			}
			if($("#queryName").val()!=""){
				jsonData.queryName = $("#queryName").val();
			}
			var queryCategory = $("#queryCategory :selected");
			if(queryCategory.val()!=""){
				jsonData.queryCategory = queryCategory.val();
			}	
			
			$
					.ajax({
						type : "POST",
						url : "${APP_PATH}/drug/pageQuery",
						data : jsonData,
						beforeSend : function() {//代表发送数据前怎么怎么做
							loadingIndex = layer.msg('数据处理中', {
								icon : 16
							});
						},
						success : function(result) {
							layer.close(loadingIndex);
							if (result.success == true) {
								//局部刷新数据
								var tableContext = "";
								var PageContext = "";

								var pageData = result.data;
								var drugData = pageData.datas;
								//i当前索引,user 当前临时数据
								//shift + alt + a
								$.each(drugData,function(i, druginfo) {
									tableContext += '	 <tr>  ';
									tableContext += '    <td>'+(i+1)+'</td>';
									tableContext += '	  <td><input type="checkbox"  name="id"  value="'+druginfo.drugid+'" </td>';
									tableContext += '    <td>'+druginfo.drugid+'</td>';
									tableContext += '    <td>'+druginfo.drugname+'</td>';
									tableContext += '    <td>'+druginfo.drugCategory.name+'</td>';
									tableContext += '    <td>￥'+druginfo.price+'</td>'; 
									
									if((druginfo.yt).length>18){
										tableContext += '    <td>'+(druginfo.yt).substring(1,18)+'......</td>';
									}else{
										tableContext += '    <td>'+(druginfo.yt)+'</td>';
									}
									tableContext += '    <td>';
									tableContext += '	      <button type="button" onclick="updateDruginfo(\''+druginfo.drugid+'\')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
									tableContext += '		  <button type="button" onclick="deleteUser(\''+druginfo.drugid+'\',\''+druginfo.drugname+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
									tableContext += '	  </td>';
									tableContext += '  </tr>';
								});
                                if(pageno>1){//上一页的判断
                                	PageContext+='<li><a href="#" onclick="pageQuery('+(pageno-1)+')">上一页</a></li>';
                                }
                                for(var i=1;i<=pageData.totalno;i++){
                                	if(i==pageno){
                                		PageContext+='<li class="active"><a href="#" onclick="pageQuery('+i+')">'+i+'</a></li>';
                                	}else{
                                		PageContext+='<li><a href="#" onclick="pageQuery('+i+')">'+i+'</a></li>';
                                	}
                                	
                                }
                                if(pageno<pageData.totalno){//下一页的判断
                                	PageContext+='<li><a href="#" onclick="pageQuery('+(pageno+1)+')">下一页</a></li>';
                                }
								$("#userData").html(tableContext);
								$(".pagination").html(PageContext); 
							} else {
								layer.msg("用户分页查询失败", {
									time : 1000,
									icon : 5,
									shift : 6
								}, function() {
								});
							}
						}
					});
		}
		//修改
		function updateDruginfo(uid){
			window.location.href="${APP_PATH}/drug/edit?id="+ uid;
		}
		
		//删除
		function deleteUser(uid,drugname){
			var index;
			layer.confirm("删除药品信息【"+drugname+"】,是否继续",  {icon: 3, title:'提示'}, function(cindex){
				//第一个代表的是确定按钮
				$.ajax({
					type:"POST",
					url:"${APP_PATH}/drug/deleteDrugInfoById",
					data:{
						"id":uid
					},
					success:function(result){
						if (result.success == true) {
							window.location.href="${APP_PATH}/drug/index";
						}else{
							layer.msg("药品信息删除失败", {
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
		}
	</script>
</body>
</html>
