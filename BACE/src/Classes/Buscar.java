package Classes;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Buscar {

    @FXML private Button buttonBuscar;

    @FXML private TextField textFieldNombreId;

    @FXML private VBox vBoxResultados;

    @FXML void buscarAction(ActionEvent event) {
        try {
            vBoxResultados.getChildren().add(FXMLLoader.load(getClass().getResource("../Resources/formatoResultado.fxml")));
        }//try
        catch (Exception ignored) {
            System.out.println("Error en las pantallas");
        }//catch
    }

    @FXML void buscarEntered(MouseEvent event) {

    }

    @FXML void buscarExited(MouseEvent event) {

    }

    @FXML void textFieldNombreIdAction(ActionEvent event) {

    }

    void changeScreen(String fxml){
        try{
            Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/"+fxml+".fxml")),Main.stage.getWidth(),Main.stage.getHeight()));        }//try
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
