import java.net.InetAddress;

public class Inet2 {
    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
