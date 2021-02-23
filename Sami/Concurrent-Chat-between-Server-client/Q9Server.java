
import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

class Q9ServerRun extends Thread {
    Socket socket;
    BufferedReader socketInput;
    PrintWriter socketOutput;
    int no;

    Q9ServerRun(Socket s, int i) throws Exception {
        no = i;
        socket = s;
        socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOutput = new PrintWriter(socket.getOutputStream(), true);
    }

    void closeSocket() throws Exception {
        socketOutput.println("closed");
        socket.close();
    }

    void send(String ip) {
        socketOutput.println(ip);
    }

    public void run() {
        try {
            String ip;
            while (socket.isConnected()) {
                ip = socketInput.readLine();
                System.out.println("\nClient-" + no + ">" + ip + "\n");
                if (ip.equals("bye")) {
                    closeSocket();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}

class ServerControl implements Runnable {
    int port = 9900;
    Scanner sc = new Scanner(System.in);
    ServerSocket ss;
    ArrayList<Q9ServerRun> connections = new ArrayList<Q9ServerRun>();

    void serverStart() throws Exception {
        new Thread(this).start();
        ss = new ServerSocket(port);
        while (true) {
            connections.add(new Q9ServerRun(ss.accept(), connections.size() + 1));
            connections.get(connections.size() - 1).start();
        }
    }

    public void run() {
        System.out.println("type any message to send to client no X(e.g. 1,2,..) in given format-'c-X-<message>'");
        String ip;
        String[] msg;
        while (true) {
            try {
                ip = sc.nextLine();
                if (!ip.equals("q")) {
                    msg = ip.split("-", 0);
                    try {
                        connections.get(Integer.parseInt(msg[1]) - 1).send(msg[2]);
                    } catch (Exception e) {
                        System.out.println(e.getClass());
                    }
                } else {
                    for (Q9ServerRun i : connections) {
                        i.closeSocket();
                    }
                    ss.close();
                    System.exit(1);
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }
}

class Q9Server {

    public static void main(String[] args) {
        try {
            new ServerControl().serverStart();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
