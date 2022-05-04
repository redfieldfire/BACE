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

        buttonDocumento.setText(Data.nombreDocumento);

    }//intialize

    @FXML void actionDocumento(){
        try {
            Desktop.getDesktop().open(new File(Data.nombreDocumento));
        }//try
        catch (IOException ex) {
            ex.printStackTrace();
        }//catch
    }//actionDocumento

}//Documento
