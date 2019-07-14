<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="resources/css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" src="resources/js/script.js"></script>
	</head>
	<body>
		<div id="container">
			<nav>
				<a href="#">Inventory Management</a>
				<a href="/InventoryManagement/" style="float:right" class="active">Login</a>
			</nav><br>
			<section>		
				<article id="loginapp">
					<form>
						<fieldset>
							<legend>Login</legend>
							<label>Username:</label><br><input type="text" id="name"><br><br>
							<label>Password:</label><br><input type="password" id="password"><br><br>
							<div id="errormsg" class="error"></div>
							<br>
							<div class="centerbutton">
								<input type="submit" value="Login">
							</div>
						 </fieldset>
					</form>	
					<br>
				</article>			
			</section>			
		</div>
		<footer>
			<label style="float:right; padding-right:30px;">&copy; Inventory Management</label>
		</footer>	
	</body>
</html>
