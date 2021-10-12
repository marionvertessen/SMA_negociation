import java.sql.SQLOutput;


public class main extends Thread{

    public static void main (String[] args) throws Exception {

        acheteur a1 = new acheteur(1);
        fournisseur f1 = new fournisseur(2, "AirFrance");
        Negociation n1 = new Negociation();

        MultithreadedSocketServer multi = new MultithreadedSocketServer(f1, n1);
        TCPClient client = new TCPClient(a1, n1);
        multi.start();
        sleep (20);
        client.start();
    }
}

