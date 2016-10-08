<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看计划</title>
</head>
<body class="main">
	<span class="page_title">查看计划</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.back(-1);">返回</button>
	</div>
	<form:form action="${ctp}/plan/execute" method="post" modelAttribute="chance">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${chance.id }</td>
				<th>机会来源</th>
				<td>${chance.source }</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName }</td>
				<th>成功机率（%）</th>
				<td>${chance.rate }</td>
			</tr>
			<tr>
				<th>概要</th>
				<td colspan="3">${chance.title }</td>
			</tr>
			<tr>
				<th>联系人</th>
				<td>${chance.contact }</td>
				<th>联系人电话</th>
				<td>${chance.contactTel }</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description }</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name }</td>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>指派给</th>
				<td>${chance.designee.name }</td>
			</tr>
		</table>

		<br />
		<c:if test="${empty chance.salesPlans }">
			没有任何记录.
		</c:if>
		<c:if test="${!empty chance.salesPlans }">
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th width="200px">日期</th>
					<th>计划</th>
					<th>执行效果</th>
				</tr>
				<c:forEach items="${chance.salesPlans }" var="plan">
					<tr>
						<td class="list_data_text">
							<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/>&nbsp;
						</td>
						<td class="list_data_ltext">${plan.todo }&nbsp;</td>
						<td>${plan.result }&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</form:form>
</body>

</html>