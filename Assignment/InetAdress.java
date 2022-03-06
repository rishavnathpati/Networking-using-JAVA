import java.net.InetAddress;

public class InetAdress {

    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("www.rkmrc.in");
            System.out.println("Host name: " + inetAddress.getHostName());
            System.out.println("IP adress: " + inetAddress.getHostAddress());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
