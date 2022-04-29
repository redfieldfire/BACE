package Classes;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ModificarTodo {

    @FXML private Button buttonFinalizar;

    @FXML private VBox vBox;

    @FXML void initialize(){

        try {
            for(int x = 0; x < 5; x++)
            vBox.getChildren().add(FXMLLoader.load(getClass().getResource("../Resources/notasModificar.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for(int x = 0; x < 5; x++)
                vBox.getChildren().add(FXMLLoader.load(getClass().getResource("../Resources/seccionDocumentos.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML void actionFinalizar(ActionEvent event) {
        changeScreen("menu");
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

}
