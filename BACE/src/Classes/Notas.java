package Classes;

import Data.Data;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Notas {

    @FXML private Text textNombreNota;

    @FXML private Text textNota;

    @FXML void initialize(){

        textNombreNota.setText(Data.tituloNota);
        textNota.setText(Data.nota);

    }//initialize

}//Notas
