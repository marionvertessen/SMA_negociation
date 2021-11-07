import java.util.ArrayList;
import java.util.List;

public class listeVol {
    public List<vol> liste = new ArrayList<vol>();

    public List<vol> load_vol () {
        liste.add(new vol(0, "Lyon", "Paris", 500, 200, "AirFrance", "03/08/1998","01/08/1998"));
        liste.add(new vol (1, "New York", "Tokyo", 2000, 1000, " Delta Air Lines", "13/02/1998", "10/02/1998") );
        liste.add(new vol (2, "Lyon", "Marseille", 100, 50, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new vol (3, "Tokyo", "Paris", 1500, 500, "AirFrance", "20/09/2021", "05/09/2021"));
        return liste;
    }

}
