import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TCPClient {


    public static void main(String[] args) throws Exception {
        acheteur a1 = new acheteur(1);
        try{
            Socket socket=new Socket("127.0.0.1",8888);
            DataInputStream inStream=new DataInputStream(socket.getInputStream());
            DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String clientMessage="",serverMessage="";
            System.out.println("Numéro client: "+ a1.id);
            System.out.println("Budget max: "+ a1.budgetMax);
            System.out.println("Budget min: "+ a1.budgetMin);
            System.out.println("Date de depart: "+ a1.jourDepart+"/"+a1.moisDepart+"/"+a1.anneeDepart);
            System.out.println("Ville de depart: " + a1.ville_depart);
            System.out.println("Ville d'arrivee: " + a1.ville_arrivee);

            clientMessage = ""+a1.id;
            outStream.writeUTF(clientMessage);
            outStream.flush();
            serverMessage=inStream.readUTF();
            System.out.println(serverMessage);

            /**clientMessage = ""+a1.budgetMin;
            outStream.writeUTF(clientMessage);
            outStream.flush();
            serverMessage=inStream.readUTF();
            System.out.println(serverMessage);

            clientMessage = ""+a1.budgetMax;
            outStream.writeUTF(clientMessage);
            outStream.flush();
            serverMessage=inStream.readUTF();
            System.out.println(serverMessage);**/


           /** while(!clientMessage.equals("bye")){

                clientMessage=br.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                serverMessage=inStream.readUTF();
                System.out.println(serverMessage);
            }**/
            outStream.close();
            outStream.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}