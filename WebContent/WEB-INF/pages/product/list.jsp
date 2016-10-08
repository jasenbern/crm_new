<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>产品查询</title>
	<script type="text/javascript">
	$(function(){
		$("img[id^='delete-']").click(function(){
			var name = $(this).next(":hidden").val();
			var flag = confirm("是否确认删除【" + name + "】的信息？");
			if(flag){
				var id = $(this).prev(":hidden").val();
				window.location.href="${ctp}/product/"+id+"?_method=DELETE";
			}
			return false;
		});
	});
</script>
</head>

<body>
	<div class="page_title">
		产品管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/product/'">
		产品添加
	</button>
		<button class="common_button" onclick="document.forms[0].submit();">
		查询
	</button>
	</div>
	
	<form action="${ctp}/product/list">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>
					名称
				</th>
				<td>
					<input type="text" name="search_LIKE_name" />
				</td>
				<th>
					型号
				</th>
				<td>
					<input type="text" name="search_LIKE_type" />
				</td>
				<th>
					批次
				</th>
				<td>
					<input type="text" name="search_LIKE_batch" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
	</form>
	
	<c:if test="${empty page.content }">
		没有任何记录.
	</c:if>
	<c:if test="${!empty page.content }">
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>型号</th>
				<th>等级/批次</th>
				<th>单位</th>
				<th>单价（元）</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			
			<c:forEach items="${page.content }" var="item">
				<tr>
					<td class="list_data_number">${item.id }</td>
					<td class="list_data_ltext">${item.name }</td>
					<td class="list_data_text">${item.type }</td>
					<td class="list_data_text">${item.batch }</td>
	
					<td class="list_data_text">${item.unit }</td>
					<td class="list_data_number">${item.price }</td>
					<td class="list_data_ltext">${item.memo }</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctp}/product/${item.id }'" 
							title="编辑" src="${ctp}/static/images/bt_edit.gif" class="op_button" />
							
						<input type="hidden" value="${item.id }">
						<img id="delete-${item.id }"
							title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
						<input type="hidden" value="${item.name }">
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<atguigu:pages page="${page }"/>
	</c:if>
</body>
</html>