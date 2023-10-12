package coursework_question3;

import java.util.HashMap;
import java.util.Map;

public abstract class Dealership {
  protected String name;
  protected Map<Advert, Seller> carsForSale;
  protected Map<Advert, Buyer> soldCars;
  protected Map<Advert, Seller> unsoldCars;
  
  public Dealership(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.carsForSale = new HashMap<Advert, Seller>();
    this.soldCars = new HashMap<Advert, Buyer>();
    this.unsoldCars = new HashMap<Advert, Seller>();
  }
  
  public abstract String displaySoldCars();
  
  public abstract String displayStatistics();
  
  public abstract String displayUnsoldCars();
  
  public abstract boolean placeOffer(Advert carAdvert, User user, double value);
  
  public abstract void registerCar(Advert carAdvert, User user, String colour, CarType type, 
      CarBody body, int noOfSeats);
  
}
