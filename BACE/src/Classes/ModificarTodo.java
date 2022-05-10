package Classes;

import Data.Data;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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

    @FXML void actionFinalizar(ActionEvent event) {

        status++;

        switch (status){

            case 1:
                Data.cantidadArchivosTemporales = 0;

                vBox.getChildren().clear();

                try {

                    resultSet = Main.conexion.consultar("SELECT DISTINCT(CATEGORIA) FROM documentos WHERE ID_NIÑO = '"+Data.idNinoD+"';");

                    while (resultSet.next()){

                        Data.apartado = "" + resultSet.getObject(1);

                        vBox.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Resources/seccionDocumentos.fxml"))));

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
                changeScreen("menu");

        }//switch

    }//actionFinalizar

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
