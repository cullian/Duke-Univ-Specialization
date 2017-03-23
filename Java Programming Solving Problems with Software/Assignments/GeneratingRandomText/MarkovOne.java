
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne {
    private String myText;
	private Random myRandom;
	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - 1);
		String key = myText.substring(index, index + 1);
		sb.append(key);
		
		for(int k=0; k < numChars - 1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if(follows.size() == 0){break;}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = next;
		}
		
		return sb.toString();
	}
	
	public ArrayList<String> getFollows(String key){
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
// 	    System.out.println(myText);
//         System.out.println("Text Length: " + myText.length());
//         System.out.println("key Length: " + key.length());
	    while(pos < myText.length()){
	        int startOfKey = myText.indexOf(key, pos);
	        if(startOfKey == -1){break;}
	        int endOfKey = startOfKey + key.length();
	        if( endOfKey > myText.length() - 1){break;}
// 	        System.out.println("Start: " + startOfKey + " End: " + endOfKey);
	        String next = myText.substring(endOfKey, endOfKey + 1);
	        follows.add(next);
	        pos = endOfKey;
	    }
	    return follows;
	}
}

