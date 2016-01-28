/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcs;

import com.sun.corba.se.impl.orbutil.ORBConstants;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

/**
 *
 * @author brazhnik
 */
public class ServerJob implements Runnable{
  private JTextArea bufferText;
  private DefaultListModel clientList;
  private int portNumber;
  private ServerSocket serverSocket;    
  

  @Override
    public void run() {
       

       try {
          serverSocket = new ServerSocket(portNumber);
      } catch (IOException ex) {
          Logger.getLogger(ServerJob.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        while (true) {          
           try {
               Socket ss = serverSocket.accept();
               ClientConnection cc = new ClientConnection();
                       cc.setCurrentSocket(ss);
                       cc.setClientList(clientList);
                       Thread clientConection = new Thread(cc);
                       clientConection.start();
           } catch (IOException ex) {
               Logger.getLogger(ServerJob.class.getName()).log(Level.SEVERE, null, ex);
           }
      }
        
        
        
//        
//        DataInputStream dis;
//     
//       
//       
//       
//       
//       
//      try {
//          
//          dis = new DataInputStream(ss.getInputStream());
//          
//      } catch (IOException ex) {
//          Logger.getLogger(ServerJob.class.getName()).log(Level.SEVERE, null, ex);
//          return;
//      }
//        
//        
//        while (true) {
//           try {
//               bufferText.setText(dis.readUTF());
//           } catch (IOException ex) {
//               Logger.getLogger(ServerJob.class.getName()).log(Level.SEVERE, null, ex);
//           }
//        }
        
    }

    public void setBufferText(JTextArea bufferText) {
        this.bufferText = bufferText;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public void setClientList(DefaultListModel clientList) {
        this.clientList = clientList;
    }
  
    
    
    
    
}
