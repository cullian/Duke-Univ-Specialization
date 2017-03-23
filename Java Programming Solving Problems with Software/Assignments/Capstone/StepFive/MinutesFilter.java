
/**
 * Write a description of MinutesFilter here.
 * 
 * @author William Cullian 
 * @version 1.0 3/20/17
 */

public class MinutesFilter implements Filter {
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int min, int max) {
        minMinutes = min;
        maxMinutes = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= minMinutes 
            && MovieDatabase.getMinutes(id) <= maxMinutes;
    }

}
