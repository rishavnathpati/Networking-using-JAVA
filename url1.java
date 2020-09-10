import java.net.MalformedURLException;
import java.net.URL;

class url1 {
    public static void main(final String[] args) throws MalformedURLException {
        URL u = new URL("https://meet.google.com:8080/jzg-posp-dry");

        System.out.println("Protocol: " + u.getProtocol());
        System.out.println("Host name: " + u.getHost());
        System.out.println("Port no: " + u.getPort());
        System.out.println("File name: " + u.getFile());
    }

}