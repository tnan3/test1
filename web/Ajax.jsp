<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>
	function showFields(string1) {
	    //alert (string1);
		var xRequest1;
		
		if (string1 == "") {
			document.getElementById("Show_update").innerHTML = "";
			return;
		}

		xRequest1 = new XMLHttpRequest();
        xRequest1.open("get", "/helloServlet?user_name="+string1, "true");

		xRequest1.onreadystatechange = function() {
			if ((xRequest1.readyState == 4) && (xRequest1.status == 200)) {
				document.getElementById("Show_update").innerHTML = xRequest1.responseText;
			}
		}


		xRequest1.send();

	}
</script>
</head>

<body>
	<form>

		<select name="user_name" onchange="showFields(this.value)">

			<option value="">Employee Designations</option>

			<option value="Officer">Officer</option>

			<option value="Supervisor">Supervisor</option>

			<option value="Manager">Manager</option>

		</select>

	</form>

	<br />

	<div id="Show_update">To Update the designation...</div>

</body>
</html>