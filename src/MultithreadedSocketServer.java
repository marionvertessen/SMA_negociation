import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedSocketServer extends Thread{
    public Fournisseur f1;
    public int port;
    public MultithreadedSocketServer(Fournisseur f, int no_port) {
        f1 = f;
        port = no_port;
    }

    public void run() {
        try{
            ServerSocket server=new ServerSocket(port);
            int counter=0;
            System.out.println("Server Started ....");
            while(true){
                counter++;
                Socket serverClient=server.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ServerClientThread sct = new ServerClientThread(serverClient,counter, f1); //send  the request to a separate thread
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}