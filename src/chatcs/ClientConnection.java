/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcs;

import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 *
 * @author brazhnik
 */
public class ClientConnection implements Runnable {

    private Socket currentSocket;
    private DefaultListModel clientList;
    private String clientIP;

    public void setClientList(DefaultListModel clientList) {
        this.clientList = clientList;
    }

    public void setClientName(String clientIP) {
        this.clientIP = clientIP;
    }

    public void setCurrentSocket(Socket currentSocket) {
        this.currentSocket = currentSocket;
    }

    @Override
    public void run() {
        
      clientIP = currentSocket.getInetAddress().getHostAddress();
      clientList.addElement(clientIP);
    
        while (true) {            
            
        }
    
    
    }

}
