package socketchat.server;

import com.google.gson.Gson;
import socketchat.dto.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server implements Runnable {

    private ServerSocket serverSocket;
    private Gson gson;

    public Server() {
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Server did not start");
        }
        System.out.println("Server started");

        gson = new Gson();

    }

    private void createNewThread(Socket clientSocket) {
        PrintWriter outToClient = null;
        try {
            outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            InputStream inputFromClient = clientSocket.getInputStream();
            BufferedReader bufferedReaderFromClient = new BufferedReader(new InputStreamReader(inputFromClient));

            runClientConnection(serverSocket, gson, clientSocket, outToClient, bufferedReaderFromClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("Waiting for new connection");

            try {

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new MyClientThread(clientSocket)).start();// asynch , fork
                    System.out.println("started new client " + clientSocket.getInetAddress());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyClientThread implements Runnable {

        private Socket clientSocket;

        public MyClientThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            createNewThread(clientSocket);
        }
    }

    private void runClientConnection(ServerSocket serverSocket, Gson gson, Socket clientSocket, PrintWriter outToClient, BufferedReader bufferedReaderFromClient) throws IOException {
        try {

            String serverHostName = serverSocket.getInetAddress().toString();

            String serverMessage = gson.toJson(
                    new Message(serverHostName, "Hello, client"));

            outToClient.println(serverMessage);

            while (true) {
                //read message
                String jsonMessageFromClient = bufferedReaderFromClient.readLine();
                Message clientMessage = gson.fromJson(jsonMessageFromClient, Message.class);
                System.out.println("Client:" + clientMessage);

                String message = clientMessage.getMessage().trim();
                if ("exit".equals(message)) {
                    System.out.println("End connection with the user " + clientSocket.getInetAddress());
                    break;
                } else {
                    // send message
                    System.out.print("You:");
                    // get message from the console
                    String serverResponce = new Scanner(System.in).nextLine();
                    outToClient.println(gson.toJson(new Message("server", serverResponce)));
                }
            }
        } catch (Throwable a) {
            a.printStackTrace();
            System.out.println("Closed client connection due a error");
        }
    }
}
