package de.schlueter;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
public class PortScannerTests {
    private List<String> hosts = Arrays.asList("localhost");
    private int port = 80;

    @Test
    public void simplePortScanning_test() {
        PortScanner scanner = new PortScanner();
        int result = scanner.simplePortScanning(hosts, port);
        assertEquals(-1, result);
    }
    @Test
    public void multiThreadedPortScanning_test() {
        PortScanner scanner = new PortScanner();
        int result = scanner.multiThreadedPortScanning(hosts);
        assertEquals(18, result);
    }
}
