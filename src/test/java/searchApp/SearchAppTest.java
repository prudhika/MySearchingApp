package searchApp;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class SearchAppTest {

    @Test
    public void testMain() {
        // Setup

        // Run the test
        SearchApp.main(new String[]{"value"});

        // Verify the results
    }

    @Test
    public void testScanUserInput() {
        // Setup
        final Scanner scanner = new Scanner(new ByteArrayInputStream("content".getBytes()), "charsetName");

        // Run the test
        SearchApp.scanUserInput(scanner);

        // Verify the results
    }
}
