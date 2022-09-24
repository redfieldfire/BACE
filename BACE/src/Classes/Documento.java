package Classes;

import Data.Data;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Documento {

    @FXML private ImageView imagenPDF;

    @FXML private Button buttonDocumento;

    File file;
    @FXML void initialize(){

        try {
            imagenPDF.setImage(new javafx.scene.image.Image(new FileInputStream("C:BACE\\Images\\pdf.png")));
        }//try
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }//catch

        file = Data.file;
        buttonDocumento.setText(Data.nombreDocumento);

    }//intialize

    @FXML void actionDocumento(){
        try {
            Desktop.getDesktop().open(file);
        }//try
        catch (IOException ex) {
            ex.printStackTrace();
        }//catch
    }//actionDocumento

}//Documento
