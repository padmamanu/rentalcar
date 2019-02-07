package RentalCars.RentalCars;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RentalCars {

	public static List<RentalCarsObject> rentalCarObjects =null;
	
	public static void init( ) {
		// TODO Auto-generated method stub

		JSONParser parser = new JSONParser();
		
		
		try {
			Object obj = parser.parse(new BufferedReader(new FileReader(
					"/home/bhavya/workspace/RentalCars/target/cars3.json")));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("Car");
			int size = jsonArray.size();
			

			
			rentalCarObjects=new ArrayList<RentalCarsObject>();
			
			for (int i = 0; i <size; i++) {
				JSONObject Cars = (JSONObject) jsonArray.get(i);
				String cars = Cars.toString();
				
				String make = (String) Cars.get("make");
				String vin = (String) Cars.get("vin");
				JSONObject metadata = (JSONObject) Cars.get("metadata");
				String color = (String) metadata.get("Color");
				String notes = (String) metadata.get("Notes");
				
				
					JSONObject perdayrent = (JSONObject) Cars.get("perdayrent");
					Long discount = (Long) perdayrent.get("Discount");
					Long price = (Long) perdayrent.get("Price");

					JSONObject metrics=(JSONObject) Cars.get("metrics");
					Double depreciation=(Double) metrics.get("depreciation");
					Double yoymaintenancecost=(Double) metrics.get("yoymaintenancecost");
					
					RentalCarsObject carObject = new RentalCarsObject();
			        carObject.setPrice(price);
			        carObject.setDiscount(discount);
			        carObject.setMake(make);
			        carObject.setColor(color);
			        carObject.setDepreciation(depreciation);
			        carObject.setYoymaintenancecost(yoymaintenancecost);
			     
				    rentalCarObjects.add(carObject);
					
					
					  
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	
	public static void blueCar(List<RentalCarsObject> cars) {
		for(RentalCarsObject c:cars) {
			//System.err.println("----" + c.getMake() + " color " + c.getColor());
			if((c.getMake().equalsIgnoreCase("Tesla"))&& c.getColor().equalsIgnoreCase("Blue") ) {
				System.err.println("  Here is Make: "+c.make+ " color "+c.color );
			}
		}
		
		
		}

	
	public static void givelowprice(){
	   
		Collections.sort(rentalCarObjects, new RentalpriceComparator());
		RentalCarsObject k=rentalCarObjects.get(0);
	    System.err.println("Lowest price car is  "+k.make+ " with color "+k.color +" and of car is  "+k.price+ " discount of: "+k.discount);
	}
	

	
	
	public static void getLeastPriceAfterDiscount( ){
		
		
		Collections.sort(rentalCarObjects, new RentalpricediscountComparator());
		RentalCarsObject k=rentalCarObjects.get(0);
		
	    System.err.println("Lowest  price after discount car is  "+k.make+ " with color "+k.color +" and of car is: "+k.price+ " discount:  "+k.discount);
	}
	
	
	
	public static void revenueGeneratingCar( ){
		
		Collections.sort(rentalCarObjects,new RentalPriceComparatorrevenue());
		RentalCarsObject k=rentalCarObjects.get(0);
		Double revenue=k.yoymaintenancecost+k.depreciation;
		
		System.err.println("Highest revenue generating car is  "+k.make+ " with color "+k.color +" and it generate revenu of :"+revenue);
	}
}
