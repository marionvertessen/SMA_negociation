import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

class ServerClientThread extends Thread {
    Socket serverClient;

    int clientNo, noPropServ=0, noPropClient=1;
    fournisseur f;
    Negociation nego= new Negociation();
    String d_d, d_a, comp, dep, serv_mess_string, client_mess_string;
    DefaultListModel model = new DefaultListModel();
    DefaultListModel model_client = new DefaultListModel();

    ServerClientThread(Socket inSocket,int counter, fournisseur f1){
        serverClient = inSocket;
        clientNo=counter;
        f = f1;
    }
    public void run(){
        NewClient client = new NewClient();
        List<vol> list = f.load_vols();
        System.out.println("Serveur => " + list);
        try{
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage, serverMessage="";
            List<String> list_contrainte = new ArrayList<String>();
            for (int i=0; i<5;i++) {
                clientMessage = inStream.readUTF();
                list_contrainte.add(clientMessage);
            }

            boolean estOkTot = false;
            vol vol_ok = new vol();
            for (vol vol : list) {
                List<String> liste_verif_s = new ArrayList<String>();
                liste_verif_s.add(vol.ville_depart);
                d_d = vol.ville_depart;
                liste_verif_s.add(vol.ville_arrivee);
                d_a = vol.ville_arrivee;
                liste_verif_s.add(vol.compagnie);
                comp = vol.compagnie;
                liste_verif_s.add(vol.depart);
                dep = String.valueOf(vol.depart);
                liste_verif_s.add(String.valueOf(vol.prix_min));
                boolean estOk = true;
                for (int i = 0; i < list_contrainte.size()-1; i++) {
                    if (!Objects.equals(liste_verif_s.get(i), list_contrainte.get(i))) {
                        estOk = false;
                    }
                }
                if (Integer.parseInt(liste_verif_s.get(list_contrainte.size()-1)) >  Integer.parseInt(list_contrainte.get(list_contrainte.size()-1))) {
                    estOk = false;
                }
                if (estOk) {
                    estOkTot = true;
                    vol_ok = vol;
                }
            }
            if (estOkTot) {
                serverMessage = "La negociation peut commencer!";
            }
            else {
                serverMessage = "Aucune offre n'est disponible !";
                serv_mess_string = "Aucune offre n'est disponible !";
                model.addElement(serv_mess_string);
                client.getparam1(model);
            }
            System.out.println("Serveur => " + serverMessage);
            outStream.writeUTF(serverMessage);
            outStream.flush();
            outStream.writeUTF(String.valueOf(f.id));
            outStream.writeUTF(String.valueOf(vol_ok.id));

            client.setVisible(true);
            client.getparam(d_d, d_a, comp, dep, f.name);
            System.out.println(f.name);
            nego.date_debut = new Date();
            nego.id_fournisseur =f.id;
            clientMessage = inStream.readUTF();
            nego.id_acheteur = Integer.parseInt(clientMessage);
            nego.service = vol_ok.id;
            sleep(1000);
            affichage_Negociation_Serveur(nego);
            sleep(4000);
            boolean trouve = false;
            int prix_courant = 0 ;
            //On propose le 1er prix
            while (nego.nb_max_nego >= nego.nb_nego && !trouve) {
                int p=0;
                prix_courant = f.negociate_f(nego, vol_ok);
                nego.memoire_vendeur.add(prix_courant);

                if (prix_courant == -2) {
                    serverMessage = "OK";
                    trouve =true;
                    serv_mess_string = "Proposition acceptée";
                    model.addElement(serv_mess_string);
                    client.getparam1(model);

                }
                else {
                    serverMessage = String.valueOf(prix_courant);
                    outStream.writeUTF(serverMessage);
                    System.out.println("Serveur => " + serverMessage);


                    clientMessage = inStream.readUTF();
                    if (nego.nb_max_nego == nego.nb_nego && Integer.parseInt(clientMessage)!=-2){
                        p=1;
                        serv_mess_string = "Proposition refusée";
                        client_mess_string = "";
                        model_client.addElement(client_mess_string);
                        client.getparam3(model_client, p);
                    }else{
                        client_mess_string = String.valueOf(noPropClient) + ". Je propose " + String.valueOf(clientMessage);
                        if ("OK".equals(serverMessage)) {
                            serv_mess_string = "Proposition acceptée";
                            trouve = true;
                        } else {
                            serv_mess_string = String.valueOf(noPropServ) + ". Je propose " + String.valueOf(serverMessage);
                        }

                        model_client.addElement(client_mess_string);
                        client.getparam2(model_client,p);
                    }

                    model.addElement(serv_mess_string);
                    client.getparam1(model);


                    if (!clientMessage.equals("OK")) {
                        nego.memoire_acheteur.add(Integer.valueOf(clientMessage));
                    } else {
                        trouve = true;
                    }
                    nego.nb_nego++;
                    noPropServ = noPropServ + 2;
                    noPropClient = noPropClient + 2;
                }
            }

            sleep(2000);
            inStream.close();
            outStream.close();
            serverClient.close();

        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }

    public void affichage_Negociation_Serveur (Negociation N) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Negociation Fournisseur => ID Vol: "+ N.service);
        System.out.println("Negociation Fournisseur => Date: "+ dateFormat.format(N.date_debut));
        System.out.println("Negociation Fournisseur => ID fournisseur: "+ N.id_fournisseur);
        System.out.println("Negociation Fournisseur => Liste fournisseur: "+ N.memoire_vendeur);
        System.out.println("Negociation Fournisseur => ID acheteur: "+ N.id_acheteur);
        System.out.println("Negociation Fournisseur => Liste acheteur: "+ N.memoire_acheteur);
        System.out.println("Negociation Fournisseur => Nombre max proposition: "+ N.nb_max_nego);
        System.out.println("Negociation Fournisseur => Nombre proposition: "+ N.nb_nego);
    }


}