package Classes;

import Data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NotasModificar {

    @FXML private Button buttonModificar;

    @FXML private TextArea textAreaNota;

    @FXML private TextField textFieldTituloNota;

    String id,titulo,nota;

    @FXML void initialize(){

        id = Data.idNotaModificar;
        titulo = Data.tituloNotaModificar;
        nota = Data.notaTextoModificar;

        textFieldTituloNota.setText(titulo);
        textAreaNota.setText(nota);

    }//initialize

    @FXML void actionModificar(ActionEvent event) {



    }//actionModificar

}//NotasModificar
