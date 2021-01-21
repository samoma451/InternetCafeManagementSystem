package cafe.internetcafe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController implements Initializable {

    @FXML
    Label label;//date label. Change name to something more recognisable
    @FXML
    Button enterButton;
    @FXML
    DatePicker datePicker;
    public LocalDate date;
    boolean isDatePicked;

    ObservableList<String> seatList = FXCollections.observableArrayList();
    @FXML
    ChoiceBox<String> seatPick;
    int seatNo;
    boolean isSeatPicked;

    ObservableList<String> startList = FXCollections.observableArrayList();
    @FXML
    ChoiceBox<String> startPick;
    int startTime;

    @FXML
    Label selectEnd;
    ObservableList<String> endList = FXCollections.observableArrayList();
    @FXML
    ChoiceBox<String> endPick;
    @FXML
    Button endPickButton;
    int endTime;

    String outputText;
    @FXML
    TextField output;

    String errorText;
    @FXML
    TextField errorBox;

    @FXML
    Button enter;

    @FXML
    Button startPickButton;
    @FXML
    Label startTimeLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        outputText = "";
        errorText = "";
        isDatePicked = false;
        isSeatPicked = false;
        date = null;
        seatNo = 0;
        startTime = 0;
        endTime = 0;

    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("greenScreen0");
    }

    @FXML
    private void seatPicked() {
        hideWhenReentering();

        isSeatPicked = false;
        if (seatPick.getValue() == null) {
            System.err.println("oops");
        } else {
            seatNo = Integer.parseInt(seatPick.getValue());
            isSeatPicked = true;

            if (isDatePicked) {
                startPick.setVisible(true);
                startPickButton.setVisible(true);
                startTimeLabel.setVisible(true);
                errorBox.setVisible(true);
                output.setVisible(true);
            }
        }
    }

    @FXML
    private void datePicked() {
        hideWhenReentering();

        isDatePicked = false;

        if (datePicker.getValue() == null) {
            System.err.println("oops");
        } else if (datePicker.getValue().compareTo(LocalDate.now()) < 0) {
            System.err.println("oops");
        } else {
            isDatePicked = true;
            date = datePicker.getValue();

            if (isSeatPicked) {
                startPick.setVisible(true);
                startPickButton.setVisible(true);
                startTimeLabel.setVisible(true);
                errorBox.setVisible(true);
                output.setVisible(true);
            }
        }
    }

    @FXML
    private void startPicked() {
        errorText = "";
        errorBox.setText(errorText);

        if (startPick.getValue() == null) {
            errorText = "Please input a start time. ";
            errorBox.setText(errorText);
            enter.setVisible(false);
        } else if (Integer.parseInt(String.valueOf(startPick.getValue())) < LocalTime.now().getHour()
                && LocalDate.now() == date) {
            errorText = "Sorry, it is too late to book at that time. ";
            errorBox.setText(errorText);
            enter.setVisible(false);
        } else {
            startTime = Integer.parseInt(String.valueOf(startPick.getValue()));
            if (!outputText.equals("")) {
                outputText = "";
            }
            outputText += "Starting time is " + startTime + ":00. ";
            output.setText(outputText);
            endPick.setVisible(true);
            endPickButton.setVisible(true);
            selectEnd.setVisible(true);

            if (endList.isEmpty()) {
                endList.removeAll(endList);
                endList.addAll("9", "10", "11", "12", "13", "14", "15", "16", "17",
                        "18", "19", "20", "21", "22");
                endPick.getItems().addAll(endList);
            }

            /* Fix this code so it only shows the valid times?
            endList.forEach(elem -> {                
                    for(int i=0;i<Integer.parseInt(elem);i++){
                        if(Integer.parseInt(elem)-1==startTime){
                            endList.remove(i);
                        }
                    }
            });*/
        }
    }

    @FXML
    private void endPicked() {
        errorText = "";
        errorBox.setText(errorText);

        if (endPick.getValue() == null) {
            errorText = "Please input an end time. ";
            errorBox.setText(errorText);
        } else if (startTime >= Integer.parseInt(String.valueOf(endPick.getValue()))) {
            errorText = "Please input an end time after the inputted start time. ";
            errorBox.setText(errorText);
            enter.setVisible(false);
        } else {
            if (!outputText.equals("Starting time is " + startTime + ":00. ")) {
                outputText = "Starting time is " + startTime + ":00. ";
            }
            endTime = Integer.parseInt(String.valueOf(endPick.getValue()));
            outputText += "Ending time is " + endTime + ":00. ";
            output.setText(outputText);

            enter.setVisible(true);
        }
        Cafe.setSeats();
        if(Cafe.getSeatNum(seatNo).getBookings().isEmpty()){
                Booking newBook = new Booking(seatNo, startTime, endTime, date);
                Cafe.getSeatNum(seatNo).newBooking(startTime, endTime, date);
                Cafe.setNewestBooking(newBook);
                return;
        }else{
            for (Booking bk : Cafe.getSeatNum(seatNo).getBookings()) {
            if (date.equals(bk.getDate())) {
                if (startTime > bk.getEndTime() || endTime < bk.getStartTime()) {
                    Booking newBook = new Booking(seatNo, startTime, endTime, date);
                    Cafe.getSeatNum(seatNo).newBooking(startTime, endTime, date);
                    Cafe.setNewestBooking(newBook);
                }
            }
        }
        }
    }

    @FXML
    private void enterPressed() throws IOException {
        App.setRoot("greenScreen2");
    }

    private void loadData() {
        seatList.removeAll(seatList);
        seatList.addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        seatPick.getItems().addAll(seatList);

        startList.removeAll(startList);//cafe open from 8am to 10pm(22)
        startList.addAll("8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21");
        startPick.getItems().addAll(startList);

    }

    //hides stuff not meant to be immediately accessed if the user reenters details
    private void hideWhenReentering() {
        startPick.setVisible(false);
        startPickButton.setVisible(false);
        startTimeLabel.setVisible(false);
        errorBox.setVisible(false);
        output.setVisible(false);
    }

}
