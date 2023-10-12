package coursework_question3;

public class Seller extends User {
  
  public Seller(String fullname) {
    super(fullname);
  }
  
  @Override
  public String toString() {
    String output = "";
    String[] fullNameSplitted = super.toString().split("\\s");
    output += fullNameSplitted[0] + " "
        + fullNameSplitted[1].charAt(0) + ". ()";
    return output;
  }
}
