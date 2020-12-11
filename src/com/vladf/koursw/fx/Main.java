package com.vladf.koursw.fx;
import com.vladf.koursw.fx.windows.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Thread emuThread;
    public static Controller controller;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("windows/main.fxml"));
        Parent root = loader.load();
        controller =  loader.getController();
        emuThread = new Thread(new TLauncher());


        stage.setTitle("VTaskViewer");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();

    }


    @Override
    public void stop() throws Exception {
        super.stop();
        if(emuThread.isAlive())
            emuThread.stop();
    }

    }
