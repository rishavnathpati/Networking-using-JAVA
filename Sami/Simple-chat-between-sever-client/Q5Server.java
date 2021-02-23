
import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Q5Server extends Thread {
    public static void main(String[] args) {
        int port = 9900;
        Socket socket;
        ServerSocket ss;
        BufferedReader socketInput;
        PrintWriter socketOutput;
        Scanner sc = new Scanner(System.in);
        try {
            ss = new ServerSocket(port);
            socket = ss.accept();
            socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOutput = new PrintWriter(socket.getOutputStream(), true);

            new Thread() {
                public void run() {
                    try {
                        String ip;
                        while (!socket.isClosed()) {
                            ip = socketInput.readLine();
                            if (!ip.equals(""))
                                System.out.println("client:-" + ip);
                            if (ip.equals("exit")) {
                                System.out.println("client left...hit enter to close server");

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
                            if (op.equals("close")) {
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
