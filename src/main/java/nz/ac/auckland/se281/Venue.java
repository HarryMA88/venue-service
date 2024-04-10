package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  private String venueName;
  private String venueCode;
  private int venueCapacity;
  private int hireFee;
  private ArrayList<String> bookingDates = new ArrayList<String>();
  private ArrayList<String> customerEmails = new ArrayList<String>();
  private ArrayList<Integer> attendees = new ArrayList<Integer>();

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

  public ArrayList<String> getBookingDates() {
    return this.bookingDates;
  }

  public ArrayList<String> getCustomerEmails() {
    return this.customerEmails;
  }

  public ArrayList<Integer> getAttendees() {
    return this.attendees;
  }

  public void setBookingDate(String bookingDate) {
    this.bookingDates.add(bookingDate);
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmails.add(customerEmail);
  }

  public void setAttendees(int attendees) {
    this.attendees.add(attendees);
  }
}
