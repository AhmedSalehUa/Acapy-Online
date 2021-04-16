/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewDesign;

import assets.classes.Shell32X;
import com.jfoenix.controls.JFXProgressBar;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Kernel32Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author AHMED
 */
public class MainScreenController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label size;
    @FXML
    private JFXProgressBar statue;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setFileInfo(String fileName, int contentLength) {
        name.setText(fileName);
        size.setText(Integer.toString(contentLength));
    }

    public void setDownloadSource() {
    }

    public void setProgress(double i) {
        statue.setProgress(i);
    }
    
    public static void executeAsAdministrator(String command, String args) {
        Shell32X.SHELLEXECUTEINFO execInfo = new Shell32X.SHELLEXECUTEINFO();
        execInfo.lpFile = new WString(command);
        if (args != null) {
            execInfo.lpParameters = new WString(args);
        }
        execInfo.nShow = Shell32X.SW_SHOWDEFAULT;
        execInfo.fMask = Shell32X.SEE_MASK_NOCLOSEPROCESS;
        execInfo.lpVerb = new WString("runas");
        boolean result = Shell32X.INSTANCE.ShellExecuteEx(execInfo);

        if (!result) {
            int lastError = Kernel32.INSTANCE.GetLastError();
            String errorMessage = Kernel32Util.formatMessageFromLastErrorCode(lastError);
            throw new RuntimeException("Error performing elevation: " + lastError + ": " + errorMessage + " (apperror=" + execInfo.hInstApp + ")");
        }
    }
}
