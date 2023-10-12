package coursework_question4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Trader extends Dealership {
  private List<Seller> sellers;

  public Trader(String name) {
    super(name);
    this.sellers = new ArrayList<Seller>();
  }

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

  @Override
  public String displaySoldCars() {
    String output = "";
    output += "SOLD CARS:\n";
    for (Advert advert : this.soldCars.keySet()) {
      output += advert.getCar().getID() + " - " + "Purchased by ";
      output += this.soldCars.get(advert).toString() + " with a successful £";
      output += String.format("%.2f", advert.getHighestOffer().getValue()) + " bid.\n";
    }
    return output;
  }

  @Override
  public String displayStatistics() {
    String output = "";
    String line = "";

    BufferedReader reader = null;
    output += "** Trader - " + this.name + "**\n";
    try {
      FileReader in = new FileReader("trade_statistics.txt");
      reader = new BufferedReader(in);
      line = reader.readLine();
      while (line != null) {
        output += line;
        line = reader.readLine();
        if (line != null) {
          output += "\n";
        }
      }
      reader.close();
      System.out.println("Read the file successfully!");
    } catch (FileNotFoundException fnfe) {
      System.out.println("Cannot find the file");
      fnfe.printStackTrace(); 
    } catch (IOException e) {
      System.out.println("Cannot read the file");
      e.printStackTrace(); 
    }
    return output;
  }

  @Override
  public String displayUnsoldCars() {
    String output = "";
    output += "UNSOLD CARS:\n";
    for (Advert advert : this.unsoldCars.keySet()) {
      output += advert.toString();
    }
    return output;
  }

  private void endSale(Advert advert) throws IllegalArgumentException {
    if (advert == null) {
      throw new IllegalArgumentException();
    } else {
      int sellerSales = this.carsForSale.get(advert).getSales();
      sellerSales++;
      boolean nameAlreadyInList = false;
      if (this.sellers.size() > 0) {
        for (Seller seller : sellers) {
          if (carsForSale.get(advert).getName().equals(seller.getName())) {        
            nameAlreadyInList = true;
          }
        }
      } else {
        sellers.add(this.carsForSale.get(advert));
        nameAlreadyInList = true;
      }

      if (!nameAlreadyInList) {
        sellers.add(this.carsForSale.get(advert));
      }

      this.carsForSale.get(advert).setSales(sellerSales);
      this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
      this.carsForSale.remove(advert);
      updateStatistics(this.carsForSale.get(advert));
    }
  }

  @Override
  public boolean placeOffer(Advert carAdvert, User user, double value) 
      throws IllegalArgumentException {
    if (carAdvert == null || user == null || value < 0) {
      throw new IllegalArgumentException();
    } else if (checkExistence(carAdvert.getCar())) {
      boolean offerPlaced = carAdvert.placeOffer(user, value);

      if (offerPlaced  && carAdvert.getHighestOffer().getValue() >= carAdvert.getCar().getPrice()) {
        endSale(carAdvert); 
      } else if (offerPlaced && carAdvert.getHighestOffer().getValue()
          < carAdvert.getCar().getPrice()) {
        this.unsoldCars.put(carAdvert, this.carsForSale.get(carAdvert));
        return false;
      }
      return true;
    }
    return false;
  }

  @Override
  public void registerCar(Advert carAdvert, User user, String colour, CarType type, 
      CarBody body, int noOfSeats) throws IllegalArgumentException {
    if (carAdvert == null || user == null || colour == null || type == null 
        || body == null || noOfSeats <= 0) {
      throw new IllegalArgumentException();
    } else if (!checkExistence(carAdvert.getCar())
        && carAdvert.getCar().getType() == SaleType.FORSALE) {
      carAdvert.getCar().setColour(colour);
      carAdvert.getCar().setGearbox(type);
      carAdvert.getCar().setBody(body);
      carAdvert.getCar().setNumberOfSeats(noOfSeats);
      this.carsForSale.put(carAdvert, (Seller) user);
    }
  }

  private void saveInFile(int noOfSales) throws IllegalArgumentException {
    if (noOfSales < 0) {
      throw new IllegalArgumentException();
    } else {
      try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("trade_statistics.txt"));
        writer.write("Total Sales: " + noOfSales + "\n");
        writer.write("All Sellers:\n");
        for (Seller seller : sellers) {
          writer.write("\t" + seller.toString() + "\n");
        }      
        writer.close();
        System.out.println("Written into the file successfully!");
      } catch (IOException e) {
        System.out.println("An IOExeption has occured!");
        e.printStackTrace();
      }
    }
  }

  private List<Seller> sortSellers(List<Seller> sellers) {
    Seller tempSeller = new Seller("Temporary Seller");
    boolean swap = false;

    do {
      swap = false;
      for (int i = 0; i < sellers.size() - 1; i++) {
        if (sellers.get(i).getName().compareTo(sellers.get(i + 1).getName()) > 0) {
          swap = true;
          tempSeller = sellers.get(i);
          sellers.set(i + 1, sellers.get(i));
          sellers.set(i, tempSeller);
        }
      }
    } while (swap == true);

    return sellers;
  }

  private void updateStatistics(Seller seller) {
    int noOfSales = this.soldCars.size();
    sellers = sortSellers(sellers);
    saveInFile(noOfSales);
  }
}
