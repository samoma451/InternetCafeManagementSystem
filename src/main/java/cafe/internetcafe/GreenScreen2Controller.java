package cafe.internetcafe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class GreenScreen2Controller implements Initializable{

    @FXML
    TextArea message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message.setText(String.format("Booking confirmed!\nSeat %d booked from %d:00 to %d:00\nOn %s. ",
                Cafe.getNewestBooking().getSeatNo(), Cafe.getNewestBooking().getStartTime(),
               Cafe.getNewestBooking().getEndTime(), Cafe.getNewestBooking().getDate()));
    }
    
    @FXML
    private void switchToScreen1() throws IOException {
        App.setRoot("greenScreen0");
    }

    
}
