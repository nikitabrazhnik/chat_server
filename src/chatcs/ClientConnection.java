/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListModel;

/**
 *
 * @author brazhnik
 */
public class ClientConnection implements Runnable {

    private Socket currentSocket;
    private DefaultListModel clientList;
    private String clientIP;
    private JTextArea bufferField;

    public void setClientList(DefaultListModel clientList) {
        this.clientList = clientList;
    }

    public void setBufferField(JTextArea bufferField) {
        this.bufferField = bufferField;
    }

    public void setClientName(String clientIP) {
        this.clientIP = clientIP;
    }

    public void setCurrentSocket(Socket currentSocket) {
        this.currentSocket = currentSocket;
    }

    @Override
    public void run() {

        DataInputStream dis = null;
        try {
            //clientIP = currentSocket.getInetAddress().getHostAddress();
            clientList.addElement(currentSocket);

            dis = new DataInputStream(currentSocket.getInputStream());
            while (true) {
                String s = dis.readUTF();
                bufferField.setText(s);

                if ("exit".equals(s)) {
                    clientList.removeElement(currentSocket);
                    dis.close();
                    return;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dis.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
