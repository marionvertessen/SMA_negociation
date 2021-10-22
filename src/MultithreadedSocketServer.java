import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedSocketServer extends Thread{
    public fournisseur f1;

    public MultithreadedSocketServer(fournisseur f) {
        f1 = f;
    }

    public void run() {
        try{
            ServerSocket server=new ServerSocket(8888);
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

