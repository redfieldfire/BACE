package Classes;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Agregar {

    @FXML
    private Button buttonSiguiente;

    @FXML
    void siguienteAction(ActionEvent event) {
        changeScreen("agregar2");
    }

    @FXML
    void siguienteEntered(MouseEvent event) {

    }

    @FXML
    void siguienteExited(MouseEvent event) {

    }
    void changeScreen(String fxml){
        try{
            Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/"+fxml+".fxml"))));
        }//try
        catch (Exception ignored){
            System.out.println("Error en las pantallas");
        }//catch
    }//changeScreen

    @FXML void atrasAction(){
        changeScreen("menu");
    }//atrasAction

    @FXML void atrasExited(){

    }//atrasExited

    @FXML void atrasEntered(){

    }//atrasEntered

}