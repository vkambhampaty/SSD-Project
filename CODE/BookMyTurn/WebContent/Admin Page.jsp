<!DOCTYPE html>
<html lang="en">
    <head>
 	<title>Admin</title>
 	<meta charset="utf-8" />
	<link href="myStyle.css" rel="stylesheet">
    </head>
    <body>
	<div id="head">
		<h1>BookMyTurn</h1>
		<div align="right">Hello, ${requestScope['user'].username}</div>
	</div>
	<div id="book">
     <table class="tab-menu-group" width="98%">
		<tr>
			<td class="tab active" width="14%"><a href="#suspend">Supend</a></td>
			
			<td class="tab" width="20%"><a href="#resume" >Resume</a></td>
			
			<td class="tab" width="20%"><a href="#logout" >Logout</a></td>
			</tr>
	</table>
	</div>
	</body>
	</html>
	