package Classes;

        import Data.Data;
        import Main.Main;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import java.io.File;

public class Agregar2 {

    @FXML private Button buttonAgregarDoc;

    @FXML private Button buttonAgregarNota;

    @FXML private Button buttonAtras;

    @FXML private Button buttonBuscarDoc;

    @FXML private Button buttonFinalizar;

    @FXML private ComboBox<String> comboBoxDoc;

    @FXML private TextField textFieldCategoriaDoc;

    @FXML private TextField textFieldIdDoc;

    @FXML private TextField textFieldNombreDoc;

    @FXML private TextField textFieldNombreNota;

    @FXML private TextArea textFieldNota;

    @FXML void initialize(){

        if(Data.action.equals("editar")){
            buttonFinalizar.setText("Siguiente");
        }

    }//initialize

    @FXML void finalizarAction(ActionEvent event) {
        if(Data.action.equals("agregar")) changeScreen("menu");
        else if(Data.action.equals("editar")) changeScreen("modificarTodo");
    }

    @FXML void finalizarEntered(MouseEvent event) {

    }

    @FXML void finalizarExited(MouseEvent event) {

    }
    void changeScreen(String fxml){
        try{
            Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/"+fxml+".fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
        }//try
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error en las pantallas");
        }//catch
    }//changeScreen

    @FXML void atrasAction(){
        changeScreen("agregar");
    }//atrasAction

    @FXML void atrasExited(){

    }//atrasExited

    @FXML void atrasEntered(){

    }//atrasEntered

    void alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje de Credenciales");
        alert.setContentText(message);
        alert.show();
    }//alert

    @FXML void actionAgregarDoc(ActionEvent event) {

    }

    @FXML void actionAgregarNota(ActionEvent event) {

    }

    File file;
    FileChooser fileChooser;

    @FXML void actionBuscarDoc(ActionEvent event) {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un PDF");

        try{
            file = fileChooser.showOpenDialog(new Stage());
        }//try
        catch (Exception e){
            alert("Sin seleccionar");
        }//catch

    }

    @FXML void atrasAction(ActionEvent event) {

    }

    @FXML void atrasEntered(MouseEvent event) {

    }

    @FXML void atrasExited(MouseEvent event) {

    }

}

