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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new BufferedReader(new FileReader(
					"/home/bhavya/workspace/RentalCars/target/cars3.json")));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("Car");
			int size = jsonArray.size();
			System.out.println(size);

			Map<String, RentalCarsObject> m=  new HashMap<String, RentalCarsObject>();
			List<RentalCarsObject> list=new ArrayList<RentalCarsObject>();
			//CarsObject c = new CarsObject();
			for (int i = 0; i < size; i++) {
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
					RentalCarsObject c = new RentalCarsObject();
			        c.setPrice(price);
			        c.setDiscount(discount);
			        c.setMake(make);
			        c.setColor(color);
			       c.setDepreciation(depreciation);
			       c.setYoymaintenancecost(yoymaintenancecost);
					m.put(vin, c);
					list.add(c);
					
					verifyCarColror(c);
					  
			}
			givelowprice(list);
			//getLeastPrice( m);
		   getLeastPriceAfterDiscount(list);
		   
		   revenueGeneratingCar(list);

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

//1.Verify the Car color 
		public static void verifyCarColror(RentalCarsObject c){
			if((c.getMake().equalsIgnoreCase("Tesla"))&& c.getColor().equals("Blue") ){
				System.err.println("  Here is Make+ "+c.make+ " color "+c.color );
			}
			
		}

	
	
	//2. Question 2: Return all cars which have the lowest per day rental cost for both cases:
		//a. Price only
	public static void givelowprice(List<RentalCarsObject> list){
	
		Collections.sort(list, new RentalpriceComparator());
		RentalCarsObject k=list.get(0);
	    System.out.println("  The lest price car is  "+k.make+ " with color "+k.color +" and of car is"+k.price+ " "+k.discount);
	}
	

	
	//2. Question 2: Return all cars which have the lowest per day rental cost for both cases:
	//b. Price after discounts
	public static void getLeastPriceAfterDiscount(List<RentalCarsObject> list){
		
		//List<CarsObject> list =new ArrayList(m.values());
		Collections.sort(list, new RentalpricediscountComparator());
		RentalCarsObject k=list.get(0);
		
	    System.out.println("  The lowest  price after discount car is  "+k.make+ " with color "+k.color +" and of car is"+k.price+ " "+k.discount);
	}
	
	public static void revenueGeneratingCar(List<RentalCarsObject> list){
		
		Collections.sort(list,new RentalPriceComparatorrevenue());
		RentalCarsObject k=list.get(0);
		
		Double revenue=k.yoymaintenancecost+k.depreciation;
		//System.out.println(k.yoymaintenancecost);
		System.out.println("  The highes revenue generating car is  "+k.make+ " with color "+k.color +" and it generate revenu is:"+revenue);
	}
}
