module pt.ul.fc.di.css.javafxexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires com.google.gson;
    requires javafx.graphics;

    opens pt.ul.fc.di.css.javafxexample to javafx.fxml, javafx.web;
    opens pt.ul.fc.di.css.javafxexample.presentation.control to javafx.fxml;
    exports pt.ul.fc.di.css.javafxexample;
}
