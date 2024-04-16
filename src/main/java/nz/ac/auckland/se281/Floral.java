package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class Floral extends Service {

  private FloralType floralType;

  public Floral(Booking tempBooking, FloralType floralType) {
    super(tempBooking);
    this.floralType = floralType;
  }

  @Override
  public void setCost() {
    cost = floralType.getCost();
  }

  public FloralType getFloralType() {
    return floralType;
  }
}
