package RentalCars.RentalCars;

import java.util.Comparator;

public class RentalpricediscountComparator implements Comparator<RentalCarsObject>  {
	
	
	public int compare(RentalCarsObject c1, RentalCarsObject c2){
		if((c1.price-c1.discount)> (c2.price-c2.discount)){
			return 1;
		}
		else if((c1.price-c1.discount)== (c2.price-c2.discount)){
			return 0;
			
		}
		else{
			return -1;
		}
		
	}

}


