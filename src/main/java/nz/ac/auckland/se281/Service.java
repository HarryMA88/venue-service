package nz.ac.auckland.se281;

public abstract class Service {

  protected Booking booking;
  protected int cost;

  public Service(Booking booking) {
    this.booking = booking;
  }

  public abstract void setCost();

  public int getCost() {
    return cost;
  }



}
