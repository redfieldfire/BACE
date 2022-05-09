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
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.time.LocalDate;
        import java.time.Month;

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

    @FXML void initialize(){

        //Saltos de linea
        textFieldNombre.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) {
                textFieldApellidoP.requestFocus();
                Data.idNino = textFieldNombre.getText().charAt(0) + "" + comboBoxSexo.getSelectionModel().getSelectedItem() + "" + numeroNinoI;
                textFieldId.setText(Data.idNino);
            }//if
        });
        textFieldApellidoM.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldNombreMama.requestFocus();
        });
        textFieldApellidoP.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.ENTER) textFieldApellidoM.requestFocus();
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
        comboBoxEdad.getSelectionModel().clearSelection();

        comboBoxSexo.getItems().addAll("M","F");
        comboBoxSexo.getSelectionModel().clearSelection();

        comboBoxTallaCamisa.getItems().addAll("XS","S","M","L","XL","XXL","XXL");
        comboBoxTallaCamisa.getSelectionModel().clearSelection();

        for(double x = 0; x < 20; x = x + 0.5) comboBoxTallaZapato.getItems().add(x);
        comboBoxTallaZapato.getSelectionModel().clearSelection();

        if(Data.action.equals("agregar")) {
            calcularNumeroNino();
            if(Data.firstTime == 1) ingresarDatos();
            else Data.firstTime = 1;
        }//if
        else if(Data.action.equals("editar")){
            mostrarDatos();
        }//else editar

    }//initialize

    String fechas[];

    void mostrarDatos(){

        try{

            resultSet = Main.conexion.consultar("SELECT * FROM datos WHERE ID_NIÑO = '"+Data.idNinoD+"';");

            if(resultSet.next()){

                comboBoxEdad.getSelectionModel().select(Integer.parseInt("" + resultSet.getObject("EDAD")));

                comboBoxSexo.getSelectionModel().select("" + resultSet.getObject("SEXO"));

                comboBoxTallaCamisa.getSelectionModel().select("" + resultSet.getObject("TALLA_CAMISA"));

                comboBoxTallaZapato.getSelectionModel().select(Double.parseDouble("" + resultSet.getObject("TALLA_ZAPATO")));

                fechas = ("" + resultSet.getObject("FECHA_EGRESO")).split("-");

                datePickerFechaEgreso.setValue(LocalDate.of(
                        Integer.parseInt(fechas[0]),
                        Month.of(Integer.parseInt(fechas[1])),
                        Integer.parseInt(""+fechas[2])));

                fechas = ("" + resultSet.getObject("FECHA_INGRESO")).split("-");

                datePickerFechaIngreso.setValue(LocalDate.of(
                        Integer.parseInt(fechas[0]),
                        Month.of(Integer.parseInt(fechas[1])),
                        Integer.parseInt(""+fechas[2])));

                textAreaColoresFavoritos.setText("" + resultSet.getObject("COLORES_FAVORITOS"));

                textAreaIntegracionFamiliar.setText("" + resultSet.getObject("INTEGRACION_FAMILIAR"));

                textAreaPasatiempos.setText("" + resultSet.getObject("PASATIEMPOS"));

                textFieldNombreMama.setText("" + resultSet.getObject("NOMBRE_MAMA"));

                textFieldNombrePapa.setText("" + resultSet.getObject("NOMBRE_PAPA"));

                textFieldBoca.setText("" + resultSet.getObject("BOCA"));

                textFieldColorCabello.setText("" + resultSet.getObject("COLOR_CABELLO"));

                textFieldColorOjos.setText("" + resultSet.getObject("COLOR_OJOS"));

                textFieldColorPiel.setText("" + resultSet.getObject("COLOR_PIEL"));

                textFieldComplexion.setText("" + resultSet.getObject("COMPLEXION"));

                textFieldEstatura.setText("" + resultSet.getObject("ESTATURA"));

                textFieldId.setText(Data.idNinoD);

                textFieldLugarNacimiento.setText("" + resultSet.getObject("LUGAR_NACIMIENTO"));

                textFieldLugarOrigen.setText("" + resultSet.getObject("LUGAR_ORIGEN"));

                textFieldNariz.setText("" + resultSet.getObject("NARIZ"));

                textFieldPeso.setText("" + resultSet.getObject("PESO"));

                textFieldTallaPantalon.setText("" + resultSet.getObject("TALLA_PANTALON"));

                textFieldGradoEscolar.setText("" + resultSet.getObject("GRADO_ESCOLAR"));

            }//if

            resultSet = Main.conexion.consultar("SELECT * FROM niños WHERE ID_NIÑO = '"+Data.idNinoD+"';");

            if(resultSet.next()){

                textFieldNombre.setText(Data.nombreNinoD);

                imagenNino.setImage(Data.imagenNinoD);

                fechas = ("" + resultSet.getObject("FECHA_NACIMIENTO")).split("-");

                datePickerFechaNacimiento.setValue(LocalDate.of(
                        Integer.parseInt(fechas[0]),
                        Month.of(Integer.parseInt(fechas[1])),
                        Integer.parseInt(""+fechas[2])));

                textFieldApellidoM.setText("" + resultSet.getObject("APELLIDO_MATERNO"));

                textFieldApellidoP.setText("" + resultSet.getObject("APELLIDO_PATERNO"));

            }//if

        }//try
        catch(Exception e){
            e.printStackTrace();
        }//catch

    }//mostrarDatos

    void calcularNumeroNino(){

        resultSet = Main.conexion.consultar("SELECT COUNT(*) FROM niños;");

        try{
            while (resultSet.next()){
                ultimoId = "" + resultSet.getObject(1);
            }//while

            if(ultimoId.equals("0")) numeroNinoI = 1;
            else{
                for(int x = 0; x < ultimoId.length(); x++){
                    try{
                        numeroNinoS = numeroNinoS + Integer.parseInt("" + ultimoId.charAt(x));
                    }//try
                    catch (Exception e){
                        System.out.println("" + ultimoId.charAt(x));
                    }//catch
                }//for
                numeroNinoI = Integer.parseInt(numeroNinoS) + 1;
            }//else

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

    }//calcularNumeroNino

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
                if(fileImage != null) {
                    imagenNino.setImage(SwingFXUtils.toFXImage(ImageIO.read(fileImage), null));
                }//if la imagen existe
                else{
                    alert("No se seleccionó nada");
                }//else sin seleccion
            }//try
            catch (Exception e) {
                e.printStackTrace();
                alert("Error, no es una imagen");
                fileImage = null;
            }//Catch


    }//actionImagen

    @FXML void siguienteAction(ActionEvent event) {

        guardarDatos();

        if(Data.action.equals("agregar")) {

            if (textFieldNombre.getText().equals("")
                    || comboBoxSexo.getSelectionModel().isEmpty()
                    || Data.fechaEgreso.equals("")
                    || Data.fechaIngreso.equals("")
                    || Data.fechaNacimiento.equals("")) {
                alert("El nombre,sexo,ingreso y egreso deben ser especificados");
            }//if
            else {
                changeScreen("agregar2");
            }//else

        }//agregar action
        else{

            modificar();
            alert("Datos modificados");
            changeScreen("agregar2");

        }//else editar

    }//siguiente action

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

        Data.idNino = textFieldId.getText();

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

        if(!textFieldEstatura.getText().equals("")) Data.estatura = textFieldEstatura.getText();

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

        textFieldId.setText(Data.idNino);

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

    PreparedStatement psModificarDatos = null;

    String sqlModificarDatos = "UPDATE datos SET " +
            "EDAD = ?," +
            "SEXO = ?," +
            "TALLA_ZAPATO = ?," +
            "TALLA_PANTALON = ?," +
            "TALLA_CAMISA = ?," +
            "COLORES_FAVORITOS = ?," +
            "PASATIEMPOS = ?," +
            "FECHA_INGRESO = '"+Data.fechaIngreso+"'," +
            "FECHA_EGRESO = '"+Data.fechaEgreso+"'," +
            "LUGAR_ORIGEN = ?," +
            "LUGAR_NACIMIENTO = ?," +
            "NOMBRE_MAMA = ?," +
            "NOMBRE_PAPA = ?," +
            "GRADO_ESCOLAR = ?," +
            "ESTATURA = ?," +
            "PESO = ?," +
            "COLOR_PIEL = ?," +
            "COMPLEXION = ?," +
            "COLOR_CABELLO = ?," +
            "COLOR_OJOS = ?," +
            "NARIZ = ?," +
            "BOCA = ?," +
            "INTEGRACION_FAMILIAR = ? " +
            "WHERE ID_NIÑO = '"+Data.idNino+"';";

    void modificar(){

        try{

            psModificarDatos = Main.conexion.connection.prepareStatement(sqlModificarDatos);

            psModificarDatos.setInt(1,Data.edad);
            psModificarDatos.setString(2,Data.sexo);
            psModificarDatos.setDouble(3,Data.tallaZapato);
            psModificarDatos.setString(4,Data.tallaPantalon);
            psModificarDatos.setString(5,Data.tallaCamisa);
            psModificarDatos.setString(6,Data.coloresFavoritos);
            psModificarDatos.setString(7,Data.pasatiempos);
            psModificarDatos.setString(8,Data.lugarOrigen);
            psModificarDatos.setString(9,Data.lugarNacimiento);
            psModificarDatos.setString(10,Data.nombreMama);
            psModificarDatos.setString(11,Data.nombrePapa);
            psModificarDatos.setString(12,Data.gradoEscolar);
            psModificarDatos.setString(13,Data.estatura);
            psModificarDatos.setString(14,Data.peso);
            psModificarDatos.setString(15,Data.colorPiel);
            psModificarDatos.setString(16,Data.complexion);
            psModificarDatos.setString(17,Data.colorCabello);
            psModificarDatos.setString(18,Data.colorOjos);
            psModificarDatos.setString(19,Data.nariz);
            psModificarDatos.setString(20,Data.boca);
            psModificarDatos.setString(21,Data.integracionFamiliar);

            psModificarDatos.executeUpdate();

        }//try
        catch(Exception e){
            e.printStackTrace();
        }//catch

    }//modify

    @FXML void atrasAction(){
        if(Data.action.equals("agregar")) guardarDatos();
        changeScreen("menu");
    }//atrasAction

    @FXML void atrasExited(){

    }//atrasExited

    @FXML void atrasEntered(){

    }//atrasEntered

}//Agregar
