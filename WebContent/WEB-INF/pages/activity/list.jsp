<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>交往记录管理</title>
</head>
<body>

<div class="page_title">
	交往记录管理
</div>
<div class="button_bar">
	<button class="common_button"
		onclick="window.location.href='${ctp}/activity/?customerId=${customer.id }'">
		新建
	</button>
	<button class="common_button" onclick="javascript:history.go(-1);"> 
		返回
	</button>
</div>

<form action="${ctp}/activity/list" method="POST">
	<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
		<tr>
			<th>客户编号</th>
			<td>${customer.no }</td>
			<th>客户名称</th>
			<td>${customer.name }</td>
		</tr>
	</table>
	<!-- 列表数据 -->
	<br />
	
	<c:if test="${empty page.content }">
		没有任何记录.
	</c:if>
	<c:if test="${!empty page.content }">
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>时间</th>
				<th>地点</th>
				<th>概要</th>
				<th>详细信息</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.content }" var="activity">
				<tr>
					<td class="list_data_text">
						<fmt:formatDate value="${activity.date }" pattern="yyyy-MM-dd"/>
					</td>
					<td class="list_data_ltext">
						${activity.place }
					</td>
					<td class="list_data_ltext">
						${activity.title }
					</td>
					<td class="list_data_ltext">
						${activity.description }
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctp}/activity/${activity.id }'" 
							title="编辑" src="${ctp}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctp}/activity/${activity.id }?_method=DELETE&customerId=${customer.id }'" 
							title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
					</td>
				</tr>
			</c:forEach>
		</table>	
	</c:if>
	

	<atguigu:pages page="${page }"></atguigu:pages>
	
	
</form>
</body>
</html>