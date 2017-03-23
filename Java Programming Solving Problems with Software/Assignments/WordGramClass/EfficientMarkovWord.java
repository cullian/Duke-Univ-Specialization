
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> followsMap;

    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        followsMap = new HashMap<Integer, ArrayList<String>>();
}
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
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
        int key = kGram.hashCode();
        return followsMap.get(key);
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
    
    public void buildMap(){
        int length = myText.length;
        Integer key = 0;
        for(int i = 0; i < length - myOrder; i++){
            WordGram wg = new WordGram(myText, i, myOrder);
            key = wg.hashCode();
//       System.out.println(myText);
//          System.out.println("Index: " + i);
//          System.out.println("text Length: " + myText.length());
//          System.out.println("key Length: " + key.length());
//          System.out.println("key: " + key );
            String nextWord = myText[i + myOrder];
//          System.out.println("followsMap.isEmpty: " + followsMap.isEmpty());

//       System.out.println("nextWord: " + nextWord);
            if(followsMap.containsKey(key)){
                followsMap.get(key).add(nextWord);
            }
            else{
                ArrayList<String> follows = new ArrayList<String>();
                follows.add(nextWord);
                followsMap.put(key, follows);
            }

//           System.out.println("key: " + key + " follows: " + followsMap.get(key));
//           System.out.println("--------------------------------------------------------");
           
            
        }
        WordGram wg = new WordGram(myText, length - myOrder, myOrder);
        key = wg.hashCode();
        if(!followsMap.containsKey(key)){
            followsMap.put(key, new ArrayList<String>());
        }
        
        
    }
    
    public void printHashMapInfo(){
        int max = 0;
        int length = myText.length;
        for(int i = 0; i < length - myOrder + 1; i++){
            WordGram wg = new WordGram(myText, i, myOrder);
            int key = wg.hashCode();
//        for(int s : followsMap.keySet()){
            System.out.println(wg + "\tKey: " + key + "\tValue: " + followsMap.get(key));
            if(followsMap.get(key).size() > max){
                max = followsMap.get(key).size();
            }
        }
        System.out.println("Number of keys: " + followsMap.size());
        System.out.println("Largest ArrayList: " + max);
        System.out.println("Keys with max value: ");
        for(int s : followsMap.keySet()){
            if(followsMap.get(s).size() == max){
                System.out.println(s);
            }
        }
    }
    
}
