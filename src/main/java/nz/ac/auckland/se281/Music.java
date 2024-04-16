package nz.ac.auckland.se281;

public class Music extends Service {

  public Music(Booking booking) {
    super(booking);
  }

  @Override
  public void setCost() {
    cost = 500;
  }

}
