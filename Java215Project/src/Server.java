import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(2000);
        while (true){
            System.out.println("Waiting For Clients >>");
            Socket socket= server.accept();


        }

    }
}