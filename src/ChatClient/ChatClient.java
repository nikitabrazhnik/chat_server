/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author brazhnik
 */
public class ChatClient {
    
    
    public static void main(String[] args) throws IOException {
        Socket cs = new Socket("127.0.0.1", 6000);
        DataOutputStream dos = new DataOutputStream(cs.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
        while (true) {            
            String readLine = br.readLine();
            System.err.println(readLine);
            dos.writeUTF(readLine);
            
            if ("exit".equals(readLine)) {
                br.close();
                dos.flush();
                dos.close();
                cs.close();
                        }
           
        }
        
    }
    
    
    
    


    
}
