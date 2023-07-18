package networking;

import java.net.*;
import java.io.*;

public class Server {

    // main method
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7777);
        Socket socket = serverSocket.accept();
        ObjectInputStream din = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream dout = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
            try {
                PlayerInput playerInput = (PlayerInput) din.readObject();
                System.out.println(playerInput.PlayerId);
            } catch (Exception e) {
            }
        }
    }
}
