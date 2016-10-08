<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>制定计划</title>
<script type="text/javascript">
	$(function() {
		$("#savePlan").click(function(){
			var url = "${ctp}/plan/make/";
			var todo = $("#todo").val();
			var date = $("#date").val();
			var chanceId = $("#chanceId").val();
			
			var args = {"todo":todo,"date":date,"chance.id":chanceId};
			$.post(url, args, function(data){
				var split = data.split("~");
				var reg = /^\d+$/;
				var flag = reg.test(split[0]);
				if(flag){
					var id = parseInt(split[0]);
					if(id > 0){
						alert("保存成功！");
						var $tr = $("<tr id='plan-"+id+"'></tr>");
						$tr.append("<td class='list_data_text'> " + split[1] + " &nbsp; </td>");
						var $td = $("<td class='list_data_ltext'></td>");
						$td.append("<input type='text' size='50' value='" + todo + "' id='todo-" + id + "'/>")
						   .append(" <button class='common_button' id='update-" + id + "'> 更新 </button> ")
						   .append(" <button class='common_button' id='delete-" + id + "'> 删除 </button> ")
						   .append("<input type='hidden' value='" + id + "'/>");
						$td.find("#update-" + id).click(function(){
							updatePlan(this);
							return false;
						});
						$td.find("#delete-" + id).click(function(){
							deletePlan(this);
							return false;
						});
						$tr.append($td);
						$tr.appendTo("#planTable");
					}
				}else{
					alert("保存失败！");
				}
			});
			
			return false;
		});
		
		$("button[id^='update']").click(function(){
			updatePlan(this);
			return false;
		});
		function updatePlan(button) {
			var id = $(button).attr("id");
			id = id.split("-")[1];
			var todo = $("#todo-" + id).val();
			var url = "${ctp}/plan/make/"+id+"?_method=PUT";
			var args = {
				"id": id,
				"todo": todo
			};
			$.post(url, args, function(data) {
				$.post(url, args, function(data) {
					if (data == "1") {
						alert("修改成功!");
					} else {
						alert("修改失败!");
					}
				});
			});
			return false;
		};
		$("button[id^='delete']").click(function(){
			deletePlan(this);
			return false;
		});
		function deletePlan(button) {
			var id = $(button).attr("id");
			id = id.split("-")[1];
			var url = "${ctp}/plan/make/"+id+"?_method=DELETE";
			var args = {
				"id": id
			};
			$.post(url, args, function(data) {
				if (data == "1") {
					$("#plan-" + id).remove();
					alert("删除成功!");
				} else {
					alert("删除失败!");
				}
			});
			return false;
		}
		$("#execute").click(function() {
			var id = $(":hidden[name='chance.id']").val();
			window.location.href = "${ctp}/plan/execute/" + id;
			return false;
		});
	});
</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute">
		执行计划
	</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
		返回
	</button>
	</div>

	<form:form id="makeForm" action="${ctp}/plan/make/" method="post" modelAttribute="chance">
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
			<table id="planTable" class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th width="200px">日期</th>
					<th>计划项</th>
				</tr>
	
				<c:forEach items="${chance.salesPlans }" var="plan">
					<tr id="plan-${plan.id }">
						<td class="list_data_text">
							<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/> &nbsp;
						</td>
						<c:if test="${empty plan.result }">
							<td class="list_data_ltext">
								<input type="text" size="50" value="${plan.todo }" id="todo-${plan.id }" />
								<button class="common_button" id="update-${plan.id }">
									更新
								</button>
								<button class="common_button" id="delete-${plan.id }">
									删除
								</button>
							</td>
						</c:if>
						<c:if test="${!empty plan.result }">
							<td class="list_data_ltext">
								<input type="text" size="50" value="${plan.todo }" readonly="readonly" />
								<input type="text" size="50" value="${plan.result }" readonly="readonly" />
							</td>
						</c:if>
					</tr>
		
				</c:forEach>
			</table>
			<div class="button_bar">
				<button id="savePlan" class="common_button"><!--  onclick="document.forms[0].submit();" -->
				新建
			</button>
			</div>
			<input id="chanceId" type="hidden" name="chance.id" value="${chance.id }" />
			<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						日期
						<br /> (格式: yyyy-mm-dd)
					</th>
					<td>
						<input type="text" name="date" id="date" /> &nbsp;
					</td>
					<th>
						计划项
					</th>
					<td>
						<input type="text" name="todo" size="50" id="todo" /> &nbsp;
					</td>
				</tr>
			</table>
		</c:if>
	</form:form>
</body>

</html>