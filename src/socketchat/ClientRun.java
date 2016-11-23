package socketchat;

import com.google.gson.Gson;
import socketchat.dto.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ClientRun {

    public static void main(String[] args) {

        final Gson gson = new Gson();

        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter pw = new PrintWriter(socket.getOutputStream())) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true){

                String jsonServerMessage = bufferedReader.readLine();
                Message serverMessage = gson.fromJson(jsonServerMessage,Message.class);

                System.out.println("Server:" + serverMessage);
                // String(Json) -> object Message

                System.out.print("You:");
                // get message from the console
                String message = new Scanner(System.in).nextLine();

                String responseFromServer = gson.toJson(new Message("client", message));
                pw.println(responseFromServer);
                pw.flush();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
