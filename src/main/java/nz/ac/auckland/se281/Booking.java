package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String bookingDate;
  private String customerEmail;
  private int attendees;
  private String bookingReference;
  private ArrayList<Service> services = new ArrayList<Service>();

  public Booking(String bookingDate, String customerEmail, int attendees, String bookingReference) {
    this.bookingDate = bookingDate;
    this.customerEmail = customerEmail;
    this.attendees = attendees;
    this.bookingReference = bookingReference;
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

  public void addService(Service service) {
    services.add(service);
  }
}
