<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/fragments/headerStudent.jspf" %>
	</head>
	<body>
	Welcome Student<br>
		<div class="container">
			<h3 id="output_header" class="text-success"><%= request.getAttribute("output") %></h3>
			<br>
			Name: ${userRealName}
		</div>	  
		
	</body>
</html>
