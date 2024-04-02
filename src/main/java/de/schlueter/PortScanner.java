package de.schlueter;

import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
/**
 * PortScanner
 */
@NoArgsConstructor
public class PortScanner {
    public int simplePortScanning(List<String> hosts, int port) {
        if (!hosts.isEmpty() && port == 0) {
            long start = System.currentTimeMillis();
            TCPConnector connector = new TCPConnector();
            Map<String, List<Integer>> hostListWithPorts = connector.scanNetwork(hosts, port);
            // size of openPorts
            System.out.println(hostListWithPorts.size() + " open ports found");
            hostListWithPorts.forEach((host, openPorts) -> {
                System.out.println("Host: " + host + " has open ports: " + openPorts);
            });

            Utils.printSeparator();
            Utils.timeTaken(start);
        }
        return -1;
    }
}
