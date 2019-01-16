$(function() {
	
	$("#logoutId").on("click", function() {
		var requestUrl = basePath + '/user/logout';
		var logoutUrl = basePath + '/order/jsp/index.jsp';

		$.ajax({
			async : false,
			type : "POST",
			url : encodeURI(requestUrl),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {},
			dataType : "json",
			success : function(data) {
				
				if (data.responseMessage == "success") {
						window.location.href=logoutUrl; 
				}else{
					alert("登出失败");
				}
			},
			error : function() {
				alert("出现失败");
			}
		})
	});
})

