/**
* @author JefersonCunha
* jefersoncunha.com
*/
package so_webserver;

public class Server {

    public static void main(String[] args) throws Exception {
      
      Servidor server = new Servidor(8080);
      new Thread(server).start();

      try {
          Thread.sleep(30 * 1000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      server.fecharServerSocket();
    }
}
