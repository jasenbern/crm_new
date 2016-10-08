<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>新建客户服务</title>
</head>

<body class="main">

	<span class="page_title">新建客户服务</span>
	
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	<form action="${ctp}/service/" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<select name="serviceType">
						<option value="">
							未指定
						</option>
						<c:forEach items="${types }" var="type">
							<option value="${type.item }">${type.item }</option>
						</c:forEach>
					</select>
					<span class="red_star">*</span>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					<input type="text" name="serviceTitle" size="50" />
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<select name="customer.id">
						<option value="">
							未指定
						</option>
						<c:forEach items="${customers }" var="customer">
							<option value="${customer.id }">${customer.name }</option>
						</c:forEach>
					</select>
					<span class="red_star">*</span>
				</td>
				<th>
					状态
				</th>
				<td>
					新创建 <input type="hidden" name="serviceState" value="新创建"/>
				</td>
			</tr>
			<tr>
				<th>
					服务请求
				</th>
				<td colspan="3">
					<textarea name="serviceRequest" rows="6" cols="50"></textarea>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${sessionScope.user.name }(${sessionScope.user.role.name })
					<span class="red_star">*</span>
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input name="createDate" id="createDate" 
						value='<fmt:formatDate value="${service.createDate }" pattern="yyyy-MM-dd"/>' 
						readonly="readonly" />
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
