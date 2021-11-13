import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        //Test de satisfaction
        //test();

        //On teste d'abord les compagnies préférées
        for (Acheteur a : listA) {
            int compt = -1;
            boolean trouvee = false;
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
                trouvee = client.Atrouve();
            }
            if (!trouvee) {
                int compteur =0;
                while (!trouvee && compteur<listF.size()) {
                //for (int i=0; i<listF.size();i++){
                    if (!a.compagnie_pref.equals(listF.get(compteur).name)) {
                        TCPClient client = new TCPClient(a, compteur+1);
                        client.start();
                        sleep(2000);
                        trouvee = client.Atrouve();
                    }
                    compteur++;
                }
            }
        }
    }

    public static List<Acheteur> createAcheteur () {
        List<Acheteur> list = new ArrayList<>();
        //Acheteur a1 = new Acheteur(1, 400, 500, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        Acheteur a1 = new Acheteur(1, 50, 880, 2021, 9, 20, "20/09/2021", "Benin", "Paris", "Delta Air Lines");
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

    private static float SatisfactionClient (int prix_min, int prix_max, int prix_final){
        float res = ((float)prix_max-(float)prix_final)/ ((float)prix_max-(float)prix_min) ;
        return res;
    }
    private  static float SatisfactionFournisseur (int prix_min, int prix_max, int prix_final) {
        float res = ((float) prix_final - (float) prix_min) /((float) prix_max - (float) prix_min);
        return res;
    }
    private static void test () {
        Fournisseur f = new Fournisseur(0, "Benin Golf Air");
        MultithreadedSocketServer multi = new MultithreadedSocketServer(f,1);
        multi.start();
        Vol vol_t = new Vol();
        for (Vol vol :f.load_vols()) {
            if (Objects.equals(vol.ville_arrivee, "Benin") && Objects.equals(vol.ville_depart, "Paris") && Objects.equals(vol.depart, "20/09/2021")){
                vol_t =vol;
            }
        }

        int i=10; //On commence avec un prix minimal de 10
        try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
            try (PrintWriter writer2 = new PrintWriter(new File ("test2.csv"))) {
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                while (i < 865) {
                    Acheteur a1 = new Acheteur(1, i, 1000, 2021, 9, 20, "20/09/2021", "Benin", "Paris", "AirFrance");
                    TCPClient client = new TCPClient(a1, 1);
                    client.start();
                    int prix_trouve = client.PrixConvenu();
                    float pourcentage_client = 0;
                    float pourcentage_fournisseur = 0;
                    if (prix_trouve != -1) {
                        pourcentage_client = SatisfactionClient(a1.budgetMin, a1.budgetMax, prix_trouve);
                        pourcentage_fournisseur = SatisfactionFournisseur(vol_t.prix_min, vol_t.prix, prix_trouve);
                    } else {
                        pourcentage_client = 0.0F;
                        pourcentage_fournisseur = 0.0F;
                    }
                    sb.append(i);
                    sb.append(';');
                    sb.append(pourcentage_client);
                    sb.append('\n');
                    sb2.append(i);
                    sb2.append(";");
                    sb2.append(pourcentage_fournisseur);
                    sb2.append('\n');
                    i = i + 10;
                }
                writer.write(sb.toString());
                writer.close();
                System.out.println("done!");
                writer2.write(sb2.toString());
                writer2.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

