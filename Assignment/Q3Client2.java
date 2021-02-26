import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Q3Client2 {
    public static void main(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);
            Socket s = new Socket("127.0.0.1", 5056);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {
                System.out.println(dis.readUTF());
                String operator = scn.nextLine();
                dos.writeUTF(operator);
                if (operator.equals("0")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    System.exit(0);
                    break;
                }
                System.out.print("Enter 1st number: ");
                dos.writeUTF(scn.nextLine());
                System.out.print("Enter 2nd number: ");
                dos.writeUTF(scn.nextLine());

                System.out.println(dis.readUTF());
            }

            scn.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}