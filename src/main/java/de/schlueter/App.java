package de.schlueter;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class App implements Runnable {
    @Option(names = {"-h", "--host"}, description = "Host to scan for") private String host;
    @Option(names = {"-p", "--port"}, description = "Port to scan for") private int port;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        if (host != null && port != 0) {
            TCPConnector connector = new TCPConnector(host, port);
            if (connector.connect()) {
                System.out.println("Connection to " + host + ":" + port + " successful");
            } else {
                System.out.println("Connection to " + host + ":" + port + " failed");
            }
        } else {
            System.out.println("Please provide a host and a port");
        }
    }
}
