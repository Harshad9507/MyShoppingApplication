<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="padding-left:500px;padding-top:200px;-webkit-text-fill-color: rgba(100, 10, 7, 0.986);">
	
	<form action="logincheck">
		<div style="border: 1px solid;width: fit-content;border-radius: 10px;padding: 20px;border-color: blue;">
			<b><h1 style="color:blue">Login Form</h1></b>
			<b>USER-ID:</b>
			<input type="text" name="uid" id="uid"/><br/>
			<b>PASSWORD:</b>
			<input type="password" name=pwd id=pwd/><br/>
			<input type="submit" value="SIGN IN"/>
			New user?
			<a href="users.html" ><input type="button" value="SIGN UP"/></a>
			
		</div>
		${cookie.loginerror.value}
	</form>
	
	
</body>
</html>