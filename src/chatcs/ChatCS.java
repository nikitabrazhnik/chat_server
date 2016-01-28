/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brazhnik
 */
public class ChatCS {

    final static int SERVER_PORT = 6000;
    final static String SERVER_ADDRESS = "127.0.0.1";

    StatusType CurrentStatus;

    private boolean isServerExist() {
        Socket cs;
        try {
            cs = new Socket(SERVER_ADDRESS, SERVER_PORT);
            DataOutputStream dataOS = new DataOutputStream(cs.getOutputStream());
            dataOS.writeBytes("_isServer");
            dataOS.flush();
            dataOS.close();

        } catch (IOException ex) {
            return false;
        }

        return true;
    }

    enum StatusType {
        SERVER, CLIENT
    }

    public static void main(String[] args) {

        //  CurrentStatus = (isServerExist() == true) ? StatusType.CLIENT : StatusType.SERVER;
        //checking is there a server in network at moment
        MainFrameServer mfs = new MainFrameServer();
        mfs.setVisible(true);

        ServerJob sj = new ServerJob();
        sj.setBufferText(mfs.getBufferField());
        sj.setClientList(mfs.getClientList());
        sj.setPortNumber(SERVER_PORT);
        
        Thread threadServer = new Thread(sj);
        threadServer.start();
    }

}
