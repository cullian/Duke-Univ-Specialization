
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int boysNames = 0;
        int girlsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boysNames++;
            }
            else {
                totalGirls += numBorn;
                girlsNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total names = " + totalNames);
        System.out.println("female names = " + girlsNames);
        System.out.println("male names = " + boysNames);
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank (int year, String name, String gender){
        int count = 0;
//        FileResource fr = new FileResource("data\\us_babynames_by_year\\yob" + year + "short.csv");
        FileResource fr = new FileResource("data\\us_babynames_by_year\\yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (rec.get(0).equals(name)){
                    return count;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank () {
        System.out.println("Rank of Mason Male 2012 : " + getRank(1971, "Frank", "M"));
    }
    
    public String getName  (int year, int rank, String gender){
        int count = 0;
//        FileResource fr = new FileResource("data\\us_babynames_by_year\\yob" + year + "short.csv");
        FileResource fr = new FileResource("data\\us_babynames_by_year\\yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (count == rank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void testGetName () {
        System.out.println("Name of rank 1 Male 2012 : " + getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender){
        String newName = getName(newYear, getRank(year, name, gender), gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
        
    }
    
    public void testWhatIsNameInYear () {
        whatIsNameInYear("Owen", 1974, 2014, "M");

    }
    
    public void testYearOfHighestRank () {
        System.out.println(yearOfHighestRank("Mich", "M"));

    }
    
    public int yearOfHighestRank (String name, String gender) {
        int highestRankYear = -1;
        int highestRank = 999999999;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentRank = getRankFromFile(name, gender, fr.getCSVParser(false));

            if (currentRank == -1) {
                continue;
            }
            if (currentRank < highestRank) {
                highestRank = currentRank;
                highestRankYear = Integer.parseInt(f.getName().substring(3,7));
            }
        }

        return highestRankYear;
    }
    
    public void testGetRankFromFile () {
        FileResource fr = new FileResource();
        System.out.println("Emily F");
        System.out.println(getRankFromFile("Frank", "M", fr.getCSVParser(false)));
    }
    
    public int getRankFromFile (String name, String gender, CSVParser parser){
        int count = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (rec.get(0).equals(name)){
                    return count;
                }
            }
        }
        return -1;
    }
    
    public double getAverageRank (String name, String gender){
        double averageRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currentRank = getRankFromFile(name, gender, fr.getCSVParser(false));

            if (currentRank != -1) {
                averageRank += currentRank;
                count++;
            }
        }
        if (count == 0) {
            return -1.0;
        }
        return averageRank / count;
    }
    
    public void testGetAverageRank () {
        System.out.println("Average rank for Susan Female");
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        int totalBirths = 0;

        FileResource fr = new FileResource("data\\us_babynames_by_year\\yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)){
                    return totalBirths;
                }
            totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }
 
    public void testGetTotalBirthsRankedHigher (){
        System.out.println("Total births ranked higher for 2012 Ethan Male");
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
    }

    public String getNameFromFileFromRank (int rank, String gender, CSVParser parser){
        int count = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (count == rank){
                    return rec.get(0);
                }
            }
        }
        return "NOT FOUND";
    }
    
    public void testGetNameFromFileFromRank () {
        FileResource fr = new FileResource();
        System.out.println("Rank");
        System.out.println(getNameFromFileFromRank(450, "M", fr.getCSVParser(false)));
    }
    
    
}
