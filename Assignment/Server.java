import java.io.*;
import java.net.*;

// Server class 
public class Server {
    public static void main(String[] args) throws IOException {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);

        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            } catch (Exception e) {
                s.close();
                ss.close();
                e.printStackTrace();
            }
        }
    }
}

// ClientHandler class
class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String string;
        boolean ret;
        while (true) {
            try {

                // Ask user what he wants
                dos.writeUTF("Enter string to check: ");

                // receive the answer from client
                string = dis.readUTF();

                if (string.equals("0")) {
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                } else {
                    ret = checkPalindrome(string);
                    dos.writeUTF(String.valueOf(ret));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}