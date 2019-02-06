package RentalCars.RentalCars;

import java.util.Comparator;




public class RentalpriceComparator implements Comparator<RentalCarsObject>{

	
	public int compare(RentalCarsObject c1,RentalCarsObject c2){
		if(c1.price > c2.price){
			return 1;
		
		}
		else if(c1.price==c2.price){
			return 0;
		}
		else {
			return  -1;
		}
	}
	
}

