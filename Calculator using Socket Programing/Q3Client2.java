import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Q3Client2 {

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

            while (socket.isConnected()) {
                System.out.println("enter a operator");
                String op = sc.nextLine();
                socketOutput.println(op);
                if (op.equals("exit")) {
                    socket.close();
                    System.exit(1);
                }
                System.out.println("enter 1st number");
                socketOutput.println(sc.nextLine());
                System.out.println("enter 2nd number");
                socketOutput.println(sc.nextLine());
                System.out.println(ifServerStopped(socketInput.readLine()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
