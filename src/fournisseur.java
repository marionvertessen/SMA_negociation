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
    public int negociate_f (Negociation n) {
        int prix_prop = 0 ;

        return prix_prop;
    }

    public fournisseur(int id, String nom) {
        super(id);
        name = nom;
    }
}
