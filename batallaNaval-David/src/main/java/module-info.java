module org.example.batallanaval1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.batallanaval1 to javafx.fxml;
    exports org.example.batallanaval1;
    opens org.example.batallanaval1.controller;
    exports org.example.batallanaval1.controller;
}