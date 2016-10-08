<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${ctp }/static/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctp }/static/jquery/themes/icon.css">
	<script type="text/javascript" src="${ctp }/static/jquery/jquery.easyui.min.js"></script>
</head>
<body>
	<ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true"></ul>
</body>
</html>