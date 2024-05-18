module pt.ul.fc.di.css.javafxexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    //requires demo;
    //server.src.main.java.

    opens pt.ul.fc.di.css.javafxexample to javafx.fxml, javafx.web;
    opens pt.ul.fc.di.css.javafxexample.presentation.control to javafx.fxml;
    exports pt.ul.fc.di.css.javafxexample;
}
