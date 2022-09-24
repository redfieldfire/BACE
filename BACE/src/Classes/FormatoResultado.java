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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FormatoResultado {

    @FXML private Text apellidos;

    @FXML private Text id;

    @FXML private Text nombre;

    @FXML private ImageView imagen;

    @FXML private HBox hBox;

    String idS,apellidoMS,apellidoPS,nombreS;
    Image imagenS;

    @FXML void initialize(){

        try {
            imagen.setImage(new Image(new FileInputStream("C:\\BACE\\Images\\user.png")));
        }//try
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }//catch

        idS = Data.idNinoD;
        apellidoMS = Data.apellidoMD;
        apellidoPS = Data.apellidoPD;
        nombreS = Data.nombreNinoD;
        imagenS = Data.imagenNinoD;

        imagen.setImage(imagenS);
        apellidos.setText("Apellidos:\n" + apellidoPS + " " + apellidoMS);
        id.setText("Id:\n" + idS);
        nombre.setText("Nombre(s):\n" + nombreS);

        hBox.setOnMouseClicked(event -> {
            try{
                Data.idNinoD = idS;
                Data.nombreNinoD = nombreS;
                Data.imagenNinoD = imagenS;
                Data.apellidoMD = apellidoMS;
                Data.apellidoPD = apellidoPS;
                if(Data.action.equals("buscar"))
                    Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/expediente.fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
                else if(Data.action.equals("editar"))
                    Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/Agregar.fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
            }//try
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Error en las pantallas");
            }//catch
        });

    }//initialize

}//FormatoResultado
