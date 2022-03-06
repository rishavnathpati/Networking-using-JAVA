import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Q6Client2 {

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
                System.out.println("enter command in format 'download <filename>' to download");
                ip = sc.nextLine();
                socketOutput.println(ip);
                if (ip.equals("exit")) {
                    socket.close();
                    break;
                } else {
                    String[] com = ip.split(" ", 0);
                    if (com[0].equals("download")) {
                        OutputStream os = new FileOutputStream(new File("(down)" + com[1]));
                        long size = Long.parseLong(socketInput.readLine());
                        byte[] data = new byte[1024];
                        DataInputStream dataRead = new DataInputStream(socket.getInputStream());
                        int read = -1;
                        socketOutput.println(1);
                        while (true) {
                            read = dataRead.read(data, 0, data.length);
                            os.write(data, 0, read);
                            size -= read;
                            if (size <= 0)
                                break;
                        }
                        socketOutput.println(0);
                        os.close();
                        System.out.println("successfully received file ");
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
