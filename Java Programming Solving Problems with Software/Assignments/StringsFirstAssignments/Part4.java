
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;


public class Part4 {
    public void sayHello(){
		URLResource rs = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html"); 
		for (String word : rs.words()) {
		    int i = word.toLowerCase().indexOf("youtube.com");
		    if (i == -1) {
		        continue;
		    }
		    int lq = word.lastIndexOf("\"", i);		    
		    int rq = word.indexOf("\"", i);		    
		    
			System.out.println(word.substring(lq, rq + 1));
		}
	}
}
    