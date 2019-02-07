package RentalCars.RentalCars;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RentalCarsTest {
	
	
	RentalCars c=null;
	
	//RentalCarsObject cars=new RentalCarsObject();
	@BeforeMethod
	public void init() {
		if(c==null) {
			c=new RentalCars();
			c.init();
		}
	}
	
	
	
	@Test
	public void verifyCarColor(){
		c.blueCar(c.rentalCarObjects);
	}
	
	
	@Test
	public void verifyLowestPriceCar( ){
		c.givelowprice( );
	}
	
	
	@Test
	public void verifyLowestPriceAfterDiscount(){
		c.getLeastPriceAfterDiscount( );
	}
	
	@Test
	public void verifyHeightRevenu(){
		c.revenueGeneratingCar( );
	}
	
	 
}
