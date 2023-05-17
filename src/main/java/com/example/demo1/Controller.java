package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Spinner<Integer> EndPage;

    @FXML
    private Label ResultLabel;

    @FXML
    private Button closeButton;
    @FXML
    private Button CompressButton;

    @FXML
    private Button srcText;

    @FXML
    private Spinner<Integer> startPage;


    File oldFile;
    @FXML
void OnClickSelectFile()
{
    FileChooser fileChooser = new FileChooser();
    File selectedFile = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
    if (selectedFile != null) {
        oldFile = new File(selectedFile.getAbsolutePath());
        System.out.println(oldFile);
    }
}
    @FXML
    void OnClickCompress(ActionEvent event) throws IOException {
        if(event.getSource()==CompressButton)
        {
            PDDocument document = PDDocument.load(oldFile);
            File newFileDestination = new File("C:\\Users\\Logesh.M\\Downloads");
            newFileDestination.mkdirs();
            Splitter splitter = new Splitter();
            splitter.setStartPage(startPage.getValue());
            splitter.setEndPage(EndPage.getValue());
            List<PDDocument> splitPages = splitter.split(document);
            PDDocument newDoc = new PDDocument();

            for (PDDocument mydoc : splitPages) {

                newDoc.addPage(mydoc.getPage(0));
            }
            newDoc.save(newFileDestination +"/" +oldFile.getName()+"-(1).pdf");
            newDoc.close();
            ResultLabel.setText("File is saved");
        } else if (event.getSource()==closeButton) {
            System.exit(0);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        startPage.setValueFactory(valueFactory);
        EndPage.setValueFactory(valueFactory1);
    }
}
