package com.company;

import com.company.logs.LogAnalyzer;

public class Main {

    public static void main(String[] args) {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("com/data/short-test_log");
        lg.printAll();
        lg.countUniqueIPs();
        lg.printAllHigherThanNum(200);

    }
}
