import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Negociation {
    int service; //Vol concerne par la negociation
    Date date_debut;
    int id_acheteur ;
    int id_fournisseur;
    List<Integer> memoire_acheteur = new ArrayList<Integer>();
    List<Integer> memoire_vendeur = new ArrayList<Integer>();
    int nb_max_nego = 3; //3 porpositions chacun !
    int nb_nego = 0;

}
