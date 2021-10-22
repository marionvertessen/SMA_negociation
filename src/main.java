import java.sql.SQLOutput;
import java.util.List;


public class main extends Thread{

    public static void main (String[] args) throws Exception {

        acheteur a1 = new acheteur(1);
        acheteur a2 = new acheteur (2);
        fournisseur f1 = new fournisseur(3, "AirFrance");

        MultithreadedSocketServer multi = new MultithreadedSocketServer(f1);
        TCPClient client = new TCPClient(a1);
        TCPClient client2 = new TCPClient(a2);
        multi.start();
        sleep (20);
        client.start();
        client2.start();
    }


}

