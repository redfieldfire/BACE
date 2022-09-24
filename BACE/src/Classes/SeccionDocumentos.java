package Classes;

import Data.Data;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.Objects;

public class SeccionDocumentos {

    ResultSet resultSet;
    byte[] data;
    Blob blob;

    @FXML void initialize(){

        textNombreApartado.setText(Data.apartado);

        resultSet = Main.conexion.consultar("SELECT * FROM documentos WHERE CATEGORIA = '"+ Data.apartado +"' AND ID_NIÑO = '"+Data.idNinoD+"';");

        try{

            while (resultSet.next()){

                Data.cantidadArchivosTemporales++;

                data = null;
                blob = resultSet.getBlob("DOCUMENTO");
                data = blob.getBytes(1,(int)blob.length());

                try {

                    OutputStream outputStream = new FileOutputStream("C:\\BACE\\Temporal\\archivo"+Data.cantidadArchivosTemporales+".pdf");
                    outputStream.write(data);

                    Data.file = new File("C:\\BACE\\Temporal\\archivo"+Data.cantidadArchivosTemporales+".pdf");

                }//try
                catch (Exception ignored){

                }//catch

                Data.nombreDocumento = "" + resultSet.getObject("TITULO_DOCUMENTO");

                if(Data.action.equals("buscar"))
                    flowPaneEspacioDocumentos.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Resources/documento.fxml"))));

                else if(Data.action.equals("editar")){
                    Data.idDocumentoModificar = "" + resultSet.getObject("ID_DOCUMENTO");
                    flowPaneEspacioDocumentos.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Resources/documentoModificar.fxml"))));
                }

            }//while

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }//initialize

    @FXML private FlowPane flowPaneEspacioDocumentos;

    @FXML private Text textNombreApartado;

}
