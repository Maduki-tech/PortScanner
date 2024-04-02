package de.schlueter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TCPConnector
 */
public class TCPConnector {
    private HashMap<String, List<Integer>> openPorts = new HashMap<>();


    public boolean connect(String host, int port) {
        try {
            Socket socket = new Socket();
            socket.connect(new java.net.InetSocketAddress(host, port), 200);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isReachable(String host) {
        try {
            if (!InetAddress.getByName(host).isReachable(200)) {
                return false;
            }
        } catch (IOException e) {
            // do nothing
        }
        return true;
    }

    /**
     * This funciton will scan the network for open ports
     *
     * @param hosts 
     * @param port 
     * @return runs a list of open ports for each host
     */
    public Map<String, List<Integer>> scanNetwork(List<String> hosts, int port) {
        int firstPort = 1;
        int lastPort = 7000;
        for (String host : hosts) {
            // Check if the host is reachable
            if (!isReachable(host)) {
                continue;
            }
            new Thread(() -> {
                List<Integer> openPorts = scanPorts(host, firstPort, lastPort);
                if (!openPorts.isEmpty()) {
                    // System.out.println("Adding" + host + " to openPorts");
                    this.openPorts.put(host, openPorts);
                }
            }).start();
        }

        // wait for all threads to Finish
        while (Thread.activeCount() > 1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // do nothing
            }
        }

        return openPorts;
    }

	private List<Integer> scanPorts(String host, int firstPort, int lastPort) {
        List<Integer> openPorts = new ArrayList<>();
        for (int i = firstPort; i <= lastPort; i++) {
            if (connect(host, i)) {
                openPorts.add(i);
            }
            if (i == lastPort) {
                System.out.println("Finished scanning ports on host " + host);
            }
        }
        return openPorts;
	}
}
