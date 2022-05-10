package Data;

import Formatos.FormatoBlob;
import Formatos.FormatoDocumento;
import Formatos.FormatoNota;
import javafx.scene.image.Image;

import java.io.File;
import java.util.LinkedList;

public class Data {

    //----------First time
    public static int firstTime = 0;

    public static String action = "";

    //Almacenar datos temporales de los campos modificar o agregar

    public static String idNino = "";
    public static String nombre = "";
    public static String apellidoM = "";
    public static String apellidoP = "";
    public static String fechaNacimiento = "";
    public static String sexo = "";
    public static int edad = 100;
    public static double tallaZapato = 100;
    public static String tallaPantalon = "";
    public static String tallaCamisa = "";
    public static String estatura = "";
    public static String peso = "";
    public static String colorPiel = "";
    public static String complexion = "";
    public static String colorOjos = "";
    public static String colorCabello = "";
    public static String nariz = "";
    public static String boca = "";
    public static String fechaIngreso = "";
    public static String fechaEgreso = "";
    public static String lugarOrigen = "";
    public static String lugarNacimiento = "";
    public static String nombreMama = "";
    public static String nombrePapa = "";
    public static String integracionFamiliar = "";
    public static String pasatiempos = "";
    public static String coloresFavoritos = "";

    public static String gradoEscolar = "";

    public static File fileImagen = null;
    public static Image imagen = null;

    //---------------------------------------IdDATO

    public static String idDato = "";

    //-------------------------------------Datos nino

    public static String nombreNinoD = "";
    public static String apellidoMD = "";
    public static String apellidoPD = "";
    public static String idNinoD = "";
    public static Image imagenNinoD;

    //----------------------------------------------Notas

    public static String tituloNota = "";
    public static String nota = "";

    //----------------------------------------------Documentos

    public static File file = null;
    public static String nombreDocumento = "";
    public static String apartado = "";

    //------------------------------------------------Agregar

    public static LinkedList<FormatoDocumento> documentos = new LinkedList<>();
    public static LinkedList<FormatoNota> notas = new LinkedList<>();

    public static LinkedList<FormatoBlob> blobsDocumentos = new LinkedList<>();

    //-------------------------------------------------Cantidad de archivos temporales

    public static int cantidadArchivosTemporales = 0;

    //-------------------------------------------------Modificar las notas

    public static String idNotaModificar = "";
    public static String tituloNotaModificar = "";
    public static String notaTextoModificar = "";

    //-------------------------------------------------Editar Documento

    public static String idDocumentoModificar = "";


}//Data

