import java.net.InetAddress;
import java.net.UnknownHostException;

class url1 {
    // public static void main(final String[] args) throws MalformedURLException {
    // URL u = new URL("https://meet.google.com:8080/jzg-posp-dry");

    // System.out.println("Protocol: " + u.getProtocol());
    // System.out.println("Host name: " + u.getHost());
    // System.out.println("Port no: " + u.getPort());
    // System.out.println("File name: " + u.getFile());
    // }

    public static void main(String[] args) throws UnknownHostException {
        String hostname = "www.facebook.com";
        try {

            InetAddress a = InetAddress.getByName("hostname");

            System.out.println(hostname + ":" + a.getHostAddress());

        } catch (UnknownHostException e) {

            System.out.println("No address found for " + hostname);

        }

    }

}