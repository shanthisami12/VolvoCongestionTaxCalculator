<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/ctax.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="js/ctax.js"></script>
<title>Congestion Tax Calculator - Gothenburg</title>
</head>
<body>
	<h1 id="heading">Congestion Tax Calculator - Gothenburg</h1>
	<div id="formDiv">
		<form action="" id="form">
			<table>
				<tr>
					<td> Select the type of vehicle : </td>
					<td>
						<select name="cars" id="vehicles">
						 <option value="">Select the vehicle type </option>
						 <option value="motorcycle">Motorcycle</option>
						 <option value="car">Car</option>
						 <option value="bus">Bus</option>
						 <option value="tractor">Tractor</option>
						 <option value="emergency">Emergency</option>
						 <option value="diplomat">Diplomat</option>
						 <option value="military">Military</option>
						 <option value="foreign">Foreign</option>
						</select>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td> Select the date : </td>
					<td> <input type="text" id="datePicker" placeholder="Select the date"/> </td>
				</tr>
				<tr>
					<td> Select the time : </td>
					<td> 
						<input type="time" id="timePicker" placeholder="Select the time"/> 
					</td>
				</tr>
				<tr>
					<td><input type="button" id="add" value="Add data"/>
					<input type="button" id="send" value="Submit"/>
					<input type="reset" value="Reset"/></td>
				</tr>
			</table>
			<br/>
			<h3 id="totalFee"></h3>
		</form>
	</div>
</body>
</html>