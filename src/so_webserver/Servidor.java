package so_webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JefersonCunha
 */
public class Servidor implements Runnable{

    protected int          serverPort     = 8080;
    protected ServerSocket serverSocket   = null;
    protected boolean      estaLivre      = false;
    protected Thread       estaExecutando = null;

    public Servidor(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized(this){
            this.estaExecutando = Thread.currentThread();
            System.out.println("###--------> Servidor currentThread!"+this.estaExecutando);
        }
       
                
        abrirServerSocket();  
        while(!estaLivre()){
            System.out.println("###--------> Servidor estaLivre:"+estaLivre);
            
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
                new GerarLog(clientSocket).LogTxt();
              
            } catch (IOException e) {
                if(estaLivre()) {
                    System.out.println("###--------> Servidor Fechado!");
                    return;
                }
                throw new RuntimeException("###--------> Erro em Aceitar a conexão do cliente!",e);
            } catch (Exception ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            new Thread(new WebServer(clientSocket)).start();
            new Thread(new WebDados()).start();
        }

        System.out.println("Servidor parado.") ;
    }

    private synchronized boolean estaLivre() {
        return this.estaLivre;
    }

    public synchronized void fecharServerSocket(){
        this.estaLivre = true;
        System.out.println("###--------> Servidor Fechado!");
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("###--------> Erro em fechar o servidor!",e);
            //System.out.println("###--------> Erro em fechar o servidor!");
        }
    }

    private void abrirServerSocket() {
         System.out.println("###--------> Servidor abrirServerSocket!");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            System.out.println("###--------> Porta 8080 aberta!");
            // Aguarda alguém se conectar. A execução do servidor
            // fica bloqueada na chamada do método accept da classe
            // ServerSocket. Quando alguém se conectar ao servidor, o
            // método desbloqueia e retorna com um objeto da classe
            // Socket, que é uma porta da comunicação.
            System.out.println("###--------> Aguardando conexão do cliente...");
        } catch (IOException e) {
            throw new RuntimeException("###--------> Nao conseguiu abrir a porta 8080!", e);
        }
    }

}
