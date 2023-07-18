package networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable {

    public static void main(String[] args) throws Exception {
        new Client();
    }

    Socket socket;
    ObjectOutputStream dout;
    ObjectInputStream din;
    static PlayerInput clientPlayerInput = new PlayerInput(0);
    static PlayerInput[] playerInput;

    static int playerCount = 0;

    private boolean waittingResponse = true;

    public Client() throws Exception {

        socket = new Socket("localhost", 7777);
        dout = new ObjectOutputStream(socket.getOutputStream());
        din = new ObjectInputStream(socket.getInputStream());

        playerInput = new PlayerInput[4];

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            dout.writeObject(clientPlayerInput);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
