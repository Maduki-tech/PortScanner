package de.schlueter;

import java.io.IOException;
import java.net.Socket;

/**
 * TCPConnector
 */
public class TCPConnector {
    private String host;
    private int port;

    TCPConnector(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean connect() {
        try {
            Socket socket = new Socket(host, port);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
