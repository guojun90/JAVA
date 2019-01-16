$(function() {
	$("#1bt").on('click',function(){
		console.log("qweqwe");	
	})
	$('#submitBtn').on('click', function(ev) {
		console.log("123");
		actlist(1, 10);
		var c = $('#sendDateStart').datebox('getValue');
		console.log(dateToString(c));
		console.log(dateToString($('#createDateStart').datebox('getValue')));
	});
	$("#bt").on('click',function(){
		var requestUrl = basePath + '/order/add';
        console.log("asdfds")
        $.ajax({
            async: false,
            type: "POST",
            url: encodeURI(requestUrl),
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            data:$("#apply_link_form").serialize(),
            dataType: "text",
            success: function () {
              },
            error: function () {
            }
        })
    })
	$("#submitTestId").click(
			function(){
				var requestUrl = basePath + '/order/add'
				$.ajax({
					method : 'POST',
					url : encodeURI(requestUrl),
					data : {
						orderId : "orderId"
					},
					dataType : 'json',
					cache : false,
					beforeSend : function() {
					},
					complete : function() {
					},
					success : function(data) {
						
					}
				});
			});
	// 数据网格
	$("#dg").datagrid({
		pagination : true,
		singleSelect : true,
		columns : mainColumn
	})
	// 日期控件
	$("#createDateStart").datebox();
	$("#date").datebox();
	$("#createDateEnd").datebox();
	$("#sendDateStart").datebox();
	$("#sendDateEnd").datebox();
	// 全部查询
	actlist(1, 10);
	// 条件查询
	$("#searchBtn").on("click", function(ev) {
		actlist(1, 10);
		var c = $('#sendDateStart').datebox('getValue');
		console.log(dateToString(c));
		console.log(dateToString($('#createDateStart').datebox('getValue')));
	});
	// 重置
	$("#reset").on("click", function(ev) {
		window.location.reload();
	});
	// 导出
	$("#excelExport").on("click", function(ev) {
		download();
	});
	// 客群状态改变
	$("#cgpStatus").change(function() {
		actlist(1, 10);
		console.log($("#cgpStatus").val());
	})
	// 活动状态改变
	$("#status").change(function() {
		actlist(1, 1000);
		console.log($("#status").val());
	})

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
					var button1 = '<a onclick="showInfoPerDay(\''
							+ data.responseData.orderId + '\')">点击查看物流信息</a>'
					var row_data = {
						senderPhone : data.responseData.senderPhone,
						senderName : data.responseData.senderName,
						receiverPhone : data.responseData.receiverPhone,
						cargoName : data.responseData.cargoName,
						shippingDate : data.responseData.shippingDate,
						orderId : data.responseData.orderId,
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
					// var pager = $("#dg").datagrid("getPager");
					// pager.pagination({
					// total : data.total,
					// pageNumber : pageNum,
					// onSelectPage : function(pageNo, pageSize) {
					// actlist(pageNo, pageSize);
					// }
					// });

				} else {
					alert('加载出错！');
				}
			}
		});
	}
};
// 测试用
function sayhello() {
	var requestUrl = basePath + '/operview/say';
	$.ajax({
		url : encodeURI(requestUrl),
		dataType : 'json',
		type : 'POST',
		traditional : true,
		success : function(data) {
			console.log("连接成功");
		},

	});

}
// 日期格式转换
function dateToString(date) {
	if (date == "") {
		return "";
	}
	var a = new Array();
	a = date.split("/");
	var s = a[2] + "-" + a[0] + "-" + a[1];
	return s;
}
function showRecords(redords) {
	alert(":" + redords)
	console.log("显示订单物流信息:" + redords);
	$(".alert-box").hide();
	$("#createBox").show();
	$(".alert-content")
			.css(
					"marginTop",
					($(".alert-box").outerHeight() - $(".alert-content")
							.outerHeight()) / 2);
	$(".alert-content-inner").find("input").val("");
	var data_json = new Array();
	console.log("row_data:" + redords.updateMsg);
	var row_data = {
		updateTime : redords.updateTime,
		recordMsg : redords.updateMsg,
		updateStatus : redords.updateStatus
	};
	data_json.push(row_data);
	$('#dgPerDay').datagrid('loadData', data_json);

	$("#dgPerDay").datagrid({
		pagination : false,
		singleSelect : true,
		columns : perDayColumn
	})
}
// 显示订单物流信息
function showInfoPerDay(orderId) {
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
// 客群状态映射
function cgpStatusToCh(En) {

	var csMap = new Map();
	csMap.set("running", "创建中");
	csMap.set("done", "已完成");
	csMap.set("error", "失败");
	return csMap.get(En);

}
function statusToCh(En) {
	var sMap = new Map();
	sMap.set("pending", "待投放");
	sMap.set("running", "投放中");
	sMap.set("done", "已完成");
	sMap.set("cancel", "取消");
	return sMap.get(En);
}
// 下载当前页面数据
function download() {

	var bidActId = $('#bidActId').val();
	var buyerId = $('#buyerId').val();
	var status = $("#status").val();
	var cgpStatus = $("#cgpStatus").val();
	var sendDateStart = dateToString($('#sendDateStart').datebox('getValue'));
	var sendDateEnd = dateToString($('#sendDateEnd').datebox('getValue'));
	var createDateStart = dateToString($('#createDateStart')
			.datebox('getValue'));
	var createDateEnd = dateToString($('#createDateEnd').datebox('getValue'));

	var requestUrl = basePath + '/operview/searchNoPage'

	$.ajax({
		method : 'POST',
		url : encodeURI(requestUrl),
		data : {
			bidActId : bidActId,
			buyerId : buyerId,
			status : status,
			cgpStatus : cgpStatus,
			sendDateStart : sendDateStart,
			sendDateEnd : sendDateEnd,
			createDateStart : createDateStart,
			createDateEnd : createDateEnd,
		},
		dataType : 'json',
		cache : false,
		beforeSend : function() {
		},
		complete : function() {
		},
		success : function(data) {

			if (data.success == true) {
				var data_json = new Array();
				if (data.success == true && data.data.length >= 0) {
					jsonData = data.data;
					for (var i = 0; i < data.data.length; i++) {
						var row_data = {
							活动编号 : data.data[i].bidActId,
							活动创建时间 : data.data[i].auctionCreateTime,
							活动创建人 : data.data[i].buyerId,
							客群编号 : data.data[i].cgpId,
							客群状态 : cgpStatusToCh(data.data[i].cgpStatus),
							客群人数 : data.data[i].cgpCnt,
							初始报价 : (data.data[i].price).toFixed(2),
							活动预算 : (data.data[i].budget).toFixed(2),
							活动状态 : statusToCh(data.data[i].status),
							投放开始时间 : data.data[i].sendStartDate,
							投放结束时间 : data.data[i].sendEndDate,
							投放量 : data.data[i].count,
							营收 : (data.data[i].sumPrice).toFixed(2)
						};
						data_json.push(row_data);
					}
					JSONToCSVConvertor(data_json, "活动投放情况", true);
				}
			} else {
				alert('加载出错！');
			}
		}
	});
};
// jason转csv并下载
function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {
	// If JSONData is not an object then JSON.parse will parse the JSON string
	// in an Object
	var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;

	var CSV = '';
	// Set Report title in first row or line

	CSV += ReportTitle + '\r\n\n';

	// This condition will generate the Label/Header
	if (ShowLabel) {
		var row = "";

		// This loop will extract the label from 1st index of on array
		for ( var index in arrData[0]) {

			// Now convert each value to string and comma-seprated
			row += index + ',';
		}

		row = row.slice(0, -1);

		// append Label row with line break
		CSV += row + '\r\n';
	}

	// 1st loop is to extract each row
	for (var i = 0; i < arrData.length; i++) {
		var row = "";

		// 2nd loop will extract each column and convert it in string
		// comma-seprated
		for ( var index in arrData[i]) {
			row += '"' + arrData[i][index] + "" + '",';
		}

		row.slice(0, row.length - 1);

		// add a line break after each row
		CSV += row + '\r\n';
	}

	if (CSV == '') {
		alert("Invalid data");
		return;
	}

	// Generate a file name
	var fileName = "活动投放情况";
	// this will remove the blank-spaces from the title and replace it with an
	// underscore
	// fileName += ReportTitle.replace(/ /g,"_");

	// Initialize file format you want csv or xls
	var uri = 'data:text/csv;charset=utf-8,\uFEFF' + encodeURI(CSV);

	// Now the little tricky part.
	// you can use either>> window.open(uri);
	// but this will not work in some browsers
	// or you will not get the correct file extension

	// this trick will generate a temp <a /> tag
	var link = document.createElement("a");
	link.href = uri;

	// set the visibility hidden so it will not effect on your web-layout
	link.style = "visibility:hidden";
	link.download = fileName + ".csv";

	// this part will append the anchor tag and remove it after automatic click
	document.body.appendChild(link);
	link.click();
	document.body.removeChild(link);
}
