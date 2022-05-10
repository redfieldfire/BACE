package Classes;

import Data.Data;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Menu {

    @FXML private Button btnAgregar;

    @FXML private Button btnBuscar;

    @FXML private Button btnEditar;

    @FXML void initialize(){
        limpiarSiHayArchivosONotas();
    }//initialize

    void changeScreen(String fxml){
        try{
            Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/"+fxml+".fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
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

    }//enteredAgregar

    @FXML void enteredBuscar() {

    }//enteredBuscar

    @FXML void enteredEditar() {

    }//enteredEditar

    @FXML void exitedAgregar() {

    }//exitedAgregar

    @FXML void exitedBuscar() {

    }//exitedBuscar

    @FXML void exitedEditar() {

    }//exitedEditar

}//Menu
