package test.java.company.logs;

import main.java.company.logs.LogEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogEntryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIpAddress() {
    }

    @Test
    void getAccessTime() {
    }

    @Test
    void getRequest() {
    }

    @Test
    void getStatusCode() {
    }

    @Test
    void getBytesReturned() {
    }


    @Test
    void testToString() {
        Date date = new Date();
        LogEntry le = new LogEntry("1.2.3.4", date,
                "example request", 200, 500);
        String expected = "1.2.3.4 " + date + " example request 200 500";
        assertEquals(le.toString(), expected);
    }
}