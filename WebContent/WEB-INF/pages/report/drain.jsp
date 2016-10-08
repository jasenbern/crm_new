<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户流失分析</title>
  </head>  
  <body>
  	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>  
	</div>
	
  	<form action="${ctp }/report/drain">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>
					客户名称
				</th>
				<td>
					<input type="text" name="search_LIKE_customer.name" />
				</td>
				<th>
					客户经理
				</th>
				<td>
					<input type="text" name="search_LIKE_customer.manager.name" />
				</td>
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
				<th>序号</th>
				<th>确认流失时间</th>			
				<th>客户</th>
				<th>客户经理</th>
				<th>流失原因</th>
			</tr>
				<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_number">${item.RN }</td>				
						<td class="list_data_text"><fmt:formatDate value="${item.drain.drainDate }" pattern="yyyy-MM-dd"/></td>
						<td class="list_data_text">${item.drain.customer.name }</td>				
						<td class="list_data_text">${item.drain.customer.manager.name }</td>
						<td class="list_data_ltext">${item.drain.reason }</td>				
					</tr>			
				</c:forEach>
			</table>
			<atguigu:pages page="${page}" />
		</c:if>
	</form>	
  </body>
</html>