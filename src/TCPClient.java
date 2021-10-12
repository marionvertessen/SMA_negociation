import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TCPClient extends Thread {

    public acheteur a;
    public volatile Negociation n = new Negociation();

    public TCPClient (acheteur a1, Negociation n1){
        a = a1;
    }
    public void run() {
        try{
            Socket socket=new Socket("127.0.0.1",8888);
            DataInputStream inStream=new DataInputStream(socket.getInputStream());
            DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String clientMessage;
            String serverMessage;


           sleep(1000);
           List<String> liste_verif = new ArrayList<String>();
           liste_verif.add(a.ville_depart);
           liste_verif.add(a.ville_arrivee);
           //liste_verif.add(String.valueOf(a.budgetMax));
           //liste_verif.add(String.valueOf(a.budgetMin));
           liste_verif.add(a.compagnie_pref);
           liste_verif.add(a.date_depart);
           liste_verif.add(String.valueOf(a.budgetMax));
           int compteur = 0;

           //On envoie les differentes infos permettant si la negociation peut commencer
            while (compteur< liste_verif.size()) {
                clientMessage = ""+liste_verif.get(compteur);
                System.out.println("Client =>" + clientMessage);
                outStream.writeUTF(clientMessage);
                outStream.flush();
                compteur++;
            }
            serverMessage= inStream.readUTF();
            if (serverMessage == "La negociation peut commencer!") {
                n.a = a;
                System.out.println();
            }

            outStream.close();
            outStream.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}