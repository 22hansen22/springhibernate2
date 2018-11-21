<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<%@ include file="/WEB-INF/fragments/headerTeacher.jspf" %>
</head>
<body>

		<div class="container">
			<h3 id="output_header" class="text-success"><%= request.getAttribute("output") %></h3>
			<br>
			Name: ${userRealName}<br>
			Type: ${type}			
		</div>	  


</body>
</html>