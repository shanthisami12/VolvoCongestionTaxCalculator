var disabledDays = ["01-01-2013", "03-28-2013", "03-29-2013",
					  "04-01-2013", "04-30-2013","05-01-2013",
					  "05-08-2013","05-09-2013","06-05-2013",
					  "06-06-2013","06-21-2013","07-01-2013",
					  "07-02-2013","07-03-2013","07-04-2013",
					  "07-05-2013","07-06-2013","07-07-2013",
					  "07-08-2013","07-09-2013","07-10-2013",
					  "07-11-2013","07-12-2013","07-13-2013",
					  "07-14-2013","07-15-2013","07-16-2013",
					  "07-17-2013","07-18-2013","07-19-2013",
					  "07-20-2013","07-21-2013","07-22-2013",
					  "07-23-2013","07-22-2013","07-23-2013",
					  "07-24-2013","07-25-2013","07-26-2013",
					  "07-27-2013","07-28-2013","07-29-2013",
					  "07-30-2013","07-31-2013","11-01-2013",
					  "12-24-2013","12-25-2013","12-26-2013",
					  "12-31-2013"
					  ];
var items = [];
var vehicles = ["motorcycle","bus","tractor","emergency","diplomat","military","foreign"];

function disableDates(date) {
    var dt = $.datepicker.formatDate('mm-dd-yy', date);
    var noWeekend = jQuery.datepicker.noWeekends(date);
    return noWeekend[0] ? (($.inArray(dt, disabledDays) < 0) ? [true] : [false]) : noWeekend;
}

$(document).ready(function(){
	$( "#datePicker").datepicker({
		changeMonth:true,
		changeYear:true,
	    beforeShowDay: disableDates,
	});
	
	$("#add").click(function(){
		var vehicleType = $("#vehicles").val();
		var date = $("#datePicker").val();
		var time = $("#timePicker").val();
		
		if(vehicleType != '' && date != '' && time != ''){
			item = {};
			item['vehicleType'] = vehicleType;
			item['date'] = date;
			item['time'] = time;
			
			items.push(item);
			
			$("#form").trigger("reset");
			alert("Data successfully added!");
		}
		else
		{
			alert("Please fill all the fields");
		}
	});
	
	$("#send").click(function(){
		var jsonString = JSON.stringify(items)
		console.log(jsonString);
		$.ajax({
			type:"POST",
			url: "CongestionTax",
			dataType:"json",
			data:{
				vehicleData : jsonString
			},
			success:function(data){
				items = [];
				totalFee.innerText = "The total congestion tax is : " + JSON.stringify(data.fee);
			},
			error:function(data){
				items = [];
				alert("Data not processed successfully" + JSON.stringify(data));
			}
		});
	});
	
	$("#vehicles").on('change',function(){
		if(vehicles.indexOf(this.value) > -1){
			alert("The selected vehicle is tax exempted, please change the vehicle type!!!");
			$("#form").trigger("reset");
		}
	})
});