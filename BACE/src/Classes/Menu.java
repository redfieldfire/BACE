package Classes;

import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Menu {

    @FXML private Button btnAgregar;

    @FXML private Button btnBuscar;

    @FXML private Button btnEditar;

    void changeScreen(String fxml){
        try{
            Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/"+fxml+".fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
        }//try
        catch (Exception ignored){
            System.out.println("Error en las pantallas");
        }//catch
    }//changeScreen

    @FXML void actionAgregar() {
        changeScreen("agregar");
    }//actionAgregar

    @FXML void actionBuscar() {
        changeScreen("buscar");
    }//actionBuscar

    @FXML void actionEditar() {
        changeScreen("editar");
    }//actionEditar

    @FXML void enteredAgregar() {

    }//enteredAgregar

    @FXML void enteredBuscar() {

    }//enteredBuscar

    @FXML void enteredEditar() {

    }//enteredEditar

    @FXML void exitedAgregar() {

    }//exitedAgregar

    @FXML void exitedBuscar() {

    }//exitedBuscar

    @FXML void exitedEditar() {

    }//exitedEditar

}//Menu
