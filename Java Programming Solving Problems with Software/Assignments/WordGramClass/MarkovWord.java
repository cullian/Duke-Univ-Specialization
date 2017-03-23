
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  //   random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        int len = myText.length;
        while(pos < len){
            int start = indexOf(myText, kGram, pos);
            if(start == -1){break;}
            if(start >= len - kGram.length()){break;}
            String next = myText[start + kGram.length()];
            follows.add(next);
            pos = start + 1;
            
        }
        return follows;
    }

    private int indexOf(String[] words, WordGram target, int start){
        for(int i = start; i < words.length - target.length(); i++){
            WordGram w = new WordGram(words, i, target.length());
            if(w.equals(target)){
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf(){
        String s = "this is just a test yes this is a simple test";
        String[] words = s.split("\\s+");
        WordGram target = new WordGram(words, 0, 2);

        System.out.println("text : " + s);
        System.out.println("target : " + target);

        int start = 0;
        int i = indexOf(words, target, start);
        System.out.println("indexOf(words, " + target + ", " + start + ") = " + i);
 
        start = 1;
        i = indexOf(words, target, start);
        System.out.println("indexOf(words, " + target + ", " + start + ") = " + i);
 
       
    }
}
