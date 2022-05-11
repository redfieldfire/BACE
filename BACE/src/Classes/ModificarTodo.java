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
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class ModificarTodo {

    @FXML private Button buttonFinalizar;

    @FXML private VBox vBox;

    ResultSet resultSet;

    int status = 0;

    @FXML void initialize(){

        try {

            resultSet = Main.conexion.consultar("SELECT ID_NOTA,TITULO_NOTA,NOTA FROM notas_medicas WHERE ID_NIÑO = '" + Data.idNinoD + "' ORDER BY 2;");

            while (resultSet.next()){
                Data.idNotaModificar = "" + resultSet.getObject("ID_NOTA");
                Data.tituloNotaModificar = "" + resultSet.getObject("TITULO_NOTA");
                Data.notaTextoModificar = "" + resultSet.getObject("NOTA");
                vBox.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Resources/notasModificar.fxml"))));
            }//while

        }//try
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ModificarTodo.40");
        }//catch

    }

    Alert alert;

    @FXML void actionFinalizar(ActionEvent event) {

        status++;
        System.out.println(status);

        switch (status){

            case 1:

                Data.cantidadArchivosTemporales = 0;

                vBox.getChildren().clear();

                vBox.getChildren().add(buttonFinalizar);

                buttonFinalizar.setText("Siguiente");

                try {

                    resultSet = Main.conexion.consultar("SELECT DISTINCT(CATEGORIA) FROM documentos WHERE ID_NIÑO = '"+Data.idNinoD+"';");

                    while (resultSet.next()){

                        Data.apartado = "" + resultSet.getObject(1);

                        vBox.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Resources/seccionDocumentos.fxml"))));

                    }//while

                }//try
                catch (IOException ignored) {
                    System.out.println("Error en el vBox");
                }//catch
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 2:

                vBox.getChildren().clear();

                vBox.getChildren().add(buttonFinalizar);

                buttonFinalizar.setText("Finalizar");

                resultSet = Main.conexion.consultar("SELECT ID_IMAGEN,IMAGEN FROM imagenes WHERE ID_NIÑO = '" + Data.idNinoD + "';");

                try {

                    while (resultSet.next()){

                        //-----------------Proceso crear una imagen con blob

                        Blob blob = resultSet.getBlob(2);
                        byte[] data = blob.getBytes(1,(int)blob.length());
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(data));

                        //----------------------------------------Convertirlo a imagen FX

                        ImageView imageView = new ImageView();
                        imageView.setFitHeight(700);
                        imageView.setFitWidth(700);
                        imageView.setPreserveRatio(true);
                        imageView.setImage(SwingFXUtils.toFXImage(image,null));

                        String id = "" + resultSet.getObject("ID_IMAGEN");

                        imageView.setOnMouseClicked(e -> {

                            try{

                                alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setHeaderText("Mensaje de BACE");
                                alert.setContentText("¿Estas seguro de deseas eliminar esta imagen?");
                                Optional<ButtonType> action = alert.showAndWait();

                                if(action.get() == ButtonType.OK){
                                    Main.conexion.inmodel("DELETE FROM imagenes WHERE ID_IMAGEN = '"+id+"';");
                                    vBox.getChildren().remove(imageView);
                                    mensaje("Se ha eliminado la imagen");
                                }//if okay
                            }//try
                            catch (Exception exception){
                                exception.printStackTrace();
                            }//catch

                        });

                        //-----------------------------------------------------------------

                        vBox.getChildren().add(imageView);

                    }//while

                }//try
                catch (Exception e){
                    e.printStackTrace();
                }//catch

                break;
            case 3:
                changeScreen("menu");

        }//switch

    }//actionFinalizar

    void mensaje(String mensaje){

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje de BACE");
        alert.setContentText(mensaje);
        alert.show();

    }//mensaje

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
