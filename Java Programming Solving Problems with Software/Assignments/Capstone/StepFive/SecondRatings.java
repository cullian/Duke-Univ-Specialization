
/**
 * Write a description of SecondRatings here.
 * 
 * @author William Cullian 
 * @version (1.0 3/10/2017)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }

    public SecondRatings(String moviefile,String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "ID was not found";
    }

    public String getID(String title){
        for(Movie m : myMovies){
            if(m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
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
        for(Movie m : myMovies){
            double averageRating = getAverageByID(m.getID(), minimalRaters);
            if(averageRating != 0.0){
                aveMovieRatings.add(new Rating(m.getID(), averageRating));
            }
        }
        
        return aveMovieRatings;
    }

}
