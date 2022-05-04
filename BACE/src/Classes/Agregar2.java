package Classes;

        import Data.Data;
        import Formatos.FormatoBlob;
        import Formatos.FormatoDocumento;
        import Formatos.FormatoNota;
        import Main.Main;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import javax.sql.rowset.serial.SerialBlob;
        import java.io.File;
        import java.sql.*;

public class Agregar2 {

    @FXML private Button buttonAgregarDoc;

    @FXML private Button buttonAgregarNota;

    @FXML private Button buttonAtras;

    @FXML private Button buttonBuscarDoc;

    @FXML private Button buttonFinalizar;

    @FXML private ComboBox<String> comboBoxCategoriaDoc;

    @FXML private TextField textFieldCategoriaDoc;

    @FXML private TextField textFieldIdDoc;

    @FXML private TextField textFieldNombreDoc;

    @FXML private TextField textFieldNombreNota;

    @FXML private TextArea textFieldNota;

    ResultSet resultSet;

    @FXML void initialize(){

        if(Data.action.equals("editar")){
            buttonFinalizar.setText("Siguiente");
        }

        resultSet = Main.conexion.consultar("SELECT DISTINCT CATEGORIA FROM documentos");

        try{
            while (resultSet.next()){
                comboBoxCategoriaDoc.getItems().add("" + resultSet.getObject("CATEGORIA"));
            }//while
        }//catch
        catch (Exception e){
            e.printStackTrace();
        }//catch

        boolean b = false;

        try{
            if(Data.documentos.size() != 0){
                if(comboBoxCategoriaDoc.getItems().size() == 0)
                    comboBoxCategoriaDoc.getItems().add(Data.documentos.get(0).categoria);
                for(int x = 0; x < Data.documentos.size(); x++){
                    for(int y = 0; y < comboBoxCategoriaDoc.getItems().size(); y++){
                        if(!comboBoxCategoriaDoc.getItems().get(y).equals(Data.documentos.get(x).categoria)){
                            b = true;
                            break;
                        }//if
                    }//for combo
                    if(b){
                        comboBoxCategoriaDoc.getItems().add(Data.documentos.get(x).categoria);
                        b = false;
                    }//if
                }//for docs
            }//if
        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

        comboBoxCategoriaDoc.setOnAction(event -> textFieldCategoriaDoc.setText(
                comboBoxCategoriaDoc.getSelectionModel().getSelectedItem()));

    }//initialize

    byte[] dataDocumento;
    Blob blobDocumento;

    int idDocumento = 0;

    void convertirPDFsABlob(){

        for(int x = 0 ; x < Data.documentos.size(); x++){

            dataDocumento = null;
            blobDocumento = null;
            idDocumento++;

            //---------------------------------------------------Convertirmos el file de documentos a bytes

            dataDocumento = new byte[(int) Data.documentos.get(x).documento.length()];

            //---------------------------------------------------Convertirmos los bytes a blob

            try {
                blobDocumento = new SerialBlob(dataDocumento);
            }//try
            catch (SQLException e) {
                throw new RuntimeException(e);
            }//catch

            //---------------------------------------------------Agregamos los blobs a la lista del data

            Data.blobsDocumentos.add(new FormatoBlob(blobDocumento,"" + idDocumento));

        }//for

    }//converter

    PreparedStatement preparedStatement = null;
    String sql = "INSERT INTO documentos VALUES (?,?,?,?,?);";

    void insertarBlobsPDF(){

        for(int x = 0; x < Data.blobsDocumentos.size();x++){

            try {

                preparedStatement = Main.conexion.connection.prepareStatement(sql);

                preparedStatement.setString(1,Data.blobsDocumentos.get(x).idDocumento);
                preparedStatement.setString(2,Data.idNino);
                preparedStatement.setString(3,Data.documentos.get(x).titulo);
                preparedStatement.setString(4,Data.documentos.get(x).categoria);
                preparedStatement.setBlob(5,Data.blobsDocumentos.get(x).blobDocumento);

                preparedStatement.executeUpdate();

            }//try
            catch (Exception e){
                e.printStackTrace();
            }//catch

        }//for

    }//insert

    PreparedStatement preparedStatement2 = null;
    String sql2 = "INSERT INTO datos VALUES (?,?,?,?,?,?,?,?,?,TO DATE('"+Data.fechaIngreso+"','YYYY-MM-DD'),TO DATE('"+Data.fechaEgreso+"','YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    void insertarDatos(){

            try {

                preparedStatement2 = Main.conexion.connection.prepareStatement(sql);

                preparedStatement2.setString(1,Data.idDato);
                preparedStatement2.setString(2,Data.idNino);
                preparedStatement2.setInt(3,Data.edad);
                preparedStatement2.setString(4,Data.sexo);
                preparedStatement2.setDouble(5,Data.tallaZapato);
                preparedStatement2.setString(6,Data.tallaPantalon);
                preparedStatement2.setString(7,Data.tallaCamisa);
                preparedStatement2.setString(8,Data.coloresFavoritos);
                preparedStatement2.setString(9,Data.pasatiempos);
                preparedStatement2.setString(12,Data.lugarOrigen);
                preparedStatement2.setString(13,Data.lugarNacimiento);
                preparedStatement2.setString(14,Data.nombreMama);
                preparedStatement2.setString(15,Data.nombrePapa);
                preparedStatement2.setString(16,Data.gradoEscolar);
                preparedStatement2.setString(17,Data.estatura);
                preparedStatement2.setString(18,Data.peso);
                preparedStatement2.setString(19,Data.colorPiel);
                preparedStatement2.setString(20,Data.complexion);
                preparedStatement2.setString(21,Data.colorCabello);
                preparedStatement2.setString(22,Data.colorOjos);
                preparedStatement2.setString(23,Data.nariz);
                preparedStatement2.setString(24,Data.boca);
                preparedStatement2.setString(25,Data.integracionFamiliar);

                preparedStatement2.executeUpdate();

            }//try
            catch (Exception e){
                e.printStackTrace();
            }//catch

    }//insert

    int idNota = 0;

    void agregarNotas(){

        for(int x = 0; x < Data.notas.size(); x++){
            idNota++;
            Main.conexion.inmodel("INSERT INTO notas_medicas VALUES (" +
                    "'"+idNota+"'"+
                    "'"+Data.idNino+"'"+
                    "'"+Data.notas.get(x).tituloNota+"'"+
                    "'"+Data.notas.get(x).nota+"'");

        }//for

    }//add

    public void limpiarTodo(){
        Data.documentos.clear();
        Data.notas.clear();
        Data.blobsDocumentos.clear();
        Data.idNino = "";
        Data.nombre = "";
        Data.apellidoM = "";
        Data.apellidoP = "";
        Data.fechaNacimiento = "";
        Data.sexo = "";
        Data.edad = 100;
        Data.tallaZapato = 100;
        Data.tallaPantalon = "";
        Data.tallaCamisa = "";
        Data.estatura = "";
        Data.peso = "";
        Data.colorPiel = "";
        Data.complexion = "";
        Data.colorOjos = "";
        Data.colorCabello = "";
        Data.nariz = "";
        Data.boca = "";
        Data.fechaIngreso = "";
        Data.fechaEgreso = "";
        Data.lugarOrigen = "";
        Data.nombreMama = "";
        Data.nombrePapa = "";
        Data.lugarNacimiento = "";
        Data.integracionFamiliar = "";
        Data.pasatiempos = "";
        Data.coloresFavoritos = "";
        Data.gradoEscolar = "";
    }//clear

    @FXML void finalizarAction(ActionEvent event) {
        if(Data.action.equals("agregar")){

            convertirPDFsABlob();
            insertarBlobsPDF();
            agregarNotas();

            limpiarTodo();

            changeScreen("menu");
        }
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
        alert.setHeaderText("Mensaje de BACE");
        alert.setContentText(message);
        alert.show();
    }//alert

    @FXML void actionAgregarDoc(ActionEvent event) {

        if(!file.exists())
            alert("Ningun archivo seleccionado");
        else if(textFieldNombreDoc.getText().equals("") || textFieldCategoriaDoc.equals(""))
            alert("No se completaron los campos");
        else {
            try {
                for(int x = 0; x < comboBoxCategoriaDoc.getItems().size(); x++){
                    if(!(textFieldCategoriaDoc.getText().equals(comboBoxCategoriaDoc.getItems().get(x)))){
                        comboBoxCategoriaDoc.getItems().add(textFieldCategoriaDoc.getText());
                        break;
                    }//if
                }//for agregar categorias
                if(comboBoxCategoriaDoc.getItems().size() == 0)
                    comboBoxCategoriaDoc.getItems().add(textFieldCategoriaDoc.getText());
                Data.documentos.add(new FormatoDocumento(textFieldNombreDoc.getText(),textFieldCategoriaDoc.getText(),file));
                alert("Documento " + textFieldNombreDoc.getText());
                textFieldNombreDoc.setText("");
                textFieldCategoriaDoc.setText("");
                comboBoxCategoriaDoc.getSelectionModel().clearSelection();
                file = null;
            }//try
            catch (Exception e){
                alert("Error al agregar el documento");
                e.printStackTrace();
            }//catch
        }//else

    }

    @FXML void actionAgregarNota(ActionEvent event) {

        if(textFieldNombreNota.getText().equals(""))
            alert("Sin titulo");
        else if(textFieldNota.getText().equals(""))
            alert("Sin nota");
        else{

            try{
                Data.notas.add(new FormatoNota(textFieldNombreNota.getText(),textFieldNota.getText()));
                alert("Nota " + textFieldNombreNota.getText());
                textFieldNombreNota.setText("");
                textFieldNota.setText("");
            }//try
            catch (Exception e){
                alert("Error al agregar la nota");
            }//catch

        }//else

    }

    File file;
    FileChooser fileChooser;

    @FXML void actionBuscarDoc(ActionEvent event) {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un PDF");

        try{
            file = fileChooser.showOpenDialog(new Stage());
            if(!file.exists()) textFieldNombreDoc.setText("Sin archivo");
            else textFieldNombreDoc.setText(file.getName());
        }//try
        catch (Exception e){
            alert("Sin seleccionar");
        }//catch

    }

}

