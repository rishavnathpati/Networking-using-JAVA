import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Q7Client1 {

    static String ifServerStopped(String arg) throws Exception {
        if (arg.equals("closed")) {
            System.out.println("Server is Closed");
            throw new Exception();
        }
        return arg;
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9900);

            BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            String ip;
            while (socket.isConnected()) {
                System.out.println(
                        "command\t\tresult\nmyip\t\tget your IP\ndate\t\tget current date and time\nexit\t\tclose connection");
                ip = sc.nextLine();
                socketOutput.println(ip);
                if (ip.equals("exit")) {
                    socket.close();
                    break;
                }
                System.out.println(ifServerStopped(socketInput.readLine()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
