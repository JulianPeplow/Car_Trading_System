package coursework_question1;

public class Offer {
  private double value;
  private User buyer;

  public Offer(User buyer, double value) throws IllegalArgumentException {
    if (value <= 0 || buyer == null) {
      throw new IllegalArgumentException();
    } else {
      this.value = value;
      this.buyer = buyer;
    }
  }

  public double getValue() {
    return value;
  }
  
  public User getBuyer() {
    return buyer;
  }
  
  @Override
  public String toString() {
    return buyer.toString() + " offered " + "£" + String.format("%.2f", value);
    /*Reference:
     * URL:https://mkyong.com/java/java-display-double-in-2-decimal-points/#stringformat2f
     * Date: 14/12/2022
     */
  }
}
