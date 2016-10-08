<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>角色管理 - 分配权限</title>
	<script type="text/javascript">
		$(function(){
			$(".authority :checkbox[name!='authorityIds']").click(function(){
				var flag = $(this).is(":checked");
				var $checkboxIds = $(this).parent("div").find(" :checkbox[name='authorityIds']");
				$checkboxIds.prop("checked", flag);
			});
			
			$(".authority :checkbox[name='authorityIds']").click(function(){
				var $checkboxIds = $(this).parents("div").find(" :checkbox[name='authorityIds']");
				var $checkboxIds2 = $(this).parents("div").find(" :checkbox[name='authorityIds']:checked");
				
				var flag = ($checkboxIds.length == $checkboxIds2.length);
				$(this).parents("div").find(" :checkbox[name!='authorityIds']").prop("checked", flag);
			});
			
			$(".authority").each(function(){
				var $checkboxIds = $(this).find(" :checkbox[name='authorityIds']");
				var $checkboxIds2 = $(this).find(" :checkbox[name='authorityIds']:checked"); 
				
				var flag = ($checkboxIds.length == $checkboxIds2.length);
				$(this).find(" :checkbox[name!='authorityIds']").prop("checked", flag);
			});
		})
	</script>
</head>

<body class="main">
 	
 	<form:form id="role" action="${ctp}/role/${role.id }" method="PUT" modelAttribute="role">
		<form:hidden path="id"/>
		
		<div class="page_title">
			角色管理 &gt; 分配权限
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.back(-1);">
				返回
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				保存
			</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title" width="10%">
					角色名
				</th>
				<td class="input_content" width="20%">
					${role.name }
				</td>
				<th class="input_title" width="10%">
					角色描述
				</th>
				<td class="input_content" width="20%">
					${role.description }
				</td>
				<th class="input_title" width="10%">
					状态
				</th>
				<td class="input_content" width="20%">
					${role.enabled?"有效":"无效" }
				</td>
			</tr>
			<tr>
				<th class="input_title">
					权限
				</th>
				<td class="input_content" colspan="5" valign="top">
					
					<c:forEach items="${parentAuthorities}" var="pa">
						<div class="authority">
							<input type="checkbox" />${pa.displayName }
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<form:checkboxes items="${pa.subAuthorities }" path="authorityIds"
								itemLabel="displayName" itemValue="id"
								delimiter="<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>
						</div>
						<br>
					</c:forEach>
					
					
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
