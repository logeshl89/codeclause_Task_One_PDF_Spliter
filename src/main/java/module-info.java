module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}