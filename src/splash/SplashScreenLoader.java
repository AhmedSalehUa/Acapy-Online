/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash;

import acapy.online.AcapyOnline;
import assets.classes.alerts;
import assets.classes.statics;
import static assets.classes.statics.*;
import java.util.Optional;
import java.util.prefs.Preferences;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author AHMED
 */
public class SplashScreenLoader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;
    @FXML
    private ProgressBar prog;
    @FXML
    private Label num;
    Preferences prefs;

    public SplashScreenLoader() {

    }

    @Override
    public void init() throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("/splash/SplashScreen.fxml"));
        scene = new Scene(root1);
        prefs = Preferences.userNodeForPackage(AcapyOnline.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.preloaderStage = primaryStage;

        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();

    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {

        if (info instanceof ProgressNotification) {
            String massege = "";
            double progress = ((ProgressNotification) info).getProgress();
            if (progress == 0) {
                massege = "check network Connection ";
            } else if (progress == 1) {
                massege = "check online Api For Updates";
            } else if (progress == 2) {
                massege = "check localdb";

            } else if (progress == 3) {
                massege = "Connection check for updates";
            } else if (progress == 4) {
                massege = "Downloading";
            } else if (progress == 5) {
                massege = "successful";
            }
            SplashScreenController.label.setText(massege);
        }

    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {

        StateChangeNotification.Type type = info.getType();
        switch (type) {

            case BEFORE_START:

                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }

    }

}
