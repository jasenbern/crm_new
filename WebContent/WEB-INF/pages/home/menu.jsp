<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${ctp }/static/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctp }/static/jquery/themes/icon.css">
	<script type="text/javascript" src="${ctp }/static/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#tt').tree({    
			    onClick: function(node){
					//alert(node.text);  // 在用户点击的时候提示
					if(node.url){
						//alert(node.url);
						window.parent.document.getElementById("content").src = node.url;
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul id="tt" class="easyui-tree" data-options="url:'${ctp }/navigate',method:'get',animate:false"></ul>
</body>
</html>