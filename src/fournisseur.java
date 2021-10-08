public class fournisseur extends agent{
    String nom = "Marion";

    public fournisseur(int id) {
        super(id);
    }

    /*public static void main(String[] args) throws Exception {
            ServerSocket server=new ServerSocket(8888);
            Socket serverClient=server.accept();
            DataInputStream inStream=new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream=new DataOutputStream(serverClient.getOutputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            String clientMessage="", serverMessage="";
            while(!clientMessage.equals("bye")){
                clientMessage=inStream.readUTF();
                System.out.println("From Client: "+clientMessage);
                serverMessage=reader.readLine();
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }
            inStream.close();
            outStream.close();
            serverClient.close();
            server.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }*/
}
