
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index + 1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
//  	System.out.println("key1 : " + key1);
//  	System.out.println("key2 : " + key2);
//  	System.out.println(follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    int len = myText.length;
	    while(pos < len){
	        int start = indexOf(myText, key1, key2, pos);
	        if(start == -1){break;}
	        if(start >= len - 2){break;}
	        String next = myText[start + 2];
	        follows.add(next);
	        pos = start + 2;
	        
	    }
	    return follows;
    }

	private int indexOf(String[] words, String target1, String target2, int start){
	    for(int i = start; i < words.length - 1; i++){
	        if(words[i].equals(target1) && words[i + 1].equals(target2)){
	            return i;
	        }
	    }
	    return -1;
	}
	
	public void testIndexOf(){
	    String s = "this is just a test yes this is a simple test";
	    String[] a = s.split("\\s+");

	    System.out.println("text : " + s);
	    System.out.println("split : " + a);

	    String ss1 = "this";
	    String ss2 = "is";
	    int ind = 0;
	    int i = indexOf(a, ss1, ss2, ind);
	    System.out.println("indexOf(text, " + ss1 + ", " + ss2 + ", " + ind + ") = " + i);

	    ss1 = "just";
	    ss2 = "a";
	    ind = 0;
	    i = indexOf(a, ss1, ss2, ind);
	    System.out.println("indexOf(text, " + ss1 + ", " + ss2 + ", " + ind + ") = " + i);

	   
	}
	
	public void testGetFollows(){
	    String s = "this is just a test yes this is a simple test";
	    String[] a = s.split("\\s+");
	    myText = a;

	    System.out.println("text : " + s);
	    System.out.println("split : " + a);

	    String ss1 = "this";
	    String ss2 = "is";
	    ArrayList<String> i = getFollows(ss1, ss2);
	    System.out.println("getFollows(" + ss1 + ", " + ss2 + ") = ");
	    System.out.println(i);

	    ss1 = "just";
	    ss2 = "a";
	    i = getFollows(ss1, ss2);
	    System.out.println("getFollows(" + ss1 + ", " + ss2 + ") = ");
	    System.out.println(i);

	   
	}
}

