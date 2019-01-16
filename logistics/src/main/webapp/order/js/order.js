$(function () {
	$('#shippingDate').datetimebox({
		format: 'yyyy-mm-dd hh:ii:ss',
	    language: 'zh-CN',
	});//'setValue', formatterDate(new Date())
	$('#orderReceptTime').datetimebox({
		required : false,  
	    onShowPanel:function(){  
	        $(this).datetimebox("setValue",formatterDate(new Date()));  
	    } 
	});
	$("#commitBtn").on("click",function(){
		console.log("开始添加订单");
		console.log("cargoName"+$("#cargoName").val())
		var requestUrl = basePath + '/order/add';
		var orderUrl = basePath + '/order/jsp/index.jsp'
		$.ajax({
			async : false,
			type : "POST",
			url : encodeURI(requestUrl),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				orderId : $("#orderId").val(),
				shippingOrderNo : $("#shippingOrderNo").val(),
				cargoName : $("#cargoName").val(),
//				shippingDate : $("#shippingDate").val(),
				shippingDate : timeToString($('#shippingDate').datetimebox('getValue')),
				senderName : $("#senderName").val(),
				fromLocation : $("#fromLocation").val(),
				toLocation : $("#toLocation").val(),
				orderStatus : $("#orderStatus").val(),
				orderReceptTime : timeToString($('#orderReceptTime').datetimebox('getValue')),
				senderPhone : $("#senderPhone").val(),
				receiverPhone : $("#receiverPhone").val(),
				receiverName : $("#receiverName").val()
				
			},
			dataType : "json",
			success : function(data) {
				
				if (data.responseMessage == "success") {
						alert("添加订单信息成功");
						window.location.href=orderUrl; 
				}else{
					alert("添加订单信息失败："+data.responseData);
					window.location.href=orderUrl; 
				}
			},
			error : function() {
				alert("出现失败");
			}
		})
	});
});
formatterDate = function (date) {
var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
+ (date.getMonth() + 1);
var hor = date.getHours();
var min = date.getMinutes();
var sec = date.getSeconds();
return date.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
};
// 日期格式转换12/31/2018 17:17:32 -> 2018-12-31 17:17:32
function timeToString(time) {
	if (time == "") {
		return "";
	}
	var a = new Array();
	date = time.split(" ");
	dateArr = date[0].split("/");
//	timeArr = date.split(":");
	var retval = dateArr[2] + "-" + dateArr[0] + "-" + dateArr[1]+" "+date[1];
	return retval;
}