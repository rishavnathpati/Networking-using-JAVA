import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

public class ClientTxt {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("127.0.0.1", 11111);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());
        System.out.println("Enter a the file name: ");
        String str = br.readLine();
        dos.writeUTF(String.valueOf(str));
        dos.flush();
        String output = dis.readUTF();
        System.out.println("Content of file is : " + output);
        dos.close();
        dis.close();
        s.close();
    }
}
