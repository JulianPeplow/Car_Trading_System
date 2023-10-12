package coursework_question4;

public class Car {
  private int id;
  private String name;
  private String colour;
  private double reservedPrice;
  private CarType gearbox;
  private CarBody body;
  private int numberOfSeats;
  private Condition condition;
  private SaleType type;

  public Car(int id, String name, double reservedPrice, Condition condition, SaleType type) 
      throws IllegalArgumentException {
    super();
    if (id <= 0 || name == null || reservedPrice < 0 || condition == null || type == null) {
      throw new IllegalArgumentException();
    } else {
      this.id = id;
      this.name = name;
      this.reservedPrice = reservedPrice;
      this.condition = condition;
      this.type = type;
    }
  }

  public int getID() {
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
  
  public SaleType getType() {
    return type;
  }

  public void setType(SaleType type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException();
    }
    this.type = type;
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
