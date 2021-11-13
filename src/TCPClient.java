import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class TCPClient extends Thread{

    public Acheteur a;
    public int port;
    public Negociation nego = new Negociation();
    boolean trouve = false;
    int prixC = -1;

    public TCPClient (Acheteur a1, int no_port){
        a = a1;
        port = no_port;
    }
    public void run() {
        try{
            Socket socket=new Socket("127.0.0.1",port);
            DataInputStream inStream=new DataInputStream(socket.getInputStream());
            DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String clientMessage;
            String serverMessage;


            List<String> liste_verif = new ArrayList<String>();
            String budget_min=String.valueOf(a.budgetMin);
            //liste_verif.add(String.valueOf(a.budgetMin));
            liste_verif.add(a.ville_depart);
            liste_verif.add(a.ville_arrivee);
            //liste_verif.add(String.valueOf(a.budgetMax));

            liste_verif.add(a.compagnie_pref);
            liste_verif.add(a.date_depart);
            //liste_verif.add(String.valueOf(a.budgetMin));
            liste_verif.add(String.valueOf(a.budgetMax)+","+String.valueOf(a.budgetMin));
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
            if (serverMessage.equals("La negociation peut commencer!")) {
                //On remplit la classe negociation
                nego.date_debut = new Date();
                nego.id_acheteur = a.id;
                serverMessage= inStream.readUTF();
                nego.id_fournisseur = Integer.parseInt(serverMessage);
                serverMessage= inStream.readUTF();
                nego.service = Integer.parseInt(serverMessage);
                affichage_Negociation_Client(nego);
                outStream.writeUTF(String.valueOf(a.id));
                //Début negociation
                int prix_courant = 0 ;
                while (nego.nb_max_nego >= nego.nb_nego && !trouve) {

                    serverMessage = inStream.readUTF();
                    if (!serverMessage.equals("OK")) {


                        if (!serverMessage.equals("FALSE")) {
                            nego.memoire_vendeur.add(Integer.parseInt(serverMessage));
                            prix_courant = a.negociateAcheteur(nego);
                            nego.memoire_acheteur.add(prix_courant);
                            if (prix_courant == -1) {
                                clientMessage = "STOP";
                            } else if (prix_courant == -2) {
                                clientMessage = "OK";
                                prixC = Integer.parseInt(serverMessage);
                                trouve = true;
                            } else {
                                clientMessage = String.valueOf(prix_courant);
                                prixC = prix_courant;
                            }
                            outStream.writeUTF(clientMessage);
                            System.out.println("Client => " + clientMessage);
                            nego.nb_nego++;
                        }
                        else {
                            prixC=-1;
                            break;
                        }
                    }
                    else {
                        trouve = true;
                    }

                }
                if (trouve) {
                    System.out.println("Client => PRIX CONVENU A " + prix_courant);
                }
            }
            else {
                outStream.writeUTF(String.valueOf(a.id));
            }

            outStream.close();
            outStream.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public boolean Atrouve () {
        return trouve;
    }

    public void affichage_Negociation_Client (Negociation N) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Negociation Acheteur => ID Vol: "+ N.service);
        System.out.println("Negociation Acheteur => Date: "+ dateFormat.format(N.date_debut));
        System.out.println("Negociation Acheteur => ID fournisseur: "+ N.id_fournisseur);
        System.out.println("Negociation Acheteur => Liste fournisseur: "+ N.memoire_vendeur);
        System.out.println("Negociation Acheteur => ID acheteur: "+ N.id_acheteur);
        System.out.println("Negociation Acheteur => Liste acheteur: "+ N.memoire_acheteur);
        System.out.println("Negociation Acheteur => Nombre max proposition: "+ N.nb_max_nego);
        System.out.println("Negociation Acheteur => Nombre proposition: "+ N.nb_nego);
    }

    public int PrixConvenu (){
        return prixC;
    }
}