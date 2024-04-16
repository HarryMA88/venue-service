package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private String systemDate;
  private ArrayList<Booking> bookings = new ArrayList<Booking>();

  public VenueHireSystem() {}

  public void printVenues() {
    // checks if there are no existing venues
    if (venues.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    } else if (venues.size() == 1) { // prints venues for if there is one venue
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      printVenuesHelper();
      return;
    } else if (venues.size() < 10) { // prints venues for if there is less than 10 venues
      switch (venues.size()) {
        case 2:
          MessageCli.NUMBER_VENUES.printMessage("are", "two", "s");
          printVenuesHelper();
          break;
        case 3:
          MessageCli.NUMBER_VENUES.printMessage("are", "three", "s");
          printVenuesHelper();
          break;
        case 4:
          MessageCli.NUMBER_VENUES.printMessage("are", "four", "s");
          printVenuesHelper();
          break;
        case 5:
          MessageCli.NUMBER_VENUES.printMessage("are", "five", "s");
          printVenuesHelper();
          break;
        case 6:
          MessageCli.NUMBER_VENUES.printMessage("are", "six", "s");
          printVenuesHelper();
          break;
        case 7:
          MessageCli.NUMBER_VENUES.printMessage("are", "seven", "s");
          printVenuesHelper();
          break;
        case 8:
          MessageCli.NUMBER_VENUES.printMessage("are", "eight", "s");
          printVenuesHelper();
          break;
        case 9:
          MessageCli.NUMBER_VENUES.printMessage("are", "nine", "s");
          printVenuesHelper();
          break;
      }
      return;
    } else { // prints venues if there are 10 or more venues
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venues.size()), "s");
      printVenuesHelper();
    }
  }

  public void printVenuesHelper() {
    // prints all the venues in the venues arraylist
    if (systemDate == null) {
      for (Venue venue : venues) {
        // REMEMBER TO ADD ANOTHER INPUT ONCE DOING TASK 2
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            String.valueOf(venue.getVenueCapacity()),
            String.valueOf(venue.getVenueHireFee()));
      }
    } else {
      for (Venue venue : venues) {
        ArrayList<Booking> venueBookings = venue.getBookings();
        String available = systemDate;
        if (!venueBookings.isEmpty()) {
          for (int i = 0; i < venueBookings.size(); i++) {
            Booking booking = venueBookings.get(i);
            if (booking.getBookingDate().equals(available)) {
              String[] dateParts = booking.getBookingDate().split("/");
              int day = (Integer.parseInt(dateParts[0]) + 1);
              if (day < 10) {
                dateParts[0] = "0" + day;
              } else {
                dateParts[0] = String.valueOf(day);
              }
              available = dateParts[0] + "/" + dateParts[1] + "/" + dateParts[2];
              i = -1;
            }
          }
        }
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            String.valueOf(venue.getVenueCapacity()),
            String.valueOf(venue.getVenueHireFee()),
            available);
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // checks if venue name is valid
    if (venueName.isBlank()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // checks if capacity input is valid
    try {
      Integer.parseInt(capacityInput);
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }
    int capacity = Integer.parseInt(capacityInput);
    if (capacity <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    }

    // checks if hire fee input is valid
    try {
      Integer.parseInt(hireFeeInput);
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }
    int hireFee = Integer.parseInt(hireFeeInput);
    if (hireFee <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      return;
    }

    // checks if venueCode is valid
    for (Venue venue : venues) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenueName());
        return;
      }
    }

    // creates new venue
    venueName = venueName.trim();
    Venue newVenue = new Venue(venueName, venueCode, capacity, hireFee);
    venues.add(newVenue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    if (systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {
    Venue tempVenue = null;
    // reject if date is not set
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (venues.isEmpty()) { // reject if there are no venues
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    } else { // reject if venue code does not exist
      boolean venueFound = false;
      for (Venue venue : venues) {
        if (venue.getVenueCode().equals(options[0])) {
          venueFound = true;
          tempVenue = venue;
          break;
        }
      }
      if (!venueFound) {
        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }
    }
    String[] dateInputParts = options[1].split("/");
    // reject if venue is booked on that date
    ArrayList<Booking> venueBookings = tempVenue.getBookings();
    for (Booking booking : venueBookings) {
      String[] bookingDateParts = booking.getBookingDate().split("/");
      if (dateInputParts[0].equals(bookingDateParts[0])
          && dateInputParts[1].equals(bookingDateParts[1])
          && dateInputParts[2].equals(bookingDateParts[2])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            tempVenue.getVenueName(), booking.getBookingDate());
        return;
      }
    }
    // reject if date is in the past
    String[] systemDateParts = systemDate.split("/");
    for (int i = 2; i >= 0; i--) {
      if (Integer.parseInt(dateInputParts[i]) < Integer.parseInt(systemDateParts[i])) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
        return;
      } else if (Integer.parseInt(dateInputParts[i]) > Integer.parseInt(systemDateParts[i])) {
        break;
      }
    }
    // adjust attendees if under 25% of venue capacity or over venue capacity
    if (Integer.parseInt(options[3]) < tempVenue.getVenueCapacity() * 0.25) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3],
          String.valueOf((int) (tempVenue.getVenueCapacity() * 0.25)),
          String.valueOf(tempVenue.getVenueCapacity()));
      options[3] = String.valueOf((int) (tempVenue.getVenueCapacity() * 0.25));
    } else if (Integer.parseInt(options[3]) > tempVenue.getVenueCapacity()) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3],
          String.valueOf(tempVenue.getVenueCapacity()),
          String.valueOf(tempVenue.getVenueCapacity()));
      options[3] = String.valueOf(tempVenue.getVenueCapacity());
    }
    // create booking
    String bookingReference = BookingReferenceGenerator.generateBookingReference();
    Booking newBooking =
        new Booking(
            options[1],
            options[2],
            Integer.parseInt(options[3]),
            bookingReference,
            systemDate,
            tempVenue);
    bookings.add(newBooking);
    tempVenue.addBooking(newBooking);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, tempVenue.getVenueName(), options[1], options[3]);
  }

  public void printBookings(String venueCode) {
    // reject if there are no venues
    if (venues.isEmpty()) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    } else {
      Venue tempVenue = null;
      boolean venueFound = false;
      for (Venue venue : venues) {
        if (venue.getVenueCode().equals(venueCode)) {
          venueFound = true;
          tempVenue = venue;
          break;
        }
      }
      if (!venueFound) { // reject if venue code does not exist
        MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
        return;
      } else { // print bookings
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(tempVenue.getVenueName());
        ArrayList<Booking> venueBookings = tempVenue.getBookings();
        if (venueBookings.isEmpty()) {
          MessageCli.PRINT_BOOKINGS_NONE.printMessage(tempVenue.getVenueName());
        } else {
          for (Booking booking : venueBookings) {
            MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
                booking.getBookingReference(), booking.getBookingDate());
          }
        }
      }
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    boolean bookingReferenceFound = false;
    Booking tempBooking = null;
    // find if booking reference exists
    for (Booking booking : bookings) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceFound = true;
        tempBooking = booking;
        break;
      }
    }
    // reject if booking reference not found
    if (!bookingReferenceFound) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    } else {
      Service service = new Catering(tempBooking, cateringType);
      service.setCost();
      tempBooking.addService(service);
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Catering (" + cateringType.getName() + ")", bookingReference);
    }
  }

  public void addServiceMusic(String bookingReference) {
    boolean bookingReferenceFound = false;
    Booking tempBooking = null;
    // find if booking reference exists
    for (Booking booking : bookings) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceFound = true;
        tempBooking = booking;
        break;
      }
    }
    // reject if booking reference not found
    if (!bookingReferenceFound) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    } else {
      Service service = new Music(tempBooking);
      service.setCost();
      tempBooking.addService(service);
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    boolean bookingReferenceFound = false;
    Booking tempBooking = null;
    // find if booking reference exists
    for (Booking booking : bookings) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceFound = true;
        tempBooking = booking;
        break;
      }
    }
    // reject if booking reference not found
    if (!bookingReferenceFound) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    } else {
      Service service = new Floral(tempBooking, floralType);
      service.setCost();
      tempBooking.addService(service);
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Floral (" + floralType.getName() + ")", bookingReference);
    }
  }

  public void viewInvoice(String bookingReference) {
    // finds the booking
    Booking tempBooking = null;
    for (Booking booking : bookings) {
      if (booking.getBookingReference().equals(bookingReference)) {
        tempBooking = booking;
        break;
      }
    }
    // reject if booking reference not found
    if (tempBooking == null) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }
    // prints top half
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        tempBooking.getCustomerEmail(),
        tempBooking.getDateOfBooking(),
        tempBooking.getBookingDate(),
        String.valueOf(tempBooking.getAttendees()),
        tempBooking.getVenue().getVenueName());
    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(
        String.valueOf(tempBooking.getVenue().getVenueHireFee()));
    // prints services
    int total = tempBooking.getVenue().getVenueHireFee();
    ArrayList<Service> services = tempBooking.getServices();
    for (Service service : services) {
      if (service instanceof Catering) {
        Catering catering = (Catering) service;
        MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
            catering.getCateringType().getName(), String.valueOf(catering.getCost()));
        total += catering.getCost();
      } else if (service instanceof Music) {
        Music music = (Music) service;
        MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(String.valueOf(music.getCost()));
        total += music.getCost();
      } else if (service instanceof Floral) {
        Floral floral = (Floral) service;
        MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
            floral.getFloralType().getName(), String.valueOf(floral.getCost()));
        total += floral.getCost();
      }
    }
    // prints bottom half
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(String.valueOf(total));
  }
}
