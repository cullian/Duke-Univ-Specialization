
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
// 
//         for(QuakeEntry qe : list){
//             System.out.println(qe);
//         }
//         System.out.println("number found: "+list.size());

//         int largest = indexOfLargest(list);
//         System.out.println("largest magnitude found at index: " + largest);
//         System.out.println(list.get(largest));

        ArrayList<QuakeEntry> largestMags  = getLargest(list, 20);
        for(QuakeEntry qe : largestMags){
            System.out.println(qe);
        }
        


    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int lMag = 0;
        double largest = 0.0;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getMagnitude() > largest){
                largest = data.get(i).getMagnitude();
                lMag = i;
            }
        }

        return lMag;
    }

    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData,
            int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        int limit = howMany > quakeData.size() ? quakeData.size() : howMany;
        for(int j = 0; j < limit; j++){
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;
    }


}
