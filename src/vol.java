import java.util.Calendar;
import java.util.Date;

public class vol {
    //Identifiant de vol unique !!!
    public int id;
    public String ville_arrivee;
    public String ville_depart;
    public int prix;
    public int prix_min;
    public String compagnie;
    public String depart;
    public String dAchat;
    public
    vol (int identifiant, String ville_a, String ville_d, int prixx, int prix_m, String name, String dep, String arr){
        id = identifiant;
        ville_arrivee = ville_a;
        ville_depart = ville_d;
        prix = prixx;
        prix_min = prix_m;
        compagnie=name;
        depart = dep;
        dAchat= arr;
    }
    vol () {
    }


}
