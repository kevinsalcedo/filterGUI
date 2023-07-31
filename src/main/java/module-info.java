module com.kev.sal.filtergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kev.sal.filtergui to javafx.fxml;
    exports com.kev.sal.filtergui;
}