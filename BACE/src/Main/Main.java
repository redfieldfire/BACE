package Main;

import Conexion.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    public static Stage stage;
    public static Conexion conexion;

    @Override public void start(Stage primaryStage) throws Exception {

        Runtime.getRuntime().exec("c:\\xampp\\xampp_start.exe");

        conexion = new Conexion();

        stage = primaryStage;

        // System.out.println(new FileInputStream("C:\\BACE\\BACE\\BACE\\src\\Resources\\menu.fxml"));
        // URL url = getClass().getResource("../Resources/menu.fxml");

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Resources/menu.fxml"))));
        primaryStage.getIcons().add(new Image(new FileInputStream("C:\\BACE\\Images\\logo.jpeg")));
        primaryStage.show();
        primaryStage.setMaximized(true);

        primaryStage.setOnCloseRequest(event -> {
            try {
                Runtime.getRuntime().exec("c:\\xampp\\xampp_stop.exe");
            }//try
            catch (IOException e) {
                e.printStackTrace();
            }//catch
        });

    }//start

    public static void main(String[] args) {
        launch(args);
    }//main

}//Main.Main
