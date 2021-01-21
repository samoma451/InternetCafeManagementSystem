package cafe.internetcafe;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author samom
 */
public class Seat {
    
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int seatNo;    
    
    public Seat(int seatNo){
        
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
    
    public void newBooking(int startTime, int endTime, LocalDate date){
        Booking bk = new Booking(seatNo, startTime, endTime, date);
        bookings.add(bk);
    }
    
}
