<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户贡献分析</title>
</head>
<body>

	<div class="page_title">
		客户贡献分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/report/contribute">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						客户名称
					</th>
					<td>
						<input type="text" name="search_LIKE_custName" />
					</td>
					<th>
						日期
					</th>
					<td>
						<input type="text" name="search_D_minOrderDate" size="10" />
						-
						<input type="text" name="search_D_maxOrderDate" size="10" />
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
						<th>
							客户名称
						</th>
						<th>
							订单金额（元）
						</th>
					</tr>
					<c:forEach items="${page.content }" var="item">
						<tr>
							
							<td align="center">
								${item.custName }
							</td>
							<td align="center">
								${item.totalMoney }
							</td>
		
						</tr>
					</c:forEach>
				</table>
			<atguigu:pages page="${page }"></atguigu:pages>
			</c:if>
		</div>
	</form>
</body>
</html>