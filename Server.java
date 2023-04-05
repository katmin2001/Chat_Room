package BTTK1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<String> user = new ArrayList<>();
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

//    public int checkUsername(String username) {
//        if (!user.isEmpty()) {
//            for (String u : user) {
//                if (u.equals(username)) {
//                    return 0;
//                }
//            }
//        } else return 1;
//        return 1;
//    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
//                user.add(clientHandler.getClientUsername());
                System.out.println("User -" + clientHandler.getClientUsername() + "- has connected!");
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
        System.out.println(user);
    }
}
