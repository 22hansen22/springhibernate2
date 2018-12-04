<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/WEB-INF/fragments/headerStudent.jspf" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


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
								<td>${rowData.question}</td>
								<td>${rowData.dateClass}</td>
								<td>
									<c:if test="${writtenYN[status.index]== false}">
									<div style="float: right;">
<%-- 										<form method="GET"> --%>
<%-- 											<input type="hidden" name="exitTicketId" value="${rowData.id}" /> --%>
<!-- 											<button type="submit" class="btn btn-warning" -->
<!-- 												name="showETInputStudent" value="editCD"> -->
<!-- 												<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> -->
<!-- 												</button> -->
<%-- 										</form> --%>
										
										<button class="btn btn-danger" data-toggle="modal"
											data-target="#myModal">
											<span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
										</button>
										<!-- Button trigger modal -->
									
									<!-- Modal -->
									<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<form method="GET">
													<input type="hidden" name="classDayId" value="${rowData.id}" />
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title" id="myModalLabel">Entering password:</h4>
													</div>
													<div class="modal-body">
														<label for="exampleInputPassword1">Password</label>
			    										<input name="password" class="form-control" id="exampleInputPassword1" placeholder="Password" maxlength="4">
													</div>
													<div class="modal-footer">
														<div style="float: left;">
															<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
														</div>
														<button type="submit" class="btn btn-primary" name="showCDInputStudent" value="editCD">
														Submit</button>
													</div>
												</form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div> <!-- /.modal --> 
									</div>
									</c:if>
									
									<c:if test="${writtenYN[status.index]}">
									<div style="float: right;">
										<form method="GET">
											<input type="hidden" name="classDayId" value="${rowData.id}" />
											<button type="submit" class="btn btn-success"
												name="showCDStudent" value="editCD">
												<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
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
			<c:if test="${not empty showCDInputStudent}">
				Enter answer ro the daily question:<br>
				<br>
				<form:form method="GET"
					action="attendanceStudent/addAnswerToUserClassDay">
					<input type="hidden" name="classDayId" value="${classDayId}" />
					<form:textarea path="answer" class="form-control" rows="5" />
					<br>
					<button type="submit" class="btn btn-primary" value="submit">Save</button>
				</form:form>
			</c:if>

			<c:if test="${not empty showCDStudent}">
				Entry ticket answer:<br>
				<br>
				<form:form action="saveUserClassDay" method="post" modelAttribute="command">
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