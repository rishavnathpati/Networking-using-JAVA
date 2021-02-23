
import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

class Q3ServerRun extends Thread {
    Socket socket;
    BufferedReader socketInput;
    PrintWriter socketOutput;

    Q3ServerRun(Socket s) throws Exception {
        socket = s;
        socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOutput = new PrintWriter(socket.getOutputStream(), true);
    }

    void closeSocket() throws Exception {
        socketOutput.println("closed");
        socket.close();
    }

    public void run() {
        String ip;
        double n1, n2;
        Double res;
        try {
            while (true) {
                ip = socketInput.readLine();
                if (ip.equals("+")) {
                    n1 = Double.parseDouble(socketInput.readLine());
                    n2 = Double.parseDouble(socketInput.readLine());
                    res = n1 + n2;
                    socketOutput.println(res.toString());
                } else if (ip.equals("-")) {
                    n1 = Double.parseDouble(socketInput.readLine());
                    n2 = Double.parseDouble(socketInput.readLine());
                    res = n1 - n2;
                    socketOutput.println(res.toString());
                } else if (ip.equals("*")) {
                    n1 = Double.parseDouble(socketInput.readLine());
                    n2 = Double.parseDouble(socketInput.readLine());
                    res = n1 * n2;
                    socketOutput.println(res.toString());
                } else if (ip.equals("/")) {
                    n1 = Double.parseDouble(socketInput.readLine());
                    n2 = Double.parseDouble(socketInput.readLine());
                    if (n2 != 0) {
                        res = n1 / n2;
                        socketOutput.println(res.toString());
                    } else {
                        socketOutput.println("undefined");
                    }
                } else if (ip.equals("exit")) {
                    socket.close();
                    break;
                } else {
                    socketInput.readLine();
                    socketInput.readLine();
                    socketOutput.println("enter corrrect operator");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}

class ServerControl implements Runnable {
    int port = 9900;
    Scanner sc = new Scanner(System.in);
    ServerSocket ss;
    ArrayList<Q3ServerRun> connections = new ArrayList<Q3ServerRun>();

    void serverStart() throws Exception {
        new Thread(this).start();
        ss = new ServerSocket(port);
        while (true) {
            connections.add(new Q3ServerRun(ss.accept()));
            connections.get(connections.size() - 1).start();
        }
    }

    public void run() {
        try {
            while (!sc.nextLine().equals("q")) {
            }
            for (Q3ServerRun i : connections) {
                i.closeSocket();
            }
            ss.close();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}

class Q3Server {

    public static void main(String[] args) {
        try {
            new ServerControl().serverStart();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
