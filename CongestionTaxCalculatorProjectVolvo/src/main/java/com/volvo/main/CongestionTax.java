package com.volvo.main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.volvo.util.Util;

/**
 * Servlet implementation class CongestionTax
 */
@WebServlet("/CongestionTax")
public class CongestionTax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CongestionTax() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jelem = gson.fromJson(request.getParameter("vehicleData"), JsonElement.class);
        JsonArray vehicleDataArray = jelem.getAsJsonArray();
        
        Date[] dates = new Date[vehicleDataArray.size()];
        String vehicleType = " ";
        int i = 0;
        for (JsonElement vehicle : vehicleDataArray) {
            JsonObject vehicleData = vehicle.getAsJsonObject();
            vehicleType = vehicleData.get("vehicleType").getAsString();
            try {
				dates[i] = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(vehicleData.get("date").getAsString() + " " + vehicleData.get("time").getAsString());
				i+=1;
			} catch (ParseException e) {
				System.out.println("Some exception occurred : " + e.getLocalizedMessage());
			}
        }
      
        Util util = new Util();
        int totalFee = util.getTax(vehicleType, dates);
        JsonObject obj = new JsonObject();
        obj.addProperty("fee", totalFee);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
		response.getWriter().write(obj.toString());
	}
}
