/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acapy.online;

import assets.classes.statics;
import com.sun.javafx.application.LauncherImpl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import oldUpdater.go;
import splash.SplashScreenLoader;

/**
 *
 * @author AHMED
 */
public class AcapyOnline extends Application {

    Preferences prefs;
    int state = 1;

    @Override
    public void start(Stage stage) throws Exception {
        if (state == 1) {
            prefs = Preferences.userNodeForPackage(AcapyOnline.class);
            String propertyValue = prefs.get(statics.THEME, statics.DEFAULT_THEME);
            Parent root = FXMLLoader.load(getClass().getResource("/NewDesign/MainScreen.fxml"));

            Scene scene = new Scene(root);

            root.getStylesheets().add(getClass().getResource("/assets/styles/" + propertyValue + ".css").toExternalForm());

            stage.setTitle("Acapy Online");
            stage.setScene(scene);
            stage.toFront();
            stage.show();
        } else {
        }

    }

    @Override
    public void init() throws Exception {
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0));
        boolean isConnected = checkNetworkConnection();
        Thread.sleep(1000);
        if (isConnected) {
            boolean hasUpdate = checkForOnlineUpdate();
            if (hasUpdate) {
                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(1));
                Thread.sleep(1000);
            }
        } else {
        }

        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(2));
        Thread.sleep(1000);
        boolean localCoonect = go.canCoonect();
        if (localCoonect) {
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(3));
            Thread.sleep(1000);
        } else {

        }

        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(4));
        /**
         * check network Connection check online Api For Updates check localdb
         * Connection check for updates
         *
         */

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(AcapyOnline.class, SplashScreenLoader.class, args);
    }

    private boolean checkNetworkConnection() {

        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }

    }

    private boolean checkForOnlineUpdate() {
       return false;
    }

}
