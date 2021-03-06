import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fournisseur {
    public int id;
    String name ;
    List<Vol> vols = new ArrayList<Vol>();

    public List<Vol> load_vols () {
        ListVol liste_vol = new ListVol();
        List<Vol> liste_tot_vol = liste_vol.load_vol();
        for (int i=0; i< liste_tot_vol.size(); i++) {
            if (Objects.equals(liste_tot_vol.get(i).compagnie, name)) {
                vols.add(liste_tot_vol.get(i));
            }
        }
        return vols;
    }
    public int negociateFournisseur(Negociation n, Vol v) {
        int prix_prop ;
        int prix_prop1 ;
        System.out.println(n.memoire_acheteur);
        //Si aucune proposion faite ! Le fournisseur commence !
        if (n.nb_nego==0) {
            prix_prop = v.prix;
        }
        else {

            if(n.memoire_acheteur.get(n.memoire_acheteur.size()-1)>= v.prix_min){
                //prix_prop = n.memoire_vendeur.get(n.memoire_vendeur.size()-1);
                prix_prop = -2;
            }
            else {
                float nb_alea = (float) Math.random() /10;
                prix_prop1 = (int) (n.memoire_vendeur.get(n.memoire_vendeur.size()-1)  / (1+nb_alea));
                if (prix_prop1 <= v.prix_min){
                    prix_prop = v.prix_min;
                }else{
                    prix_prop = (int) (n.memoire_vendeur.get(n.memoire_vendeur.size()-1)  / (1+nb_alea));

                }
            }
        }
        return prix_prop;
    }

    public Fournisseur(int id, String nom) {
        this.id=id;
        this.name = nom;
    }
}