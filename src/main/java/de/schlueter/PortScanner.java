package de.schlueter;

import java.util.List;

import lombok.NoArgsConstructor;

/**
 * PortScanner
 */
@NoArgsConstructor
public class PortScanner {
    public int simplePortScanning(List<String> hosts, int port) {
        System.out.println("Simple port scanning");
        if (hosts.get(0) != null && port == 0) {
            long start = System.currentTimeMillis();
            TCPConnector connector = new TCPConnector(hosts.get(0));
            List<Integer> openPorts = connector.scanNetwork();
            System.out.println(openPorts.size() + " open ports found");
            Utils.printSeparator();
            System.out.println("Scanning took " + (System.currentTimeMillis() - start) + "ms");
            return openPorts.size();
        }
        return -1;
    }

    public int multiThreadedPortScanning(List<String> hosts, int port) {
        System.out.println("Multi-threaded port scanning");
        if (hosts.get(0) != null && port == 0) {
            long start = System.currentTimeMillis();
            TCPConnector connector = new TCPConnector(hosts.get(0));
            List<Integer> openPorts = connector.scanNetworkMulti();
            System.out.println(openPorts.size() + " open ports found");
            Utils.printSeparator();
            System.out.println("Scanning took " + (System.currentTimeMillis() - start) + "ms");
            return openPorts.size();
        }
        return -1;
    }
}
