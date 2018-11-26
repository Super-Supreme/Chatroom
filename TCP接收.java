import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP接收 {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        BufferedReader wt = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8888);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new 
                                   OutputStreamWriter(socket.getOutputStream()));
            wt = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
           
                String str = wt.readLine();
                out.write(str + "\n");
                out.flush();
               
                if (str.equals("end")) {
                    break;
                }
               
                System.out.println("服务器端说：" + in.readLine());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (wt != null) {
                try {
                    wt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
