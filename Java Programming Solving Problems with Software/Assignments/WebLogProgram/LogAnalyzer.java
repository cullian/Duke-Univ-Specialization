
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String s : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(s);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public int countUniqueIPs() {
         HashMap<String, Integer> uniqueIPs = countVisitsPerIP();
         return uniqueIPs.size();
     }

//      public int countUniqueIPs() {
//          ArrayList<String> uniqueIPs = new ArrayList<String>();
//          for (LogEntry le : records) {
//              String ipAddr = le.getIpAddress();
//              if(!uniqueIPs.contains(ipAddr)){
//                  uniqueIPs.add(ipAddr);
//              }
//          }
//          return uniqueIPs.size();
//      }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int sCode = le.getStatusCode();
             if(sCode > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String dstr = d.toString();
             if(dstr.contains(someday)){
                 String ipAddr = le.getIpAddress();
                 if(!uniqueIPs.contains(ipAddr)){
                     uniqueIPs.add(ipAddr);
                 }
                 
             }
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         uniqueIPs.clear();
         for (LogEntry le : records) {
             int sCode = le.getStatusCode();
             if(sCode >= low && sCode <= high){
                 String ipAddr = le.getIpAddress();
                 if(!uniqueIPs.contains(ipAddr)){
                     uniqueIPs.add(ipAddr);
                 }
             }
         }
         return uniqueIPs.size();
     }

     public HashMap<String, Integer>  countVisitsPerIP(){
         HashMap<String, Integer> count = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if(!count.containsKey(ip)){
                 count.put(ip, 1);
             }
             else{
                 count.put(ip, count.get(ip) + 1);
             }
                 
         }
         return count;
     }
       
     public int  mostNumberVisitsByIP(HashMap<String, Integer> visitsPerIP){
         int max = 0;
         for (int i : visitsPerIP.values()) {
             if(i > max){max = i;}
         }
         return max;
     }
       
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitsPerIP){
         ArrayList<String> maxVisitsIP = new ArrayList<String>();
         int max = mostNumberVisitsByIP(visitsPerIP);
         for (String s : visitsPerIP.keySet()) {
             if(visitsPerIP.get(s) == max){maxVisitsIP.add(s);}
         }
         return maxVisitsIP;
     }

     public HashMap<String, ArrayList<String>>  iPsForDays(){
         HashMap<String, ArrayList<String>> dayz = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String day = d.toString().substring(4, 10);
             String ip = le.getIpAddress();
             if(!dayz.containsKey(day)){
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ip);
                 dayz.put(day, ips);
             }
             else{
                 dayz.get(day).add(ip);
             }
                 
         }
         return dayz;
     }

     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> visitsPerIP){
         String day = "";
         int max = 0;
         for (String s : visitsPerIP.keySet()) {
             if(visitsPerIP.get(s).size() > max){
                 max = visitsPerIP.get(s).size();
                 day = s;
             }
         }
         return day;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> visitsPerIP, String day){
         HashMap<String, Integer> ipCount = new HashMap<String, Integer>();
//          System.out.println("HashMap visitsPerIP:");
//          System.out.println(visitsPerIP);
//          for(String key : visitsPerIP.keySet()){
//              System.out.println("key : " + key + " l: " + key.length());
//          }
//          System.out.println("day = " + day + " l : " + day.length());
//          System.out.println("visitsPerIP.containsKey(day) = " + visitsPerIP.containsKey(day));
         ArrayList<String> iPsOnDay = visitsPerIP.get(day);
//          System.out.println("iPsOnDay = " + iPsOnDay);
         for (String s : iPsOnDay) {
             if(!ipCount.containsKey(s)){
                 ipCount.put(s, 1);
             }
             else{
                 ipCount.put(s, ipCount.get(s) + 1);
             }
         }
         return iPsMostVisits(ipCount);
     }

}
