module hu.vtg {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens hu.vtg to javafx.fxml;
    exports hu.vtg;
}
