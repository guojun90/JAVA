<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	HttpSession s = request.getSession(); 
%>
<html>
<head>
<title>订单信息</title>
<jsp:include page="${path}/common/jsp/head.jsp" />
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<script src="<%=path%>/order/js/main.js"></script>

<script type="text/javascript">
        var basePath = '<%=path%>';
</script>
</head>
<body>
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
								String user = (String) s.getAttribute("loginName");
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
						<li><a <%session = request.getSession(); session.invalidate();%> href="index.jsp">登出</a></li>
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
	<form id="testForm"  class="form-horizontal" role="form" onsubmit="return false">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">名字</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="orderId" 
				   placeholder="请输入名字">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">姓</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="lastname" 
				   placeholder="请输入姓">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<div class="checkbox">
				<label>
					<input type="checkbox"> 请记住我
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input type="button" class="org-btn" id="submitBtn" value="查询" /> 
		</div>
	</div>
</form>
	<!-- <div class="main">
			<div class="search-bar">
				<span>订单编号：</span> <input type="text" id="orderId" class="se-text" width ="40px"/>
				<span>用户：</span> <input type="text" id="userId" class="se-text" />
				<input type="button" class="org-btn" id="searchBtn" value="查询" /> <input
					type="button" class="org-btn" id="reset" value="重置" />
			</div>

			<div class="ztable">
				<table id="dg">
					<thead>
					</thead>
				</table>
			</div>
			<div class="bottom"></div>
		</div> -->
</body>
</html>
