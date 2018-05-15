package com.github.andreasbrown.dnsearch;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();


        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add((getClass().getResource("style.css")).toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.setTitle("VisualDNSearch");
        primaryStage.setMinHeight(480);
        primaryStage.setMinWidth(640);

        primaryStage.getIcons().add(new Image("com/github/andreasbrown/dnsearch/icons/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
