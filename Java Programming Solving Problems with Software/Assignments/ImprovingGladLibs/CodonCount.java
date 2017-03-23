
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codonCount;
    
    public CodonCount(){
        codonCount = new HashMap<String, Integer>();
    }

    private void buildCodonMap (int start, String dna){
        codonCount.clear();
        while(start < dna.length() - 3){
            String codon = dna.substring(start, start + 3);
            if (codonCount.containsKey(codon)){
                codonCount.put(codon, codonCount.get(codon) + 1);
            }
            else{
                codonCount.put(codon, 1);
            }
            start += 3;
        }
        
    }
    
    private String getMostCommonCodon (){
        int largest = 0;
        String codon = "";
        for (String s : codonCount.keySet()){
            if (codonCount.get(s) > largest){
                largest = codonCount.get(s);
                codon = s;
            }
        }
        return codon;
    }
    
    private void printCodonCounts (int start, int end){
        for (String s : codonCount.keySet()){
            if (codonCount.get(s) >= start && codonCount.get(s) <= end){
                System.out.println(s + " " + codonCount.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource resource = new FileResource();
        String dna = resource.asString();
        dna = dna.trim();
        dna = dna.toUpperCase();
        for (int i = 0; i < 3; i++){
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + 
                " results in " + codonCount.size() + " unique codons");
            String mc = getMostCommonCodon();
            System.out.println("and most common codon is " + mc + 
            " with count " + codonCount.get(mc));
              System.out.println("Counts of codons between 1 and 5 inclusive are:");
              printCodonCounts(7, 7);
        }
        
    }

}
