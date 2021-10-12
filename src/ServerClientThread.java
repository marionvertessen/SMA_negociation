import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    fournisseur f;
    Negociation n;

    ServerClientThread(Socket inSocket,int counter, fournisseur f1, Negociation n1){
        serverClient = inSocket;
        clientNo=counter;
        f = f1;
        n=n1;
    }
    public void run(){
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
            vol vol_ok;
            for (vol vol : list) {
                List<String> liste_verif_s = new ArrayList<String>();
                liste_verif_s.add(vol.ville_depart);
                liste_verif_s.add(vol.ville_arrivee);
                liste_verif_s.add(vol.compagnie);
                liste_verif_s.add(vol.depart);
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
            }
            System.out.println("Serveur => " + serverMessage);
            outStream.writeUTF(serverMessage);
            outStream.flush();


            sleep(4000);

            inStream.close();
            outStream.close();
            serverClient.close();

        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}