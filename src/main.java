public class main extends Thread{

    public static void main (String[] args) throws Exception {

        acheteur a1 = new acheteur(1, 400, 500, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        acheteur a2 = new acheteur(2, 400, 500, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        fournisseur f1 = new fournisseur(3, "AirFrance");
        fournisseur f2 = new fournisseur(4, "AirFrance");

        MultithreadedSocketServer multi = new MultithreadedSocketServer(f1, 8888);
        MultithreadedSocketServer multi1 = new MultithreadedSocketServer(f2, 9999);

        TCPClient client = new TCPClient(a1, 8888);
        TCPClient client2 = new TCPClient(a2, 8888);
        TCPClient client_port2 = new TCPClient(a1, 9999);
        TCPClient client2_port2 = new TCPClient(a2, 9999);
        multi.start();
        multi1.start();
        sleep (20);
        client.start();
        client2.start();
        client2_port2.start();
        client_port2.start();
    }


}

