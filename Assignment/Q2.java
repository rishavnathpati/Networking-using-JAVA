import java.net.URL;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        try {
            String url;
            Scanner sc = new Scanner(System.in);
            System.out.println("enter an URL");
            url = sc.nextLine();
            URL urlObj = new URL(url);
            System.out.println("Protocol : " + urlObj.getProtocol());
            System.out.println("Host : " + urlObj.getHost());
            System.out.println("Port No : " + urlObj.getPort());
            System.out.println("File : " + urlObj.getFile());
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
