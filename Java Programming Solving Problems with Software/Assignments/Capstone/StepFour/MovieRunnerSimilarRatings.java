
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author William Cullian 
 * @version 1.0 3/20/17
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratingsfull.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        int minRatings = 1;
        ArrayList<Rating> aveRat = fr.getAverageRatings(minRatings);
        System.out.println("Number of ratings at least " + minRatings + ": " + aveRat.size());
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + " : " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("-------------------------------------");
        
        int year = 2001;
        ArrayList<String> movies = MovieDatabase.filterBy(new YearAfterFilter(year));        
        int c = 0;
        for(Rating r: aveRat){
            if(movies.contains(r.getItem())){c++;}
        }
        System.out.println("Number of ratings at least " + minRatings + " and made on or after " + year + " : " + c);
        System.out.println("-------------------------------------");
    }
    

    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratingsfull.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        AllFilters f = new AllFilters();
        YearAfterFilter y = new YearAfterFilter(1990);
        GenreFilter g = new GenreFilter("Drama");
        f.addFilter(y);
        f.addFilter(g);
        int minRatings = 1;
        ArrayList<Rating> aveRat = fr.getAverageRatingsByFilter(minRatings, f);
        System.out.println("Found " + aveRat.size() + " movies");
        System.out.println("-------------------------------------");
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + "\t" 
            + MovieDatabase.getYear(r.getItem()) + "\t"
            + MovieDatabase.getTitle(r.getItem()) + "\t"
            + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("-------------------------------------");
    }

    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings();
        
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        String raterID = "71";
        int minSimRat = 20;
        int minRatings = 5;
        ArrayList<Rating> simRat = fr.getSimilarRatings(raterID, minSimRat, minRatings);
        System.out.println("Number of ratings at least " 
            + minRatings + " and min similar raters " + minSimRat 
            + " to rater " + raterID + " : " + simRat.size());
        // sort arraylist
        //Collections.sort(simRat);
        for(Rating r : simRat){
            System.out.println(r.getValue() + " : " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("------------------------------------->");
    }

    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new FourthRatings();
        
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
                
        String raterID = "964";
        int minSimRat = 20;
        int minRatings = 5;
        GenreFilter f = new GenreFilter("Mystery");
        ArrayList<Rating> simRat = 
            fr.getSimilarRatingsByFilter(raterID, minSimRat, minRatings, f);
        System.out.println("Number of ratings at least " 
            + minRatings + " and min similar raters " + minSimRat 
            + " to rater " + raterID + " : " + simRat.size());
        // sort arraylist
        //Collections.sort(simRat);
        for(Rating r : simRat){
            System.out.println(r.getValue() + " : " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("------------------------------------->");
    }

    public void printSimilarRatingsByDirector(){
        FourthRatings fr = new FourthRatings();
        
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        String raterID = "120";
        int minSimRat = 10;
        int minRatings = 2;
        DirectorsFilter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> simRat = 
            fr.getSimilarRatingsByFilter(raterID, minSimRat, minRatings, f);
        System.out.println("Number of ratings at least " 
            + minRatings + " and min similar raters " + minSimRat 
            + " to rater " + raterID + " : " + simRat.size());
        // sort arraylist
        //Collections.sort(simRat);
        for(Rating r : simRat){
            System.out.println(r.getValue() + " : " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("------------------------------------->");
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new FourthRatings();
        
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        String raterID = "168";
        int minSimRat = 10;
        int minRatings = 3;
        GenreFilter g = new GenreFilter("Drama");
        MinutesFilter m = new MinutesFilter(80, 160);
        AllFilters f = new AllFilters();
        f.addFilter(g);
        f.addFilter(m);
        ArrayList<Rating> simRat = 
            fr.getSimilarRatingsByFilter(raterID, minSimRat, minRatings, f);
        System.out.println("Number of ratings at least " 
            + minRatings + " and min similar raters " + minSimRat 
            + " to rater " + raterID + " : " + simRat.size());
        // sort arraylist
        //Collections.sort(simRat);
        for(Rating r : simRat){
            System.out.println(r.getValue() + " : " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("------------------------------------->");
    }

    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings();
        
        //RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        String raterID = "314";
        int minSimRat = 10;
        int minRatings = 5;
        YearAfterFilter y = new YearAfterFilter(1975);
        MinutesFilter m = new MinutesFilter(70, 200);
        AllFilters f = new AllFilters();
        f.addFilter(y);
        f.addFilter(m);
        ArrayList<Rating> simRat = 
            fr.getSimilarRatingsByFilter(raterID, minSimRat, minRatings, f);
        System.out.println("Number of ratings at least " 
            + minRatings + " and min similar raters " + minSimRat 
            + " to rater " + raterID + " : " + simRat.size());
        // sort arraylist
        //Collections.sort(simRat);
        for(Rating r : simRat){
            System.out.println(r.getValue() + " : " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("------------------------------------->");
    }


}
