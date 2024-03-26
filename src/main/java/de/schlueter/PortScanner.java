package de.schlueter;

import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PortScanner
 */
@Slf4j
@NoArgsConstructor
public class PortScanner {

    public int simplePortScanning(List<String> hosts, int port) {
        System.out.println("Simple port scanning");
        if (!hosts.isEmpty() && port == 0) {
            long start = System.currentTimeMillis();
            TCPConnector connector = new TCPConnector();
            Map<String, Integer> openPorts = connector.scanNetwork(hosts, port);

            openPorts.forEach((host, openPort) -> {
                System.out.println("Host: " + host + " Port: " + openPort);
            });


            Utils.printSeparator();
            log.info("Scanning took {}ms", (System.currentTimeMillis() - start));
            return openPorts.size();
        }
        return -1;
    }

    // public int multiThreadedPortScanning(List<String> hosts, int port) {
    //     System.out.println("Multi-threaded port scanning");
    //     if (hosts.get(0) != null) {
    //         long start = System.currentTimeMillis();
    //         TCPConnector connector = new TCPConnector();
    //         List<Integer> openPorts = connector.scanNetworkMulti(hosts, port);
    //         System.out.println(openPorts.size() + " open ports found");
    //         Utils.printSeparator();
    //         log.info("Scanning took {}ms", (System.currentTimeMillis() - start));
    //         return openPorts.size();
    //     }
    //     return -1;
    // }
}
