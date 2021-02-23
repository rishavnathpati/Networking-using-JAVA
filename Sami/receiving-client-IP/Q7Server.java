
import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.LocalDateTime;

class Q7ServerRun extends Thread {
    Socket socket;
    BufferedReader socketInput;
    PrintWriter socketOutput;

    Q7ServerRun(Socket s) throws Exception {
        socket = s;
        socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOutput = new PrintWriter(socket.getOutputStream(), true);
    }

    void closeSocket() throws Exception {
        socketOutput.println("closed");
        socket.close();
    }

    public void run() {
        try {
            String ip;
            while (true) {
                ip = socketInput.readLine();
                if (ip.equals("exit")) {
                    socket.close();
                    break;
                } else if (ip.equals("myip")) {
                    socketOutput.println(socket.getInetAddress().toString());
                } else if (ip.equals("date")) {
                    socketOutput.println(LocalDateTime.now().toString().replaceFirst("T", " "));
                } else {
                    socketOutput.println("");
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
    ArrayList<Q7ServerRun> connections = new ArrayList<Q7ServerRun>();

    void serverStart() throws Exception {
        new Thread(this).start();
        ss = new ServerSocket(9900);
        while (true) {
            connections.add(new Q7ServerRun(ss.accept()));
            System.out.println(connections.get(connections.size() - 1).socket.getInetAddress() + "connected");
            connections.get(connections.size() - 1).start();
        }
    }

    public void run() {
        try {
            while (!sc.nextLine().equals("q")) {
            }
            for (Q7ServerRun i : connections) {
                i.closeSocket();
            }
            ss.close();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}

class Q7Server {

    public static void main(String[] args) {
        try {
            new ServerControl().serverStart();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
