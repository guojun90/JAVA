<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>订单信息</title>
<jsp:include page="${path}/common/jsp/head.jsp" />
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<script src="<%=path%>/order/js/test.js"></script>

<script type="text/javascript">
        var basePath = '<%=path%>';
</script>
</head>
<body>
	<div class="alert-box" id="createBox">
		<div class="alert-content">
			<div class="alert-content-top">
				<span>物流信息</span> <i class="close-btn" onclick="hideBox();">×</i>
			</div>

			<table id="dgPerDay">
				<thead>
				</thead>
			</table>

		</div>
		<div class="alert-box-bg"></div>
	</div>

	<div class="top-bar">
		<span class="top-left"></span>
		<div class="btn-group-lg">
			<button type="button" class="btn btn-default"
				onclick="window.location.href='../jsp/index.jsp'">首页</button>
			<button type="button" class="btn btn-default"
				onclick="window.location.href='../jsp/search.jsp'">订单查询</button>
			<button type="button" class="btn btn-default"
				onclick="window.location.href='../jsp/order.jsp'">添加订单</button>
			<button type="button" class="btn btn-default"
				onclick="window.location.href='../jsp/login.jsp'">登录</button>
			<button type="button" class="btn btn-default"
				onclick="window.location.href='../jsp/test.jsp'">测试</button>
		</div>
	</div>
	<div class="main">
		<div style="padding: 10px 10px 10px;"></div>
		<div class="input-append date" id="datetimepicker" data-date="12-02-2012" data-date-format="dd-mm-yyyy">
    <input size="16" type="text" value="12-02-2012" readonly>
    <span class="add-on"><i class="icon-th"></i></span>
</div>
	</div>
</body>
</html>
