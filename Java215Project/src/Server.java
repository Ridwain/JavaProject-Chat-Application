import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandlers = new ClientHandler(socket);
                Thread thread = new Thread(clientHandlers);
                thread.start();

            }

        }catch(IOException e){
            closingServer();
        }
    }
    public void closingServer(){
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(2000);
        Server server = new Server(serverSocket);
        server.startServer();

    }
}