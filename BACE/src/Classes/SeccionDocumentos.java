package Classes;

import Data.Data;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.nio.file.Files;
import java.sql.Blob;
import java.sql.ResultSet;

public class SeccionDocumentos {

    ResultSet resultSet;
    byte[] data;
    Blob blob;

    @FXML void initialize(){

        textNombreApartado.setText(Data.apartado);

        resultSet = Main.conexion.consultar("SELECT * FROM documentos WHERE CATEGORIA = '"+ Data.apartado +"';");

        try{

            while (resultSet.next()){

                data = null;
                blob = resultSet.getBlob("DOCUMENTO");
                data = blob.getBytes(1,(int)blob.length());
                Files.write(Data.file.toPath(),data);

                Data.nombreDocumento = "" + resultSet.getObject("TITULO_DOCUMENTO");

                flowPaneEspacioDocumentos.getChildren().add(FXMLLoader.load(getClass().getResource("../Resources/documento.fxml")));

            }//while

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }//initialize

    @FXML private FlowPane flowPaneEspacioDocumentos;

    @FXML private Text textNombreApartado;

}
