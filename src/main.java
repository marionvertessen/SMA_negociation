import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class main extends  Thread{

    private List<Acheteur> listA = new ArrayList<Acheteur>();



    public static void main (String[] args) throws Exception {
        ListVol liste_vol = new ListVol();
        List<Vol> liste_tot_vol = liste_vol.load_vol();
        int port1 =111 , port2 =222 ;

        List<Fournisseur> listF = createFournisseur(liste_tot_vol);
        for (Fournisseur f :listF) {
            System.out.println(f.name);
        }
        List<MultithreadedSocketServer> listMulti = creatMultiThread (listF);
        System.out.println(listMulti);
        for (MultithreadedSocketServer multi : listMulti) {
            multi.start();
        }

        List<Acheteur> listA = createAcheteur() ;
        int compt = 111;
        for (Acheteur a : listA) {
            for (int i =0 ; i<listMulti.size(); i++) {
                TCPClient client = new TCPClient(a, compt);
                client.start();
                compt = compt + 111;
            }
            compt = 111;
        }

        //MultithreadedSocketServer multi = new MultithreadedSocketServer(f1, port1);
        //MultithreadedSocketServer multi1 = new MultithreadedSocketServer(f2, port2);

        /**TCPClient client = new TCPClient(a1, port1);
        TCPClient client2 = new TCPClient(a2, port1);
        TCPClient client_port2 = new TCPClient(a1, port2);
        TCPClient client2_port2 = new TCPClient(a2, port2);
        //multi.start();
        //multi1.start();
        sleep (20);
        client.start();
        client2.start();
        client2_port2.start();
        client_port2.start();**/
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
        int compt = 111;
        for (Fournisseur f: listF) {
            MultithreadedSocketServer multi = new MultithreadedSocketServer(f,compt);
            listMulti.add(multi);
            compt = compt + 111;
        }
        return listMulti;
    }

}

