package de.schlueter;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class App implements Runnable {
    @Option(names = {"-h", "--host"}, description = "Host to scan for") private String host;
    @Option(names = {"-p", "--port"}, description = "Port to scan for") private String port;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
	if (host != null) {
	    System.out.println("Host: " + host);
	}
	if (port != null) {
	    System.out.println("Port: " + port);
	}
    }
}
