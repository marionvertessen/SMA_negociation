import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    ServerClientThread(Socket inSocket,int counter){
        serverClient = inSocket;
        clientNo=counter;
    }
    public void run(){
        float budgetMin; //en euro
        float budgetMax;
        int id;
        try{
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage, serverMessage="";

            clientMessage= inStream.readUTF();
            id = Integer.parseInt(clientMessage);
            System.out.println(id);
            serverMessage= "OK";
            outStream.writeUTF(serverMessage);
            outStream.flush();

            /**clientMessage= inStream.readUTF();
            budgetMin = Float.parseFloat(clientMessage);
            System.out.println(budgetMin);
            serverMessage= "OK";
            outStream.writeUTF(serverMessage);
            outStream.flush();

            clientMessage= inStream.readUTF();
            budgetMax = Float.parseFloat(clientMessage);
            System.out.println(budgetMax);
            serverMessage= "OK";
            outStream.writeUTF(serverMessage);
            outStream.flush();**/








            /**while(!clientMessage.equals("bye")){
                clientMessage=inStream.readUTF();
                System.out.println("From Client-" +clientNo+ ": Number is :"+clientMessage);
                squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                serverMessage="From Server to Client-" + clientNo + " Square of " + clientMessage + " is " +squre;
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }**/
            inStream.close();
            outStream.close();
            serverClient.close();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}