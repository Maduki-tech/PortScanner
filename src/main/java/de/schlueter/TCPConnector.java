package de.schlueter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TCPConnector
 */
public class TCPConnector {
    public boolean connect(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // TODO: Finding a faster methdoe
    // NOTE: If there cannot be made a connection to one IP the skip filling in the ports for that
    // ip and skip to the next ip
    public Map<String, Integer> scanNetwork(List<String> hosts, int port) {
        int firstPort = 1;
        int lastPort = 65535;
        Map<String, Integer> openPorts = new HashMap<>();

        for (String host : hosts) {
            // Check if the host is reachable
            System.out.println("Checking host: " + host);
            for (int i = firstPort; i <= lastPort; i++) {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, i), 200); // Set a timeout
                    socket.close();
                    System.out.println("Host: " + host + " Port: " + i);
                    openPorts.put(host, i);
                } catch (IOException e) {
                    // Do nothing
                }
            }
            System.out.println("Finished checking host: " + host);
        }
        return openPorts;
    }
    // TODO: Improve the methode
    // public Map< String, Integer> scanNetworkMulti(List<String> hosts, int port) {
    //     final int firstPort = 1;
    //     final int lastPort = 65535;
    //     Map< String, Integer> openPorts = new HashMap<>();
    //     List<Integer> openPortsPerIP =
    //         Collections.synchronizedList(new ArrayList<>());          // Make the list
    //         thread-safe
    //     ExecutorService executor = Executors.newFixedThreadPool(100); // Adjust the thread pool
    //     size
    //
    //     for (int i = firstPort; i <= lastPort; i++) {
    //         final int finalPort = i;
    //         executor.submit(() -> {
    //             try {
    //                 Socket socket = new Socket();
    //                 socket.connect(new InetSocketAddress(host, finalPort), 200); // Set a timeout
    //                 socket.close();
    //                 synchronized (openPortsPerIP) {
    //                     openPortsPerIP.add(finalPort);
    //                 }
    //             } catch (IOException e) {
    //                 // Do nothing
    //             }
    //         });
    //     }
    //     executor.shutdown();
    //     try {
    //         executor.awaitTermination(1, TimeUnit.HOURS); // Wait with a reasonable timeout
    //     } catch (InterruptedException e) {
    //         Thread.currentThread().interrupt(); // Handle interruption properly
    //     }
    //     return new ArrayList<>(openPortsPerIP); // Return a copy to avoid concurrent modification
    //     issues
    // }
}
