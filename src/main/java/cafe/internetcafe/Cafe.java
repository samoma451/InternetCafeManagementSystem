package cafe.internetcafe;

import java.util.ArrayList;

/**
 *
 * @author samom
 */
public class Cafe {
    //private ArrayList<Booking> bookings = new ArrayList<>();
    private static int numSeats;
    private static ArrayList<Seat> seats = new ArrayList<>();
    public static Booking newestBooking;
    
    public int getNoSeats() {
        return numSeats;
    }
    
    public static void setSeats(){
        numSeats=9;
        for(int i=0;i<numSeats;i++){
            seats.add(new Seat(i+1));
        }
    }

    public static Seat getSeatNum(int num) {
        return seats.get(num-1);
    }

    public static Booking getNewestBooking() {
        return newestBooking;
    }

    public static void setNewestBooking(Booking newestBooking) {
        System.out.println("done");
        Cafe.newestBooking = newestBooking;
    }
    
    
}
