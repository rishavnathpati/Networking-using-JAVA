import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class factserver {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(11111);
        System.out.println("Server waiting");
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        String inp = dis.readUTF();
        int n = Integer.parseInt(inp);
        System.out.println("Client sent "+n);
        fact ob=new fact();
        int f=ob.calfact(n);
        dos.writeUTF(String.valueOf(f));
        dos.flush();
        dis.close();
        dos.close();
        ss.close();
    }
}
