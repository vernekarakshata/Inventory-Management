$(document).ready(function(){
			    $("form").submit(function(){
	        event.preventDefault();
	        checkUser();
	    });
	});

function checkUser() {
	var obj = {};
	
	obj["username"] = document.getElementById("name").value;
	obj["password"] = document.getElementById("password").value;

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "checkuserexist",
		data : JSON.stringify(obj),
		timeout : 100000,
		success : function(data, textStatus, request) {
			window.location.replace(request.getResponseHeader('Location'));
		},
		error : function(e) {
			document.getElementById("errormsg").innerHTML = e.responseText;
		}
	});
}

function additem() {
    var table = document.getElementById("inventoryTable");
	var tr = document.createElement("tr");
	var td = document.createElement("td");
	var form = document.createElement("form");
	var tbody = document.getElementById("tb");
	tr.appendChild(td);
	for(var i=0; i<6; i++)
	{
		var tdwithinput = document.createElement("td");
		var input = document.createElement("input");
		input.setAttribute("type", "text");
		input.setAttribute("value", '');
		tdwithinput.appendChild(input);
		tr.appendChild(tdwithinput);			
	}
	var td1 = document.createElement("td");
	tr.appendChild(td1);
	var edita = document.createElement("a");
	edita.setAttribute("href", "#");
	edita.setAttribute("onclick", "return afunction(this)");
	var editi = document.createElement("i");
	editi.setAttribute("class", "material-icons");
	var edittext = document.createTextNode("playlist_add_check");
	var edittd = document.createElement("td");
	
	editi.appendChild(edittext);
	edita.appendChild(editi);
	edittd.appendChild(edita);
	tr.appendChild(edittd);
	
	var deletea = document.createElement("a");
	deletea.setAttribute("href", "#");
	deletea.setAttribute("onclick", "return dfunction(this)");
	var deletei = document.createElement("i");
	deletei.setAttribute("class", "material-icons");
	var deletetext = document.createTextNode("close");
	var deletetd = document.createElement("td");
	
	deletei.appendChild(deletetext);
	deletea.appendChild(deletei);
	deletetd.appendChild(deletea);
	tr.appendChild(deletetd);
	
	tbody.appendChild(tr);
	table.appendChild(tbody);
}

function efunction(element)
{
	var iTable = document.getElementById('inventoryTable').tBodies[0];	
	var row = element.parentNode.parentNode.rowIndex;
	var col = element.parentNode.cellIndex;
	
	for(var i=1; i<7; i++)
	{
		var value = iTable.rows[row - 1].cells[i].childNodes[0].value;
		var input = "<input type='text' value='"+value+"'>";
		document.getElementById("inventoryTable").rows[row].cells[i].innerHTML = input;
	}
	
	var addstr = "<a href='#' onclick='return afunction(this)'><i class='material-icons' style='font-size:25px;'>playlist_add_check</i></a>";
	document.getElementById("inventoryTable").rows[row].cells[8].innerHTML = addstr;
	
	var closestr = "<a href='#' onclick='return dfunction(this)'><i class='material-icons' style='font-size:25px;''>close</i></a>";
	document.getElementById("inventoryTable").rows[row].cells[9].innerHTML = closestr;			
				
	return false;
}



function afunction(element)
{
	var iTable = document.getElementById('inventoryTable').tBodies[0];	
	var row = element.parentNode.parentNode.rowIndex;
	var col = element.parentNode.cellIndex;
	var thead = document.getElementsByTagName("th");
	var obj = {};
	if(performValidations(row, iTable))
	{
		for(var i=0; i<8; i++)	{
			
			if(i==0 || i==7)
				var value = iTable.rows[row - 1].cells[i].innerHTML;
			else
				var value = iTable.rows[row - 1].cells[i].childNodes[0].value;
			
			var name = thead[i].innerHTML.replace(/\s+/g, '').toLowerCase();
			obj[name] = value;
		}
		
		var rowId = iTable.rows[row - 1].cells[0].innerHTML;
		
		if(rowId)
		{
			$.ajax({
				type : "PUT",
				contentType : "application/json",
				url : "updateInventory",
				data : JSON.stringify(obj),
				timeout : 100000,
				success : function(s) {		
					alert(s);
					location.reload();
				},
				error : function(e) {
					alert(e.responseText);
				}
			});		
			
		}else{
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "addInventory",
				data : JSON.stringify(obj),
				timeout : 100000,
				success : function(s) {		
					alert(s);
					location.reload();
				},
				error : function(e) {
					alert(e.responseText);
				}
			});			
		}
		return false;
	}
				
}

function deleterowfn(element)
{
	var iTable = document.getElementById('inventoryTable').tBodies[0];	
	var thead = document.getElementsByTagName("th");
	var row = element.parentNode.parentNode.rowIndex;
	var col = element.parentNode.cellIndex;
	var obj = {};
	
	for(var i=0; i<8; i++)	{
		
		if(i==0 || i==7)
			var value = iTable.rows[row - 1].cells[i].innerHTML;
		else
			var value = iTable.rows[row - 1].cells[i].childNodes[0].value;
		
		var name = thead[i].innerHTML.replace(/\s+/g, '').toLowerCase();
		obj[name] = value;
	}
	
	$.ajax({
		type : "DELETE",
		contentType : "application/json",
		url : "deleteInventory",
		data : JSON.stringify(obj),
		timeout : 100000,
		success : function(s) {		
			alert(s);
		},
		error : function(e) {
			alert(e.responseText);
		},
		complete : function(e) {
			location.reload();
		}
	});			
}

function dfunction(element)		//check if id is present den dont delete the row else delete the entire row
{
	var row = element.parentNode.parentNode.rowIndex;
	var col = element.parentNode.cellIndex;
	var iTable = document.getElementById('inventoryTable').tBodies[0];
	var rowId = iTable.rows[row - 1].cells[0].innerHTML;
				
	if(rowId)
	{
		var replaceString = 'readonly>';				
		for(var i=1; i<7; i++)
		{
			var txt = document.getElementById("inventoryTable").rows[row].cells[i].innerHTML;
			txt = txt.replace(">", replaceString);
			document.getElementById("inventoryTable").rows[row].cells[i].innerHTML = txt;
		}
		var editstr = "<a href='#' onclick='return efunction(this)'><i class='material-icons' style='font-size:25px;'>edit</i></a>";
		document.getElementById("inventoryTable").rows[row].cells[8].innerHTML = editstr;
		
		var deletestr="<a href='#' onclick='return deleterowfn(this)'><i class='material-icons' style='font-size:25px;'>delete</i></a>";
		document.getElementById("inventoryTable").rows[row].cells[9].innerHTML = deletestr;
	}else{
		document.getElementById("inventoryTable").deleteRow(row);
	}
	
	
	return false;
}

function performValidations(row, table)
{
	var alphapattern = new RegExp("^[a-zA-Z ]+$");
	var decimalpattern = new RegExp("^[0-9]+(.)?[0-9]+$");
	var numberpattern = new RegExp("^[0-9]+$");
	var datepattern = new RegExp("^[1-2]{1}[0-9]{3}-(0[1-9]{1}|1[0-2]{1})-(0[1-9]{1}|[1-2]{1}[0-9]{1}|3[0-1]{1})$");
	var productName = table.rows[row - 1].cells[1].childNodes[0].value;
	var vendor = table.rows[row - 1].cells[2].childNodes[0].value;
	var mrp = table.rows[row - 1].cells[3].childNodes[0].value;
	var batchnumber = table.rows[row - 1].cells[4].childNodes[0].value;
	var batchdate = table.rows[row - 1].cells[5].childNodes[0].value;
	var quantity = table.rows[row - 1].cells[6].childNodes[0].value;
	
	if(!productName || !vendor || !mrp || !batchnumber || !batchdate || !quantity)
	{
		alert("Fields cant be empty!Please enter valid data");
		return false;
	}		
	if(!alphapattern.test(productName))
	{
		alert("Product Name must contain only alphabets!");
		return false;				
	}
	if(!alphapattern.test(vendor))
	{
		alert("Vendor must contain only alphabets!");
		return false;				
	}
	if(!decimalpattern.test(mrp))
	{
		alert("MRP must be decimal format");
		return false;				
	}
	if(!numberpattern.test(batchnumber))
	{
		alert("Batch Number must consist of only numbers");
		return false;				
	}
	if(!datepattern.test(batchdate))
	{
		alert("Date must be of valid type : yyyy-mm-dd");
		return false;				
	}
	if(!numberpattern.test(quantity))
	{
		alert("Quantity must be valid integer value");
		return false;				
	}
	return true;
	
}

function reject(element){
	var iTable = document.getElementById('inventoryTable').tBodies[0];	
	var thead = document.getElementsByTagName("th");
	var row = element.parentNode.parentNode.rowIndex;
	var col = element.parentNode.cellIndex;
	var obj = {};
	
	for(var i=0; i<8; i++)	{
		
		if(i==0 || i==7)
			var value = iTable.rows[row - 1].cells[i].innerHTML;
		else
			var value = iTable.rows[row - 1].cells[i].childNodes[0].value;
		
		var name = thead[i].innerHTML.replace(/\s+/g, '').toLowerCase();
		obj[name] = value;
	}
	
	$.ajax({
		type : "DELETE",
		contentType : "application/json",
		url : "rejectApprovalInventory",
		data : JSON.stringify(obj),
		timeout : 100000,
		success : function(s) {		
			alert(s);
		},
		error : function(e) {
			alert(e.responseText);
		},
		complete : function(e) {
			location.reload();
		}
	});			
	
	return false;
}


function approve(element){
	
	var iTable = document.getElementById('inventoryTable').tBodies[0];	
	var thead = document.getElementsByTagName("th");
	var row = element.parentNode.parentNode.rowIndex;
	var col = element.parentNode.cellIndex;
	var obj = {};
	
	for(var i=0; i<8; i++)	{
		
		if(i==0 || i==7)
			var value = iTable.rows[row - 1].cells[i].innerHTML;
		else
			var value = iTable.rows[row - 1].cells[i].childNodes[0].value;
		
		var name = thead[i].innerHTML.replace(/\s+/g, '').toLowerCase();
		obj[name] = value;
	}
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "approveApprovalInventory",
		data : JSON.stringify(obj),
		timeout : 100000,
		success : function(s) {		
			alert(s);
		},
		error : function(e) {
			alert(e.responseText);
		},
		complete : function(e) {
			location.reload();
		}
	});			
	
	return false;
}