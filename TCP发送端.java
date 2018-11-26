import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP发送端{
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        BufferedReader br = null;
        try {
      
            ServerSocket server = new ServerSocket(8888);
          
            socket = server.accept();
        
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new 
                                   OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
             
                String str = in.readLine();
                System.out.println("客户端说：" + str);
                String str2 = "";
                
                if (str.equals("end")){
                    break;
                }
                
                str2 = br.readLine(); 
                out.write(str2 + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}