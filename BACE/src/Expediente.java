import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class Expediente {

    @FXML
    private Button buttonAlbum;

    @FXML
    private Button buttonAtras;

    @FXML
    private Button buttonDatosGenerales;

    @FXML
    private Button buttonDocumentos;

    @FXML
    private Button buttonNotas;

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

    @FXML
    private VBox vBoxInteligente;

    @FXML void initialize(){



    }

    @FXML
    void albumAction(ActionEvent event) {

        ImageView imageView = new ImageView();
        imageView.setImage(new Image("Images/boy.png"));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        ImageView imageView2 = new ImageView();
        imageView2.setImage(new Image("Images/boy2.png"));
        imageView2.setFitWidth(150);
        imageView2.setFitHeight(150);
        ImageView imageView3 = new ImageView();
        imageView3.setImage(new Image("Images/boy3.png"));
        imageView3.setFitWidth(150);
        imageView3.setFitHeight(150);

        vBoxInteligente.getChildren().clear();
        vBoxInteligente.getChildren().addAll(imageView,imageView2,imageView3);

    }

    @FXML
    void albumEntered(MouseEvent event) {

    }

    @FXML
    void albumExited(MouseEvent event) {

    }

    @FXML
    void atrasAction(ActionEvent event) {
        changeScreen("buscar");
    }

    @FXML
    void atrasEntered(MouseEvent event) {

    }

    @FXML
    void atrasExited(MouseEvent event) {

    }

    @FXML
    void datosGeneralesAction(ActionEvent event) {

        vBoxInteligente.getChildren().clear();
        try {
            vBoxInteligente.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Resources/datos.fxml"))));
        }//try
        catch (Exception ignored) {
            System.out.println("Error expediente.datosGeneralesAction.102");
        }//catch

    }

    @FXML
    void datosGeneralesExited(MouseEvent event) {

    }

    @FXML
    void documentosAction(ActionEvent event) {

        vBoxInteligente.getChildren().clear();
        try {
            vBoxInteligente.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Resources/seccionDocumentos.fxml"))));
        }//try
        catch (IOException ignored) {
            System.out.println("Error en el vboxInteligente");
        }//catch

    }

    @FXML
    void documentosEntered(MouseEvent event) {

    }

    @FXML
    void documentosExited(MouseEvent event) {

    }

    @FXML
    void notasAction(ActionEvent event) {

        vBoxInteligente.getChildren().clear();
        try {
            vBoxInteligente.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Resources/notas.fxml"))));
        }//try
        catch (Exception ignored) {
            System.out.println("Error Expediente.nA");
        }//catch

    }

    @FXML
    void notasEntered(MouseEvent event) {

    }

    @FXML
    void notasExited(MouseEvent event) {

    }

    void changeScreen(String fxml){
        try{
            Main.stage.setScene(
                    new Scene(
                            FXMLLoader.load(Objects.requireNonNull(
                                    getClass().getResource("Resources/" + fxml + ".fxml")))
                            ,Main.stage.getWidth(),Main.stage.getHeight()));
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
