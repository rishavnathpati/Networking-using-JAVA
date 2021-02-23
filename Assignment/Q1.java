import java.net.*;
import java.util.Scanner;

class Q1 {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter domain name: ");
            String domain = sc.nextLine();
            InetAddress addr = InetAddress.getByName(domain);
            System.out.println("Domain Name: " + addr.getHostName() + "\nIP Address: " + addr.getHostAddress());
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}