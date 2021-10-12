public class acheteur extends agent {
    public int budgetMin = 200; //en euro
    public int budgetMax = 1000;

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

    public int negociate_a () {
        int prix_prop = 0 ;

        return prix_prop;
    }

}
