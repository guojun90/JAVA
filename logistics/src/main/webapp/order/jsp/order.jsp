<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	HttpSession s = request.getSession(); 
	String user;
%>
<html>
<head>
<title>订单查询</title>
<jsp:include page="${path}/common/jsp/head.jsp" />
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<script src="<%=path%>/order/js/common.js"></script>
<script src="<%=path%>/order/js/order.js"></script>

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
	
	<div class="main">
		<div style="padding: 10px 10px 10px;">
			<form id="order_login_form" class="bs-example bs-example-form"
				role="form">
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">订单编号</span> <input type="text"
						class="form-control" id="orderId">
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">托运单号</span> <input type="text"
						class="form-control" id="shippingOrderNo">
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">货物名称</span> <input type="text"
						class="form-control" id="cargoName" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">托运日期</span> <input type="text"
						class="form-control" id="shippingDate" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">发货地址</span> <input type="text"
						class="form-control" id="fromLocation" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">目的地址</span> <input type="text"
						class="form-control" id="toLocation" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">寄件人</span> <input type="text"
						class="form-control" id="senderName" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">寄件人号码</span> <input type="text"
						class="form-control" id="senderPhone" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">收件人</span> <input type="text"
						class="form-control" id="receiverName" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">收件人号码</span> <input type="text"
						class="form-control" id="receiverPhone" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">订单状态</span> <input type="text"
						class="form-control" id="orderStatus" />
				</div>
				<div class="am-input-group am-form-field ">
					<span class="input-group-addon">签收时间</span> <input type="text"
						class="form-control" id="orderReceptTime" />
				</div>

				<br>
				<div class="form-group">
					<div class="col-sm-offset col-sm-5">
						<button id="commitBtn" class="btn btn-success pull-right">提交</button>
					</div>
				</div>

			</form>
		</div>

		<div class="bottom"></div>
	</div>

</body>
</html>
