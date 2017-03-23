
/**
 * Write a description of ThirdRatings here.
 * 
 * @author William Cullian 
 * @version 1.0 3/20/17
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters){
        double total = 0.0;
        int count = 0;
        for(Rater r : myRaters){
            if(r.hasRating(id)){
                count++;
                total += r.getRating(id);
            }
        }
        if(count < minimalRaters || count == 0){return 0.0;}
        return total / count;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> aveMovieRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());        
        for(String m : movies){
            double averageRating = getAverageByID(m, minimalRaters);
            if(averageRating != 0.0){
                aveMovieRatings.add(new Rating(m, averageRating));
            }
        }
        
        return aveMovieRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> aveMovieRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);        
        for(String m : movies){
            double averageRating = getAverageByID(m, minimalRaters);
            if(averageRating != 0.0){
                aveMovieRatings.add(new Rating(m, averageRating));
            }
        }
        
        return aveMovieRatings;
    }

}
