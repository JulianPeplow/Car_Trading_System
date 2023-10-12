package coursework_question1;

import java.util.ArrayList;
import java.util.List;

public class Advert {
  private Car car;
  private List<Offer> offers;

  public Advert(Car car) throws IllegalArgumentException {
    super();
    if (car == null) {
      throw new IllegalArgumentException();
    } else {
      this.car = car;
      this.offers = new ArrayList<Offer>();
    }
  }

  public Car getCar() {
    return car;
  }

  public Offer getHighestOffer() {
    Double highestOfferValue = -1.0;
    Offer highestOffer = null;
    for (Offer offer : offers) {
      if (offer.getValue() > highestOfferValue) {
        highestOffer = offer;
      }
    }
    return highestOffer;
  }

  public boolean placeOffer(User buyer, double value) throws IllegalArgumentException {
    if (buyer == null || value < 0) {
      throw new IllegalArgumentException();
    } else {
      if (offers.size() > 0) {
        if (value > getHighestOffer().getValue()) {
          offers.add(new Offer(buyer, value));
          return true;
        }
      } else {
        offers.add(new Offer(buyer, value));
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    String output = "";
    if (getHighestOffer().getValue() > getCar().getPrice()) {
      output += "Ad: " + getCar().getId() + " - " + getCar().getName();
      output += " (£" + String.format("%.2f", getHighestOffer().getValue()) + ")\n";
    } else {
      output += "Ad: " + getCar().getId() + " - " + getCar().getName();
      output += " (£" + String.format("%.2f", getCar().getPrice()) + ")\n";
    }
    output += "\tType: " + getCar().getGearbox() + "\n";
    output += "\tStyle: " + getCar().getBodyStyle() + "\n";
    output += "\tColour: " + getCar().getColour() + "\n";
    output += "\tNo. of Seats: " + getCar().getNumberOfSeats() + "\n";
    output += "\tCondition: " + getCar().getCondition() + "\n";
    return output;
  }
}



