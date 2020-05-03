<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="utf-8">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/main.css">
<link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
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
					<a class="navbar-brand" style="font-size: 32px;" href="#">药品管理
						- 许可维护</a>
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
								<li><a href="${APP_PATH}/logout"><i
										class="glyphicon glyphicon-off"></i> 退出系统</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<%-- <%@include file="/WEB-INF/jsp/common/top.jsp" %> --%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="tree">
					<%@include file="/WEB-INF/jsp/common/menue.jsp"%>
				</div>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
</body>
</html>
<script>
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
	});
	/* var zNodes =[
	 { name:"父节点1 - 展开", open:true,
	 children: [
	 { name:"父节点11 - 折叠",
	 children: [
	 { name:"叶子节点111"},
	 { name:"叶子节点112"},
	 { name:"叶子节点113"},
	 { name:"叶子节点114"}
	 ]},
	 { name:"父节点12 - 折叠",
	 children: [
	 { name:"叶子节点121"},
	 { name:"叶子节点122"},
	 { name:"叶子节点123"},
	 { name:"叶子节点124"}
	 ]},
	 { name:"父节点13 - 没有子节点", isParent:true}
	 ]},
	 { name:"父节点2 - 折叠",
	 children: [
	 { name:"父节点21 - 展开", open:true,
	 children: [
	 { name:"叶子节点211"},
	 { name:"叶子节点212"},
	 { name:"叶子节点213"},
	 { name:"叶子节点214"}
	 ]},
	 { name:"父节点22 - 折叠",
	 children: [
	 { name:"叶子节点221"},
	 { name:"叶子节点222"},
	 { name:"叶子节点223"},
	 { name:"叶子节点224"}
	 ]},
	 { name:"父节点23 - 折叠",
	 children: [
	 { name:"叶子节点231"},
	 { name:"叶子节点232"},
	 { name:"叶子节点233"},
	 { name:"叶子节点234"}
	 ]}
	 ]},
	 { name:"父节点3 - 没有子节点", isParent:true}

	 ]; */
	var setting = {
		async : {
			enable : true,
			url : "${APP_PATH}/permission/loadData",
			autoParam : [ "id", "name=n", "level=lv" ]
		},
		view : {
			selectedMulti : false,//是否多选,false为否
			addDiyDom : function(treeId, treeNode) {//treeId:当前节点的ID,treeNode:当前节点的json 该方法是改变菜单的图标
				var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
				if (treeNode.icon) {
					//icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background","");
					icoObj.removeClass("button ico_docu ico_open").addClass(
							treeNode.icon).css("background", "");
				}

			},
			addHoverDom : function(treeId, treeNode) { //在节点后添加按钮
				//   <a><span></span></a>
				var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
				aObj.attr("href", "javascript:;");
				aObj.attr("target","");
				if (treeNode.editNameFlag
						|| $("#btnGroup" + treeNode.tId).length > 0)
					return;
				var s = '<span id="btnGroup'+treeNode.tId+'">';
				if (treeNode.level == 0) {//根节点
					s += '<a class="btn btn-info dropdown-toggle btn-xs"  href="javascript:;" target="" style="margin-left:10px;padding-top:0px;" onclick="addPermission('
							+ treeNode.id
							+ ')">&nbsp;&nbsp;<i   class="fa fa-fw fa-plus rbg "></i></a>';
				} else if (treeNode.level == 1) {//一级节点
					s += '<a class="btn btn-info dropdown-toggle btn-xs"  href="javascript:;" target="" style="margin-left:10px;padding-top:0px;"  onclick="updatePermission('
							+ treeNode.id
							+ ')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
					if (treeNode.children.length == 0) {
						s += '<a class="btn btn-info dropdown-toggle btn-xs"  target=""  href="javascript:;" style="margin-left:10px;padding-top:0px;" onclick= "deleteePermission('
								+ treeNode.id
								+ ',\''
								+ treeNode.name
								+ '\')" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
					}
					s += '<a class="btn btn-info dropdown-toggle btn-xs"   href="javascript:;" target="" style="margin-left:10px;padding-top:0px;" onclick="addPermission('
							+ treeNode.id
							+ ')"   >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
				} else if (treeNode.level == 2) {//
					s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  target="" href="javascript:;" onclick="updatePermission('
							+ treeNode.id
							+ ')" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
					s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" target="" href="javascript:;" onclick= "deleteePermission('
							+ treeNode.id
							+ ',\''
							+ treeNode.name
							+ '\')" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
				}

				s += '</span>';
				aObj.after(s);
			},
			removeHoverDom : function(treeId, treeNode) {//在节点后
				$("#btnGroup" + treeNode.tId).remove();
			}
		}
	};
	var zNodes;
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	//添加
	function addPermission(pid) {
		window.location.href = "${APP_PATH}/permission/add?pid=" + pid;
	}
	//修改
	function updatePermission(id) {
		window.location.href = "${APP_PATH}/permission/edit?id=" + id;
	}
	//删除
	function deleteePermission(id, name) {
		layer.confirm("删除【" + name + "】节点信息,是否继续", {
			icon : 3,
			title : '提示'
		}, function(cindex) {
			$.ajax({
				type : "POST",
				url : "${APP_PATH}/permission/delete",
				data : {
					"id" : id
				},
				success : function(result) {
					if (result.success == true) {
						//刷新Tree
						var treeObject = $.fn.zTree.getZTreeObj("treeDemo");
						treeObject.reAsyncChildNodes(null, "refresh");
						layer.close(cindex);
					} else {
						layer.msg("节点信息删除失败", {
							time : 1000,
							icon : 5,
							shift : 6
						}, function() {
						});
					}
				}
			});
		}, function(cindex) {
			layer.close(cindex);
		});
	}
</script>
