package Classes;

import Data.Data;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Documento {

    @FXML private Button buttonDocumento;

    File file;
    @FXML void initialize(){

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
