package Classes;

import Data.Data;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class DocumentoModificar {

    @FXML private HBox content;

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

    Alert alert;

    @FXML void actionBorrar(ActionEvent event) {

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Mensaje de BACE");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();

        if(action.get() == ButtonType.OK){
            Main.conexion.inmodel("DELETE FROM documentos WHERE ID_DOCUMENTO = '"+id+"';");
            ((FlowPane)content.getParent()).getChildren().remove(content);
            mensaje("Se ha eliminado el documento");
        }//if okay

    }

    void mensaje(String mensaje){

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje de BACE");
        alert.setContentText(mensaje);
        alert.show();

    }//mensaje

    @FXML void actionDocumento(ActionEvent event) {
        try {
            Desktop.getDesktop().open(file);
        }//try
        catch (IOException ex) {
            ex.printStackTrace();
        }//catch
    }//actionDocumento

}
