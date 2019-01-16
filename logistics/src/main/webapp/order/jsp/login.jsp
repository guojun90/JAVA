<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	HttpSession s = request.getSession();
%>
<html>
<head>
<title>登录</title>
<jsp:include page="${path}/common/jsp/head.jsp" />
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<script src="<%=path%>/order/js/login.js"></script>
<script src="<%=path%>/order/js/common.js"></script>

<script type="text/javascript">
        var basePath = '<%=path%>';
</script>
</head>
<body>
	<div class="top-bar">
		<span class="top-left"></span>
		<div class="btn-group-lg">
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
						<li><a id ="logoutId">登出</a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="form row">
			<div class="form-horizontal col-md-offset-4" id="login_form">
				<h3 class="form-title">登录</h3>
				<div class="col-md-4">
					<div class="form-group">
						<i class="fa fa-user fa-lg"></i> <input
							class="form-control required" type="text" placeholder="Username"
							id="username" name="username" autofocus="autofocus"
							maxlength="20" />
					</div>
					<div class="form-group">
						<i class="fa fa-lock fa-lg"></i> <input
							class="form-control required" type="password"
							placeholder="Password" id="password" name="password"
							maxlength="8" />
					</div>
					<div class="form-group">
						<label class="checkbox"> <input type="checkbox"
							name="remember" value="1" />记住我
						</label>
					</div>
					<div class="form-group col-md-offset-7">
						<button id="loginBtn" class="btn btn-success pull-right"
							name="submit">登录</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
