package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static Stage stage;

    @Override public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Resources/menu.fxml")))));
        primaryStage.getIcons().add(new Image("Images/logo.jpeg"));
        primaryStage.show();
        primaryStage.setMaximized(true);

    }//start

    public static void main(String[] args) {
        launch(args);
    }//main

}//Main.Main
