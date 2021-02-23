import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class mstr {
    Socket s;
    long x;
}

public class Q9Server {
    public static ArrayList<mstr> slist = new ArrayList<mstr>();
    public static int counter;

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(11111);
        Scanner sc = new Scanner(System.in);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    int flag = 0;
                    String str = sc.nextLine();
                    if (str.equals("exit")) {
                        for (mstr i : Q9Server.slist) {
                            try {
                                DataOutputStream dos = new DataOutputStream(i.s.getOutputStream());
                                dos.writeUTF(str);
                                i.s.close();
                            } catch (IOException e) {
                            }
                        }
                        try {
                            ss.close();
                            break;
                        } catch (IOException e) {
                        }
                        sc.close();
                    }
                    int cl = Integer.parseInt(str.split("-", 0)[0]);
                    String msg = str.split("-", 0)[1];
                    for (mstr i : Q9Server.slist) {
                        if (i.x == cl) {
                            flag = 1;
                            try {
                                DataOutputStream dos = new DataOutputStream(i.s.getOutputStream());
                                dos.writeUTF(msg);
                                if (msg.equals("exit")) {
                                    System.out.println("Client " + i.x + " has been disconnected");
                                    i.s.close();
                                    slist.remove(i);
                                }
                                break;
                            } catch (IOException e) {
                            }
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Client " + cl + " does not exist");
                    }
                }
            }
        }).start();
        while (!ss.isClosed()) {
            try {
                Socket s = ss.accept();
                mstr v = new mstr();
                v.s = s;
                DataInputStream dis = new DataInputStream(s.getInputStream());
                Thread t = new ClientChatHandlerx(dis, s);
                v.x = t.getId();
                System.out.println("Client connected: " + v.x);
                slist.add(v);
                t.start();
            } catch (Exception e) {
            }
        }
    }

    public static void closeSocketID(long ID) {
        for (mstr i : Q9Server.slist) {
            if (i.x == ID) {
                try {
                    i.s.close();
                    slist.remove(i);
                    break;
                } catch (IOException e) {
                }
            }
        }
    }
}

class ClientChatHandlerx extends Thread {
    DataInputStream dis;
    Socket s;

    public ClientChatHandlerx(DataInputStream dis, Socket s) {
        this.dis = dis;
        this.s = s;
    }

    public void run() {
        while (!s.isClosed()) {
            try {
                String msg = dis.readUTF();
                System.out.println(this.getId() + "->> " + msg);
                if (msg.equals("exit")) {
                    System.out.println("Client exited: " + this.getId());
                    Q9Server.closeSocketID(this.getId());
                    break;
                }
            } catch (IOException e) {
            }
        }
    }
}