<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="padding-left:500px;padding-top:200px;-webkit-text-fill-color: rgba(100, 10, 7, 0.986);">
	<form action="update">
		<div style="border: 1px solid;width: fit-content;border-radius: 10px;padding: 20px;border-color: blue;">
			<b>u_id:</b>
			<input type="text" name="uid" id="uid" value=${loggedinuser.getUid()} disabled="disabled" /><br/>
			<b>password:</b>
			<input type="password" name="pwd" id="pwd" value=${loggedinuser.getPwd()} disabled /><br/>
			<b>fname:</b>
			<input type="text" name="fn" id="fn" value=${loggedinuser.getFname()} disabled /><br/>
			<b>mname:</b>
			<input type="text" name="mn" id="mn" value=${loggedinuser.getMname()} disabled /><br/>
			<b>lname:</b>
			<input type="text" name="ln" id="ln" value=${loggedinuser.getLname()} disabled /><br/>
			<b>email:</b>
			<input type="email" name="em" id="em" value=${loggedinuser.getEmail()}/><br/>
			<b>contact:</b>
			<input type="number" name="ct" id="ct" value=${loggedinuser.getContact()}/><br/>
			
			<input type="submit" value="Update"/>
		</div>
	</form>
</body>
</html>