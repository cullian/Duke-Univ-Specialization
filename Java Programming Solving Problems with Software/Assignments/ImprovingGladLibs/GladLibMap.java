
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    
    private ArrayList<String> wordsUsed;
    private ArrayList<String> tagsConsidered;

    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordsUsed = new ArrayList<String>();
        tagsConsidered = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        wordsUsed = new ArrayList<String>();
        tagsConsidered = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", 
            "name", "color", "timeframe", "verb", "fruit"};
        for (String s : labels){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (!myMap.containsKey(label)){return "**UNKNOWN**";}
        return randomFrom(myMap.get(label));
        
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        if (!tagsConsidered.contains(w.substring(first+1,last))){
            tagsConsidered.add(w.substring(first+1,last));
        }
        String sub = getSubstitute(w.substring(first+1,last));
        if(sub.equals("**UNKNOWN**"))return prefix+sub+suffix;
        while(true){
            if(!wordsUsed.contains(sub)){
                wordsUsed.add(sub);
                break;
            }
            sub = getSubstitute(w.substring(first+1,last));
        }
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        wordsUsed.clear();
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total number of tags replaced: " + wordsUsed.size());
        System.out.println("Total number of words in map: " + totalWordsInMap());
        for (String s : myMap.keySet()){
            System.out.println(s);
        }
        System.out.println("Total number of words considered: " + totalWordsConsidered());
        for (String s : tagsConsidered){
            System.out.println(s);
        }
    }
    
    private int totalWordsInMap(){
        int t = 0;
        for (String s : myMap.keySet()){
            t += myMap.get(s).size();
        }
        return t;
    }
    
    private int totalWordsConsidered (){
        int t = 0;
        for (String s : tagsConsidered){
            t += myMap.get(s).size();
        }
        return t;
    }
    

}
