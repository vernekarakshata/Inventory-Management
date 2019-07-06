<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<title>Log Out</title>
		<link rel="stylesheet" type="text/css" href="resources/css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" src="resources/js/script.js"></script>
		<script type="text/javascript">
		setTimeout(function() {
			  window.location.href = "/InventoryManagement/";
			}, 3000);						
		</script>
	</head>
	<body>
		<div id="container">
			<nav>
				<a href="#">Inventory Management</a>
			</nav><br>
			<section>		
				<div id="logoutapp">
					<p class="loader"></p>
					Please wait as you are being logged out.
				</div>								
			</section>			
		</div>
		<footer>
			<label style="float:right; padding-right:30px;">&copy; Inventory Management</label>
		</footer>	
	</body>
</html>
