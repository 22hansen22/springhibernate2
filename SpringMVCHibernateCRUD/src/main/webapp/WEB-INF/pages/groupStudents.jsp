<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/fragments/headerTeacher.jspf" %>
</head>
<body>

<hr>
	<div class="wrapper">
		<div class="one">
		This is the list of Students in class:<br><br>
			<c:if test="${not empty listRealNames}">
				<ul class="list-group-item">
					<c:forEach items="${listRealNames}" var="listRealNames">
						<li class="list-group-item">${listRealNames}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		
		<div class="two">
			<c:url var="groupPeople" value="groupStudents/groupPeople" />
			<form method="GET">
				<select name="groupSize">
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
				<button type="submit" class="btn btn-primary" name="group" value="Group">Group Students</button>
			</form>
			<c:if test="${not empty gmsg}">
			<br><br>
			${gmsg}
			</c:if>
		</div>
		
		<div class="three">
			<c:if test="${not empty listOfGroups}">
			<c:forEach var="rowData" items="${listOfGroups}" varStatus="l1">
				<div style="float:left; margin-left:15px; margin-top:10px;">
				
				<ul class="list-group-item">
				Group # ${l1.index+1}<br><br>
					<c:forEach var="cellData" varStatus="loop" items="${rowData}">
						<li class="list-group-item" style="background-color:#ccc; font-weight: bold;">${cellData}</li>
					</c:forEach>
				</ul>
				</div>
			</c:forEach>
		</c:if>
		</div>
		<!--  
		<div class="four">Four</div>
		<div class="five">Five</div>
		<div class="six">Six</div>-->
	</div>

</body>


</html>