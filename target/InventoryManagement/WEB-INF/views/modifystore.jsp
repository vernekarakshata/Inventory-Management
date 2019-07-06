<!DOCTYPE html>
<html>
	<head>
		
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" type="text/css" href="resources/css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" src="resources/js/script.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){			
				var role = document.getElementById("userrole").innerHTML;
				if(role == "Store Manager")
					$("#approvelink").show();
				else
					$("#approvelink").hide();
			});
		</script>
	</head>
	<body>	
		<div id="container">
			<nav>
				<a href="#">Welcome ${user_name} to Inventory Management</a> 
				<a href="/InventoryManagement/logout" style="float:right">Log Out</a>
				<a href="/InventoryManagement/modify" style="float:right"  class="active">Modify</a>
				<a href="/InventoryManagement/approve" style="float:right" id="approvelink">Approve</a>
			</nav>
			<header>
				<label id="userrole" hidden=true>${user_role}</label>
				<h1 id="inventoryheader">Inventory List</h1>
			</header>
			<section>
				<button type="button" style="float:right" onclick="additem()" id="additembtn">
					<i class="material-icons">playlist_add</i>Add Item
				</button><br><br><br>
				
				<table id="inventoryTable">
					<colgroup>
						<col width="5%"><col width="30%"><col width="10%"><col width="7%"><col width="7%">
						<col width="10%"><col width="5%"><col width="16%"><col width="5%"><col width="5%">
					</colgroup>
					<thead>
						<tr>
							<th>Product Id</th><th>Product Name</th><th>Vendor</th><th>MRP</th><th>Batch Number</th>
							<th>Batch Date</th><th>Quantity</th><th>Status</th>	<th colspan="2">Activity</th>
						</tr>
					</thead>
					<tbody id="tb"></tbody>
				</table>
				<script>
							
					$.get("/InventoryManagement/getInventory", function(data) {
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
				        		"<td><a href='#' onclick='return efunction(this)'><i class='material-icons' style='font-size:25px;'>edit</i></a></td>"+
				        		"<td><a href='#' onclick='return deleterowfn(this)''><i class='material-icons' style='font-size:25px;'>delete</i></a></td>"+
				        		"</tr>");
				   		 });
					});
				</script>
			</section>
		</div>
		<footer>
			<label style="float:right; padding-right:30px;">&copy; Inventory Management</label>
		</footer>
	</body>
</html>
