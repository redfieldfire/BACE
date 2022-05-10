package Classes;

import Data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DocumentoModificar {

    @FXML private Button buttonBorrar;

    @FXML private Button buttonDocumento;

    String id,nombre;
    File file;

    @FXML void initialize(){

        file = Data.file;
        id = Data.idDocumentoModificar;
        nombre = Data.nombreDocumento;

        buttonDocumento.setText(nombre);

    }//initialize

    @FXML void actionBorrar(ActionEvent event) {

    }

    @FXML void actionDocumento(ActionEvent event) {
        try {
            Desktop.getDesktop().open(file);
        }//try
        catch (IOException ex) {
            ex.printStackTrace();
        }//catch
    }//actionDocumento

}
