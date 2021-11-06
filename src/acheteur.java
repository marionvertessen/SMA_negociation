public class acheteur extends agent {
    public int budgetMin = 200; //en euro
    public int budgetMax = 650;

    public int anneeDepart = 2021;
    public int moisDepart = 12;
    public int jourDepart = 25;
    public int heureDepart = 12;
    public String date_depart = "20/09/2021";

    public String ville_arrivee = "Tokyo";
    public String ville_depart = "Paris";
    public String compagnie_pref ="AirFrance";

    public acheteur(int id) {
        super(id);
    }

    public int negociate_a (Negociation n) {
        int prix_prop = -1 ;
        if (n.memoire_vendeur.get(n.memoire_vendeur.size()-1) < budgetMax) {
            prix_prop = -2;
        }
        else if(n.memoire_acheteur.size()==0) {
            prix_prop = budgetMin;
        }
        else {
            float nb_alea = (float) Math.random() /10;
            prix_prop = (int) (n.memoire_acheteur.get(n.memoire_acheteur.size()-1)  * (1+nb_alea));
        }
        return prix_prop;
    }
}
