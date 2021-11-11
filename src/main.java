import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class main extends  Thread{

    public static void main (String[] args) throws Exception {
        ListVol liste_vol = new ListVol();
        List<Vol> liste_tot_vol = liste_vol.load_vol();

        List<Fournisseur> listF = createFournisseur(liste_tot_vol);
        for (Fournisseur f :listF) {
            System.out.println(f.name);
        }

        List<MultithreadedSocketServer> listMulti = creatMultiThread (listF);
        System.out.println(listMulti);
        for (MultithreadedSocketServer multi : listMulti) {
            multi.start();
        }
        List<Acheteur> listA = createAcheteur();

        //On teste d'abord les compagnies préférées
        for (Acheteur a : listA) {
            int compt = -1;
            boolean trouve = false;
            for (int i=0; i< listF.size(); i++){
                if (a.compagnie_pref.equals(listF.get(i).name)){
                    compt = i+1;
                    break;
                }
            }
            if (compt != -1){
                TCPClient client = new TCPClient(a, compt);
                client.start();
                sleep(2000);
                trouve = client.Atrouve();
            }
            if (!trouve) {
                for (int i=0; i<listF.size();i++){
                    if (!a.compagnie_pref.equals(listF.get(i).name)) {
                        TCPClient client = new TCPClient(a, i+1);
                        client.start();
                        sleep(2000);
                        trouve = client.Atrouve();
                    }
                }
            }
        }
    }

    public static List<Acheteur> createAcheteur () {
        List<Acheteur> list = new ArrayList<>();
        Acheteur a1 = new Acheteur(1, 400, 500, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        Acheteur a2 = new Acheteur(2, 1350, 1450, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        list.add(a1);
        list.add(a2);
        return  list;
    }

    public static List<Fournisseur> createFournisseur (List<Vol> liste_tot_vol) {
        List<Fournisseur> list = new ArrayList<>();
        int comp =0;
        for (Vol vol : liste_tot_vol) {
            boolean estdeja = false;
            for (Fournisseur fournisseur : list) {
                if (fournisseur.name.equals(vol.compagnie)) {
                    estdeja = true;
                    break;
                }
            }
            if (!estdeja) {
                Fournisseur f = new Fournisseur(comp, vol.compagnie);
                list.add(f);
                comp ++;
            }
        }
        return list;
    }
    public static List<MultithreadedSocketServer> creatMultiThread (List<Fournisseur> listF) {
        List<MultithreadedSocketServer> listMulti = new ArrayList<>();
        int compt = 1;
        for (Fournisseur f: listF) {
            MultithreadedSocketServer multi = new MultithreadedSocketServer(f,compt);
            listMulti.add(multi);
            compt = compt + 1;
        }
        return listMulti;
    }
}

