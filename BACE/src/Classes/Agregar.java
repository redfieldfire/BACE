package Classes;

        import Data.Data;
        import Main.Main;
        import javafx.embed.swing.SwingFXUtils;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import javax.imageio.ImageIO;
        import java.io.File;
        import java.sql.ResultSet;
        import java.time.LocalDate;

public class Agregar {

    @FXML private Button buttonAtras;

    @FXML private Button buttonImagen;

    @FXML private Button buttonSiguiente;

    @FXML private ComboBox<Integer> comboBoxEdad;

    @FXML private ComboBox<String> comboBoxSexo;

    @FXML private ComboBox<String> comboBoxTallaCamisa;

    @FXML private ComboBox<Double> comboBoxTallaZapato;

    @FXML private DatePicker datePickerFechaEgreso;

    @FXML private DatePicker datePickerFechaIngreso;

    @FXML private DatePicker datePickerFechaNacimiento;

    @FXML private ImageView imagenNino;

    @FXML private ScrollPane scroll;

    @FXML private TextArea textAreaColoresFavoritos;

    @FXML private TextArea textAreaIntegracionFamiliar;

    @FXML private TextArea textAreaPasatiempos;

    @FXML private TextField textFieldApellidoM;

    @FXML private TextField textFieldApellidoP;

    @FXML private TextField textFieldNombreMama;

    @FXML private TextField textFieldNombrePapa;

    @FXML private TextField textFieldBoca;

    @FXML private TextField textFieldColorCabello;

    @FXML private TextField textFieldColorOjos;

    @FXML private TextField textFieldColorPiel;

    @FXML private TextField textFieldComplexion;

    @FXML private TextField textFieldEstatura;

    @FXML private TextField textFieldId;

    @FXML private TextField textFieldLugarNacimiento;

    @FXML private TextField textFieldLugarOrigen;

    @FXML private TextField textFieldNariz;

    @FXML private TextField textFieldNombre;

    @FXML private TextField textFieldPeso;

    @FXML private TextField textFieldTallaPantalon;

    @FXML private TextField textFieldGradoEscolar;

    int numeroNinoI;
    String numeroNinoS = "";
    ResultSet resultSet;
    String ultimoId = "";

    void calcularNumeroNino(){

        resultSet = Main.conexion.consultar("SELECT ID_NIÑO FROM niños;");

        try{
            while (resultSet.next()){
                ultimoId = "" + resultSet.getObject(1);
            }//while

            if(ultimoId.equals("")) numeroNinoI = 1;
            else{
                for(int x = 0; x < ultimoId.length(); x++){
                    try{
                        numeroNinoS = numeroNinoS + Integer.parseInt("" + ultimoId.charAt(x));
                    }//try
                    catch (Exception e){
                        System.out.println("" + ultimoId.charAt(x));
                    }//catch
                }//for
                numeroNinoI = Integer.parseInt(numeroNinoS);
            }//else

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }//calcularNumeroNino

    @FXML void initialize(){

        if(Data.action.equals("agregar")) calcularNumeroNino();

        //Saltos de linea
        textFieldNombre.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) {
                textFieldApellidoM.requestFocus();
                Data.idNino = textFieldNombre.getText().charAt(0) + "" + comboBoxSexo.getSelectionModel().getSelectedItem() + "" + numeroNinoI;
                textFieldId.setText(Data.idNino);
            }//if
        });
        textFieldApellidoM.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldApellidoP.requestFocus();
        });
        textFieldApellidoP.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldNombreMama.requestFocus();
        });
        textFieldNombreMama.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldNombrePapa.requestFocus();
        });
        textFieldNombrePapa.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) datePickerFechaNacimiento.requestFocus();
        });
        comboBoxSexo.setOnAction(event -> {
            Data.idNino = textFieldNombre.getText().charAt(0) + "" + comboBoxSexo.getSelectionModel().getSelectedItem() + "" + numeroNinoI;
            textFieldId.setText(Data.idNino);
        });
        textFieldTallaPantalon.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) comboBoxTallaCamisa.requestFocus();
        });
        textFieldEstatura.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldPeso.requestFocus();
        });
        textFieldPeso.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldColorPiel.requestFocus();
        });
        textFieldColorPiel.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldComplexion.requestFocus();
        });
        textFieldComplexion.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldColorOjos.requestFocus();
        });
        textFieldColorOjos.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldColorCabello.requestFocus();
        });
        textFieldColorCabello.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldNariz.requestFocus();
        });
        textFieldNariz.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER){
                textFieldBoca.requestFocus();
                scroll.setVvalue(1);
            }
        });
        textFieldBoca.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) datePickerFechaIngreso.requestFocus();
        });
        textFieldLugarOrigen.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldLugarNacimiento.requestFocus();
        });
        textFieldLugarNacimiento.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textAreaIntegracionFamiliar.requestFocus();
        });

        //--------------------Predeterminados

        for(int x = 0; x < 99; x++) comboBoxEdad.getItems().add(x);
        //comboBoxEdad.getSelectionModel().select(0);

        comboBoxSexo.getItems().addAll("M","F");
        //comboBoxSexo.getSelectionModel().select("M");

        comboBoxTallaCamisa.getItems().addAll("XS","S","M","L","XL","XXL","XXL");
        //comboBoxTallaCamisa.getSelectionModel().select(2);

        for(double x = 0; x < 20; x = x + 0.5) comboBoxTallaZapato.getItems().add(x);
        //comboBoxTallaZapato.getSelectionModel().select(10);


        if(Data.firstTime == 1) ingresarDatos();
        else Data.firstTime = 1;

    }//initialize

    void alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje de BACE");
        alert.setContentText(message);
        alert.show();
    }//alert

    FileChooser fileChooser;
    File fileImage;

    @FXML void actionImagen(ActionEvent event) {

        fileImage = null;
        fileChooser = new FileChooser();

        try {
            fileImage = fileChooser.showOpenDialog(new Stage());
            //----------------------------------------------------------------Aqui se convierte un file a imagen
            //----------------------------------------------------------------Tambien la imagen que proviene de
            //----------------------------------------------------------------swing la convierte a FX
            imagenNino.setImage(SwingFXUtils.toFXImage(ImageIO.read(fileImage),null));
        }//try
        catch (Exception e){
            e.printStackTrace();
            alert("Error, no es una imagen");
            fileImage = null;
        }//Catch

    }

    @FXML void siguienteAction(ActionEvent event) {

        guardarDatos();

        if(textFieldNombre.getText().equals("")
                || comboBoxSexo.getSelectionModel().isEmpty()
                || Data.fechaEgreso.equals("")
                || Data.fechaIngreso.equals("")
                || Data.fechaNacimiento.equals("")){
            alert("El nombre,sexo,ingreso y egreso deben ser especificados");
        }//if
        else {
            changeScreen("agregar2");
        }//else

    }

    @FXML void siguienteEntered(MouseEvent event) {

    }

    @FXML void siguienteExited(MouseEvent event) {

    }

    void changeScreen(String fxml){
        try{
            Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/"+fxml+".fxml")),Main.stage.getWidth(),Main.stage.getHeight()));
        }//try
        catch (Exception ignored){
            System.out.println("Error en las pantallas");
        }//catch
    }//changeScreen

    void guardarDatos(){

        Data.nombre = textFieldNombre.getText();
        Data.apellidoM = textFieldApellidoM.getText();
        Data.apellidoP = textFieldApellidoP.getText();

        if(!(datePickerFechaNacimiento.getValue() + "").equals("null"))
            Data.fechaNacimiento = datePickerFechaNacimiento.getValue() + "";

        if(!comboBoxSexo.getSelectionModel().isEmpty())
            Data.sexo = comboBoxSexo.getSelectionModel().getSelectedItem();

        if(!comboBoxEdad.getSelectionModel().isEmpty())
            Data.edad = comboBoxEdad.getSelectionModel().getSelectedIndex();

        if(!comboBoxTallaZapato.getSelectionModel().isEmpty())
            Data.tallaZapato = Double.parseDouble("" + comboBoxTallaZapato.getSelectionModel().getSelectedItem());

        Data.tallaPantalon = textFieldTallaPantalon.getText();

        if(!comboBoxTallaCamisa.getSelectionModel().isEmpty())
            Data.tallaCamisa = comboBoxTallaCamisa.getSelectionModel().getSelectedItem();

        if(!textFieldEstatura.getText().equals(""))

        Data.estatura = textFieldEstatura.getText();

        Data.peso = textFieldPeso.getText();

        Data.colorPiel = textFieldColorPiel.getText();
        Data.complexion = textFieldComplexion.getText();
        Data.colorOjos = textFieldColorOjos.getText();
        Data.colorCabello = textFieldColorCabello.getText();
        Data.nariz = textFieldNariz.getText();
        Data.boca = textFieldBoca.getText();

        if(!(datePickerFechaNacimiento.getValue() + "").equals("null"))
            Data.fechaIngreso = datePickerFechaIngreso.getValue() + "";

        if(!(datePickerFechaEgreso.getValue() + "").equals("null"))
            Data.fechaEgreso = datePickerFechaEgreso.getValue() + "";

        Data.lugarOrigen = textFieldLugarOrigen.getText();
        Data.nombreMama = textFieldNombreMama.getText();
        Data.nombrePapa = textFieldNombrePapa.getText();
        Data.lugarNacimiento = textFieldLugarNacimiento.getText();
        Data.integracionFamiliar = textAreaIntegracionFamiliar.getText();
        Data.pasatiempos = textAreaPasatiempos.getText();
        Data.coloresFavoritos = textAreaColoresFavoritos.getText();
        Data.gradoEscolar = textFieldGradoEscolar.getText();

        Data.imagen = imagenNino.getImage();
        Data.fileImagen = fileImage;

    }//guardarDatos

    void ingresarDatos(){

        textFieldNombre.setText(Data.nombre);
        textFieldApellidoM.setText(Data.apellidoM);
        textFieldApellidoP.setText(Data.apellidoP);
        System.out.println(Data.fechaNacimiento);

        if(!Data.fechaNacimiento.equals(""))
            datePickerFechaNacimiento.setValue(LocalDate.parse(Data.fechaNacimiento));

        if(!Data.sexo.equals(""))
            comboBoxSexo.getSelectionModel().select(Data.sexo);

        if(Data.edad != 100)
            comboBoxEdad.getSelectionModel().select(Data.edad);

        if(Data.tallaZapato != 100)
           comboBoxTallaZapato.getSelectionModel().select(Data.tallaZapato);

        textFieldTallaPantalon.setText(Data.tallaPantalon);

        if(!Data.tallaCamisa.equals(""))
            comboBoxTallaCamisa.getSelectionModel().select(Data.tallaCamisa);

        textFieldEstatura.setText(Data.estatura);
        textFieldPeso.setText(Data.peso);

        textFieldColorPiel.setText(Data.colorPiel);
        textFieldComplexion.setText(Data.complexion);
        textFieldColorOjos.setText(Data.colorOjos);
        textFieldColorCabello.setText(Data.colorCabello);
        textFieldNariz.setText(Data.nariz);
        textFieldBoca.setText(Data.boca);

        if(!Data.fechaIngreso.equals(""))
            datePickerFechaIngreso.setValue(LocalDate.parse(Data.fechaIngreso));

        if(!Data.fechaEgreso.equals(""))
            datePickerFechaEgreso.setValue(LocalDate.parse(Data.fechaEgreso));

        textFieldLugarOrigen.setText(Data.lugarOrigen);
        textFieldNombreMama.setText(Data.nombreMama);
        textFieldNombrePapa.setText(Data.nombrePapa);
        textFieldLugarNacimiento.setText(Data.lugarNacimiento);
        textAreaIntegracionFamiliar.setText(Data.integracionFamiliar);
        textAreaPasatiempos.setText(Data.pasatiempos);
        textAreaColoresFavoritos.setText(Data.coloresFavoritos);
        textFieldGradoEscolar.setText(Data.gradoEscolar);

        try{
            imagenNino.setImage(Data.imagen);
        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }

    @FXML void atrasAction(){
        guardarDatos();
        changeScreen("menu");
    }//atrasAction

    @FXML void atrasExited(){

    }//atrasExited

    @FXML void atrasEntered(){

    }//atrasEntered

}
