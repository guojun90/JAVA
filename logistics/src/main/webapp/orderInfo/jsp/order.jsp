<%--
  Created by IntelliJ IDEA.
  User: wangtianfeng
  Date: 2018/11/13
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <jsp:include page="${path}/common/jsp/head.jsp"/>
    <script src="<%=path%>/assets/js/jquery.min.js"></script>
    <script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
    <script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
    <script src="<%=path%>/assets/js/amazeui.min.js"></script>
    <script src="<%=path%>/orderInfo/js/main.js"></script>

    <script type="text/javascript">
        var basePath = '<%=path%>';

    </script>
</head>
<body>
<div class="alert-box" id="createBox">
    <div class="alert-content">
        <div class="alert-content-top">
            <span>订单</span>
            <i class="close-btn" onclick="hideBox();">×</i>
        </div>

                <table id="dgPerDay">
                    <thead>
                    </thead>
                </table>

    </div>
    <div class="alert-box-bg"></div>
</div>

<div class="top-bar">
    <span class="top-left"></span>
</div>
<div class="main">
    <div class="search-bar">
        <span>活动编号：</span>
        <input type="text" id="bidActId" class="se-text"/>
        <span>活动创建人：</span>
        <input type="text" id="buyerId" class="se-text"/>
        <span>客群状态：</span>
        <select id="cgpStatus">
            <option value="">全部</option>
            <option value="running">创建中</option>
            <option value="done">已完成</option>
            <option value="error">失败</option>
        </select>
        <span>活动状态：</span>
        <select id="status">
            <option value="">全部</option>
            <option value="pending">待投放</option>
            <option value="running">投放中</option>
            <option value="done">已完成</option>
            <option value="cancel">已取消</option>
        </select>
        <span>创建日期：</span>
        <input type="text" id="createDateStart" class="se-text"/>
        <span>-</span>
        <input type="text" id="createDateEnd" class="se-text"/>
        <span>投放日期：</span>
        <input type="text" id="sendDateStart" class="se-text"/>
        <span>-</span>
        <input type="text" id="sendDateEnd" class="se-text"/>
        <input type="button" class="org-btn" id="searchBtn" value="查询"/>
        <input type="button" class="org-btn" id="reset" value="重置"/>
        <input type="button" class="org-btn" id="excelExport" value="导出"/>


    </div>
    <div class="ztable">
        <table id="dg">
            <thead>
            </thead>
        </table>
    </div>
    <div class="bottom"></div>
</div>
</body>
</html>
