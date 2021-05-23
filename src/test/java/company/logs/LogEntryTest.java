package test.java.company.logs;

import main.java.company.logs.LogEntry;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogEntryTest {
    private Date date = new Date();
    private LogEntry logEntry = new LogEntry("192.168.1.1", date, "GET /favicon.ico HTTP/1.1", 200, 600);


    @Test
    void getIpAddress() {
        assertEquals("192.168.1.1", logEntry.getIpAddress());
    }

    @Test
    void getAccessTime() {
        assertEquals(date, logEntry.getAccessTime());
    }

    @Test
    void getRequest() {
        assertEquals("GET /favicon.ico HTTP/1.1", logEntry.getRequest());

    }

    @Test
    void getStatusCode() {
        assertEquals(200, logEntry.getStatusCode());

    }

    @Test
    void getBytesReturned() {
        assertEquals(600, logEntry.getBytesReturned());

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