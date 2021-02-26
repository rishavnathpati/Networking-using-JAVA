import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Q3Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);

        while (true) {
            Socket s = null;

            try {
                s = ss.accept();
                System.out.println("A new client is connected : " + s.getPort());

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");
                Thread t = new ClientHandler(s, dis, dos);
                System.out.println(t.getId());
                t.start();
                System.out.println(t.getClass());

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
                ss.close();
            }
        }
    }
}

// ClientHandler class
class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    Scanner sc = new Scanner(System.in);

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        System.out.println(s);

        while (true) {
            try {

                dos.writeUTF("Enter operator type(+,-,*,/,0: To exit): ");
                String operator = dis.readUTF();
                if (operator.equals("0")) {
                    s.close();
                    dos.writeUTF("Connection closed");
                    // System.exit(0);
                    break;
                }
                double n1 = Double.parseDouble(dis.readUTF());
                double n2 = Double.parseDouble(dis.readUTF());

                if (operator.equals("+")) {
                    dos.writeUTF("Sum is: " + String.valueOf(add(n1, n2)));
                } else if (operator.equals("-")) {
                    dos.writeUTF("Difference is: " + String.valueOf(sub(n1, n2)));
                } else if (operator.equals("*")) {
                    dos.writeUTF("Multiplication is: " + String.valueOf(mult(n1, n2)));
                } else if (operator.equals("/")) {
                    dos.writeUTF("Division is: " + String.valueOf(div(n1, n2)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    double div(double n1, double n2) throws IOException {
        if (n2 == 0) {
            dos.writeUTF("Division by zero not valid");
            return 0;
        }
        return n1 / n2;
    }

    double mult(double n1, double n2) {
        return n1 * n2;
    }

    double sub(double n1, double n2) {
        return (n1 - n2);
    }

    double add(double s1, double s2) {
        return s1 + s2;
    }
}