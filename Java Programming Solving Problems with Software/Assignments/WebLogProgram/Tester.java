
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
     
     public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
     }
     
     public void testPrintAllHigherThanNum () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
     }

     public void testUniqueIPVisitsOnDay () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> l1 = la.uniqueIPVisitsOnDay("Sep 24");
//         ArrayList<String> l2 = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("There are " + l1.size() + " IPs on Sep 24");
//         System.out.println("There are " + l2.size() + " IPs on Sep 30");
     }
     
     public void testCountUniqueIPsInRange () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int u1 = la.countUniqueIPsInRange(200, 299);
        int u2 = la.countUniqueIPsInRange(300, 399);
        System.out.println("There are " + u1 + " IPs in range 200-299");
        System.out.println("There are " + u2 + " IPs in range 300-399");
     }
     
     public void testCountVisitsPerIP () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
//        la.readFile("weblog1_log");
        HashMap<String, Integer> c = la.countVisitsPerIP();
        System.out.println(c);
     }

     public void testMostNumberVisitsByIP () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
//        la.readFile("weblog1_log");
        int c = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println(c);
     }

     public void testIPsMostVisits () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
//        la.readFile("weblog1_log");
        ArrayList<String> v = la.iPsMostVisits(la.countVisitsPerIP());
        System.out.println(v);
     }

     public void testIPsForDays () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
//        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> v = la.iPsForDays();
        System.out.println(v);
     }


     public void testDayWithMostIPVisits () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
//        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> v = la.iPsForDays();
        System.out.println(v);
        String d = la.dayWithMostIPVisits(v);
        System.out.println(d);
     }


     public void testIPsWithMostVisitsOnDay () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> v = la.iPsForDays();
        ArrayList<String>  d = la.iPsWithMostVisitsOnDay(v, "Sep 29");
        System.out.println(d);
     }

}
