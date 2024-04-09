package nz.ac.auckland.se281;

public class Venue {

  private String venueName;
  private String venueCode;
  private int venueCapacity;
  private int hireFee;
  private String bookingDate;
  private String customerEmail;
  private int attendees;

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

  public String getBookingDate() {
    return this.bookingDate;
  }

  public String getCustomerEmail() {
    return this.customerEmail;
  }

  public int getAttendees() {
    return this.attendees;
  }

  public void setBookingDate(String bookingDate) {
    this.bookingDate = bookingDate;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public void setAttendees(int attendees) {
    this.attendees = attendees;
  }
}
