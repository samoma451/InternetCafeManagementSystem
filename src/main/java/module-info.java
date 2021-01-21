module cafe.internetcafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cafe.internetcafe to javafx.fxml;
    exports cafe.internetcafe;
}
