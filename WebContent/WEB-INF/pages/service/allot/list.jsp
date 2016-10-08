<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
	<script type="text/javascript">
		$(function(){
			$(".allotTo").change(function(){
				var val = $(this).val();
				if(val != ""){
					var label = $(this).find("option:selected").text();
					var flag = confirm("确定要分配给【" + label + "】吗?");
					
					if(flag){
						var $tr = $(this).parents("tr");
						
						var id = $(this).prev(":hidden").val();
						var url = "${ctp}/service/allot/"+id+"?_method=PUT";
						var args = {"allotId":val};
						$.post(url, args, function(data){
							if(data == "1"){
								alert("分配成功!");
								$tr.remove();
							}else{
								alert("分配失败!");
							}
						});
					}else{
						$(this).find("option[value='']").attr("selected", "selected");
					}
				}
			});
			$("img[id^='delete-']").click(function(){
				var flag = confirm("确定要删除该服务吗?");
				
				if(flag){
					var $tr = $(this).parents("tr");
					
					var id = $(this).attr("id").split("-")[1];
					var url = "${ctp}/service/"+id+"?_method=DELETE";
					var args = {};
				
					$.post(url, args, function(data){
						if(data == "1"){
							alert("删除成功!");
							$tr.remove();
						}else{
							alert("删除失败!");
						}
					});
				}
				return false;
			});
		})
	</script>
</head>
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
	
	<form action="${ctp}/service/allot/list" method="GET">
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
					<th>分配给</th>
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
	
						<td class="list_data_text">
							<input type="hidden" name="id" value="${service.id }"/>
							<select name="allotTo" class="allotTo">
								<option value="">
									未指定
								</option>
								<c:forEach items="${users}" var="user">
									<option value="${user.id }">${user.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="list_data_op">
							<img id="delete-${service.id }" 
								title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<atguigu:pages page="${page }"></atguigu:pages>
		</c:if>
	</form>
</body>
</html>
