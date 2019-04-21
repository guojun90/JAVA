<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>联系我们</title>
<jsp:include page="${path}/common/jsp/head.jsp" />
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=path%>/assets/js/jquery.easyui.min.js"></script>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<%-- <script src="<%=path%>/weChat/js/contact.js"></script> --%>

</head>
<body><!-- 
<font size="6" color="black" >联系我们</font> -->
<p><br>
<p><font size="6" color="blue">电话/传真：</font><font size="6" color="black">021-61212367</font>
<p><font size="6" color="blue">手机：</font><font size="6" color="black">15821876528</font>
<p><font size="6" color="blue"> Q Q ：</font><font size="6" color="black">1834700108</font>
<p><font size="6" color="blue">微信：</font><font size="6" color="black">18815771235</font>
<p><font size="6" color="blue">地址：</font><font size="6" color="black">上海市松江区小昆山镇光华路488号</font>


</body>
</html>
