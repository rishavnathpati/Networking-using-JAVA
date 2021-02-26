import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.io.*;

public class Q7Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(11111);
        System.out.println("Server waiting");
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.writeUTF("IP adress of client is: " + s.getInetAddress());
        dos.writeUTF("Current time is: " + LocalDateTime.now());
        dos.flush();
        dis.close();
        dos.close();
        ss.close();
    }
}