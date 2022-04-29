package Classes;

import Data.Data;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Agregar2 {

    @FXML
    private Button buttonAtras;

    @FXML
    private Button buttonFinalizar;

    @FXML void initialize(){

        if(Data.action.equals("editar")){
            buttonFinalizar.setText("Siguiente");
        }

    }//initialize

    @FXML
    void finalizarAction(ActionEvent event) {
        if(Data.action.equals("agregar")) changeScreen("menu");
        else if(Data.action.equals("editar")) changeScreen("modificarTodo");
    }

    @FXML
    void finalizarEntered(MouseEvent event) {

    }

    @FXML
    void finalizarExited(MouseEvent event) {

    }
    void changeScreen(String fxml){
        try{
            Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/"+fxml+".fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
        }//try
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error en las pantallas");
        }//catch
    }//changeScreen

    @FXML void atrasAction(){
        changeScreen("agregar");
    }//atrasAction

    @FXML void atrasExited(){

    }//atrasExited

    @FXML void atrasEntered(){

    }//atrasEntered


}
