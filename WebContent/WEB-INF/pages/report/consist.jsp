<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>客户构成分析</title>
	<script type="text/javascript">
		$(function(){
			var thisType = $("#thisType").val();
			$("select[name='type']").children("option").each(function(){
				var isThis = $(this).val() == thisType;
				if(isThis){
					$(this).attr("selected","selected");
				}
			});
		});
	</script>
</head>
<body>
	
	<div class="page_title">
		客户构成分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/report/consist">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						查询方式
					</th>
					<td>
						<input type="hidden" id="thisType" value="${param.type }">
						<select name="type">
							<option value="customer_level">
								按等级
							</option>
							<option value="credit">
								按信用度
							</option>
							<option value="satify">
								按满意度
							</option>
						</select>
					</td>
					<th>
						&nbsp;
					</th>
					<td>
						&nbsp;
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
							序号
						</th>
						<th>
							${param.type }
						</th>
						<th>
							客户数量
						</th>
					</tr>
					<c:forEach items="${page.content }" var="item">
						<tr>
							<td class="list_data_number">${item.RN }</td>
							<td class="list_data_ltext">${item.type }</td>
							<td class="list_data_number">${item.num }</td>
						</tr>
					</c:forEach>
				</table>
				<atguigu:pages page="${page }"></atguigu:pages>
			</c:if>
		</div>
	</form>
</body>
</html>