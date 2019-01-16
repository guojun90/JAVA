$(function() {
	$("#loginBtn").on("click", function() {
		console.log("开始登录");
		var requestUrl = basePath + '/user/login';
		var loginSuccessUrl = basePath + '/order/jsp/index.jsp';
		$.ajax({
			async : false,
			type : "POST",
			url : encodeURI(requestUrl),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				userName : $("#username").val(),
				pwd : $("#password").val()
			},
			dataType : "json",
			success : function(data) {

				if (data.responseMessage == "success") {
					alert("登录成功");
					window.location.href = loginSuccessUrl;
				} else {
					alert("登录失败");
				}
			},
			error : function() {
				alert("出现失败");
			}
		})
	});
	$("#logout").on("click", function() {
		var logoutUrl = basePath + '/order/jsp/index.jsp';
		session = request.getSession();
		session.invalidate();
		window.location.href = logoutUrl;
	});

})
function login() {
	console.log("222222");
	var requestUrl = basePath + '/order/add';
	// parent.layer.close(index); //再执行关闭
	$.ajax({
		async : false,
		type : "POST",
		url : encodeURI(requestUrl),
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : $("#order_login_form").serialize(),
		dataType : "text",
		success : function() {
			alert("成功过");
		},
		error : function() {
		}
	})
}
