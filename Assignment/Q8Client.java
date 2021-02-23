import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Q8Client {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        DatagramSocket dataSocket = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte dataToSend[] = null;

        while (true) {
            String inp = sc.nextLine();
            dataToSend = inp.getBytes();
            DatagramPacket outputPacket = new DatagramPacket(dataToSend, dataToSend.length, ip, 12701);
            dataSocket.send(outputPacket);

            if (inp.equals("bye")) {
                sc.close();
                dataSocket.close();
                break;
            }
        }
    }
}
