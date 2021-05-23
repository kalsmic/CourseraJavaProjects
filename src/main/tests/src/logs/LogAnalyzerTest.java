package main.tests.src.logs;

import main.company.logs.LogAnalyzer;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class LogAnalyzerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readFile() {
    }

    @Test
    void countUniqueIPs() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/short-test_log");
        Assert.assertEquals(4, lg.countUniqueIPs());
    }

    @Test
    void countUniqueIPs2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog2_log");
        Assert.assertEquals(45, lg.countUniqueIPs());
    }

    @Test
    void printAll() {
    }

    @Test
    void printAllHigherThanNum() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog1_log");
        lg.printAllHigherThanNum(400);

    }

    @Test
    void uniqueIPVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog-short_log");
        ArrayList<String> uniqueIpVisits = lg.uniqueIPVisitsOnDay("Sep 14");
        Assert.assertEquals(2, uniqueIpVisits.size());

        uniqueIpVisits = lg.uniqueIPVisitsOnDay("Sep 30");
        Assert.assertEquals(3, uniqueIpVisits.size());

    }

    @Test
    void uniqueIPVisitsOnDay2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog1_log");
        ArrayList<String> uniqueIpVisits = lg.uniqueIPVisitsOnDay("Mar 17");
        Assert.assertEquals(16, uniqueIpVisits.size());

        uniqueIpVisits = lg.uniqueIPVisitsOnDay("Mar 24");
        Assert.assertEquals(35, uniqueIpVisits.size());


    }

    @Test
    void uniqueIPVisitsOnDay3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog2_log");

        ArrayList<String> uniqueIpVisits = lg.uniqueIPVisitsOnDay("Sep 24");
        Assert.assertEquals(14, uniqueIpVisits.size());
    }

    @Test
    void countUniqueIPsInRange() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/short-test_log");
        Assert.assertEquals(4, lg.countUniqueIPsInRange(200, 299));
        Assert.assertEquals(2, lg.countUniqueIPsInRange(300, 399));
    }

    @Test
    void countUniqueIPsInRange2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog1_log");
        Assert.assertEquals(65, lg.countUniqueIPsInRange(200, 299));
    }

    @Test
    void countUniqueIPsInRange3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog2_log");

        Assert.assertEquals(23, lg.countUniqueIPsInRange(400, 499));
    }

    @Test
    void countVisitsPerIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/short-test_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        Assert.assertEquals(1, (int) counts.get("157.55.39.203"));
        Assert.assertEquals(3, (int) counts.get("152.3.135.44"));
        Assert.assertEquals(2, (int) counts.get("152.3.135.63"));
        Assert.assertEquals(1, (int) counts.get("110.76.104.12"));

    }

    @Test
    void mostNumberVisitsByIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog3-short_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        int mostVisits = lg.mostNumberVisitsByIP(counts);
        Assert.assertEquals(3, mostVisits);
    }

    @Test
    void mostNumberVisitsByIP2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog1_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        int mostVisits = lg.mostNumberVisitsByIP(counts);
        Assert.assertEquals(133, mostVisits);
    }

    @Test
    void mostNumberVisitsByIP3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog2_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        int mostVisits = lg.mostNumberVisitsByIP(counts);
        Assert.assertEquals(63, mostVisits);
    }

    @Test
    void iPsMostVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog3-short_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        ArrayList<String> mostVisits = lg.iPsMostVisits(counts);
        Assert.assertEquals(2, mostVisits.size());
    }

    @Test
    void iPsMostVisits2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog1_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        ArrayList<String> mostVisits = lg.iPsMostVisits(counts);
        Assert.assertEquals(1, mostVisits.size());
        Assert.assertEquals("84.190.182.222", mostVisits.get(0));
    }

    @Test
    void iPsMostVisits3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog2_log");
        HashMap<String, Integer> counts = lg.countVisitsPerIP();

        ArrayList<String> mostVisits = lg.iPsMostVisits(counts);
        Assert.assertEquals(1, mostVisits.size());
        Assert.assertEquals("188.162.84.63", mostVisits.get(0));
    }

    @Test
    void iPsForDays() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog3-short_log");

        HashMap<String, ArrayList<String>> mostVisits = lg.iPsForDays();
        Assert.assertEquals(4, mostVisits.get("Sep 21").size());
        Assert.assertEquals(1, mostVisits.get("Sep 14").size());
        Assert.assertEquals(5, mostVisits.get("Sep 30").size());
    }

    @Test
    void dayWithMostIPVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog3-short_log");

        HashMap<String, ArrayList<String>> IPsForDays = lg.iPsForDays();
        String dayWithMostIPVisits = lg.dayWithMostIPVisits(IPsForDays);

        Assert.assertEquals("Sep 30", dayWithMostIPVisits);
    }

    @Test
    void dayWithMostIPVisits2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog1_log");

        HashMap<String, ArrayList<String>> IPsForDays = lg.iPsForDays();
        String dayWithMostIPVisits = lg.dayWithMostIPVisits(IPsForDays);

        Assert.assertEquals("Mar 24", dayWithMostIPVisits);
    }

    @Test
    void dayWithMostIPVisits3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog2_log");

        HashMap<String, ArrayList<String>> IPsForDays = lg.iPsForDays();
        String dayWithMostIPVisits = lg.dayWithMostIPVisits(IPsForDays);

        Assert.assertEquals("Sep 24", dayWithMostIPVisits);
    }

    @Test
    void iPsWithMostVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog3-short_log");

        HashMap<String, ArrayList<String>> ipRecordsByDay = lg.iPsForDays();
        ArrayList<String> dayWithMostIPVisits = lg.iPsWithMostVisitsOnDay(ipRecordsByDay, "Sep 30");
        Assert.assertTrue(dayWithMostIPVisits.contains("61.15.121.171"));
        Assert.assertTrue(dayWithMostIPVisits.contains("177.4.40.87"));
    }

    @Test
    void iPsWithMostVisitsOnDay2() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog1_log");

        HashMap<String, ArrayList<String>> ipRecordsByDay = lg.iPsForDays();
        ArrayList<String> dayWithMostIPVisits = lg.iPsWithMostVisitsOnDay(ipRecordsByDay, "Mar 17");
        System.out.println(dayWithMostIPVisits.toString());
        Assert.assertTrue(dayWithMostIPVisits.contains("200.129.163.70"));
        Assert.assertEquals(1, dayWithMostIPVisits.size());
    }

    @Test
    void iPsWithMostVisitsOnDay3() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/weblog2_log");

        HashMap<String, ArrayList<String>> ipRecordsByDay = lg.iPsForDays();
        ArrayList<String> dayWithMostIPVisits = lg.iPsWithMostVisitsOnDay(ipRecordsByDay, "Sep 30");
        System.out.println(dayWithMostIPVisits.toString());
        Assert.assertEquals(2, dayWithMostIPVisits.size());

        Assert.assertTrue(dayWithMostIPVisits.contains("106.220.155.36"));
        Assert.assertTrue(dayWithMostIPVisits.contains("66.67.61.44"));
    }
}