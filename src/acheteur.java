public class acheteur extends agent {
    public int budgetMin; //en euro
    public int budgetMax;

    public int anneeDepart;
    public int moisDepart;
    public int jourDepart;
    public int heureDepart;
    public String date_depart;

    public String ville_arrivee;
    public String ville_depart;
    public String compagnie_pref;

    public acheteur(int id, int budgetMin, int budgetMax, int anneeDepart, int moisDepart, int jourDepart, String date_depart, String ville_arrivee, String ville_depart, String compagnie_pref) {
        super(id);
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
        this.jourDepart = jourDepart;
        this.moisDepart = moisDepart;
        this.anneeDepart = anneeDepart;
        this.date_depart = date_depart;
        this.ville_arrivee = ville_arrivee;
        this.ville_depart = ville_depart;
        this.compagnie_pref = compagnie_pref;
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