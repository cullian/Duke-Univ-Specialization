
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMagnitude;
    private double maxMagnitude;
    private String name;
    
    public MagnitudeFilter(double min, double max, String n){
        minMagnitude = min;
        maxMagnitude = max;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= minMagnitude && qe.getMagnitude() <= maxMagnitude;
    }
    
    public String getName(){
        return name;
    }
}
