<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户服务分析</title>
<body>
	<div class="page_title">客户服务分析</div>
	<div class="button_bar">
	<button class="common_button" onclick="document.forms[0].submit();">查询</button>  
	</div>
	<form action="${ctp}/report/service">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						日期
					</th>
					<td>
					<input type="text" name="search_D_minCreateDate" size="10" />
					-
					<input type="text" name="search_D_maxCreateDate" size="10" />
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
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
					<th>条目</th>
					<th>客户数量</th>
					</tr>
					<c:forEach items="${page.content }" var="item">
						<tr>
							<td class="list_data_number">${item.RN }</td>
							<td class="list_data_ltext">大客户</td>
							<td class="list_data_number">2</td>
						</tr>			
					</c:forEach>
				</table>
				<atguigu:pages page="${page }"></atguigu:pages>
			</c:if>
		</div>
	</form>	
</body>
</html>