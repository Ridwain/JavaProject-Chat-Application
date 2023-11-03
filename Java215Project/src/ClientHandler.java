import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        String msgFromClient;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clients.add(this);
            while (socket.isConnected()) {

                msgFromClient = bufferedReader.readLine().trim();
                //System.out.println("Received " + msgFromClient);
                for(ClientHandler clients : clients){
                    if(clients!=this){
                        clients.bufferedWriter.write(msgFromClient);
                        clients.bufferedWriter.newLine();
                        clients.bufferedWriter.flush();
                    }
                }


            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
