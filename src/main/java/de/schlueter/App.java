package de.schlueter;

import java.util.Arrays;
import java.util.List;
import picocli.CommandLine;
import picocli.CommandLine.Option;

public class App implements Runnable {
    PortScanner portScanner = new PortScanner();

    @Option(names = {"-h", "--host"}, description = "Host to scan for") private String host;
    @Option(names = {"-p", "--port"}, description = "Port to scan for") private int port;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        // parse host for , separated values and put them in a list
        List<String> hosts = Arrays.asList(host.split(","));

        hosts.stream().forEach(System.out::println);

        if (hosts.get(0) != null && port != 0) {
            TCPConnector connector = new TCPConnector(host, port);
            if (connector.connect()) {
                System.out.println("Connection to " + host + ":" + port + " successful");
            } else {
                System.out.println("Connection to " + host + ":" + port + " failed");
            }
        }

        portScanner.simplePortScanning(hosts, port);
        portScanner.multiThreadedPortScanning(hosts, port);
    }
}
