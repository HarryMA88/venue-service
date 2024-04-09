package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private String systemDate;

  public VenueHireSystem() {}

  public void printVenues() {
    // checks if there are no existing venues
    if (venues.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    } else if (venues.size() == 1) { // prints venues for if there is one venue
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      Venue venue = venues.get(0);
      // REMEMBER TO ADD ANOTHER INPUT ONCE DOING TASK 2
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getVenueName(),
          venue.getVenueCode(),
          String.valueOf(venue.getVenueCapacity()),
          String.valueOf(venue.getVenueHireFee()));
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
    for (Venue venue : venues) {
      // REMEMBER TO ADD ANOTHER INPUT ONCE DOING TASK 2
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getVenueName(),
          venue.getVenueCode(),
          String.valueOf(venue.getVenueCapacity()),
          String.valueOf(venue.getVenueHireFee()));
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
    }
    else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {
    // reject if date is not set
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }
    // reject if there are no venues
    else if (venues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }
    // reject if venue code does not exist
    else {
      boolean venueFound = false;
      for (Venue venue : venues) {
        if (venue.getVenueCode().equals(options[0])) {
          venueFound = true;
          break;
        }
      }
      if (!venueFound) {
        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }
    }
    // reject if date is in the past
    String[] dateInputParts = options[1].split("/");
    String[] systemDateParts = systemDate.split("/");
    for (int i = 2; i >= 0; i--) {
      if (Integer.parseInt(dateInputParts[i]) < Integer.parseInt(systemDateParts[i])) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
        return;
      }
    }

  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
