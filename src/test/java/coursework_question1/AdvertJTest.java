package coursework_question1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AdvertJTest {
  Advert advert = new Advert(new Car(5533, "Volkswagen Polo", 6000, Condition.USED));

  @Test
  public void testAdvert() {
    advert = new Advert(new Car(5678, "Audi A3", 20000, Condition.NEW));
  }

  @Test
  public void testGetHighestOffer() {
    Advert advert = new Advert(new Car(5533, "Volkswagen Polo", 6000, Condition.USED));
    User buyer1 = new User("David Williams");
    User buyer2 = new User("George Taylor");

    advert.placeOffer(buyer1, 6200);
    advert.placeOffer(buyer2, 6300);

    Offer offer = new Offer(buyer2, 6300);

    assertEquals(advert.getHighestOffer().toString(), offer.toString());
  }

  @Test
  public void coursework_testPlaceOfferOneInvalidOffer() {
    Advert advert = new Advert(new Car(2468, "BMW X5", 40000.05, Condition.USED));
    User buyer = new User("Emma Jones");

    assertTrue(advert.placeOffer(buyer, 39999.99));
  }

  @Test
  public void coursework_testPlaceOfferOneValidOffer() {
    Advert advert = new Advert(new Car(1234, "Volkswagen Golf", 10000, Condition.USED));
    User buyer = new User("John Smith");

    assertTrue(advert.placeOffer(buyer, 11000));
  }

  @Test
  public void coursework_testPlaceOfferOneValidOfferAndOneInvalidOffer() {
    Advert advert = new Advert(new Car(1369, "Fiat 500", 8000, Condition.USED));
    User buyer1 = new User("David Williams");
    User buyer2 = new User("George Taylor");

    advert.placeOffer(buyer1, 8100);

    assertFalse(advert.placeOffer(buyer2, 8050));

  }

  @Test
  public void testGetCar() {
    Car car = new Car(5678, "Audi A3", 20000, Condition.NEW);
    Advert advert = new Advert(car);

    assertEquals(advert.getCar(), car);
  }

  @Test
  public void testToString() {
    Car car = new Car(6561, "Porsche 911", 90000, Condition.USED);
    car.setColour("White");
    car.setGearbox(CarType.MANUAL);
    car.setBody(CarBody.SUPERCAR);
    car.setNumberOfSeats(2);
    Advert advert = new Advert(car);
    User buyer = new User("John Smith");

    advert.placeOffer(buyer, 91000);

    assertEquals("Ad: 6561 - Porsche 911 (£91000.00)\n\tType: MANUAL\n\tStyle: SUPERCAR"
        + "\n\tColour: White\n\tNo. of Seats: 2\n\tCondition: USED\n", advert.toString());
  }

}
