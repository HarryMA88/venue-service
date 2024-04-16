package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service {

  private CateringType cateringType;

  public Catering(Booking booking, CateringType cateringType) {
    super(booking);
    this.cateringType = cateringType;
  }

  @Override
  public void setCost() {
    int attendees = booking.getAttendees();
    cost = cateringType.getCostPerPerson() * attendees;
  }

  public CateringType getCateringType() {
    return cateringType;
  }
}
