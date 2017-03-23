import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

//         Filter mag = new MagnitudeFilter(3.5, 4.5, "Magnitude"); 
//         ArrayList<QuakeEntry> m7  = filter(list, mag); 
//         Filter depth = new DepthFilter(-55000.0, -20000.0, "Depth"); 
//         ArrayList<QuakeEntry> m8  = filter(m7, depth); 

        Location l = new Location(39.7392, -104.9903); // Denver, Colorado
        Filter mag = new DistanceFilter(l, 1000000.0, "Distance"); 
        ArrayList<QuakeEntry> m7  = filter(list, mag); 
        Filter depth = new PhraseFilter("end", "a", "Phrase"); 
        ArrayList<QuakeEntry> m8  = filter(m7, depth); 
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
        System.out.println("Number of quakes found: " + m8.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
//         for(QuakeEntry qe : list){
//             System.out.println(qe);
//         }
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "o", "Phrase"));
        ArrayList<QuakeEntry> filteredList  = filter(list, maf);
        for(QuakeEntry qe : filteredList){
            System.out.println(qe);
        }
        System.out.println("Number of quakes found: " + filteredList.size());
        
        System.out.println("Filters used are: " + maf.getName());

    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
//         for(QuakeEntry qe : list){
//             System.out.println(qe);
//         }
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "Magnitude"));
//         Location l = new Location(36.1314, -95.9372); // tulsa, OK
        Location l = new Location(55.7308, 9.1153); // Billund, Denmark
        maf.addFilter(new DistanceFilter(l, 3000000.0, "Distance"));
        maf.addFilter(new PhraseFilter("any", "e", "Phrase"));
        ArrayList<QuakeEntry> filteredList  = filter(list, maf);
        for(QuakeEntry qe : filteredList){
            System.out.println(qe);
        }
        System.out.println("Number of quakes found: " + filteredList.size());
        
        System.out.println("Filters used are: " + maf.getName());


    }

}
