<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>

<c:set var="ctp" value="${pageContext.request.contextPath }"></c:set>
<script type="text/javascript" src="${ctp }/static/jquery/jquery-1.9.1.min.js"></script>
<link href="${ctp }/static/css/styles.css" rel="stylesheet" type="text/css">

<c:if test="${message != null }">
	<script type="text/javascript">
		alert("${message}");
// 		alert("headerValues:${headerValues.message}");
// 		alert("requestScope:${requestScope.message}");
	</script>
	<%
// 		System.out.println(request.getAttribute("message"));
// 		System.out.println(session.getAttribute("message"));
// 		System.out.println(pageContext.getAttribute("message"));
		
// 		request.removeAttribute("message");
	%>
</c:if>