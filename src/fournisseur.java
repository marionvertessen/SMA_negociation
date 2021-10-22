import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class fournisseur extends agent{
    String name ;
    List<vol> vols = new ArrayList<vol>();

    public List<vol> load_vols () {
        listeVol liste_vol = new listeVol();
        List<vol> liste_tot_vol = liste_vol.load_vol();
        for (int i=0; i< liste_tot_vol.size(); i++) {
            if (Objects.equals(liste_tot_vol.get(i).compagnie, name)) {
                vols.add(liste_tot_vol.get(i));
            }
        }
        return vols;
    }
    public int negociate_f (Negociation n, vol v) {
        int prix_prop ;
        System.out.println(n.memoire_acheteur);
        //Si aucune proposion faite ! Le fournisseur commence !
        if (n.nb_nego==0) {
            prix_prop = v.prix;
        }
        else {

            if(n.memoire_acheteur.get(n.memoire_acheteur.size()-1)> v.prix_min){
                //prix_prop = n.memoire_vendeur.get(n.memoire_vendeur.size()-1);
                prix_prop = -2;
            }
            else {
                float nb_alea = (float) Math.random() /10;
                prix_prop = (int) (n.memoire_vendeur.get(n.memoire_vendeur.size()-1)  / (1+nb_alea));
            }
        }
        return prix_prop;
    }

    public fournisseur(int id, String nom) {
        super(id);
        name = nom;
    }




}
