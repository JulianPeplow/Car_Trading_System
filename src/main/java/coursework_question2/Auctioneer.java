package coursework_question2;

import java.util.HashMap;
import java.util.Map;

/**
 * This class manages all the car adverts that are available for auction,
 * and keeps track of those that have been sold and/or remained unsold at the end of a sale.
 * 
 * <p>Auctioneer.java
 *
 * @author jp01534
 */
public class Auctioneer {
  /**
   * Field storing the name of this Auctioneer.
   */
  protected String name;
  /**
   * Field mapping Adverts of cars for sale to Users.
   */
  protected Map<Advert, User> carsForSale;
  /**
   * Field mapping Adverts of sold cars to Users.
   */
  protected Map<Advert, User> soldCars;
  /**
   * Field mapping Adverts of unsold cars to Users.
   */
  protected Map<Advert, User> unsoldCars;

  /**
   * This parameter constructor initialises the name field and defines the Maps as HashMaps.
   *
   * @param name The name to be set to this Auctioneer.
   * @throws IllegalArgumentException Thrown if name is null.
   */
  public Auctioneer(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.carsForSale = new HashMap<Advert, User>();
    this.soldCars = new HashMap<Advert, User>();
    this.unsoldCars = new HashMap<Advert, User>();
  }

  /**
   * This method checks whether the car passed as a parameter is already for sale.
   * 
   * <p>This method returns true if the car passed as a parameter is already for sale.
   * This method returns false if the car passed as a parameter is not already for sale.
   *
   * @param car The car to be checked if it is already for sale.
   * @return true or false.
   */
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

  /**
   * This method displays the cars that were sold at the end of a sale.
   * 
   * <p>This method displays each car that was sold and was kept track of by this auctioneer.
   * It displays the id and the buyer of the car, and the price it was bought at. 
   *
   * @return String containing display message of sold cars.
   */
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

  /**
   * Method displays "Statistics".
   *
   * @return String containing the word "Statistics".
   */
  public String displayStatistics() {
    return "Statistics";
  }

  /**
   * This method displays the cars that were not sold at the end of a sale.
   * 
   * <p>This method displays each car that was not sold and was kept track of by this auctioneer.
   * It displays the id, name, reserved price, gearbox type, body, colour, number of seats,
   * and condition of the car.
   *
   * @return String containing display message of unsold cars.
   */
  public String displayUnsoldCars() {
    String output = "";
    output += "UNSOLD CARS:\n";
    for (Advert advert : this.unsoldCars.keySet()) {
      output += advert.toString();
    }
    return output;
  }

  /**
   * This method ends a car advert.
   * 
   * <p>This method ends the car advert. If the car advert's highest offer was less than
   * the reserved price of the car, then this method puts the advert (key) in the unsoldCars HashMap
   * and associates it with the user (value). Otherwise, this method puts the advert (key) in the
   * soldCars HashMap and associates it with the user (value). Then, this method removes the 
   * advert (key) from the carsForSale HashMap as the sale has ended.
   *
   * @param advert The car advert to be ended.
   * @throws IllegalArgumentException Thrown if advert is null.
   */
  public void endSale(Advert advert) throws IllegalArgumentException {
    if (advert == null) {
      throw new IllegalArgumentException();
    } else if (advert.getHighestOffer().getValue() < advert.getCar().getPrice()) {
      this.unsoldCars.put(advert, this.carsForSale.get(advert));
    } else {
      this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
    }
    this.carsForSale.remove(advert);
  }

  /**
   * This method places an offer on a car advert.
   * 
   * <p>This method checks if the car is for sale by using the checkExisteence(Car car) method.
   * If the car is for sale, then this method passes the user and value to the placeOffer() 
   * method in Advert.java. 
   * The method placeOffer() in Advert.java first checks whether there are already offers
   * for the car and if so it will check whether the value passed is higher than the highest
   * offer that has been placed and if so it will place the offer and return true. 
   * If offers have not been placed for the car, then the placeOffer() method in Advert.java
   * will place the offer and return true.
   * If there are already offers for the car and the value passed is lower than the highest
   * offer, then the method will return false, thus this method will also return false.
   * If the car is not for sale, then this method will return false.
   *
   * @param carAdvert The car advert on which the user wants to place an offer.
   * @param user The person (potential buyer) who wants to place an offer on the car.
   * @param value The amount of money the user has offered for the car.
   * @return true or false.
   * @throws IllegalArgumentException Thrown if carAdvert or user are null,
   *     or if value is smaller than zero.
   */
  public boolean placeOffer(Advert carAdvert, User user, double value) 
      throws IllegalArgumentException {
    if (carAdvert == null || user == null || value < 0) {
      throw new IllegalArgumentException();
    } else if (checkExistence(carAdvert.getCar())) {
      return carAdvert.placeOffer(user, value);
    }
    return false;
  }

  /**
   * This method registers a specific car if it has not been registered yet.
   * 
   * <p>This method first checks if the car already exists by using the checkExisteence(Car car) 
   * method. If the car already exists, then this method does nothing. If the car does not already
   * exist, then this method initialises the car's specification which includes the colour, gearbox,
   * body and number of seats of the car. Then, this method puts the advert of the car in the
   * carsForSale HashMap and associates it with the seller of the car.
   *
   * @param carAdvert The advert of the car to be registered.
   * @param user The seller of the car.
   * @param colour The colour of the car.
   * @param type The gearbox type of the car.
   * @param body The body type of the car.
   * @param noOfSeats The number of seats of the car.
   * @throws IllegalArgumentException Thrown if carAdvert or user or colour or type or body is null,
   *     or if noOfSeats is less than or equal to zero.
   */
  public void registerCar(Advert carAdvert, User user, String colour, CarType type, 
      CarBody body, int noOfSeats) throws IllegalArgumentException {
    if (carAdvert == null || user == null || colour == null || type == null 
        || body == null || noOfSeats <= 0) {
      throw new IllegalArgumentException();
    } else if (!checkExistence(carAdvert.getCar())) {
      carAdvert.getCar().setColour(colour);
      carAdvert.getCar().setGearbox(type);
      carAdvert.getCar().setBody(body);
      carAdvert.getCar().setNumberOfSeats(noOfSeats);
      this.carsForSale.put(carAdvert, user);
    }
  }
}
