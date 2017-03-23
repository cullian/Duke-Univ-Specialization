
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths (FileResource resource, int [] counts){
        for (String word : resource.words()){
            int l = word.length();
            if (!Character.isLetter(word.charAt(l-1)))l--;
            if (!Character.isLetter(word.charAt(0))) l--;
            if (l > counts.length) l = counts.length - 1;
            if (l > 0) counts[l]++; 
        }    
    }
    
    public void testCountWordLengths (){
        FileResource fr = new FileResource();
        int [] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 0; i < 31; i++){
            System.out.println(i + " : " + counts[i]);
        }
        System.out.println("Length: " + indexOfMax(counts));
    
    }
    
    public int indexOfMax (int [] values){
        int max = 0;
        for (int i = 0; i < 31; i++){
            if (values[i] > max){
                max = values[i];
            }
        }
        return max;
    }
}
