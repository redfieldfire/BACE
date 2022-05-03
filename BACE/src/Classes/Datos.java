package Classes;

import Data.Data;
import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Datos {

    @FXML private Text textBoca;

    @FXML private Text textColorCabello;

    @FXML private Text textColorOjos;

    @FXML private Text textColorPiel;

    @FXML private Text textColoresFavoritos;

    @FXML private Text textComplexion;

    @FXML private Text textEdad;

    @FXML private Text textEstatura;

    @FXML private Text textFechaEgreso;

    @FXML private Text textFechaIngreso;

    @FXML private Text textFechaNacimiento;

    @FXML private Text textGradoEscolar;

    @FXML private Text textIntegracionFamiliar;

    @FXML private Text textLugarNacimiento;

    @FXML private Text textLugarOrigen;

    @FXML private Text textNariz;

    @FXML private Text textNombreMama;

    @FXML private Text textNombrePapa;

    @FXML private Text textPasatiempos;

    @FXML private Text textPeso;

    @FXML private Text textSexo;

    @FXML private Text textStatus;

    @FXML private Text textTallaCamisa;

    @FXML private Text textTallaPantalon;

    @FXML private Text textTallaZapato;

    ResultSet resultSet;

    @FXML void initialize(){

        resultSet = Main.conexion.consultar("SELECT * FROM datos WHERE ID_NIÑO = '" + Data.idNinoD + "';");

        try {

            if(resultSet.next()){

                textEdad.setText("" + resultSet.getObject("EDAD"));
                textSexo.setText("" + resultSet.getObject("SEXO"));
                textTallaZapato.setText("" + resultSet.getObject("TALLA_ZAPATO"));
                textTallaPantalon.setText("" + resultSet.getObject("TALLA_PANTALON"));
                textTallaCamisa.setText("" + resultSet.getObject("TALLA_CAMISA"));
                textColoresFavoritos.setText("" + resultSet.getObject("COLORES_FAVORITOS"));
                textPasatiempos.setText("" + resultSet.getObject("PASATIEMPOS"));
                textFechaIngreso.setText("" + resultSet.getObject("FECHA_INGRESO"));
                textFechaEgreso.setText("" + resultSet.getObject("FECHA_EGRESO"));
                textLugarOrigen.setText("" + resultSet.getObject("LUGAR_ORIGEN"));
                textLugarNacimiento.setText("" + resultSet.getObject("LUGAR_NACIMIENTO"));
                textNombreMama.setText("" + resultSet.getObject("NOMBRE_MAMA"));
                textNombrePapa.setText("" + resultSet.getObject("NOMBRE_PAPA"));
                textGradoEscolar.setText("" + resultSet.getObject("GRADO_ESCOLAR"));
                textEstatura.setText("" + resultSet.getObject("ESTATURA"));
                textPeso.setText("" + resultSet.getObject("PESO"));
                textColorPiel.setText("" + resultSet.getObject("COLOR_PIEL"));
                textComplexion.setText("" + resultSet.getObject("COMPLEXION"));
                textColorCabello.setText("" + resultSet.getObject("COLOR_CABELLO"));
                textColorOjos.setText("" + resultSet.getObject("COLOR_OJOS"));
                textNariz.setText("" + resultSet.getObject("NARIZ"));
                textBoca.setText("" + resultSet.getObject("BOCA"));
                textIntegracionFamiliar.setText("" + resultSet.getObject("INTEGRACION_FAMILIAR"));
                if(textFechaIngreso.getText().equals(textFechaEgreso.getText()))
                {
                    textStatus.setText("En la institución");
                    textStatus.setFill(Color.GREEN);
                }
                else
                {
                    textStatus.setText("Fuera de la institución");
                    textStatus.setFill(Color.RED);
                }

            }//if

        }//try
        catch (SQLException e) {
            e.printStackTrace();
        }//catch

    }//initialize

}//Datos
