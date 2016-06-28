/**
* @author JefersonCunha
* jefersoncunha.com
*/

package so_webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;



public class GerarLog  {
    Socket socket;

    public GerarLog(Socket socket) throws Exception{
        this.socket = socket;
    }

   public void LogTxt() throws Exception{

       
       File arquivoTxt = new File("./logs/"+"log_programa.txt");

       if(!arquivoTxt.exists()){
           try{
               //Cria o arquivo
               arquivoTxt.createNewFile();
               System.out.println("###--------> Arquivo LOG CRIADO");

               //salva o arquivo
               FileWriter  writer = new FileWriter(arquivoTxt);

               writer.write("\n\n");
               writer.write("IP:"+socket.getInetAddress()+"\n");
               writer.write("Data:"+ new Date()+"\n");
               writer.write("\n---------------------\n");

               writer.close();
               System.out.println("###--------> Arquivo LOG -> IP:"+socket.getInetAddress());
           }
           catch (IOException e){
               e.printStackTrace();
           }
       }
       else{
           try{
             System.out.println("###--------> Arquivo LOG ATUALIZADO");
               FileReader reader = new FileReader(arquivoTxt);
               BufferedReader br = new BufferedReader(reader);
               String linha = br.readLine();
               FileWriter  writer = new FileWriter(arquivoTxt);

               while(linha != null){
                   writer.write(linha+"\n");
                   br.readLine();
                   linha = br.readLine();
               }

               br.close();
               reader.close();

               writer.write("\n\n");
               writer.write("IP:"+socket.getInetAddress()+"\n");
               writer.write("Data:"+ new Date());
               writer.write("\n---------------------\n");

               writer.close();
               System.out.println("###--------> Arquivo LOG -> IP:"+socket.getInetAddress());
           }
           catch(IOException err){
               err.printStackTrace();
           }
       }
   }
}
