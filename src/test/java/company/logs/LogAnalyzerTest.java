package test.java.company.logs;

import main.java.company.logs.LogAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogAnalyzerTest {

    @Test
    void countUniqueIPs() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/short-test_log");
        assertEquals(4, lg.countUniqueIPs());
    }

    @Test
    void countUniqueIPs2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog2_log");
        assertEquals(45, lg.countUniqueIPs());
    }

    @Test
    void printAllHigherThanNum() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog1_log");
        lg.printAllHigherThanNum(400);

    }

    @Test
    void uniqueIPVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog-short_log");
        ArrayList<String> uniqueIpVisits = lg.uniqueIPVisitsOnDay("Sep 14");
        assertEquals(2, uniqueIpVisits.size());

        uniqueIpVisits = lg.uniqueIPVisitsOnDay("Sep 30");
        assertEquals(3, uniqueIpVisits.size());

    }

    @Test
    void uniqueIPVisitsOnDay2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog1_log");
        ArrayList<String> uniqueIpVisits = lg.uniqueIPVisitsOnDay("Mar 17");
        assertEquals(16, uniqueIpVisits.size());

        uniqueIpVisits = lg.uniqueIPVisitsOnDay("Mar 24");
        assertEquals(35, uniqueIpVisits.size());


    }

    @Test
    void uniqueIPVisitsOnDay3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog2_log");

        ArrayList<String> uniqueIpVisits = lg.uniqueIPVisitsOnDay("Sep 24");
        assertEquals(14, uniqueIpVisits.size());
    }

    @Test
    void countUniqueIPsInRange() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/short-test_log");
        assertEquals(4, lg.countUniqueIPsInRange(200, 299));
        assertEquals(2, lg.countUniqueIPsInRange(300, 399));
    }

    @Test
    void countUniqueIPsInRange2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog1_log");
        assertEquals(65, lg.countUniqueIPsInRange(200, 299));
    }

    @Test
    void countUniqueIPsInRange3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog2_log");

        assertEquals(23, lg.countUniqueIPsInRange(400, 499));
    }

    @Test
    void countVisitsPerIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/short-test_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        assertEquals(1, (int) counts.get("157.55.39.203"));
        assertEquals(3, (int) counts.get("152.3.135.44"));
        assertEquals(2, (int) counts.get("152.3.135.63"));
        assertEquals(1, (int) counts.get("110.76.104.12"));

    }

    @Test
    void mostNumberVisitsByIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog3-short_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        int mostVisits = lg.mostNumberVisitsByIP(counts);
        assertEquals(3, mostVisits);
    }

    @Test
    void mostNumberVisitsByIP2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog1_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        int mostVisits = lg.mostNumberVisitsByIP(counts);
        assertEquals(133, mostVisits);
    }

    @Test
    void mostNumberVisitsByIP3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog2_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        int mostVisits = lg.mostNumberVisitsByIP(counts);
        assertEquals(63, mostVisits);
    }

    @Test
    void iPsMostVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog3-short_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        ArrayList<String> mostVisits = lg.iPsMostVisits(counts);
        assertEquals(2, mostVisits.size());
    }

    @Test
    void iPsMostVisits2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog1_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        ArrayList<String> mostVisits = lg.iPsMostVisits(counts);
        assertEquals(1, mostVisits.size());
        assertEquals("84.190.182.222", mostVisits.get(0));
    }

    @Test
    void iPsMostVisits3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog2_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        ArrayList<String> mostVisits = lg.iPsMostVisits(counts);
        assertEquals(1, mostVisits.size());
        assertEquals("188.162.84.63", mostVisits.get(0));
    }

    @Test
    void iPsForDays() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog3-short_log");

        HashMap<String, ArrayList<String>> mostVisits = lg.iPsForDays();
        assertEquals(4, mostVisits.get("Sep 21").size());
        assertEquals(1, mostVisits.get("Sep 14").size());
        assertEquals(5, mostVisits.get("Sep 30").size());
    }

    @Test
    void dayWithMostIPVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog3-short_log");

        HashMap<String, ArrayList<String>> IPsForDays = lg.iPsForDays();
        String dayWithMostIPVisits = lg.dayWithMostIPVisits(IPsForDays);

        assertEquals("Sep 30", dayWithMostIPVisits);
    }

    @Test
    void dayWithMostIPVisits2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog1_log");

        HashMap<String, ArrayList<String>> IPsForDays = lg.iPsForDays();
        String dayWithMostIPVisits = lg.dayWithMostIPVisits(IPsForDays);

        assertEquals("Mar 24", dayWithMostIPVisits);
    }

    @Test
    void dayWithMostIPVisits3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog2_log");

        HashMap<String, ArrayList<String>> IPsForDays = lg.iPsForDays();
        String dayWithMostIPVisits = lg.dayWithMostIPVisits(IPsForDays);

        assertEquals("Sep 24", dayWithMostIPVisits);
    }

    @Test
    void iPsWithMostVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog3-short_log");

        HashMap<String, ArrayList<String>> ipRecordsByDay = lg.iPsForDays();
        ArrayList<String> dayWithMostIPVisits = lg.iPsWithMostVisitsOnDay(ipRecordsByDay, "Sep 30");
        assertTrue(dayWithMostIPVisits.contains("61.15.121.171"));
        assertTrue(dayWithMostIPVisits.contains("177.4.40.87"));
    }

    @Test
    void iPsWithMostVisitsOnDay2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog1_log");

        HashMap<String, ArrayList<String>> ipRecordsByDay = lg.iPsForDays();
        ArrayList<String> dayWithMostIPVisits = lg.iPsWithMostVisitsOnDay(ipRecordsByDay, "Mar 17");
        System.out.println(dayWithMostIPVisits.toString());
        assertTrue(dayWithMostIPVisits.contains("200.129.163.70"));
        assertEquals(1, dayWithMostIPVisits.size());
    }

    @Test
    void iPsWithMostVisitsOnDay3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/weblog2_log");

        HashMap<String, ArrayList<String>> ipRecordsByDay = lg.iPsForDays();
        ArrayList<String> dayWithMostIPVisits = lg.iPsWithMostVisitsOnDay(ipRecordsByDay, "Sep 30");
        System.out.println(dayWithMostIPVisits.toString());
        assertEquals(2, dayWithMostIPVisits.size());

        assertTrue(dayWithMostIPVisits.contains("106.220.155.36"));
        assertTrue(dayWithMostIPVisits.contains("66.67.61.44"));
    }
}