package apiBasNiveau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {
   static final int port = 2345;

   public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", port);
        System.out.println("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

        String str = "Bonjour je suis le client 2";
        for (int i = 0; i < 1000000; i++) {
           pred.println(str);          // envoi d'un message
        }
        System.out.println("END");     // message de terminaison
        pred.println("END") ;
        plec.close();
        pred.close();
        socket.close();
   }
}