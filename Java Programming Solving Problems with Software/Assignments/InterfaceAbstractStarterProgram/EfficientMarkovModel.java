
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int nChar;
    private HashMap<String, ArrayList<String>> substringFollows;
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        nChar = n;
        substringFollows = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        substringFollows.clear();
        buildMap();
//        printHashMapInfo();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - nChar);
        String key = myText.substring(index, index + nChar);
        sb.append(key);
        for(int k=0; k < numChars - nChar; k++){
            ArrayList<String> follows = getFollows(key);
//            System.out.println("key: " + key + " " + follows);
            if(follows.size() == 0){break;}
            index = myRandom.nextInt(follows.size());   
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }

	protected ArrayList<String> getFollows(String key){
	    return substringFollows.get(key);
	}
    
    public void buildMap(){
        int length = myText.length();
        String key = "";
	    String next = "";
	    for(int i = 0; i < length - nChar; i++){
            key = myText.substring(i, i + nChar);
//     	 System.out.println(myText);
//          System.out.println("Index: " + i);
//          System.out.println("text Length: " + myText.length());
//          System.out.println("key Length: " + key.length());
//          System.out.println("key: " + key );
	        next = myText.substring(i + nChar, i + nChar + 1);

// 	     System.out.println("Next: " + next);
            if(substringFollows.containsKey(key)){
                substringFollows.get(key).add(next);
            }
            else{
                ArrayList<String> follows = new ArrayList<String>();
                follows.add(next);
                substringFollows.put(key, follows);
	        }

//  	     System.out.println("key: " + key + " follows: " + substringFollows.get(key));
//  	     System.out.println("--------------------------------------------------------");
 	        
    	    
        }
        key = myText.substring(length - nChar, length);
        if(!substringFollows.containsKey(key)){
            substringFollows.put(key, new ArrayList<String>());
        }
        
        
    }
    
    public void printHashMapInfo(){
        int max = 0;
        for(String s : substringFollows.keySet()){
            //System.out.println("Key: " + s + " Value: " + substringFollows.get(s));
            if(substringFollows.get(s).size() > max){
                max = substringFollows.get(s).size();
            }
        }
        System.out.println("Number of keys: " + substringFollows.size());
        System.out.println("Largest ArrayList: " + max);
        System.out.println("Keys with max value: ");
        for(String s : substringFollows.keySet()){
            if(substringFollows.get(s).size() == max){
                System.out.println(s);
            }
        }
    }
    
    public String toString(){
        return "EfficientMarkovModel of order " + nChar + ".";
    }
}
