<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>编辑产品信息</title>
</head>

<body class="main">

	<span class="page_title">编辑产品信息</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
		返回
	</button>
		<button class="common_button" onclick="document.forms[0].submit();">
		保存
	</button>
	</div>

	<form:form id="productForm" action="${ctp}/product/${product.id }" method="POST" modelAttribute="product">
	
		<c:if test="${product.id != null }">
			<input type="hidden" name="_method" value="PUT">
		</c:if>
	
		<form:hidden path="id"/>
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>名称</th>
				<td><form:input path="name"/></td>
				<th>型号</th>
				<td><form:input path="type"/></td>
			</tr>
			<tr>
				<th>等级/批次</th>
				<td><form:input path="batch"/></td>
				<th>单位</th>
				<td><form:input path="unit"/></td>
			</tr>
			<tr>
				<th>单价</th>
				<td><form:input path="price"/></td>
				<th>备注</th>
				<td><form:input path="memo"/></td>
			</tr>
		</table>
	</form:form>
	
</body>

</html>