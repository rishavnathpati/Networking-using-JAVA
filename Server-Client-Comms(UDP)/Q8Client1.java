import java.net.*;
import java.util.Scanner;

public class Q8Client1 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            DatagramPacket dp;
            DatagramSocket ds = new DatagramSocket();
            byte[] data;
            while (!ds.isClosed()) {
                data = new byte[6500];
                data = sc.nextLine().getBytes();
                dp = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 9900);
                ds.send(dp);
                if (getString(data).trim().equals("bye")) {
                    ds.close();
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getString(byte[] byt) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < byt.length; i++)
            s.append((char) byt[i]);
        return s.toString();
    }

}
