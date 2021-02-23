import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Q8Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket dataSocket = new DatagramSocket(12701);
        System.out.println("Server started");
        byte[] recievedMsg = new byte[1024];

        while (true) {
            DatagramPacket inputPacket = new DatagramPacket(recievedMsg, recievedMsg.length);

            dataSocket.receive(inputPacket);
            String recievedMsgdData = new String(inputPacket.getData(), 0, inputPacket.getLength());
            System.out.println("Client:- " + recievedMsgdData);

            if (recievedMsgdData.equalsIgnoreCase("bye")) {
                System.out.println("Client said bye");
                dataSocket.close();
                break;
            }

            recievedMsg = new byte[65535];
        }
    }

    // private static StringBuilder makeData(byte[] datareceived) {
    // StringBuilder data = new StringBuilder();
    // int i = 0;
    // while (datareceived[i] != 0) {
    // ret.append((char) datareceived[i]);
    // i++;
    // }
    // return ret;
    // }
}
