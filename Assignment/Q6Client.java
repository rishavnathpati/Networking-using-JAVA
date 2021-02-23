import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Q6Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("127.0.0.1", 11111);
        Scanner sc = new Scanner(System.in);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());
        System.out.println("Enter a the file name: ");
        String str = sc.nextLine();
        dos.writeUTF(str);
        dos.flush();

        byte output[] = dis.readAllBytes();
        FileOutputStream fos = new FileOutputStream("1" + str);
        fos.write(output);
        fos.close();
        System.out.println("New file written with name: 1" + str);
        dos.close();
        dis.close();
        s.close();
        sc.close();
    }
}
