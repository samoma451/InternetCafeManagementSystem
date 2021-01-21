package cafe.internetcafe;

import java.time.LocalDate;

/**
 *
 * @author samom
 */
public class Booking {
    
    private final int startTime;
    private final int endTime;
    private final LocalDate date;
    private final int seatNo;
    
    public Booking(int seatNo, int startTime, int endTime, LocalDate date){
        this.seatNo=seatNo;
        this.startTime=startTime;
        this.endTime=endTime;
        this.date=date;
    }

    public int getSeatNo() {
        return seatNo;
    }
    

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }
    
}
