import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Resources/menu.fxml")))));
        primaryStage.getIcons().add(new Image("Images/logo.jpeg"));
        primaryStage.show();

    }//start

    public static void main(String[] args) {
        launch(args);
    }//main

}//Main
