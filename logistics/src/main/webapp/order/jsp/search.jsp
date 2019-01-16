<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	HttpSession s = request.getSession(); 
	String user;
%>
<html>
<head>
<title>订单信息</title>
<jsp:include page="${path}/common/jsp/head.jsp" />
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<script src="<%=path%>/assets/js/bootstrap.min.js"></script>
<script src="<%=path%>/order/js/common.js"></script>
<script src="<%=path%>/order/js/search.js"></script>

<script type="text/javascript">
        var basePath = '<%=path%>';
</script>
</head>
<body>
<%
	user = (String) s.getAttribute("loginName");
	if(user==null){
		%>
		<script language="javascript" type="text/javascript"> 
		window.location.href='login.jsp';
		</script><%
	}
%>
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
						<span>
							<%
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
						<li><a id ="logoutId">登出</a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
	
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
	<div class="main">
		<div class="search-bar">
			<span>订单编号：</span> <input type="text" id="orderId" class="se-text" />
			<span>用户：</span> <input type="text" id="userId" class="se-text" /> <input
				type="button" class="org-btn" id="searchBtn" value="查询" /> <input
				type="button" class="org-btn" id="reset" value="重置" />
		</div>

		<div class="ztable">
			<table id="dg">
				<thead>
				</thead>
			</table>
		</div>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加物流信息</h4>
					</div>
					<div class="modal-body">
						<form class="bs-example bs-example-form" role="form">
							<input type="hidden" id="orderId">
						<!-- 	<div class="am-input-group am-form-field ">
								<span class="input-group-addon">订单编号</span> <input type="text"
									class="form-control" id="orderId" aria-hidden="true"/>
							</div> -->
							<div class="am-input-group am-form-field ">
								<span class="input-group-addon">更新时间</span> <input type="text"
									class="form-control" id="updateTime" placeholder="更新时间"/>
							</div>
							<div class="am-input-group am-form-field ">
								<span class="input-group-addon">更新内容</span> <input type="text"
									class="form-control" id="updateMsg" placeholder="更新内容"/>
							</div>
							<div class="am-input-group am-form-field ">
								<span class="input-group-addon">更新状态</span> <input type="text"
									class="form-control" id="updateStatus" placeholder="更新状态"/>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary" id="commitRecord">提交更改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<div class="bottom"></div>
	</div>
</body>
</html>
