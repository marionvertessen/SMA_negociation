import java.util.ArrayList;
import java.util.List;

public class listeVol {
    public List<vol> liste = new ArrayList<vol>();

    public List<vol> load_vol () {
        liste.add(new vol(0, "Lyon", "Paris", 500, 200, "AirFrance", "03/08/1998","01/08/1998"));
        liste.add(new vol (1, "New York", "Tokyo", 2000, 1000, " Delta Air Lines", "13/02/1998", "10/02/1998") );
        liste.add(new vol (2, "Lyon", "Marseille", 100, 50, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new vol (3, "Tokyo", "Paris", 1500, 1400, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new vol(4, "Nante", "Barcelone", 400, 350, "Brusselle Airline", "03/08/1998","01/08/1998"));
        liste.add(new vol (5, "Casablanca", "Lisbonne", 1650, 880, "Royale Air Maroc", "13/02/1998", "10/02/1998") );
        liste.add(new vol (6, "Joanesburg", "Londres", 1500, 1349, "South African Airways", "20/09/2021", "05/09/2021"));
        liste.add(new vol (7, "Vallencienne", "Los Angeles", 999, 949, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new vol(8, "Bordeaux", "Sidney", 500, 450, "AirFrance", "03/08/1998","01/08/1998"));
        liste.add(new vol (9, "Toulouse", "Chicago", 1600, 1380, " Delta Air Lines", "13/02/1998", "10/02/1998") );
        liste.add(new vol (10, "Rome", "Dubai", 400, 380, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new vol (11, "Moscou", "Istambul", 1100, 1020, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new vol(12, "Tunis", "Budapest", 500, 450, "Tunis Airline", "03/08/1998","01/08/1998"));
        liste.add(new vol (13, "Ottawa", "Bangkok", 2000, 1800, " Delta Air Lines", "13/02/1998", "10/02/1998") );
        liste.add(new vol (14, "Abidjan", "Singapour", 900, 850, "Air Cote d'ivoire", "20/09/2021", "05/09/2021"));
        liste.add(new vol (15, "Dakar", "Boston", 1500, 1349, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new vol(16, "Pekin", "Madrid", 1700, 1690, "Delta Air lines", "03/08/1998","01/08/1998"));
        liste.add(new vol (17, "Seoul", "Shangai", 2000, 1900, " Delta Air Lines", "13/02/1998", "10/02/1998") );
        liste.add(new vol (18, "Ouagadougou", "Abidjan", 100, 90, "Air Burkina", "20/09/2021", "05/09/2021"));
        liste.add(new vol (19, "Benin", "Paris", 900, 865, "Benin Golf Aire", "20/09/2021", "05/09/2021"));
        liste.add(new vol(20, "Bruxelle", "Aix en Provence", 500, 470, "Brusselle Airline", "03/08/1998","01/08/1998"));
        liste.add(new vol (21, "Ibiza", "Nante", 800, 780, " Delta Air Lines", "13/02/1998", "10/02/1998") );
        liste.add(new vol (22, "Miami", "Milan", 1359, 1300, "USA Airline", "20/09/2021", "05/09/2021"));
        liste.add(new vol (23, "Washington", "Montreal", 500, 489, "Alaska Airline", "20/09/2021", "05/09/2021"));
        return liste;
    }

}
