import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Q4Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(11111);
        System.out.println("Server waiting");
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        String inp = dis.readUTF();
        // int n = Integer.parseInt(inp);
        System.out.println("Client sent " + inp);
        // fact ob = new fact();
        Q4palindrome pa = new Q4palindrome();
        // int f=ob.calfact(n);
        boolean f = pa.checkPalindrome(inp);
        dos.writeUTF(String.valueOf(f));
        dos.flush();
        dis.close();
        dos.close();
        ss.close();
    }
}
