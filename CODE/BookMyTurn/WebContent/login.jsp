<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookMyTrun</title>
</head>
<body background="http://www.shavercentre.com/canada/images/panasonic-ep-ma70k.jpg">
	<form action="LoginController" method="post">
		<table align="center">
		<tr><td><font color="00cc66">Username &emsp; </font>
		<input type="text" name="username"> </td></tr>
		 
		<tr><td><font color="00cc66">Password     &nbsp; &emsp; </font>
		<input type="password" name="password"> </td></tr>
		
		<tr><td><input	type="submit" name="Login" id="Login" value="Login">
		&emsp; <input type="submit" value="Forgot Password"> </td></tr>
		 </table>
	</form>
</body>
</html>