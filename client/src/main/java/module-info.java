module pt.ul.fc.di.css.javafxexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;
    //server.src.main.java.
    //requires pt.ul.fc.css.example.demo;

    opens pt.ul.fc.di.css.javafxexample to javafx.fxml, javafx.web;
    opens pt.ul.fc.di.css.javafxexample.presentation.control to javafx.fxml;
    exports pt.ul.fc.di.css.javafxexample;
}
