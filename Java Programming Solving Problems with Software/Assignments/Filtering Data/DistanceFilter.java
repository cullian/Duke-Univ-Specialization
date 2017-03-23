
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location location;
    private double maxDistance;
    private String name;
    
    public DistanceFilter(Location loc, double maxDist, String n){
        location = loc;
        maxDistance = maxDist;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(location) < maxDistance;
    }

    public String getName(){
        return name;
    }
}
