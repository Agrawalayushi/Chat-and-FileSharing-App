/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchat.networking;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author krithika
 */
public class MessageTransmitter extends Thread {

    String message, hostname;
    int port;
    WritableGUI gui;
    
    public MessageTransmitter() {
    }

    public MessageTransmitter(WritableGUI gui,String message, String hostname, int port) {
        this.message = message;
        this.hostname = hostname;
        this.port = port;
        this.gui=gui;
    }
    @Override
    public void run() {
        try {
           
            Socket s = new Socket(hostname, port);
            s.getOutputStream().write(message.getBytes());
            gui.write(port, message);
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    
