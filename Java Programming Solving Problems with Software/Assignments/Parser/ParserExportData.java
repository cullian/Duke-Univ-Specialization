
/**
 * Write a description of ParserExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParserExportData {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "germany"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
    }
    
    public void tester2(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "nauru"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo (CSVParser parser, String country){
        for(CSVRecord rec : parser){
            String c = rec.get("Country");
            if(rec.get("Country").equalsIgnoreCase(country)){
                String e = rec.get("Exports");
                String d = rec.get("Value (dollars)");
                String rec_to_return = c + ": " + e + ": " + d;
                return rec_to_return;
            }
        }
        return("NOT FOUND");
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord rec : parser){
            String ex = rec.get("Exports");
            if(ex.contains(exportItem1) && ex.contains(exportItem2)){
                String c = rec.get("Country");
                System.out.println(c);
            }
        }
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem){
        int c = 0;
        for(CSVRecord rec : parser){
            String ex = rec.get("Exports");
            if(ex.contains(exportItem)){
                c++;
            }
        }
        return c;
    }
    
    public void bigExporters  (CSVParser parser, String amount){
        for(CSVRecord rec : parser){
            String d = rec.get("Value (dollars)");
            if(d.length() > amount.length()){
                String c = rec.get("Country");
                System.out.println(c + " " + d);
            }
        }
    }
}
