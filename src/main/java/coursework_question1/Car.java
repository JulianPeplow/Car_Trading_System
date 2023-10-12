package coursework_question1;

public class Car {
  private int id;
  private String name;
  private String colour;
  private double reservedPrice;
  private CarType gearbox;
  private CarBody body;
  private int numberOfSeats;
  private Condition condition;

  public Car(int id, String name, double reservedPrice, Condition condition) 
      throws IllegalArgumentException {
    super();
    if (id <= 0 || name == null || reservedPrice < 0 || condition == null) {
      throw new IllegalArgumentException();
    } else {
      this.id = id;
      this.name = name;
      this.reservedPrice = reservedPrice;
      this.condition = condition;
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) throws IllegalArgumentException {
    if (colour == null) {
      throw new IllegalArgumentException();
    } else {
      this.colour = colour;
    }
  }

  public double getPrice() {
    return reservedPrice;
  }

  public CarType getGearbox() {
    return gearbox;
  }

  public void setGearbox(CarType gearbox) throws IllegalArgumentException {
    if (gearbox == null) {
      throw new IllegalArgumentException();
    } else {      
      this.gearbox = gearbox;
    }
  }

  public CarBody getBodyStyle() {
    return body;
  }

  public void setBody(CarBody body) throws IllegalArgumentException {
    if (body == null) {
      throw new IllegalArgumentException();
    }
    this.body = body;
  }

  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(int numberOfSeats) throws IllegalArgumentException {
    if (numberOfSeats <= 0) {
      throw new IllegalArgumentException();
    } else {      
      this.numberOfSeats = numberOfSeats;
    }
  }

  public Condition getCondition() {
    return condition;
  }

  public String displayCarSpecification() {
    String output = "";
    output += id + " " + "-" + " " + name + " " + "(£" + String.format("%.2f", reservedPrice) + ")";
    output += "\n\tType: " + gearbox + "\n";
    output += "\tStyle: " + body + "\n";
    output += "\tColour: " + colour + "\n";
    output += "\tNo. of Seats: " + numberOfSeats + "\n";
    output += "\tCondition: " + condition;
    return output;
  }

  @Override
  public String toString() {
    return id + " " + "-" + " " + name;
  }
}
