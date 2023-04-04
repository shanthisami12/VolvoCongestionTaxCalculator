package com.volvo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.volvo.model.Bus;
import com.volvo.model.Car;
import com.volvo.model.Diplomat;
import com.volvo.model.Emergency;
import com.volvo.model.Foreign;
import com.volvo.model.Military;
import com.volvo.model.Motorbike;
import com.volvo.model.Tractor;
import com.volvo.model.Vehicle;

public class Util 
{
	private Map<String, Vehicle> tollFreeVehicles = new HashMap<>();
	
	public Util()
	{
		/*
		 * Creates a map which includes vehicles that are exempted from paying tax
		*/
		this.tollFreeVehicles.put("motorcycle", new Motorbike());
        this.tollFreeVehicles.put("tractor", new Tractor());
        this.tollFreeVehicles.put("emergency", new Emergency());
        this.tollFreeVehicles.put("diplomat", new Diplomat());
        this.tollFreeVehicles.put("foreign", new Foreign());
        this.tollFreeVehicles.put("military", new Military());
        this.tollFreeVehicles.put("bus", new Bus());
	}
    
    /*
     * Checking whether the provided vehicle is exempted from paying tax
    */
    private boolean IsTollFreeVehicle(Vehicle vehicle) {
        return Objects.isNull(vehicle)?false:this.tollFreeVehicles.containsKey(vehicle.getVehicleType());
    }
    
    public int GetTollFee(Date date, Vehicle vehicle)
    {
        if (IsTollFreeVehicle(vehicle)) return 0;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int tollFee = 0;

        switch(hour) {
        case 6:
        	tollFee = (minute >=0 && minute <=29)?8:13;
        	break;
        case 7:
        	if(minute >=0 && minute <=59) {
        		tollFee = 13;
        	}
        	break;
        case 8:
        	tollFee = (minute >= 0 && minute <= 29) ? 13:8;
        	break;
        case 9:
        	tollFee = 8;
        	break;
        case 10:
        	tollFee = 8;
        	break;
        case 11:
        	tollFee = 8;
        	break;
        case 12:
        	tollFee = 8;
        	break;
        case 13:
        	tollFee = 8;
        	break;
        case 14:
        	tollFee = 8;
        	break;
        case 15:
        	tollFee = (minute>=0 && minute<=29)? 13:18;
        	break;
        case 16:
        	tollFee = 18;
        	break;
        case 17:
        	if(minute >=0 && minute <=59) {
        		tollFee = 13;
        	}
        	break;
        case 18:
        	if(minute >=0 && minute <=29) {
        		tollFee = 8;
        	}
        	break;
        default:
        	tollFee = 0;
        	break;
        }
        return tollFee;
    }
    
    public int getTax(String vehicle, Date[] dates)
    {
        Date intervalStart = dates[0];
        int totalFee = 0;
        
        Vehicle vehicleType = Objects.isNull(this.tollFreeVehicles.get(vehicle))?
        					  new Car():this.tollFreeVehicles.get(vehicle);
        
        int firstFee = GetTollFee(intervalStart, vehicleType);
        totalFee = totalFee + firstFee;
        
        for (int i = 1; i < dates.length ; i++) {
            Date date = dates[i];
            int nextFee = GetTollFee(date, vehicleType);
          
            long diffInMillies = date.getTime() - intervalStart.getTime();
            long minutes = diffInMillies/1000/60;

            if (minutes > 60)
            {
            	totalFee += nextFee;
            }
            else
            {
            	totalFee = firstFee;
            }
        }                
      
        if (totalFee > 60) totalFee = 60;
        return totalFee;
    }
}
