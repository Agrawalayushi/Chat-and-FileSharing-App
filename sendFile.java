/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchat.networking;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krithika
 */
public class sendFile extends Thread {

    Socket socket ;
    File file;
public sendFile(File f,int port,String host){
    file=f;
        try {
            socket = new Socket(host,port);
        } catch (IOException ex) {
            Logger.getLogger(sendFile.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @Override
public void run(){
    try {
       File transferFile = new File (file.getAbsolutePath());
       byte [] bytearray = new byte [(int)transferFile.length()];
       FileInputStream fin = new FileInputStream(transferFile);
       BufferedInputStream bin = new BufferedInputStream(fin); 
       bin.read(bytearray,0,bytearray.length); 
       OutputStream os = socket.getOutputStream();
       //System.out.println("Sending Files...");
       os.write(bytearray,0,bytearray.length);
       os.flush();
       socket.close();

    } catch (IOException ex) {
        Logger.getLogger(sendFile.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    
}
    
}
