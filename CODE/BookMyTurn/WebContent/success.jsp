<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<title>LoginSuccessful</title>
	<link href="myStyle.css" rel="stylesheet">
</head>
<body>
	<div id="head">
		<h1 align="left">BookMyTurn</h1>
		<div align="right">Hello, ${requestScope['user'].username}</div>
	</div>
	<div id="book">
     <table class="tab-menu-group" width="98%">
		<tr>
			<td class="tab active" width="14%"><a href="#booking">Booking</a></td>
			
			<td class="tab" width="14%"><a href="#update" >Update</a></td>
			
			<td class="tab" width="20%"><a href="#cancel" >Cancel</a></td>
			
			<td class="tab" width="20%"><a href="#logout" >Logout</a></td>
		</tr>
		</table> 
	</div>
</body>
</html>