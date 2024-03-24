package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venues = new ArrayList<Venue>();

  public VenueHireSystem() {}

  public void printVenues() {
    // checks if there are no existing venues
    if (venues.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    } else if (venues.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      Venue venue = venues.get(0);
      MessageCli.VENUE_ENTRY.printMessage(venue.getVenueName(), venue.getVenueCode(), String.valueOf(venue.getVenueCapacity()), String.valueOf(venue.getVenueHireFee()));
      return;
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
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
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
