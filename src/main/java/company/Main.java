package main.java.company;

import main.java.company.logs.LogAnalyzer;

public class Main {

    public static void main(String[] args) {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("src/main/resources/data/short-test_log");
        lg.printAll();
        lg.countUniqueIPs();
        lg.printAllHigherThanNum(200);

    }
}
