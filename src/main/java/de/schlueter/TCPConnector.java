package de.schlueter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    TCPConnector(String host) {
        this.host = host;
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

    public List<Integer> scanNetwork() {
        int firstPort = 1;
        int lastPort = 65535;
        List<Integer> openPorts = new ArrayList<>();
        for (int port = firstPort; port <= lastPort; port++) {
            try {
                Socket socket = new Socket(host, port);
                socket.close();
                openPorts.add(port);
            } catch (IOException e) {
                // Do nothing
            }
        }
        return openPorts;
    }
    public List<Integer> scanNetworkMulti() {
        final int firstPort = 1;
        final int lastPort = 65535;
        List<Integer> openPorts =
            Collections.synchronizedList(new ArrayList<>());          // Make the list thread-safe
        ExecutorService executor = Executors.newFixedThreadPool(100); // Adjust the thread pool size

        for (int port = firstPort; port <= lastPort; port++) {
            final int finalPort = port;
            executor.submit(() -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, finalPort), 200); // Set a timeout
                    socket.close();
                    synchronized (openPorts) {
                        openPorts.add(finalPort);
                    }
                } catch (IOException e) {
                    // Do nothing
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS); // Wait with a reasonable timeout
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Handle interruption properly
        }
        return new ArrayList<>(openPorts); // Return a copy to avoid concurrent modification issues
    }
}
