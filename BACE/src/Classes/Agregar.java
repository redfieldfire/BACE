package Classes;

        import Data.Data;
        import Main.Main;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.MouseEvent;

        import java.time.LocalDate;
        import java.util.Date;

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

    @FXML void initialize(){

        //Saltos de linea
        textFieldNombre.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldApellidoM.requestFocus();
        });
        textFieldApellidoM.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldApellidoP.requestFocus();
        });
        textFieldApellidoP.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) datePickerFechaNacimiento.requestFocus();
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

    @FXML void actionImagen(ActionEvent event) {

    }

    @FXML void siguienteAction(ActionEvent event) {
        guardarDatos();
        changeScreen("agregar2");
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
        Data.lugarNacimiento = textFieldLugarNacimiento.getText();
        Data.integracionFamiliar = textAreaIntegracionFamiliar.getText();
        Data.pasatiempos = textAreaPasatiempos.getText();
        Data.coloresFavoritos = textAreaColoresFavoritos.getText();

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
        textFieldLugarNacimiento.setText(Data.lugarNacimiento);
        textAreaIntegracionFamiliar.setText(Data.integracionFamiliar);
        textAreaPasatiempos.setText(Data.pasatiempos);
        textAreaColoresFavoritos.setText(Data.coloresFavoritos);

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
