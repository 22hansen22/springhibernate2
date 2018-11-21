<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/fragments/headerTeacher.jspf" %>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
	<div class="wrapper">
		<div class="one">
			<form method="GET">
			<button type="submit" class="btn btn-primary btn-block" name="showETInput" value="showETInput">
			<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
			Create a new Exit Ticket</button>
			</form>
			<br><br><br>
			<form method="GET">
			<button type="submit" class="btn btn-primary btn-block" name="showETList" value="showETList">
			<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
			Search by Ticket list</button>
			</form>
			<br><br><br>
			<form method="GET">
			<button type="submit" class="btn btn-primary btn-block" name="showETList" value="showUserList">
			<span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>
			Search by user list</button>
			</form>
		</div>
		
		<div class="two">
			In this section you can write an exit ticket or you can check the results of the exit tickets of the students.
		</div>
		
		<div class="three">
			<c:if test="${showETList == 'showETList'}">
				<!-- TABLE WITH EXIT TICKETS -->
				<table class="table table-striped table-hover"
					style="background: #fff;">
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
								<td>${rowData.title}
								<c:if test="${not empty countList2}">
								<span class="label label-pill label-success">${countList2[status.index]}</span>
								</c:if>
								</td>
								<td>${rowData.dateET}</td>
								<td>
									<div style="float: right;">
										<form method="GET">
											<input type="hidden" name="id" value="${rowData.id}" />
											<button type="submit" class="btn btn-primary"
												name="showETList" value="showUsersForET">View Responses</button>
										</form>
									</div>
								</td>
								<td>
								<!-- Button trigger modal -->
									<div style="float: right;">
										<button class="btn btn-danger" data-toggle="modal"
											data-target="#myModal">
											<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										</button>
									</div> 
									
									<!-- Modal -->
									<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">Deleting
														item in DB</h4>
												</div>
												<div class="modal-body">Are you sure you want to
													delete?</div>
												<div class="modal-footer">
													<div style="float: left;">
														<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
													</div>
														
													<!-- 													<button type="button" class="btn btn-primary">Save -->
													<!-- 														changes</button> -->
													<form method="GET">
														<input type="hidden" name="id" value="${rowData.id}" />
														<button type="submit" class="btn btn-danger"
															name="showETList" value="deleteItem" data-toggle="modal"
															data-target="#exampleModalCenter">DELETE</button>
													</form>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div> <!-- /.modal --> 
									
									
									<!-- 									<div style="float: right;"> -->
<%-- 										<form method="GET" id="delete1"> --%>
<%-- 											<input type="hidden" name="id" value="${rowData.id}" /> --%>
<!-- 											<button type="submit" class="btn btn-primary" -->
<!-- 												name="showETList" value="deleteItem" data-toggle="modal" data-target="#exampleModalCenter"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button> -->
<!-- <!-- 											<button class="btn btn-primary" onclick="myFunction('delete1btn', 'delete1')"></button> -->
<%-- 										</form> --%>
										
<!-- 									</div> -->
									
												<script>
												
													function myFunction(arg1, arg2) {
														
														
														var r = confirm("Press a button!");
														console.log("r "+r);
														console.log("arg1-"+arg1);
														console.log("arg2-"+arg2);
														
														if (r == true) {
															console.log("entro");
															console.log(document.getElementById(arg1).toString());
															document.getElementById(arg1).click();
														} else {
															txt = "You pressed Cancel!";
															//document.getElementById(arg2).onsubmit=false;
														}
														var r = confirm("Press a button!");
														
													}
												</script>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${showETList == 'showUserList'}">
				<!-- TABLE WITH USERS -->
				<table class="table table-striped table-hover"
					style="background: #fff;">
					<thead>
						<tr>
							<th>User ID#</th>
							<th>User Name</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rowData" items="${usersList}" varStatus="status">
							<tr>
								<td>${rowData.id}</td>
								<td>${rowData.realname}
								
								<c:if test="${not empty countList}">
								<span class="label label-pill label-primary">${countList[status.index]}</span>
								</c:if>
								</td>

								<td>
									<div style="float: right;">
										<form method="GET">
											<input type="hidden" name="id" value="${rowData.id}" />
											<button type="submit" class="btn btn-primary"
												name="showETList" value="showETForUser">View</button>
										</form>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${showETList == 'showUsersForET'}">
				<!-- TABLE WITH USERS THAT HAVE THAT EXIT TICKET -->
				<table class="table table-striped table-hover"
					style="background: #fff;">
					<thead>
						<tr>
							<th>ET ID#</th>
							<th>USER ID#</th>
							<th>User Name</th>
							<th>Answer</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rowData" items="${usersForET}">
							<tr>
								<td>${rowData.exitTicket.id}</td>
								<td>${rowData.user.id}</td>
								<td>${rowData.user.realname}</td>
								<td>${rowData.answer}</td>
								<td>${rowData.dateAnswer}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${showETList == 'showETForUser'}">
				<!-- TABLE OF EXIT TICKETs BY USER-->
				<table class="table table-striped table-hover"
					style="background: #fff;">
					<thead>
						<tr>
							<th>USER ID#</th>
							<th>User Name</th>
							<th>ET ID</th>
							<th>Answer</th>
							<th>Date2</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rowData" items="${etForUsers}">
							<tr>
								<td>${rowData.user.id}</td>
								<td>${rowData.user.realname}</td>
								<td>${rowData.exitTicket.id}</td>
								<td>${rowData.answer}</td>
								<td>${rowData.dateAnswer}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			
			<c:if test="${not empty showETInput}">
<%-- 				<form method="GET"> --%>
<!-- 					<textarea rows="3" class="form-control" id="questionExitT" hidden="true" placeholder="Enter question" name="questionExitT"> -->
<!-- 					</textarea> -->
<!-- 					<br> -->
<!-- 					<button type="submit" class="btn btn-primary" name="group" value="Group">Send</button> -->
<%-- 				</form> --%>
				Entry ticket question:<br><br>
				<form:form method = "GET" action = "exitTicketTeacher/addExitTicket">
					<form:textarea path = "title" class="form-control" rows = "5" />
					<br>
					<button type="submit" class="btn btn-primary" value="submit">Save</button>
				</form:form>
			</c:if>
		</div>
		<!--  
		<div class="four">Four</div>
		<div class="five">Five</div>
		<div class="six">Six</div>-->
	</div>     
        
</body>
</html>