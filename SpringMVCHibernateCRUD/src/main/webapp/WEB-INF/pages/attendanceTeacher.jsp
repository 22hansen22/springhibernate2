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
			<button type="submit" class="btn btn-primary btn-block" name="showCDList" value="showCDList">
			<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
			Search by Class Day</button>
			</form>
			<br><br><br>
			<form method="GET">
			<button type="submit" class="btn btn-primary btn-block" name="showCDList" value="showUserList">
			<span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>
			Search by User</button>
			</form>
			<br><br><br>
			<form method="GET">
			<button type="submit" class="btn btn-primary btn-block" name="showCDInput" value="showCDInput">
			<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
			Create a new Class Date</button>
			</form>
		</div>
		
		<div class="two">
			In this section you can write an exit ticket or you can check the results of the exit tickets of the students.
		</div>
		
		<div class="three">
			<c:if test="${showCDList == 'showCDList'}">
				<!-- TABLE WITH EXIT TICKCDS -->
				<table class="table table-striped table-hover"
					style="background: #fff;">
					<thead>
						<tr>
							<th>Date</th>
							<th>Question</th>
							<th>Password</th>
							
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rowData" items="${etList}" varStatus="status">
							<tr>
								<td>${rowData.dateClass}</td>
								<td>${rowData.question}
									<c:if test="${not empty countList2}">
									<span class="label label-pill label-success">${countList2[status.index]}</span>
									</c:if>
								</td>
								<td>
									<!-- Button trigger modal FOR PASSWORD -->
									<div style="float: right;">
										<button class="btn btn-info" data-toggle="modal"
											data-target="#myModal${status.index}">
											<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
										</button>
									</div> 
									
									<!-- Modal -->
									<div class="modal fade" id="myModal${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">Password</h4>
												</div>
												<div class="modal-body">
													<div class="jumbotron" align="center">
  													<h1 class="display-4">${rowData.password}</h1>
  													</div>
												</div>
												<div class="modal-footer">
													<div style="float: right;">
														<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
													</div>
														
				
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div> <!-- /.modal --> 
								</td>
								<td>
									<div style="float: right;">
										<form method="GET">
											<input type="hidden" name="id" value="${rowData.id}" />
											<button type="submit" class="btn btn-primary"
												name="showCDList" value="showUsersForCD">View Responses</button>
										</form>
									</div>
								</td>
								<td>
								<!-- Button trigger modal FOR DELETE -->
									<div style="float: right;">
										<button class="btn btn-danger" data-toggle="modal"
											data-target="#myModalDelete${status.index}">
											<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										</button>
									</div> 
									
									<!-- Modal -->
									<div class="modal fade" id="myModalDelete${status.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
													<form method="GET">
														<input type="hidden" name="id" value="${rowData.id}" />
														<button type="submit" class="btn btn-danger"
															name="showCDList" value="deleteItem" data-toggle="modal"
															data-target="#exampleModalCenter">DELETE</button>
													</form>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div> <!-- /.modal --> 
									
									
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${showCDList == 'showUserList'}">
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
												name="showCDList" value="showCDForUser">View</button>
										</form>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${showCDList == 'showUsersForCD'}">
				<!-- TABLE WITH USERS THAT HAVE THAT EXIT TICKCD -->
				<table class="table table-striped table-hover"
					style="background: #fff;">
					<thead>
						<tr>
							<th>CD ID#</th>
							<th>USER ID#</th>
							<th>User Name</th>
							<th>Answer</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rowData" items="${usersForCD}">
							<tr>
								<td>${rowData.classDay.id}</td>
								<td>${rowData.user.id}</td>
								<td>${rowData.user.realname}</td>
								<td>${rowData.answer}</td>
								<td>${rowData.dateAnswer}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<c:if test="${empty usersForCD}">
					No entries to display<br><br>
				</c:if>
				
				<button action="action" type="button" onclick="history.go(-1);" class="btn btn-info"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></button>
				
			</c:if>

			<c:if test="${showCDList == 'showCDForUser'}">
				<!-- TABLE OF EXIT TICKCDs BY USER-->
				<table class="table table-striped table-hover"
					style="background: #fff;">
					<thead>
						<tr>
							<th>USER ID#</th>
							<th>User Name</th>
							<th>CD ID</th>
							<th>Answer</th>
							<th>Date Question</th>
							<th>Date Answer</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rowData" items="${etForUsers}">
							<tr>
								<td>${rowData.user.id}</td>
								<td>${rowData.user.realname}</td>
								<td>${rowData.classDay.id}</td>
								<td>${rowData.answer}</td>
								<td>${rowData.classDay.dateClass}</td>
								<td>${rowData.dateAnswer}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${empty etForUsers}">
					No entries to display<br><br>
				</c:if>
				
				<button action="action" type="button" onclick="history.go(-1);" class="btn btn-info"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></button>
				
				
			</c:if>
			
			<c:if test="${not empty showCDInput}">
				Class Day question (optional):<br><br>
				<form:form method = "GET" action = "attendanceTeacher/addClassDay">
					<form:textarea path = "question" class="form-control" rows = "5" />
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