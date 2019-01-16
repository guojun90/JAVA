/**
 * Created by wangtianfeng on 2018/11/13.
 */

$(function () {
    //数据网格
    $("#dg").datagrid({
        pagination: true,
        singleSelect: true,
        columns: mainColumn,
        sortName:'cgpId',
        sortOrder:'asc'

    })
    //日期控件
    $("#createDateStart").datebox();
    $("#createDateEnd").datebox();
    $("#sendDateStart").datebox();
    $("#sendDateEnd").datebox();
    //全部查询
       actlist(1,10);
    //条件查询
    $("#searchBtn").on("click", function (ev) {
        actlist(1, 10);
        var c=$('#sendDateStart').datebox('getValue');
        console.log(dateToString(c));
        console.log(dateToString($('#createDateStart').datebox('getValue')));
    });
    //重置
    $("#reset").on("click", function (ev) {
        window.location.reload();
    });
    //导出
    $("#excelExport").on("click", function (ev) {
       download();
    });
    //客群状态改变
    $("#cgpStatus").change(function(){
       actlist(1,10);
        console.log($("#cgpStatus").val());
    })
    //活动状态改变
    $("#status").change(function(){
        actlist(1,1000);
        console.log($("#status").val());
    })

});
//datagride表头
var mainColumn = [[
    {
        field: 'bidActId',
        title: '活动编号',
        width: 100,
        align: 'center',
        hidden: false,

    }, {
        field: 'auctionCreateTime',
        title: '活动创建时间',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'buyerId',
        title: '活动创建人',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'cgpId',
        title: '客群编号',
        width: 100,
        align: 'center',
        hidden: false,
        sortable: true
    }, {
        field: 'cgpStatus',
        title: '客群状态',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'cgpCnt',
        title: '客群人数',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'price',
        title: '初始报价',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'budget',
        title: '活动预算',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'status',
        title: '活动状态',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'sendStartDate',
        title: '投放开始时间',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'sendEndDate',
        title: '投放结束时间',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'count',
        title: '投放量',
        width: 100,
        align: 'center',
        hidden: false
    }, {
        field: 'sumPrice',
        title: '营收',
        width: 100,
        align: 'center',
        hidden: false
    }
]];
var perDayColumn = [[
    {
        field: 'winDate',
        title: '日期',
        width: 110,
        align: 'center',
        hidden: false
    },{
        field: 'count',
        title: '投放量',
        width: 110,
        align: 'center',
        hidden: false
    },{
        field: 'limit',
        title: '限额',
        width: 110,
        align: 'center',
        hidden: false
    },{
        field: 'price',
        title: '营收',
        width: 110,
        align: 'center',
        hidden: false
    }
]]
//查询所有内容
function actlist(pageNum, pageCap) {

    var bidActId= $('#bidActId').val();
    var buyerId= $('#buyerId').val();
    var status=$("#status").val();
    var cgpStatus=$("#cgpStatus").val();
    var sendDateStart=dateToString($('#sendDateStart').datebox('getValue'));
    var sendDateEnd=dateToString($('#sendDateEnd').datebox('getValue'));
    var createDateStart=dateToString($('#createDateStart').datebox('getValue'));
    var createDateEnd=dateToString($('#createDateEnd').datebox('getValue'));

    var requestUrl = basePath + '/operview/search'

    $.ajax({
        method: 'POST',
        url: encodeURI(requestUrl),
        data:{
            bidActId:bidActId,
            buyerId:buyerId,
            status:status,
            cgpStatus:cgpStatus,
            sendDateStart:sendDateStart,
            sendDateEnd:sendDateEnd,
            createDateStart:createDateStart,
            createDateEnd:createDateEnd,
            pageId:pageNum,
            pageCap:pageCap
        },
        dataType: 'json',
        cache: false,
        beforeSend: function () {
        },
        complete: function () {
        },
        success: function (data) {

            if (data.success == true) {
                var data_json = new Array();
                if (data.success == true && data.data.length >= 0) {
                    jsonData = data.data;

                    for (var i = 0; i < data.data.length; i++) {

                        //var button1 = '<button  onclick="showInfoPerDay(\'' + data.data[i].bidActId + '\')">'+data.data[i].bidActId+'</button>';
                        var button1='<a onclick="showInfoPerDay(\'' + data.data[i].bidActId + '\')">'+data.data[i].bidActId+'</a>'
                        var row_data = {
                            bidActId: button1
                            , auctionCreateTime: data.data[i].auctionCreateTime
                            , buyerId: data.data[i].buyerId
                            , cgpId: data.data[i].cgpId
                            , cgpStatus:cgpStatusToCh(data.data[i].cgpStatus)
                            , cgpCnt: data.data[i].cgpCnt
                            , price: (data.data[i].price).toFixed(2)
                            , budget: (data.data[i].budget).toFixed(2)
                            , status:statusToCh( data.data[i].status)
                            , sendStartDate: data.data[i].sendStartDate
                            , sendEndDate: data.data[i].sendEndDate
                            , count: data.data[i].count
                            , sumPrice: (data.data[i].sumPrice).toFixed(2)
                        };
                        data_json.push(row_data);

                    }
                    $('#dg').datagrid('loadData', data_json);
                    var pager = $("#dg").datagrid("getPager");
                    pager.pagination({
                        total: data.total,
                        pageNumber: pageNum,
                        onSelectPage: function (pageNo, pageSize) {
                            actlist(pageNo, pageSize);
                        }
                    });
                }
            } else {
                alert('加载出错！');
            }
        }
    });
};
//测试用
function sayhello(){
            var requestUrl = basePath + '/operview/say';
            $.ajax({
                url : encodeURI(requestUrl),
                dataType : 'json',
                type : 'POST',
                traditional: true,
                success : function(data) {
                    console.log("连接成功");
                },

            });



}
//日期格式转换
function dateToString(date){
    if(date==""){
        return "";
    }
    var a=new Array();
    a=date.split("/");
    var s=a[2]+"-"+a[0]+"-"+a[1];
    return s;
}
//显示每日投放情况
function showInfoPerDay(bidActId){
    console.log("显示每日信息"+bidActId);
    isEdit = 0;
    $(".alert-box").hide();
    $("#createBox").show();
    $(".alert-content").css("marginTop", ($(".alert-box").outerHeight() - $(".alert-content").outerHeight()) / 2);
    $(".alert-content-inner").find("input").val("");
    var requestUrl = basePath + '/operview/searchPerDay'
    $.ajax({
        method: 'POST',
        url: encodeURI(requestUrl),
        data:{
            bidActId:bidActId,
        },
        dataType: 'json',
        cache: false,
        beforeSend: function () {
        },
        complete: function () {
        },
        success: function (data) {
            if (data.success == true) {
                var data_json = new Array();
                if (data.success == true && data.data.length >= 0) {
                    jsonData = data.data;
                    for (var i = 0; i < data.data.length; i++) {
                        var row_data = {
                            winDate: data.data[i].winDate
                            , count: data.data[i].count
                            , limit: data.limit
                            , price: data.data[i].price
                        };
                        data_json.push(row_data);
                    }
                    $('#dgPerDay').datagrid('loadData', data_json);
                }
            } else {
                alert('加载出错！');
            }
        }
    });
    $("#dgPerDay").datagrid({
        pagination: false,
        singleSelect: true,
        columns: perDayColumn
    })
}
//客群状态映射
function cgpStatusToCh(En){

    var csMap=new Map();
    csMap.set("running","创建中");
    csMap.set("done","已完成");
    csMap.set("error","失败");
    return csMap.get(En);

}
function statusToCh(En){
    var sMap=new Map();
    sMap.set("pending","待投放");
    sMap.set("running","投放中");
    sMap.set("done","已完成");
    sMap.set("cancel","取消");
    return sMap.get(En);
}
//下载当前页面数据
function download() {

    var bidActId= $('#bidActId').val();
    var buyerId= $('#buyerId').val();
    var status=$("#status").val();
    var cgpStatus=$("#cgpStatus").val();
    var sendDateStart=dateToString($('#sendDateStart').datebox('getValue'));
    var sendDateEnd=dateToString($('#sendDateEnd').datebox('getValue'));
    var createDateStart=dateToString($('#createDateStart').datebox('getValue'));
    var createDateEnd=dateToString($('#createDateEnd').datebox('getValue'));

    var requestUrl = basePath + '/operview/searchNoPage'

    $.ajax({
        method: 'POST',
        url: encodeURI(requestUrl),
        data:{
            bidActId:bidActId,
            buyerId:buyerId,
            status:status,
            cgpStatus:cgpStatus,
            sendDateStart:sendDateStart,
            sendDateEnd:sendDateEnd,
            createDateStart:createDateStart,
            createDateEnd:createDateEnd,
        },
        dataType: 'json',
        cache: false,
        beforeSend: function () {
        },
        complete: function () {
        },
        success: function (data) {

            if (data.success == true) {
                var data_json = new Array();
                if (data.success == true && data.data.length >= 0) {
                    jsonData = data.data;
                    for (var i = 0; i < data.data.length; i++) {
                        var row_data = {
                            活动编号: data.data[i].bidActId
                            , 活动创建时间: data.data[i].auctionCreateTime
                            , 活动创建人: data.data[i].buyerId
                            , 客群编号: data.data[i].cgpId
                            , 客群状态:cgpStatusToCh(data.data[i].cgpStatus)
                            , 客群人数: data.data[i].cgpCnt
                            , 初始报价: (data.data[i].price).toFixed(2)
                            , 活动预算: (data.data[i].budget).toFixed(2)
                            , 活动状态:statusToCh( data.data[i].status)
                            , 投放开始时间: data.data[i].sendStartDate
                            , 投放结束时间: data.data[i].sendEndDate
                            , 投放量: data.data[i].count
                            , 营收: (data.data[i].sumPrice).toFixed(2)
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
//jason转csv并下载
function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {
    //If JSONData is not an object then JSON.parse will parse the JSON string in an Object
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;

    var CSV = '';
    //Set Report title in first row or line

    CSV += ReportTitle + '\r\n\n';

    //This condition will generate the Label/Header
    if (ShowLabel) {
        var row = "";

        //This loop will extract the label from 1st index of on array
        for (var index in arrData[0]) {

            //Now convert each value to string and comma-seprated
            row += index + ',';
        }

        row = row.slice(0, -1);

        //append Label row with line break
        CSV += row + '\r\n';
    }

    //1st loop is to extract each row
    for (var i = 0; i < arrData.length; i++) {
        var row = "";

        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + "" + '",';
        }

        row.slice(0, row.length - 1);

        //add a line break after each row
        CSV += row + '\r\n';
    }

    if (CSV == '') {
        alert("Invalid data");
        return;
    }

    //Generate a file name
    var fileName = "活动投放情况";
    //this will remove the blank-spaces from the title and replace it with an underscore
    //fileName += ReportTitle.replace(/ /g,"_");

    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,\uFEFF' + encodeURI(CSV);

    // Now the little tricky part.
    // you can use either>> window.open(uri);
    // but this will not work in some browsers
    // or you will not get the correct file extension

    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");
    link.href = uri;

    //set the visibility hidden so it will not effect on your web-layout
    link.style = "visibility:hidden";
    link.download = fileName + ".csv";

    //this part will append the anchor tag and remove it after automatic click
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}




