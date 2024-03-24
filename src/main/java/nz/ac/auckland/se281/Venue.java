package nz.ac.auckland.se281;

public class Venue {

  private String venueName;
  private String venueCode;
  private int venueCapacity;
  private int hireFee;

  // constructor
  public Venue(String venueName, String venueCode, int venueCapacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.venueCapacity = venueCapacity;
    this.hireFee = hireFee;
  }

  public String getVenueCode() {
    return this.venueCode;
  }

  public String getVenueName() {
    return this.venueName;
  }

  public int getVenueCapacity() {
    return this.venueCapacity;
  }

  public int getVenueHireFee() {
    return this.hireFee;
  }
}
