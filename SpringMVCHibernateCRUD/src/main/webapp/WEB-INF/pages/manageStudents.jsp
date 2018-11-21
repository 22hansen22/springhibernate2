<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<%@ include file="/WEB-INF/fragments/headerTeacher.jspf" %>
</head>
<body>

<hr>
	<div align="center">
		<h1>User List</h1>
		
		<table border="1">
			<th>Id</th>
			<th>Name</th>
			<th>Password</th>
			<th>Type</th>
			<th>Real Name</th>
			<th>Action</th>

			<c:forEach var="user" items="${listUser}">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.password}</td>
					<td>${user.type}</td>
					<td>${user.realname}</td>
					<td><a href="editUser?id=${user.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deleteUser?id=${user.id}">Delete</a></td>

				</tr>
			</c:forEach>
		</table>
		<br><br>
		<table border="1">

			<th>Id</th>
			<th>Title</th>
			<th>Date</th>
			<th>Action</th>
			

			<c:forEach var="exitTicket" items="${listExitTicket}">
				<tr>

					<td>${exitTicket.id}</td>
					<td>${exitTicket.title}</td>
					<td>${exitTicket.dateET}</td>
					<td><a href="editExitTicket?id=${exitTicket.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deleteExitTicket?id=${exitTicket.id}">Delete</a></td>

				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<table border="1">
			<th>Id UserExitTicket</th>
			<th>Id User</th>
			<th>Id ExitTicket</th>
			<th>User name</th>
			<th>ExitTicket Title</th>
			<th>answer</th>

			<c:forEach var="userExitTicket" items="${listUserExitTicket}">
				<tr>
					<td>${userExitTicket.id}</td>
					<td>${userExitTicket.user.id}</td>
					<td>${userExitTicket.exitTicket.id}</td>
					<td>${userExitTicket.user.name}</td>
					<td>${userExitTicket.exitTicket.title}</td>
					<td>${userExitTicket.answer}</td>
					<td><a href="editUserExitTicket?id=${userExitTicket.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deleteUserExitTicket?id=${userExitTicket.id}">Delete</a></td>

				</tr>
			</c:forEach>
		</table>
		<h4>
			New User Register <a href="newUser">here</a>
		</h4>
		<h4>
			New ExitTicket <a href="newExitTicket">here</a>
		</h4>
		<h4>
			New UserExitTicket <a href="newUserExitTicket">here</a>
		</h4>

	</div>
</body>
</html>