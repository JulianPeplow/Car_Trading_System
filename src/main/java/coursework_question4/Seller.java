package coursework_question4;

public class Seller extends User {
  private int sales;
  
  public Seller(String fullname) {
    super(fullname);
    this.sales = 0;
  }
  
  public int getSales() {
    return sales;
  }

  public void setSales(int sales) throws IllegalArgumentException {
    if (sales < 0) {
      throw new IllegalArgumentException();
    } else {      
      this.sales = sales;
    }
  }
  
  public String identifyRating() {
    if (sales == 0) {
      return "Level 0";
    } else if (sales > 0 && sales <= 5) {
      return "Level 1";
    } else if (sales >= 6 && sales <= 10) {
      return "Level 2";
    } else if (sales > 10) {
      return "Level 3";
    }
    return null;
  }

  @Override
  public String toString() {
    String output = "";
    String[] fullNameSplitted = super.toString().split("\\s");
    output += fullNameSplitted[0] + " "
        + fullNameSplitted[1].charAt(0) + ". (Sales: " + sales + ", Rating: "
        + identifyRating() + ")";
    return output;
  }
}
