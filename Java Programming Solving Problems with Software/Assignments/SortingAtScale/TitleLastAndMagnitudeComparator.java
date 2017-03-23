
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] s1 = qe1.getInfo().split("\\W+");
        String[] s2 = qe2.getInfo().split("\\W+");
        String l1 = s1[s1.length - 1];
        String l2 = s2[s2.length - 1];
	    int c =  l1.compareTo(l2);
	    if(c == 0){
	        c = Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
	    }
	    return c;
    }

}
