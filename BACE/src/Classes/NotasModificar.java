package Classes;

import Data.Data;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class NotasModificar {

    @FXML private Button buttonModificar;

    @FXML private TextArea textAreaNota;

    @FXML private TextField textFieldTituloNota;

    @FXML private VBox content;

    String id,titulo,nota;

    @FXML void initialize(){

        id = Data.idNotaModificar;
        titulo = Data.tituloNotaModificar;
        nota = Data.notaTextoModificar;

        textFieldTituloNota.setText(titulo);
        textAreaNota.setText(nota);

        textAreaNota.setOnKeyReleased(event -> {
            if(textAreaNota.getText().length() == 0) buttonModificar.setText("Eliminar");
            else buttonModificar.setText("Modificar");
        });

        textFieldTituloNota.setOnKeyReleased(event -> {
            if(textFieldTituloNota.getText().length() == 0) buttonModificar.setText("Eliminar");
            else buttonModificar.setText("Modificar");
        });

    }//initialize

    @FXML void actionModificar(ActionEvent event) {

        if(buttonModificar.getText().equals("Modificar")) modificar();
        else confirmacionDeBorrar();

    }//actionModificar

    void modificar(){

        Main.conexion.inmodel("UPDATE notas_medicas SET " +
                "TITULO_NOTA = '"+textFieldTituloNota.getText()+"', " +
                "NOTA = '"+textAreaNota.getText()+"' " +
                "WHERE ID_NOTA = '"+id+"';");
        mensaje("Modificado");

    }//modificar

    Alert alert;

    void confirmacionDeBorrar(){

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Mensaje de BACE");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();

        if(action.get() == ButtonType.OK){
            Main.conexion.inmodel("DELETE FROM notas_medicas WHERE ID_NOTA = '"+id+"';");
            mensaje("Se ha eliminado la nota");
            ((VBox)content.getParent()).getChildren().remove(content);
        }//if okay

    }//borrar

    void mensaje(String mensaje){

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje de BACE");
        alert.setContentText(mensaje);
        alert.show();

    }//mensaje

}//NotasModificar
