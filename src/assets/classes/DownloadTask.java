/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.classes;
import NewDesign.MainScreenController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
 
import javax.swing.JOptionPane;
import javax.swing.SwingWorker; 
 
/**
 * Execute file download in a background thread and update the progress.
 * @author www.codejava.net
 *
 */
public class DownloadTask extends SwingWorker<Void, Void> {
    private static final int BUFFER_SIZE = 4096;   
    private String downloadURL;
    private String saveDirectory;
   MainScreenController controller;
     
    public DownloadTask(MainScreenController controller, String downloadURL, String saveDirectory) {
        this.controller = controller;
        this.downloadURL = downloadURL;
        this.saveDirectory = saveDirectory;
    }
     
    
    
    @Override
    protected Void doInBackground() throws Exception {
        try {
            HTTPDownloadUtil util = new HTTPDownloadUtil();
            util.downloadFile(downloadURL);
             
          
            controller.setFileInfo(util.getFileName(), util.getContentLength()); 
             
            String saveFilePath = saveDirectory + File.separator + util.getFileName().replace("%20"," ");;
 
            InputStream inputStream = util.getInputStream();
          
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            long totalBytesRead = 0;
            int percentCompleted = 0;
            long fileSize = util.getContentLength();
 
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                percentCompleted = (int) (totalBytesRead * 100 / fileSize);
 
                setProgress(percentCompleted);         
            }
 
            outputStream.close();
 
            util.disconnect();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error downloading file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);           
            ex.printStackTrace();
            setProgress(0);
            cancel(true);          
        }
        return null;
    }

    @Override
    protected void process(List<Void> chunks) {
        super.process(chunks); //To change body of generated methods, choose Tools | Templates.
    }
  
    @Override
    protected void done() {
        if (!isCancelled()) {
           
        }
    }  
}
