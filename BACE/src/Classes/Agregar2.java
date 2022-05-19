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
        import javafx.scene.image.Image;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import javax.sql.rowset.serial.SerialBlob;
        import java.io.File;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.sql.*;

public class Agregar2 {

    @FXML private Button buttonAgregarDoc;

    @FXML private Button buttonAgregarNota;

    @FXML private Button buttonAtras;

    @FXML private Button buttonBuscarDoc;

    @FXML private Button buttonFinalizar;

    @FXML private ComboBox<String> comboBoxCategoriaDoc;

    @FXML private TextField textFieldCategoriaDoc;

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

        textFieldNombreNota.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) textFieldNota.requestFocus();
        });

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

            try {
                dataDocumento = Files.readAllBytes(Data.documentos.get(x).documento.toPath());
            }//try
            catch (IOException e) {
                throw new RuntimeException(e);
            }//catch

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

    PreparedStatement psDocumentos = null;
    String sqlDocumentos = "INSERT INTO documentos VALUES (?,?,?,?,?);";

    String ultimoIdPDF = "";
    int numeroPDFI;
    String numeroPDFS;
    void calcularNumeroPDF(){

        ultimoIdPDF = "";
        numeroPDFI = 0;
        numeroPDFS = "";

        resultSet = Main.conexion.consultar("SELECT ID_DOCUMENTO FROM documentos ORDER BY LENGTH(ID_DOCUMENTO) , 1;");

        try{
            while (resultSet.next()){
                ultimoIdPDF = "" + resultSet.getObject(1);
            }//while

            if(ultimoIdPDF.equals("")) numeroPDFI = 1;
            else{
                for(int x = 0; x < ultimoIdPDF.length(); x++){
                    try{
                        numeroPDFS = numeroPDFS + Integer.parseInt("" + ultimoIdPDF.charAt(x));
                    }//try
                    catch (Exception e){
                        System.out.println("" + ultimoIdPDF.charAt(x));
                        break;
                    }//catch
                }//for
                numeroPDFI = Integer.parseInt(numeroPDFS) + 1;
            }//else

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }

    void insertarBlobsPDF(){

        calcularNumeroPDF();

        for(int x = 0; x < Data.blobsDocumentos.size(); x++){

            try {

                psDocumentos = Main.conexion.connection.prepareStatement(sqlDocumentos);

                psDocumentos.setString(1,numeroPDFI + "D" + Data.nombre.charAt(0) + Data.sexo.charAt(0));
                psDocumentos.setString(2,Data.idNino);
                psDocumentos.setString(3,Data.documentos.get(x).titulo);
                psDocumentos.setString(4,Data.documentos.get(x).categoria);
                psDocumentos.setBlob(5,Data.blobsDocumentos.get(x).blobDocumento);

                psDocumentos.executeUpdate();

            }//try
            catch (Exception e){
                e.printStackTrace();
            }//catch

            numeroPDFI++;

        }//for

    }//insert

    PreparedStatement psDatos = null;
    String sqlDatos = "INSERT INTO datos VALUES (?,?,?,?,?,?,?,?,?,'"+Data.fechaIngreso+"','"+Data.fechaEgreso+"',?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    String ultimoIdDato = "";
    int numeroDatoI;
    String numeroDatoS;
    void calcularNumeroDato(){

        ultimoIdDato = "";
        numeroDatoI = 0;
        numeroDatoS = "";

        resultSet = Main.conexion.consultar("SELECT ID_DATO FROM datos ORDER BY LENGTH(ID_DATO), 1;");

        try{
            while (resultSet.next()){
                ultimoIdDato = "" + resultSet.getObject(1);
            }//while

            if(ultimoIdDato.equals("")) numeroDatoI = 1;
            else{
                for(int x = 0; x < ultimoIdDato.length(); x++){
                    try{
                        numeroDatoS = numeroDatoS + Integer.parseInt("" + ultimoIdDato.charAt(x));
                    }//try
                    catch (Exception e){
                        System.out.println("" + ultimoIdDato.charAt(x));
                        break;
                    }//catch
                }//for
                numeroDatoI = Integer.parseInt(numeroDatoS) + 1;
            }//else

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }

    void insertarDatos(){

        calcularNumeroDato();

            try {

                psDatos = Main.conexion.connection.prepareStatement(sqlDatos);

                psDatos.setString(1, numeroDatoI + "DA" + Data.nombre.charAt(0) + Data.sexo.charAt(0));
                psDatos.setString(2,Data.idNino);
                psDatos.setInt(3,Data.edad);
                psDatos.setString(4,Data.sexo);
                psDatos.setDouble(5,Data.tallaZapato);
                psDatos.setString(6,Data.tallaPantalon);
                psDatos.setString(7,Data.tallaCamisa);
                psDatos.setString(8,Data.coloresFavoritos);
                psDatos.setString(9,Data.pasatiempos);
                psDatos.setString(10,Data.lugarOrigen);
                psDatos.setString(11,Data.lugarNacimiento);
                psDatos.setString(12,Data.nombreMama);
                psDatos.setString(13,Data.nombrePapa);
                psDatos.setString(14,Data.gradoEscolar);
                psDatos.setString(15,Data.estatura);
                psDatos.setString(16,Data.peso);
                psDatos.setString(17,Data.colorPiel);
                psDatos.setString(18,Data.complexion);
                psDatos.setString(19,Data.colorCabello);
                psDatos.setString(20,Data.colorOjos);
                psDatos.setString(21,Data.nariz);
                psDatos.setString(22,Data.boca);
                psDatos.setString(23,Data.integracionFamiliar);

                psDatos.executeUpdate();

            }//try
            catch (Exception e){
                e.printStackTrace();
            }//catch

    }//insertarDatos

    PreparedStatement psNinos = null;
    String sqlNinos = "INSERT INTO niños VALUES(?,?,?,?,'"+Data.fechaNacimiento+"',?);";

    void insertarNino(){

        try {

            psNinos = Main.conexion.connection.prepareStatement(sqlNinos);

            psNinos.setString(1,Data.idNino);
            psNinos.setString(2,Data.nombre);
            psNinos.setString(3,Data.apellidoP);
            psNinos.setString(4,Data.apellidoM);
            psNinos.setBytes(5, Files.readAllBytes(Data.fileImagen.toPath()));

            psNinos.executeUpdate();

        }//try
        catch(Exception e){
            e.printStackTrace();
        }//catch

    }//insertarNino

    PreparedStatement psNotas = null;

    String sqlNotas_Medicas = "INSERT INTO notas_medicas VALUES (?,?,?,?);";

    String ultimoIdNota = "";
    int numeroNotaI;
    String numeroNotaS;
    void calcularNumeroNota(){

        ultimoIdNota = "";
        numeroNotaI = 0;
        numeroNotaS = "";

        resultSet = Main.conexion.consultar("SELECT ID_NOTA FROM notas_medicas ORDER BY LENGTH(ID_NOTA) , 1;");

        try{
            while (resultSet.next()){
                ultimoIdNota = "" + resultSet.getObject(1);
            }//while

            if(ultimoIdNota.equals("")) numeroNotaI = 1;
            else{
                for(int x = 0; x < ultimoIdNota.length(); x++){
                    try{
                        numeroNotaS = numeroNotaS + Integer.parseInt("" + ultimoIdNota.charAt(x));
                    }//try
                    catch (Exception e){
                        System.out.println("" + ultimoIdNota.charAt(x));
                        break;
                    }//catch
                }//for
                numeroNotaI = Integer.parseInt(numeroNotaS) + 1;
            }//else

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }

    void insertarNotas(){

        calcularNumeroNota();

        try{

            for(int x = 0; x < Data.notas.size(); x++) {

                psNotas = Main.conexion.connection.prepareStatement(sqlNotas_Medicas);

                psNotas.setString(1,numeroNotaI + "N" + Data.nombre.charAt(0) + Data.sexo.charAt(0));
                psNotas.setString(2,Data.idNino);
                psNotas.setString(3,Data.notas.get(x).tituloNota);
                psNotas.setString(4,Data.notas.get(x).nota);

                psNotas.executeUpdate();

                numeroNotaI++;

            }//for

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }//insertarNotas

    PreparedStatement psImagenes = null;
    String sqlImagenes = "INSERT INTO imagenes VALUES (?,?,?);";

    String ultimoIdImagen = "";
    int numeroImagenI;
    String numeroImagenS;
    void calcularNumeroImagen(){

        ultimoIdImagen = "";
        numeroImagenI = 0;
        numeroImagenS = "";

        resultSet = Main.conexion.consultar("SELECT ID_IMAGEN FROM imagenes ORDER BY LENGTH(ID_IMAGEN) , 1;");

        try{
            while (resultSet.next()){
                ultimoIdImagen = "" + resultSet.getObject(1);
            }//while

            if(ultimoIdImagen.equals("")) numeroImagenI = 1;
            else{
                for(int x = 0; x < ultimoIdImagen.length(); x++){
                    try{
                        numeroImagenS = numeroImagenS + Integer.parseInt("" + ultimoIdImagen.charAt(x));
                    }//try
                    catch (Exception e){
                        System.out.println("" + ultimoIdImagen.charAt(x));
                        break;
                    }//catch
                }//for
                numeroImagenI = Integer.parseInt(numeroImagenS) + 1;
            }//else

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }

    void insertarImagen(){

        calcularNumeroImagen();

        try {

            psImagenes = Main.conexion.connection.prepareStatement(sqlImagenes);

            psImagenes.setString(1,numeroImagenI + "I" + Data.nombre.charAt(0) + Data.sexo.charAt(0));
            psImagenes.setString(2,Data.idNino);
            psImagenes.setBytes(3,Files.readAllBytes(Data.fileImagen.toPath()));

            psImagenes.executeUpdate();

        }//try
        catch(Exception e){
            e.printStackTrace();
        }//catch

    }//insertarImagen

    public void limpiarTodo(){

        System.out.println("" + Data.idNino + "\n" + Data.nombre + "\n" +
        Data.apellidoM + "\n" +
        Data.apellidoP + "\n" +
        Data.fechaNacimiento + "\n" +
        Data.sexo + "\n" +
        Data.edad + "\n" +
        Data.tallaZapato + "\n" +
        Data.tallaPantalon + "\n" +
        Data.tallaCamisa + "\n" +
        Data.estatura + "\n" +
        Data.peso + "\n" +
        Data.colorPiel + "\n" +
        Data.complexion + "\n" +
        Data.colorOjos + "\n" +
        Data.colorCabello + "\n" +
        Data.nariz + "\n" +
        Data.boca + "\n" +
        Data.fechaIngreso + "\n" +
        Data.fechaEgreso + "\n" +
        Data.lugarOrigen + "\n" +
        Data.nombreMama + "\n" +
        Data.nombrePapa + "\n" +
        Data.lugarNacimiento + "\n" +
        Data.integracionFamiliar + "\n" +
        Data.pasatiempos + "\n" +
        Data.coloresFavoritos + "\n" +
        Data.gradoEscolar + "\n");

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

        Data.imagen = new Image("Images/user.png");
    }//clear

    @FXML void finalizarAction(ActionEvent event) {

        if(Data.action.equals("agregar")){

            insertarNino();
            convertirPDFsABlob();
            insertarBlobsPDF();
            insertarDatos();
            insertarNotas();
            insertarImagen();

            alert(Data.nombre + " ha sido agregadó");

            limpiarTodo();

            changeScreen("menu");
        }//if agregar
        else if(Data.action.equals("editar")){

            convertirPDFsABlob();
            insertarBlobsPDF();
            insertarNotas();
            if(Data.fileImagen != null)
                insertarImagen();

            alert("Han sido agregados los PDFs y Notas: " + Data.nombreNinoD);

            limpiarTodo();

            changeScreen("modificarTodo");

        }//else if editar

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

    boolean agregarAlComboBox = true;

    @FXML void actionAgregarDoc(ActionEvent event) {

        if(file != null)
            if(!file.exists())
                alert("Ningun archivo seleccionado");
            else if(textFieldNombreDoc.getText().equals("") || textFieldCategoriaDoc.equals(""))
                alert("No se completaron los campos");
            else {
                try {
                    if(comboBoxCategoriaDoc.getItems().size() == 0)
                        comboBoxCategoriaDoc.getItems().add(textFieldCategoriaDoc.getText());
                    else
                        for(int x = 0; x < comboBoxCategoriaDoc.getItems().size(); x++){
                            if(textFieldCategoriaDoc.getText().equals(comboBoxCategoriaDoc.getItems().get(x))){
                                agregarAlComboBox = false;
                                System.out.println("Si hay repetidos");
                                break;
                            }//if
                        }//for agregar categorias
                    if(agregarAlComboBox) comboBoxCategoriaDoc.getItems().add(textFieldCategoriaDoc.getText());
                    if(textFieldCategoriaDoc.getText().equals("")) textFieldCategoriaDoc.setText("Sin categoria");
                    Data.documentos.add(new FormatoDocumento(textFieldNombreDoc.getText(),textFieldCategoriaDoc.getText(),file));
                    alert("Documento " + textFieldNombreDoc.getText());
                    textFieldNombreDoc.setText("");
                    textFieldCategoriaDoc.setText("");
                    comboBoxCategoriaDoc.getSelectionModel().clearSelection();
                    file = null;
                    agregarAlComboBox = true;
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
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
        fileChooser.setTitle("Selecciona un PDF");

        try{
            file = fileChooser.showOpenDialog(new Stage());
            if(!file.exists()) textFieldNombreDoc.setText("Sin archivo");
            else textFieldNombreDoc.setText(file.getName());
        }//try
        catch (Exception e){
            alert("Sin seleccionar");
        }//catch

    }//ActionBuscarDoc

}//Agregar 2

