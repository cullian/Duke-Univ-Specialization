import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            if(quake.getMagnitude() > magMin){
                answer.add(quake);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            Location loc = quake.getLocation();
            if(loc.distanceTo(from) < distMax){
                answer.add(quake);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> large  = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : large){
            System.out.println(qe);
        }
        System.out.println("Found "+large.size()+" quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        //Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closeOnes = filterByDistanceFrom(list, 1000000.0, city);
        for(QuakeEntry quake : closeOnes){
            Location loc = quake.getLocation();
            float dFromQuake = loc.distanceTo(city);
            System.out.println(dFromQuake + " " + quake.getInfo());
        }
        System.out.println("Found "+closeOnes.size()+" quakes that match that criteria");
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth ,double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            double depth = quake.getDepth();
            if(depth > minDepth && depth < maxDepth){
                answer.add(quake);
            }
        }

        return answer;
    }

    public void quakesOfDepth () {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> deepOnes  = filterByDepth(list, -12000.0, -10000.0);
        for(QuakeEntry qe : deepOnes){
            System.out.println(qe);
        }
        System.out.println("Found "+deepOnes.size()+" quakes that match that criteria");

    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            String title = quake.getInfo();
            if(where.equals("start") && title.startsWith(phrase)){
                answer.add(quake);
            }
            if(where.equals("end") && title.endsWith(phrase)){
                answer.add(quake);
            }
            if(where.equals("any") && title.contains(phrase)){
                answer.add(quake);
            }
        }

        return answer;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> phrase  = filterByPhrase(list, "start", "Quarry Blast");
        for(QuakeEntry qe : phrase){
            System.out.println(qe);
        }
        System.out.println("Found "+phrase.size()+" quakes that match that criteria");

    }
}
