public class main extends Thread{

    public static void main (String[] args) throws Exception {
        int port1 =8888 , port2 =9999 ;
        acheteur a1 = new acheteur(1, 400, 500, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        acheteur a2 = new acheteur(2, 1350, 1450, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        fournisseur f1 = new fournisseur(3, "AirFrance");
        fournisseur f2 = new fournisseur(4, "AirFrance");

        MultithreadedSocketServer multi = new MultithreadedSocketServer(f1, port1);
        MultithreadedSocketServer multi1 = new MultithreadedSocketServer(f2, port2);

        TCPClient client = new TCPClient(a1, port1);
        TCPClient client2 = new TCPClient(a2, port1);
        TCPClient client_port2 = new TCPClient(a1, port2);
        TCPClient client2_port2 = new TCPClient(a2, port2);
        multi.start();
        multi1.start();
        sleep (20);
        client.start();
        client2.start();
        client2_port2.start();
        client_port2.start();
    }


}

