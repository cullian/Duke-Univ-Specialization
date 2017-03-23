
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne m1 = new MarkovOne();
        String test = "this is a test yes this is a test.";
        m1.setTraining(test);
        ArrayList<String> followers = m1.getFollows("t");
        System.out.println(followers);
        followers = m1.getFollows("e");
        System.out.println(followers);
        followers = m1.getFollows("es");
        System.out.println(followers);
        followers = m1.getFollows(".");
        System.out.println(followers);
        followers = m1.getFollows("t.");
        System.out.println(followers);
    }
    
    public void testGetFollowsWithFile (){
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		
		ArrayList<String> followers = markov.getFollows("he");
		
		System.out.println(followers.size());
    }
}
