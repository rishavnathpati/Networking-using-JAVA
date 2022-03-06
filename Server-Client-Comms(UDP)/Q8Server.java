import java.net.*;

class Q8Server {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(9900);
            DatagramPacket dp;
            byte[] data;
            while (!ds.isClosed()) {
                data = new byte[6500];
                dp = new DatagramPacket(data, data.length);
                ds.receive(dp);
                System.out.println(getString(data));
                if (getString(data).trim().equals("bye")) {
                    ds.close();
                    System.out.println("Client left.....");
                    break;
                }
            }
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