<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/WEB-INF/fragments/headerStudent.jspf" %>

</head>
<body>
<div class="wrapper">
        <div class="one">
        	<!-- SHOW TABLE OF EXIT TICKETS -->
        	<table class="table table-striped table-hover" style="background: #fff;">
					<thead>
						<tr>
							<th>ID#</th>
							<th>Exit Ticket Question</th>
							<th>Date</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rowData" items="${etList}" varStatus="status">
							<tr>
								<td>${rowData.id}</td>
								<td>${rowData.title}</td>
								<td>${rowData.dateET}</td>
								<td>
									<c:if test="${writtenYN[status.index]== false}">
									<div style="float: right;">
										<form method="GET">
											<input type="hidden" name="exitTicketId" value="${rowData.id}" />
											<button type="submit" class="btn btn-warning"
												name="showETInputStudent" value="editET">
												<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
												</button>
										</form>
									</div>
									</c:if>
									
									<c:if test="${writtenYN[status.index]}">
									<div style="float: right;">
										<form method="GET">
											<input type="hidden" name="exitTicketId" value="${rowData.id}" />
											<button type="submit" class="btn btn-success"
												name="showETStudent" value="editET">
												<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
												</button>
										</form>
									</div>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		
		<div class="two">
			In this section you can write an exit ticket or you can check the results of the exit tickets of the students.
		</div>
		
		<div class="three">
			<c:if test="${not empty showETInputStudent}">
				Entry ticket answer:<br>
				<br>
				<form:form method="GET"
					action="exitTicketStudent/addAnswerToUserExitTicket">
					<input type="hidden" name="exitTicketId" value="${exitTicketId}" />
					<form:textarea path="answer" class="form-control" rows="5" />
					<br>
					<button type="submit" class="btn btn-primary" value="submit">Save</button>
				</form:form>
			</c:if>

			<c:if test="${not empty showETStudent}">
				Entry ticket answer:<br>
				<br>
				<form:form action="saveUserExitTicket" method="post" modelAttribute="command">
				<form:textarea path="answer" class="form-control" rows="5" disabled="true"/>
				<br>
					<button action="action" type="button" onclick="history.go(-1);" class="btn btn-info"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></button>
				</form:form>
				<br>
			</c:if>

		</div>
		
<!-- 		<div class="four">Four</div> -->
<!-- 		<div class="five">Five</div> -->
<!-- 		<div class="six">Six</div> -->
	</div>     
        
        
        
</body>
</html>