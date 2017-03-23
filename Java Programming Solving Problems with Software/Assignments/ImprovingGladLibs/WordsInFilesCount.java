
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;


public class WordsInFilesCount {
    private HashMap<String, ArrayList<String>> words;
    
    public WordsInFilesCount(){
        words = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile (File f){
        FileResource fr = new FileResource(f);
        for (String s : fr.words()){
            ArrayList<String> files;
            if(words.containsKey(s)){
                files = words.get(s);
            }
            else{
                files = new ArrayList<String>();
            }
            if(!files.contains(f.getName())){files.add(f.getName());}
            words.put(s, files);
        }
    }

    private void buildWordFileMap (){
        words.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    private int maxNumber (){
        int l = 0;
        for (String s : words.keySet()){
            if(words.get(s).size() > l) {
                l = words.get(s).size();
            }
        }
        return l;
    }

    private ArrayList<String> wordsInNumFiles (int number){
        ArrayList<String> list = new ArrayList<String>();
        for (String s : words.keySet()){
            if(words.get(s).size() == number) {
                if (!list.contains(s)){list.add(s);}
            }
        }
        return list;
    }

    private void printFilesIn (String word){
        for (String s : words.get(word)){
            System.out.println(s);
        }
    }


    public void tester (){
        buildWordFileMap();
        int m = maxNumber();
        System.out.println("Max num files any word is in : " + m);
//        ArrayList<String> w = wordsInNumFiles(m);
//        System.out.println("They are : ");
//         for (String s : w){
//             System.out.println(s);
//         }

        
        System.out.println("number of words that occur in all files : " + 
            words.size());
            
        int c = 0;
        for(String s : words.keySet()){
            if(words.get(s).size() == 4){c++;}
        }
        
        System.out.println("Words in 4 of the files: " + c);
        c=0;
        for(String s : words.keySet()){
            if(words.get(s).size() == 7){c++;}
        }
        
        System.out.println("Words in 7 of the files: " + c);
        

        ArrayList<String> sad = words.get("tree");
        for (String s : sad){
            System.out.println(s);
        }
        
//         for (String s : w){
//             System.out.println("\n");
//             System.out.println("\"" + s + "\" appears in the files: ");
//             printFilesIn(s);
//         }
//         System.out.println("\n");
//         System.out.println("COMPLETE LIST");
//         for (String s : words.keySet()){
//             System.out.println("\n");
//             System.out.println(s);
//             printFilesIn(s);
//         }
        
        
//         for (String s : words.get(word)){
//             System.out.println(s);
//         }
    }

}
