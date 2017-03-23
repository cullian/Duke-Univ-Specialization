
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> charCount;
    
    public CharactersInPlay() {
        charNames = new ArrayList<String>();
        charCount = new ArrayList<Integer>();
    }
    
    private void update (String person){
//         person = person.toLowerCase();
        int index = charNames.indexOf(person);

        if (index == -1){
            charNames.add(person);
            charCount.add(1);
        }
        else {
            int freq = charCount.get(index);
            charCount.set(index, freq+1);
        }
    }

    private void findAllCharacters (){
        charNames.clear();
        charCount.clear();
        FileResource resource = new FileResource();
        
        for(String s : resource.lines()){
            int index = s.indexOf(".");
            if (index == -1){continue;}
            s = s.substring(0, index);
            update(s);
        }

    }

    public void tester (){
        findAllCharacters();
        for (int i = 0; i < charNames.size(); i++){
            if (charCount.get(i) < 6) continue;
            System.out.println(charNames.get(i) + " " + charCount.get(i));
            
        }
        System.out.println("\n");
        charactersWithNumParts(10, 15);
    }
    
    private void charactersWithNumParts (int num1, int num2){
        System.out.println("CHARACTERS WITH NUM PARTS");
        for (int i = 0; i < charNames.size(); i++){
            int parts = charCount.get(i);
            if (parts >= num1 && parts <= num2){
                System.out.println(charNames.get(i) + " " + charCount.get(i));
            }
        }
    }
}
