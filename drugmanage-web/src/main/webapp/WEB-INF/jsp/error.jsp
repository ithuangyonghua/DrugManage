<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body>

</body>
</html>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/layer/layer.js"></script>
<script>
	layer.confirm("非法访问,请您重新登录", {
		icon : 3,
		title : '提示'
	}, function(cindex) {
		layer.close(cindex);
		window.location.href="${APP_PATH}/logout"
	}, function(cindex) {
		//第二个代表的是取消按钮
		window.location.href="${APP_PATH}/logout"
		layer.close(cindex);
	});
</script>