import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
    public static void main(final String[] args) throws UnknownHostException, IOException {
        final Socket s = new Socket("localhost", 12000);
        final DataOutputStream dot = new DataOutputStream(s.getOutputStream());
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter server msg");
        final String str = sc.nextLine();
        dot.writeUTF(str);
        dot.flush();
        dot.close();
        sc.close();
        s.close();
    }
}