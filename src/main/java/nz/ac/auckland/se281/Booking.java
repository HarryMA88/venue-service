package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String bookingDate;
  private String customerEmail;
  private int attendees;
  private String bookingReference;
  private String dateOfBooking;
  private Venue venue;
  private ArrayList<Service> services = new ArrayList<Service>();

  public Booking(
      String bookingDate,
      String customerEmail,
      int attendees,
      String bookingReference,
      String dateOfBooking,
      Venue venue) {
    this.bookingDate = bookingDate;
    this.customerEmail = customerEmail;
    this.attendees = attendees;
    this.bookingReference = bookingReference;
    this.dateOfBooking = dateOfBooking;
    this.venue = venue;
  }

  public int getAttendees() {
    return attendees;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public String getBookingReference() {
    return bookingReference;
  }

  public String getDateOfBooking() {
    return dateOfBooking;
  }

  public Venue getVenue() {
    return venue;
  }

  public void addService(Service service) {
    services.add(service);
  }

  public ArrayList<Service> getServices() {
    return services;
  }
}
