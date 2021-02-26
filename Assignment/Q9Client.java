import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Q9Client {
    public static void main(String[] args) throws Exception {
        try {
            Socket s = new Socket("localhost", 11111);
            Scanner sc = new Scanner(System.in);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            new Thread(new Runnable() {
                public void run() {
                    while (!s.isClosed()) {
                        try {
                            String str = sc.nextLine();
                            dos.writeUTF(str);
                            if (str.equals("exit")) {
                                System.out.println("Disconnected");
                                dis.close();
                                dos.close();
                                s.close();
                                sc.close();
                                break;
                            }

                        } catch (Exception e) {
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    while (!s.isClosed()) {
                        try {
                            String str = dis.readUTF();
                            if (str.equals("exit")) {
                                System.out.println("Disconnected from server");
                                s.close();
                                System.exit(0);
                            }
                            System.out.println("> " + str);
                        } catch (Exception e) {
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
        }
    }
}
