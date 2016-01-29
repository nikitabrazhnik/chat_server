/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

/**
 *
 * @author brazhnik
 */
public class SendMessage implements Runnable {

    private JTextArea bufferText;
    private DefaultListModel clientList;
    private DataOutputStream dos;

    public void setBufferText(JTextArea bufferText) {
        this.bufferText = bufferText;
    }

    public void setClientList(DefaultListModel clientList) {
        this.clientList = clientList;
    }

    @Override
    public void run() {

        while (true) {
            //System.out.println(Integer.toString(clientList.getSize()));
            if (!"".equals(bufferText.getText())) {

                for (int i = 0; i < clientList.getSize(); i++) {
                    try {
                        Socket curSocket = (Socket) clientList.elementAt(i);
                        dos = new DataOutputStream(curSocket.getOutputStream());
                        dos.writeUTF(bufferText.getText());
                        //dos.flush();
                        //dos.close();

                    } catch (IOException ex) {
                        Logger.getLogger(ServerJob.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bufferText.setText("");
            }

        }
    }

}
