import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Q5Client1 extends Thread {

    public static void main(String[] args) {
        Socket socket;
        BufferedReader socketInput;
        PrintWriter socketOutput;
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 9900);
            socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOutput = new PrintWriter(socket.getOutputStream(), true);
            new Thread() {
                public void run() {
                    try {
                        String ip;
                        while (!socket.isClosed()) {
                            ip = socketInput.readLine();
                            if (!ip.equals(""))
                                System.out.println("server:-" + ip);
                            if (ip.equals("close")) {
                                System.out.println("server is close..hit enter to close connection");
                                socket.close();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }.start();
            new Thread() {
                public void run() {
                    try {
                        String op;
                        while (!socket.isClosed()) {
                            op = sc.nextLine();
                            socketOutput.println(op);
                            if (op.equals("exit")) {
                                socket.close();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
