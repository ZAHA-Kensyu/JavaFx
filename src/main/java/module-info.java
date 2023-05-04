module com.example.stonepickinggame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.stonepickinggame to javafx.fxml;
    exports com.example.stonepickinggame;
}