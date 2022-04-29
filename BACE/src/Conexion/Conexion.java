package Conexion;

import javax.swing.*;
import java.sql.*;

public class Conexion {

    private String usuario = "root";
    private String password = "";
    private String db = "bd_bace";
    private String servidor = "localhost";

    public Connection connection;

    public Conexion(){

        try{
            start();
        }//try
        catch (Exception e){
            e.printStackTrace();
            System.out.println("No ha sido posible conoectar con la base de datos");
        }//catch

    }// public

    public ResultSet consultar(String sql){

        ResultSet resultSet = null;

        try{

            Statement consulta = connection.createStatement();
            resultSet = consulta.executeQuery(sql);

        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch

        return resultSet;

    }//ResultSet

    public void inmodel(String sql){

        try {
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }//inmodel

    public void close(){

        try {
            connection.close();
        }//try
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }//catch

    }//close

    public void start(){

        try {
            System.out.println("jdbc:mysql://" + servidor + "/" + db + ""+usuario+password);
            connection = DriverManager.getConnection("jdbc:mysql://" + servidor + "/" + db + "",usuario,password);
            System.out.println("Conectado exitosamente");
        }//try
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Todo mal");
          }//catch

    }//close

}// class