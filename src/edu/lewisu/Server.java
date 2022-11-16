package edu.lewisu;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket = null;
    private Socket client_socket = null;

    private ExecutorService pool = Executors.newFixedThreadPool(2);

    public Server(int port){
        Game thisGame = new Game();
        thisGame.Start("R","W");
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                client_socket = serverSocket.accept();
                System.out.println("Someone connected");
                ClientProcessing playerThread = new ClientProcessing(client_socket,thisGame);
                pool.execute(playerThread);
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void main(String args[]){
        Server server = new Server(42691);
    }

}
