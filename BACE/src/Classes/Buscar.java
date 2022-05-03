package Classes;

import Data.Data;
import Main.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.ResultSet;

public class Buscar {

    @FXML private Button buttonBuscar;

    @FXML private TextField textFieldNombre;

    @FXML private VBox vBoxResultados;

    ResultSet resultSet;
    Alert alert;

    @FXML void initialize(){

        textFieldNombre.setOnKeyReleased(event -> {
           if(event.getCode() == KeyCode.ENTER) buscar();
        });

    }//initialize

    @FXML void buscarAction(ActionEvent event) {
        buscar();
    }

    void buscar(){

        vBoxResultados.getChildren().clear();

        try {

            resultSet = Main.conexion.consultar("SELECT * FROM niños WHERE " +
                    "NOMBRE LIKE '%" + textFieldNombre.getText() + "' OR " +
                    "NOMBRE LIKE '" + textFieldNombre.getText() + "%' OR " +
                    "NOMBRE = '" + textFieldNombre.getText() + "'");

            while(resultSet.next()){

                Data.nombreNinoD = resultSet.getObject("NOMBRE") + "";
                Data.idNinoD = resultSet.getObject("ID_NIÑO") + "";

                //-----------------Proceso crear una imagen con blob

                Blob blob = resultSet.getBlob("IMAGEN");
                byte[] data = blob.getBytes(1,(int)blob.length());
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(data));

                //----------------------------------------Convertirlo a imagen FX

                Data.imagenNinoD = SwingFXUtils.toFXImage(image,null);

                //-----------------------------------------------------------------

                Data.apellidoMD = "" + resultSet.getObject("APELLIDO_MATERNO");
                Data.apellidoPD = "" + resultSet.getObject("APELLIDO_PATERNO");

                vBoxResultados.getChildren().add(FXMLLoader.load(getClass().getResource("../Resources/formatoResultado.fxml")));
            }

            if(vBoxResultados.getChildren().size() == 0) mensaje("No encontrado");

        }//try
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en las pantallas");
        }//catch
    }//buscar

    void mensaje(String mensaje){

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(mensaje);
        alert.show();

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
