package main.company;

import main.company.logs.LogAnalyzer;

public class Main {

    public static void main(String[] args) {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("main/data/short-test_log");
        lg.printAll();
        lg.countUniqueIPs();
        lg.printAllHigherThanNum(200);

    }
}
