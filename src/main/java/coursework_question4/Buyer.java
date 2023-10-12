package coursework_question4;

public class Buyer extends User {
  private int age;
  
  public Buyer(String fullname, int age) throws IllegalArgumentException {
    super(fullname);
    if (age < 18) {
      throw new IllegalArgumentException();
    }
    this.age = age;
  }
  
  public int getAge() {
    return this.age;
  }
  
  @Override
  public String toString() {
    String output = "";
    String[] fullNameSplitted = super.getName().split(" ");
    String firstName = fullNameSplitted[0];
    output += firstName.charAt(0) + "***"
        + firstName.charAt(firstName.length() - 1);
    return output;
  }
}
