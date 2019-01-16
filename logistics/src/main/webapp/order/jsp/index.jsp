<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	HttpSession s = request.getSession(); 
	String user;
%>
<html>
<head>
<title>首页</title>
<jsp:include page="${path}/common/jsp/head.jsp" />
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<script src="<%=path%>/order/js/common.js"></script>
<script src="<%=path%>/order/js/index.js"></script>


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
			<div class="pull-right">
				<div class="am-dropdown" data-am-dropdown>
					<button class="am-btn am-btn-link am-btn-sm am-dropdown-toggle"
						data-am-dropdown-toggle>
						<span> <%
 	user = (String) s.getAttribute("loginName");
 	if (user != null) {
 		out.println(user);
 	} else {
 		out.println("登录");
 	}
 %>
						</span> <span class="am-icon-caret-down"></span>
					</button>
					<ul class="am-dropdown-content">
						<li><a href="login.jsp">登录</a></li>
						<li><a id="logoutId">登出</a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
	<div class="main">
		<div style="padding: 10px 10px 10px;"></div>



		<div class="bottom"></div>
	</div>
</body>
</html>
