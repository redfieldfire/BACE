package Classes;

import Data.Data;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;

public class Menu {

    @FXML private ImageView imagenAgregar;

    @FXML private ImageView imagenBuscar;

    @FXML
    private ImageView imagenEditar;

    @FXML
    private ImageView imagenLogo;

    @FXML private Button btnAgregar;

    @FXML private Button btnBuscar;

    @FXML private Button btnEditar;


    @FXML void initialize(){

        try {
            imagenLogo.setImage(new Image(new FileInputStream("C:\\BACE\\Images\\logo.jpeg")));
            imagenAgregar.setImage(new Image(new FileInputStream("C:\\BACE\\Images\\add.png")));
            imagenBuscar.setImage(new Image(new FileInputStream("C:\\BACE\\Images\\search.png")));
            imagenEditar.setImage(new Image(new FileInputStream("C:\\BACE\\Images\\edit.png")));
        }//try for the images
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }//catch

        limpiarSiHayArchivosONotas();
    }//initialize

    void changeScreen(String fxml){
        try{
            Main.stage.setScene(
                    new Scene(
                            FXMLLoader.load(
                                    Objects.requireNonNull(
                                            getClass().getResource(
                                                    "../Resources/" + fxml + ".fxml"))),
                            Main.stage.getWidth(), Main.stage.getHeight()));
        }//try
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error en las pantallas");
        }//catch
    }//changeScreen

    @FXML void actionAgregar() {
        Data.action = "agregar";
        changeScreen("agregar");
    }//actionAgregar

    void limpiarSiHayArchivosONotas(){

        if(!Data.documentos.isEmpty())
            Data.documentos.clear();
        if(!Data.notas.isEmpty())
            Data.notas.clear();
        if(!Data.blobsDocumentos.isEmpty())
            Data.blobsDocumentos.clear();

        System.out.println("Temp Cleaned");

    }//cleanIf

    @FXML void actionBuscar() {
        Data.action = "buscar";
        changeScreen("buscar");
    }//actionBuscar

    @FXML void actionEditar() {
        Data.action = "editar";
        changeScreen("buscar");
    }//actionEditar

    @FXML void enteredAgregar() {
        btnAgregar.setOpacity(0.3);
    }//enteredAgregar

    @FXML void enteredBuscar() {
        btnBuscar.setOpacity(0.3);
    }//enteredBuscar

    @FXML void enteredEditar() {
        btnEditar.setOpacity(0.3);
    }//enteredEditar

    @FXML void exitedAgregar() {
        btnAgregar.setOpacity(1);
    }//exitedAgregar

    @FXML void exitedBuscar() {
        btnBuscar.setOpacity(1);
    }//exitedBuscar

    @FXML void exitedEditar() {
        btnEditar.setOpacity(1);
    }//exitedEditar

}//Menu
