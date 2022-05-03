package Classes;

import Data.Data;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FormatoResultado {

    @FXML private Text apellidos;

    @FXML private Text id;

    @FXML private Text nombre;

    @FXML private ImageView imagen;

    @FXML private HBox hBox;

    String idS,apellidosS,nombreS;
    Image imagenS;

    @FXML void initialize(){

        idS = Data.idNinoD;
        apellidosS = Data.apellidoMD + " " + Data.apellidoPD;
        nombreS = Data.nombreNinoD;
        imagenS = Data.imagenNinoD;

        imagen.setImage(imagenS);
        apellidos.setText("Apellidos:\n" + apellidosS);
        id.setText("Id:\n" + idS);
        nombre.setText("Nombre(s):\n" + nombreS);

        hBox.setOnMouseClicked(event -> {
            try{
                if(Data.action.equals("buscar"))
                    Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/expediente.fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
                else if(Data.action.equals("editar"))
                    Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/Agregar.fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
            }//try
            catch (Exception ignored){
                System.out.println("Error en las pantallas");
            }//catch
        });

    }//initialize

}//FormatoResultado
