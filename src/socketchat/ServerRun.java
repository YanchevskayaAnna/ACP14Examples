package socketchat;

import socketchat.server.Server;


public class ServerRun {
    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
