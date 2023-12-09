import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FroggerServer {

    public static void main(String[] args) throws IOException{

        final int SOCKET_PORT = 5556;
        ServerSocket server = new ServerSocket(SOCKET_PORT);
        System.out.println("Server started on port" + SOCKET_PORT);

        while(true) {
            Socket s = server.accept();
            System.out.println("Client connected from " + s.getLocalAddress().getHostName());

            FroggerService myServer = new FroggerService(s);
            Thread t = new Thread(myServer);
            t.start();
        }
    }

}
