package main.java.company.logs;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

public class LogAnalyzer {
    private final ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();

    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String s : fr.lines()) {
            records.add(WebLogParser.parseEntry(s));
        }

    }

    /**
     * @return an integer representing the number of unique IP addresses.
     */
    public int countUniqueIPs() {
        // uniqueIPs starts as an empty list
        ArrayList<String> uniqueIPs = new ArrayList<>();
        // for each element le in records
        for (LogEntry le : records) {
            //  ipAddress is not in uniqueIPs
            String ipAddress = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddress)) {
                // add ipAdresss to uniqueIPs
                uniqueIPs.add(ipAddress);
            }

        }
        System.out.println("Number of unique Vistors : " + uniqueIPs.size());
        return uniqueIPs.size();
    }

    /**
     * . This method should examine all the web log entries in records
     * and print those LogEntrys that have a status code greater than num
     *
     * @param num minimum status code  value
     */
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    /**
     * @param someday in the format “MMM DD” where MMM is the first three characters of the month name with the first
     *                letter capitalized and the others in lowercase, and DD is the day in two digits
     *                (examples are “Dec 05” and “Apr 22”).
     * @return an ArrayList of Strings of unique IP addresses that had access on the given day
     */
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPVisits = new ArrayList<>();
        for (LogEntry le : records) {

            String ipAddress = le.getIpAddress();
            String date = le.getAccessTime().toString();
            if (date.toLowerCase().contains(someday.toLowerCase()) && !uniqueIPVisits.contains(ipAddress)) {
                uniqueIPVisits.add(ipAddress);
            }
        }
        return uniqueIPVisits;
    }

    /**
     * @param low  lowest status code
     * @param high highest status codes
     * @return number of unique IP addresses in records that have a status code
     * in the range from low to high, inclusive
     */
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            String ipAddress = le.getIpAddress();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddress)) {
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }

    /**
     * @return a HashMap<String, Integer> that maps an IP address to the number of times that IP address appears
     * in records,  meaning the number of times this IP address visited the website
     */
    public HashMap<String, Integer> countVisitsPerIP() {
        // Make empty hashmap to store visits
        HashMap<String, Integer> counts = new HashMap<>();

        // for each log entry in records
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            // check if ipaddress is in counts
            if (!counts.containsKey(ipAddress)) {
                // if not put iipAddress in with value 1
                counts.put(ipAddress, 1);
            } else {
                // if so update the value to be 1 more
                counts.put(ipAddress, counts.get(ipAddress) + 1);
            }
        }
        return counts;
    }


    /**
     * prints all web logs
     */
    public void printAll() {
        for (LogEntry record : records) {
            System.out.println(record);

        }
    }


    /**
     * @param counts a HashMap<String, Integer> that maps an IP address to the number of times that IP address
     *               appears in the web log file
     * @return the maximum number of visits to this website by a single IP address
     */
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int mostTimesSoFar = 0;
        for (String ip : counts.keySet()) {
            int times = counts.get(ip);
            if (times > mostTimesSoFar) {
                mostTimesSoFar = times;
            }
        }
        return mostTimesSoFar;
    }

    /**
     * @param counts a HashMap<String, Integer> that maps an IP address to the number of times that IP address
     *               appears in the web log file
     * @return an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website
     */
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        // Get the maximum number of visits
        int maximumNumVisits = mostNumberVisitsByIP(counts);
        // create an Array lists of strings to store the Ipaddress with maximum Number of visits
        ArrayList<String> maxNumVisitsList = new ArrayList<>();

        // for each ipaddress record
        for (String ip : counts.keySet()) {
            // if ipaddress visits = maximumNumVisits
            if (counts.get(ip) == maximumNumVisits) {
                // add it to the maximumNumVisits ArrayList
                maxNumVisitsList.add(ip);
            }
        }
        return maxNumVisitsList;
    }

    /**
     * @return a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of
     * IP addresses that occurred on that day (including repeated IP addresses)
     */
    public HashMap<String, ArrayList<String>> iPsForDays() {
//        Mon Mar 16
        // create HashMap to store an array list of ips visits on a given day
        HashMap<String, ArrayList<String>> iPsForDaysDict = new HashMap<>();
        // for Log Entry
        for (LogEntry le : records) {
            String day = le.getAccessTime().toString().substring(4, 10);
            String ip = le.getIpAddress();
            // check if day exists already
            if (iPsForDaysDict.containsKey(day)) {
//                if so add the ipaddress
                iPsForDaysDict.get(day).add(ip);
            } else {
//                create the day and add the ip address
                ArrayList<String> ips = new ArrayList<>();
                ips.add(ip);
                iPsForDaysDict.put(day, ips);
            }
        }
        return iPsForDaysDict;
    }

    /**
     * @param ipsForDays is a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an
     *                   ArrayList of IP addresses that occurred on that day
     * @return the day that has the most IP address visits. If there is a tie, then return any such day
     */
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsForDays) {
        int highestVisitsSoFar = 0;
        String busyDay = null;

        // for each day
        for (String day : ipsForDays.keySet()) {
            // get the total number of visits
            int currentVisits = ipsForDays.get(day).size();
            // check if current is highestSoFar
            if (currentVisits > highestVisitsSoFar) {
                // set the highestVisitsSoFar to currentVisits
                // update busyDay to current day
                highestVisitsSoFar = currentVisits;
                busyDay = day;
            }
        }
        return busyDay;
    }

    /**
     * @param ipsForDays a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an
     *                   ArrayList of IP addresses that occurred on that day
     * @param day        a String representing a day in the format “MMM DD” described above
     * @return an ArrayList<String> of IP addresses that had the most accesses on the given day.
     */
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsForDays, String day) {
        // create an arraylist of Strings to store the ip addresses
        ArrayList<String> ipsWithMostVisitsOnDay = new ArrayList<>();

//        // create new  HashMap<String, Integer> to store ip visit counts for the day;
        HashMap<String, Integer> ipsForDaysFiltered = new HashMap<>();

        // Check if day exists in the ipsForDays
        if (ipsForDays.containsKey(day)) {
            //  if it does, look at every ip that visited
            for (String ip : ipsForDays.get(day)) {
                // check if the ip is already in the ipsForDaysFiltered
                if (ipsForDaysFiltered.containsKey(ip)) {
                    // if so, increase its count by 1
                    ipsForDaysFiltered.put(ip, ipsForDaysFiltered.get(ip) + 1);
                } else {
                    // otherwise add it and give it an initial count of 1
                    ipsForDaysFiltered.put(ip, 1);
                }
            }
            // pass the ipsForDaysFiltered to the iPsMostVisits to get the ips with most visits
            ipsWithMostVisitsOnDay = iPsMostVisits(ipsForDaysFiltered);
        }
        return ipsWithMostVisitsOnDay;
    }
}
