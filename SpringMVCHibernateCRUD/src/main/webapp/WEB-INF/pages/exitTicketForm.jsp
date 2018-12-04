<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit ExitTicket</h1>
        <form:form action="saveExitTicket" method="post" modelAttribute="exitTicket">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><form:input path="dateET" type="date"/></td>
            </tr>
            
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save">
                <button action="action" type="button" onclick="history.go(-1);" class="btn btn-info">Back</button>
                </td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>