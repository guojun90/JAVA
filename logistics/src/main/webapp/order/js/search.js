$(function() {
	// 数据网格
	$("#dg").datagrid({
		pagination : true,
		singleSelect : true,
		columns : mainColumn
	})
	// 全部查询
//	actlist(1, 10);
	// 条件查询
	$("#searchBtn").on("click", function() {
		actlist(1, 10);
	});
	// 重置
	$("#reset").on("click", function(ev) {
		window.location.reload();
	});
	$('#myModal').on('hidden.bs.modal', function (e) {
		
	});
	$('#commitRecord').on("click", function() {
		commitRecord();
	});
	$("#updateTime").datetimebox();
	$("#orderId").addClass('disabled');
	
});
// datagride表头
var mainColumn = [ [ {
	field : 'orderId',
	title : '订单编号',
	width : 100,
	align : 'center',
	hidden : false,

}, {
	field : 'shippingOrderNo',
	title : '托运单号',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'cargoName',
	title : '货物名称',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'shippingDate',
	title : '托运日期',
	width : 100,
	align : 'center',
	hidden : false,
	sortable : true
}, {
	field : 'senderName',
	title : '寄件人',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'fromLocation',
	title : '发货地址',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'toLocation',
	title : '目的地址',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'orderStatus',
	title : '订单状态',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'orderReceptTime',
	title : '订单签收时间',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'senderPhone',
	title : '寄件人号码',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'receiverPhone',
	title : '收件人号码',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'receiverName',
	title : '收件人名',
	width : 100,
	align : 'center',
	hidden : false
}, {
	field : 'records',
	title : '物流信息',
	width : 100,
	align : 'center',
	hidden : false
} ] ];
var perDayColumn = [ [ {
	field : 'updateTime',
	title : '更新时间',
	width : 110,
	align : 'center',
	hidden : false
}, {
	field : 'updateMsg',
	title : '路由信息',
	width : 110,
	align : 'center',
	hidden : false
}, {
	field : 'updateStatus',
	title : '更新状态',
	width : 110,
	align : 'center',
	hidden : false
} ] ]
// 查询订单信息
function actlist(pageNum, pageCap) {

//	var orderId = "orderId";
	var orderId = $('#orderId').val();
	if (orderId == "") {
		alert("活动编号不能为空")
	} else {
		var requestUrl = basePath + '/order/query'
		$.ajax({
			method : 'POST',
			url : encodeURI(requestUrl),
			data : {
				orderId : orderId,
			},
			dataType : 'json',
			cache : false,
			beforeSend : function() {
			},
			complete : function() {
			},
			success : function(data) {

				if (data.responseMessage == "success") {
					var data_json = new Array();
					jsonData = data.responseData;
					var button1 = '<a onclick="showRecords(\''
							+ data.responseData.orderId + '\')">点击查看物流信息</a>'
					var button2 = '<a onclick="addRecords(\''
							+ data.responseData.orderId + '\')">'+data.responseData.orderId+'</a>'
					var row_data = {
						senderPhone : data.responseData.senderPhone,
						senderName : data.responseData.senderName,
						receiverPhone : data.responseData.receiverPhone,
						cargoName : data.responseData.cargoName,
						shippingDate : data.responseData.shippingDate,
						orderId : button2,
						shippingOrderNo : data.responseData.shippingOrderNo,
						receiverName : data.responseData.receiverName,
						orderReceptTime : data.responseData.orderReceptTime,
						orderStatus : data.responseData.orderStatus,
						toLocation : data.responseData.toLocation,
						fromLocation : data.responseData.fromLocation,
						records : button1
					};
					data_json.push(row_data);

					$('#dg').datagrid('loadData', data_json);

				} else {
					alert('加载出错！');
				}
			}
		});
	}
};
// 显示订单物流信息
function showRecords(orderId) {
	console.log("订单编号：" + orderId);
	isEdit = 0;
	$(".alert-box").hide();
	$("#createBox").show();
	$(".alert-content")
			.css(
					"marginTop",
					($(".alert-box").outerHeight() - $(".alert-content")
							.outerHeight()) / 2);
	$(".alert-content-inner").find("input").val("");
	var requestUrl = basePath + '/order/query/records'
	$.ajax({
		method : 'POST',
		url : encodeURI(requestUrl),
		data : {
			orderId : orderId,
		},
		dataType : 'json',
		cache : false,
		beforeSend : function() {
		},
		complete : function() {
		},
		success : function(data) {
			if (data.responseMessage == "success") {
				var data_json = new Array();
				// jsonData = data.responseData;
				for (var i = 0; i < data.responseData.length; i++) {
					var row_data = {
						updateTime : data.responseData[i].updateTime,
						updateMsg : data.responseData[i].updateMsg,
						updateStatus : data.responseData[i].updateStatus
					};
					data_json.push(row_data);
					$('#dgPerDay').datagrid('loadData', data_json);
				}
			} else {
				alert('加载出错！');
			}
		}
	});
	$("#dgPerDay").datagrid({
		pagination : false,
		singleSelect : true,
		columns : perDayColumn
	})
}
function addRecords(orderId){
	console.log("添加物流信息");
	$('#myModal').modal('show');
	$('#orderId').val(orderId);
}
function commitRecord(){
	var orderId = $('#orderId').val();
	var updateTime = timeToString($('#updateTime').datetimebox('getValue'));
	var updateMsg = $('#updateMsg').val();
	var updateStatus = $('#updateStatus').val();
	var requestUrl = basePath + '/order/addRecord'
	console.log("orderId"+orderId);
	$.ajax({
		method : 'POST',
		url : encodeURI(requestUrl),
		data : {
			orderId : orderId,
			updateTime : updateTime,
			updateMsg : updateMsg,
			updateStatus : updateStatus
			
		},
		dataType : 'json',
		cache : false,
		beforeSend : function() {
		},
		complete : function() {
		},
		success : function(data) {
			if (data.responseMessage == "success") {
				alert("添加物流信息成功");
				$("#myModal").modal('hide');
			} else {
				alert('添加物流信息失败');
			}
		}
	});
}
//日期格式转换12/31/2018 17:17:32 -> 2018-12-31 17:17:32
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
