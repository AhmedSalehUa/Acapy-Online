/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author AHMED
 */
public class SplashScreenController implements Initializable {

    @FXML
    private Label num1;
    @FXML
    private Label num;

    
    
    public static Label label;
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label = num ;
       
    }    
    
}
