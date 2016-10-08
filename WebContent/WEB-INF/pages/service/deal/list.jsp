<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
<body>

	<div class="page_title">
		客户服务管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/service/'">
			新建
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/service/deal/list" method="GET">
	
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<input type="text" name="search_LIKE_serviceType" />
				</td>
				<th>
					概要
				</th>
				<td>
					<input type="text" name="search_LIKE_serviceTitle" />
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<input type="text" name="search_EQ_customer.name" />
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input type="text" name="search_D_minCreateDate" size="10" />
					-
					<input type="text" name="search_D_maxCreateDate" size="10" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		<c:if test="${empty page.content }">
			没有任何记录.
		</c:if>
		<c:if test="${!empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>编号</th>
					<th>服务类型</th>
					<th>概要</th>
					<th>客户</th>
					<th>创建人</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${page.content }" var="service">
					<tr>
						<td class="list_data_number">${service.id }</td>
						<td class="list_data_text">${service.serviceType }</td>
						<td class="list_data_ltext">${service.serviceTitle }</td>
						<td class="list_data_text">${service.customer.name }</td>
						<td class="list_data_text">${service.createdby.name }</td>
						<td class="list_data_text"><fmt:formatDate value="${service.createDate }" pattern="yyyy-MM-dd"/></td>
	
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/service/deal/${service.id }'" 
								title="处理" src="${ctp}/static/images/bt_deal.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<atguigu:pages page="${page }"></atguigu:pages>
		</c:if>
	</form>
</body>
</html>
