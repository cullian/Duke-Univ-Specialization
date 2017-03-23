
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author William Cullian 
 * @version 1.0 3/20/17
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        SecondRatings sr = new SecondRatings();
        int movies = sr.getMovieSize();
        int raters = sr.getRaterSize();
        System.out.println("Movies: " + movies + "\traters: " + raters);
        System.out.println("-------------------------------------");
        
        int minRatings = 12;
        ArrayList<Rating> aveRat = sr.getAverageRatings(minRatings);
        System.out.println("Number of ratings at least " + minRatings + ": " + aveRat.size());
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + " : " + sr.getTitle(r.getItem()));
        }
        
    }
    public void getAverageRatingOneMovie(){
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        SecondRatings sr = new SecondRatings();
        
        String movie = "Vacation";
        int minRatings = 0;
        ArrayList<Rating> aveRat = sr.getAverageRatings(minRatings);
        for(Rating r : aveRat){
            if(r.getItem().equals(sr.getID(movie))){
                System.out.println("Movie: " + movie + "\trating: " + r.getValue());
            }
        }
        
    }
}
