import java.util.ArrayList;
import java.util.List;

public class ListVol {
    public List<Vol> liste = new ArrayList<Vol>();

    public List<Vol> load_vol () {
        liste.add(new Vol(0, "Lyon", "Paris", 500, 200, "AirFrance", "21/08/2021","21/08/2021"));
        liste.add(new Vol(1, "New York", "Tokyo", 2000, 1000, "Delta Air Lines", "13/02/1998", "10/02/1998") );
        liste.add(new Vol(2, "Lyon", "Marseille", 100, 50, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(3, "Tokyo", "Paris", 1500, 1400, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(4, "Nante", "Barcelone", 400, 350, "Brusselle Airline", "21/09/2021","01/09/2021"));
        liste.add(new Vol(5, "Casablanca", "Lisbonne", 1650, 880, "Royale Air Maroc", "13/02/2021", "10/02/2021") );
        liste.add(new Vol(6, "Joanesburg", "Londres", 1500, 1349, "South African Airways", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(7, "Vallencienne", "Los Angeles", 999, 949, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(8, "Bordeaux", "Sidney", 500, 450, "AirFrance", "03/08/2021","01/08/2021"));
        liste.add(new Vol(9, "Toulouse", "Chicago", 1600, 1380, "Delta Air Lines", "13/02/2021", "10/02/2021") );
        liste.add(new Vol(10, "Rome", "Dubai", 400, 380, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(11, "Moscou", "Istambul", 1100, 1020, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(12, "Tunis", "Budapest", 500, 450, "Tunis Airline", "03/08/2021","01/08/2021"));
        liste.add(new Vol(13, "Ottawa", "Bangkok", 2000, 1800, "Delta Air Lines", "13/02/2021", "10/02/2021") );
        liste.add(new Vol(14, "Abidjan", "Singapour", 900, 850, "Air Cote d'ivoire", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(15, "Dakar", "Boston", 1500, 1349, "AirFrance", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(16, "Pekin", "Madrid", 1700, 1690, "Delta Air Lines", "03/08/2021","01/08/2021"));
        liste.add(new Vol(17, "Seoul", "Shangai", 2000, 1900, "Delta Air Lines", "13/02/2021", "10/02/2021") );
        liste.add(new Vol(18, "Ouagadougou", "Abidjan", 100, 90, "Air Burkina", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(19, "Benin", "Paris", 900, 865, "Benin Golf Air", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(20, "Bruxelle", "Aix en Provence", 500, 470, "Brusselle Airline", "03/08/2021","01/08/2021"));
        liste.add(new Vol(21, "Ibiza", "Nante", 800, 780, "Delta Air Lines", "13/02/2021", "10/02/2021") );
        liste.add(new Vol(22, "Miami", "Milan", 1359, 1300, "USA Airline", "20/09/2021", "05/09/2021"));
        liste.add(new Vol(23, "Washington", "Montreal", 500, 489, "Alaska Airline", "20/09/2021", "05/09/2021"));
        return liste;
    }

}
