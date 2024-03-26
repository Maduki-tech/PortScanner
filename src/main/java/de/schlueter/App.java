package de.schlueter;

import java.util.ArrayList;
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
        List<String> hostSplitter = new ArrayList<>(Arrays.asList(host.split(",")));

        List<String> hostsArray = new ArrayList<>();

        for (String internHost : hostSplitter) {
            if (internHost.contains("*")) {
                String hostPrefix = internHost.substring(0, internHost.indexOf("*"));
                for (int i = 0; i < 256; i++) {
                    String host = hostPrefix + i;
                    hostsArray.add(host);
                }
            }else{
                hostsArray.add(internHost);
            }
            
        }

        // hostsArray.stream().forEach(System.out::println);

        // TODO: Implement the connection to a single host and port
        // if (hostsArray.get(0) != null && port != 0) {
        //     TCPConnector connector = new TCPConnector();
        //     if (connector.connect()) {
        //         System.out.println("Connection to " + host + ":" + port + " successful");
        //     } else {
        //         System.out.println("Connection to " + host + ":" + port + " failed");
        //     }
        // }

        portScanner.simplePortScanning(hostsArray, port);
        // portScanner.multiThreadedPortScanning(hostsArray, port);
    }
}
