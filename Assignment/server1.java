import java.io.*;
import java.net.*;
class server1 {
    public static void main(final String[] args) throws IOException {
        final ServerSocket ss = new ServerSocket(12000);
        System.out.println("Waiting for client.......");
        final Socket s = ss.accept();// wait until client hit
        final DataInputStream dis = new DataInputStream(s.getInputStream());// from socket
        final String output = (String) dis.readUTF();
        System.out.println(output);
        dis.close();
        s.close();
        ss.close();
    }
}