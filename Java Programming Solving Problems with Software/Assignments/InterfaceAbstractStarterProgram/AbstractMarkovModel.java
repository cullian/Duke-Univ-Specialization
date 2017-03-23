
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
    abstract public String getRandomText(int numChars);

	protected ArrayList<String> getFollows(String key){
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
