module com.example.client_server_application {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.client_server_application to javafx.fxml;
    exports com.example.client_server_application;
}