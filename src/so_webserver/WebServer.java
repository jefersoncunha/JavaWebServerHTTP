package so_webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class WebServer implements Runnable{

    protected Socket clientSocket = null;
    private byte[] b = new byte[1024];

    public WebServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            System.out.println("###--------> Cliente Conectado ->"+clientSocket);

            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();


//            int num = input.read(b);
//            if(num > 0)
//                System.out.println(new String(b, 0, num, "ISO-8859-1"));

            output.write(new WebDados().getPagina().getBytes("ISO-8859-1"));

            output.close();
            input.close();

            long time = System.currentTimeMillis();
            System.out.println("###--------> Request processed: " + time);

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

}
