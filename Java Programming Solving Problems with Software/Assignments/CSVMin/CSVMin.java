
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile (CSVParser parser) {
        //start with lowestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getSmallerOfTwo(currentRow, lowestSoFar);
        }
        //The lowestSoFar is the answer
        return lowestSoFar;
    }

    public void testColdestHourInDay () {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEDT"));
    }

    public CSVRecord getSmallerOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If lowestSoFar is nothing
        if(Double.parseDouble(currentRow.get("TemperatureF")) == -9999){
            return lowestSoFar;
        }
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < lowestTemp) {
                //If so update largestSoFar to currentRow
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }

    public String fileWithColdestTemperature () {
        CSVRecord lowestSoFar = null;
        String lowestFile = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get lowest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            lowestSoFar = getSmallerOfTwo(currentRow, lowestSoFar);
            if(lowestSoFar.equals(currentRow)){
                lowestFile = f.getName();
            }
        }
        //The largestSoFar is the answer
        return lowestFile;
    }
    
    public void testFileWithColdestTemperature () {
        String smallest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + smallest);
        FileResource fr = new FileResource("nc_weather/2013/" + smallest);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord rec : fr.getCSVParser()){
            System.out.println(rec.get("DateUTC") + ": " + rec.get("TemperatureF"));
        }
        
    }

    public void testFileWithLowestHumidity () {
        String smallest = fileWithLowestHumidity();
        System.out.println("Lowest day was in file " + smallest);
        FileResource fr = new FileResource(smallest);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity on that day was " + lowest.get("Humidity"));
        System.out.println("All the Humidity on the coldest day were:");
        for(CSVRecord rec : fr.getCSVParser()){
            System.out.println(rec.get("DateUTC") + ": " + rec.get("Humidity"));
        }
        
    }

    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));

        parser = fr.getCSVParser();
        for(CSVRecord rec : parser){
            System.out.println(rec.get("DateUTC") + ": " + rec.get("Humidity"));
        }
        
    }
    
    public String fileWithLowestHumidity () {
        CSVRecord lowestSoFar = null;
        String lowestFile = "";
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            // use method to get lowest in file.
            CSVRecord currentRow = lowestHumidityInFile(parser);
            // use method to compare two records
            lowestSoFar = getLowestHumidity(currentRow, lowestSoFar);
            if(lowestSoFar.equals(currentRow)){
                lowestFile = f.getParent() + "\\" + f.getName();
            }
        }
        //The largestSoFar is the answer
        return lowestFile;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        //start with lowestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getLowestHumidity(currentRow, lowestSoFar);
        }
        //The lowestSoFar is the answer
        return lowestSoFar;
        
    }
    
    public CSVRecord getLowestHumidity (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If lowestSoFar is nothing
        if(currentRow.get("Humidity") == "N/A"){
            return lowestSoFar;
        }
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        //Otherwise
        else {
            double current = Double.parseDouble(currentRow.get("Humidity"));
            double lowest = Double.parseDouble(lowestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (current < lowest) {
                //If so update largestSoFar to currentRow
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }

    public double averageTemperatureInFile (CSVParser parser) {
        double total = 0;
        int count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentTemp != -9999){
                total += currentTemp;
                count++;
            }
        }
        //The lowestSoFar is the answer
        return total / count;
        
    }
    
    public void testAverageTemperatureInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);        
        System.out.println("Average temperature in file is " + avg);
        
    }
    
    public double averageTemperatureWithHighHumidityInFile  (CSVParser parser, int value) {
        double total = 0;
        int count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if (currentTemp != -9999 && currentHumidity >= value){
                total += currentTemp;
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        //The lowestSoFar is the answer
        return total / count;
        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avg == 0) {
            System.out.println("No temperatures with that humidity");        
        }
        else{
            System.out.println("Average Temp when high Humidity is " + avg);
        }
        
    }
        
}
