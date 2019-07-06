<!DOCTYPE html>
<html>
	<head>
		<title>Approve</title>
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" type="text/css" href="resources/css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" src="resources/js/script.js"></script>	
		<script>
			$.get("/InventoryManagement/getApprovalInventory", function(data) {
				if(data == "")
					document.getElementById("errormsg").innerHTML = "Congrats! Today No Pending Approvals!!!";
		
				$.each(data, function(i, item) {
					$("#inventoryTable").append(
		        		"<tr>"+
		        		"<td>"+item.productid+"</td>"+
		        		"<td><input type='text' value='"+ item.productname +"' readonly>" + "</td>"+
		        		"<td><input type='text' value='"+ item.vendor +"' readonly>" + "</td>"+
		        		"<td><input type='text' value='"+ item.mrp +"' readonly>" + "</td>"+
		        		"<td><input type='text' value='"+ item.batchnumber +"' readonly>" + "</td>"+
		        		"<td><input type='text' value='"+ item.batchdate +"' readonly>" + "</td>"+
		        		"<td><input type='text' value='"+ item.quantity +"' readonly>" + "</td>"+
		        		"<td>"+item.status+"</td>"+  
		        		"<td><a href='#' onclick='return approve(this)'><i class='material-icons' style='font-size:25px;'>check</i></a></td>"+
		        		"<td><a href='#' onclick='return reject(this)'><i class='material-icons' style='font-size:25px;'>close</i></a></td>"+
		        		"</tr>");
		    	});
		
			});
		</script>	
	</head>
	<body>
		<div id="container">
			<nav>
				<a href="#">Welcome ${user_name} to Inventory Management</a> 
				<a href="/InventoryManagement/logout" style="float:right">Log Out</a>
				<a href="/InventoryManagement/modify" style="float:right">Modify</a>
				<a href="/InventoryManagement/approve" style="float:right" class="active">Approve</a>
			</nav>
			<header>
				<h1 id="inventoryheader">Pending Approval Inventory List</h1>		
			</header>
			<section>
				<table id="inventoryTable">
					<colgroup>
						<col width="5%"><col width="30%"><col width="10%"><col width="7%"><col width="7%">
						<col width="10%"><col width="5%"><col width="16%"><col width="5%"><col width="5%">
					</colgroup>
					<thead>
						<tr>
							<th>Product Id</th><th>Product Name</th><th>Vendor</th><th>MRP</th><th>Batch Number</th>
							<th>Batch Date</th><th>Quantity</th><th>Status</th><th colspan="2">Activity</th>
						</tr>
					</thead>
					<tbody id="tb"></tbody>
				</table>	
				<br>
				<div id="errormsg" class="error" style="padding-left:1%;"></div>
			</section>
		</div>
		<footer><label style="float:right; padding-right:30px;">&copy; Inventory Management</label>
			</footer>
	</body>
</html>