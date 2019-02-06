package RentalCars.RentalCars;

import java.util.Comparator;

public class RentalPriceComparatorrevenue implements Comparator<RentalCarsObject>{
	
	
	public  int compare(RentalCarsObject c1, RentalCarsObject c2){
		if((c1.yoymaintenancecost+c1.depreciation)>(c2.yoymaintenancecost+c2.depreciation)){
			return -1;
		}
		else if((c1.yoymaintenancecost+c1.depreciation)==(c2.yoymaintenancecost+c2.depreciation)){
			return 0;
		}
		else{
			return 1;
		}
	}

}

