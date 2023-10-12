package coursework_question2;

import java.util.HashMap;
import java.util.Map;

public class Trader {
  protected String name;
  protected Map<Advert, User> carsForSale;
  protected Map<Advert, User> soldCars;
  protected Map<Advert, User> unsoldCars;

  public Trader(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.carsForSale = new HashMap<Advert, User>();
    this.soldCars = new HashMap<Advert, User>();
    this.unsoldCars = new HashMap<Advert, User>();
  }

  private boolean checkExistence(Car car) throws IllegalArgumentException {
    if (car == null) {
      throw new IllegalArgumentException();
    } else { 
      for (Advert advert : this.carsForSale.keySet()) {
        if (advert.getCar() == car) {
          return true;
        }
      }
    }
    return false;
  }

  public String displaySoldCars() {
    String output = "";
    output += "SOLD CARS:\n";
    for (Advert advert : this.soldCars.keySet()) {
      output += advert.getCar().getID() + " - " + "Purchased by ";
      output += this.soldCars.get(advert).getName() + " with a successful £";
      output += String.format("%.2f", advert.getHighestOffer().getValue()) + " bid.\n";
    }
    return output;
  }

  public String displayStatistics() {
    return "Statistics";
  }

  public String displayUnsoldCars() {
    String output = "";
    output += "UNSOLD CARS:\n";
    for (Advert advert : this.unsoldCars.keySet()) {
      output += advert.toString();
    }
    return output;
  }

  private void endSale(Advert advert) throws IllegalArgumentException {
    if (advert == null) {
      throw new IllegalArgumentException();
    } else {
      this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
      this.unsoldCars.remove(advert);
      this.carsForSale.remove(advert);
    }
  }

  public boolean placeOffer(Advert carAdvert, User user, double value) 
      throws IllegalArgumentException {
    if (carAdvert == null || user == null || value < 0) {
      throw new IllegalArgumentException();
    } else if (checkExistence(carAdvert.getCar())) {
      boolean offerPlaced = carAdvert.placeOffer(user, value);
      
      if (offerPlaced && carAdvert.getHighestOffer().getValue() >= carAdvert.getCar().getPrice()) {
        endSale(carAdvert); 
      } else if (offerPlaced && carAdvert.getHighestOffer().getValue()
          < carAdvert.getCar().getPrice()) {
        this.unsoldCars.put(carAdvert, this.carsForSale.get(carAdvert));
        return false;
      } 
      return true;
    }
    return false;
  }

  public void registerCar(Advert carAdvert, User user, String colour, CarType type, 
      CarBody body, int noOfSeats) throws IllegalArgumentException {
    if (carAdvert == null || user == null || colour == null || type == null 
        || body == null || noOfSeats <= 0) {
      throw new IllegalArgumentException();
    } else if (!checkExistence(carAdvert.getCar())
        && carAdvert.getCar().getType() == SaleType.FORSALE) {
      carAdvert.getCar().setColour(colour);
      carAdvert.getCar().setGearbox(type);
      carAdvert.getCar().setBody(body);
      carAdvert.getCar().setNumberOfSeats(noOfSeats);
      this.carsForSale.put(carAdvert, user);
    }
  }

}
