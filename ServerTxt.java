import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class ServerTxt {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(11111);
        System.out.println("Server waiting");
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        String inp = dis.readUTF();
        System.out.println("Client sent " + inp);
        fileReader fr = new fileReader();
        String f = fr.sendData(inp);
        dos.writeUTF(String.valueOf(f));
        dos.flush();
        dis.close();
        dos.close();
        ss.close();

    }
}
