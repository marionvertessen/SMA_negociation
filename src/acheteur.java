public class acheteur extends agent {
    public float budgetMin = 200; //en euro
    public float budgetMax = 1000;

    public int anneeDepart = 2021;
    public int moisDepart = 12;
    public int jourDepart = 25;
    public int heureDepart = 12;

    public String ville_arrivee = "Tokyo";
    public String ville_depart = "Paris";



    public acheteur(int id) {
        super(id);
    }

    /*public static void main(String[] args) throws Exception {
        try{
            Socket socket=new Socket("127.0.0.1",8888);
            DataInputStream inStream=new DataInputStream(socket.getInputStream());
            DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String clientMessage="",serverMessage="";
            while(!clientMessage.equals("bye")){
                clientMessage=br.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                serverMessage=inStream.readUTF();
                System.out.println("From Server: "+serverMessage);
            }
            outStream.close();
            outStream.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }*/
}
