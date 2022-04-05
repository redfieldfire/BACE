package Classes;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class Expediente {

    @FXML
    private Button buttonDatosGenerales;

    @FXML
    private Button buttonDocumentos;

    @FXML
    private ImageView imagenFoto;

    @FXML
    private Text textApellidoMaterno;

    @FXML
    private Text textApellidoPaterno;

    @FXML
    private Text textId;

    @FXML
    private Text textNombre;

    @FXML private VBox vBoxInteligente;

    public void initialize(){

        try {
            vBoxInteligente.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Resources/seccionDocumentos.fxml"))));
        }//try
        catch (IOException e) {
            e.printStackTrace();
        }//catch

    }//initialize

    @FXML
    void datosGeneralesAction(ActionEvent event) {

    }

    @FXML
    void datosGeneralesExited(MouseEvent event) {

    }

    @FXML
    void documentosAction(ActionEvent event) {

    }

    @FXML
    void documentosEntered(MouseEvent event) {

    }

    @FXML
    void documentosExited(MouseEvent event) {

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
        changeScreen("buscar");
    }//atrasAction

    @FXML void atrasExited(){

    }//atrasExited

    @FXML void atrasEntered(){

    }

}
