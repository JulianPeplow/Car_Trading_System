package coursework_question3;

public abstract class User {
  private String fullname;
  private String regExp  = "[A-Z][a-z]*\\s[A-Z][a-z]*";

  public User(String fullname) throws IllegalArgumentException {
    if (fullname.matches(regExp)) {
      this.fullname = fullname;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public String getName() {
    String[] splitString = fullname.split(" ");
    return splitString[0]; 
    /*Reference: 
     * URL: https://stackabuse.com/how-to-split-a-string-in-java/
     * Date: 14/12/2022
     */
  }
  
  @Override
  public String toString() {
    return this.fullname;
  }
}
