package cafe.internetcafe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GreenScreenController0 implements Initializable{
    
    @FXML
    private void switchToScreen1() throws IOException {
        App.setRoot("greenScreen1");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
