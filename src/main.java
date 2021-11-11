public class main extends  Thread{

    public static void main (String[] args) throws Exception {
        int port1 =8888 , port2 =9999 ;
        Acheteur a1 = new Acheteur(1, 400, 500, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        Acheteur a2 = new Acheteur(2, 1350, 1450, 2021, 12, 25, "20/09/2021", "Tokyo", "Paris", "AirFrance");
        Fournisseur f1 = new Fournisseur(3, "AirFrance");
        Fournisseur f2 = new Fournisseur(4, "AirFrance");

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

