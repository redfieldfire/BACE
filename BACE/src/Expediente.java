import Data.Data;
import Main.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.Objects;

public class Expediente {

    @FXML private Button buttonAlbum;

    @FXML private Button buttonAtras;

    @FXML private Button buttonDatosGenerales;

    @FXML private Button buttonDocumentos;

    @FXML private Button buttonNotas;

    @FXML private ImageView imagenFoto;

    @FXML private Text textApellidoMaterno;

    @FXML private Text textApellidoPaterno;

    @FXML private Text textId;

    @FXML private Text textNombre;

    @FXML private VBox vBoxInteligente;

    ResultSet resultSet;

    @FXML void initialize(){

        vBoxInteligente.setPadding(new Insets(50,20,50,20));

        textNombre.setText(Data.nombreNinoD);
        textId.setText("ID: " + Data.idNinoD);
        textApellidoPaterno.setText("Apellido P: " + Data.apellidoPD);
        textApellidoMaterno.setText("Apellido M: " + Data.apellidoMD);
        imagenFoto.setImage(Data.imagenNinoD);

    }

    @FXML
    void albumAction(ActionEvent event) {

        vBoxInteligente.getChildren().clear();

        resultSet = Main.conexion.consultar("SELECT IMAGEN FROM imagenes WHERE ID_NIÑO = '" + Data.idNinoD + "';");

        try {

            while (resultSet.next()){

                //-----------------Proceso crear una imagen con blob

                Blob blob = resultSet.getBlob(1);
                byte[] data = blob.getBytes(1,(int)blob.length());
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(data));

                //----------------------------------------Convertirlo a imagen FX

                ImageView imageView = new ImageView();
                imageView.setFitHeight(700);
                imageView.setFitWidth(700);
                imageView.setPreserveRatio(true);
                imageView.setImage(SwingFXUtils.toFXImage(image,null));

                //-----------------------------------------------------------------

                vBoxInteligente.getChildren().add(imageView);

            }//while

        }//try
        catch (Exception e){

        }//catch

    }//albumAction

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

            resultSet = Main.conexion.consultar("SELECT TITULO_NOTA,NOTA FROM notas_medicas WHERE ID_NIÑO = '" + Data.idNinoD + "' ORDER BY 1;");

            while (resultSet.next()){
                Data.tituloNota = "" + resultSet.getObject("TITULO_NOTA");
                Data.nota = "" + resultSet.getObject("NOTA");
                vBoxInteligente.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Resources/notas.fxml"))));
            }//while

        }//try
        catch (Exception ignored) {
            System.out.println("Error Expediente.146");
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
