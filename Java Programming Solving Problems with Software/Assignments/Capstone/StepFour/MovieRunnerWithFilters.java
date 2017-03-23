
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        //ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings sr = new ThirdRatings();
        int raters = sr.getRaterSize();
        System.out.println("raters: " + raters);
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("movies: " + MovieDatabase.size());
        System.out.println("-------------------------------------");
        
        
        int minRatings = 35;
        ArrayList<Rating> aveRat = sr.getAverageRatings(minRatings);
        System.out.println("Number of ratings at least " + minRatings + ": " + aveRat.size());
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + " : " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("-------------------------------------");
        
//         Filter f = new AllFilters();
//         f.addFilter(new YearsAfterFilter(2001));
        ArrayList<String> movies = MovieDatabase.filterBy(new YearAfterFilter(2001));        
        int c = 0;
        for(Rating r: aveRat){
            if(movies.contains(r.getItem())){c++;}
        }
        System.out.println("Number of ratings at least " + minRatings + " and made on or after 2001 : " + c);
        System.out.println("-------------------------------------");
        
        
        
    }
    
    public void printAverageRatingsByYear(){
        //ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings sr = new ThirdRatings();
        int raters = sr.getRaterSize();
        System.out.println("raters: " + raters);
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("movies: " + MovieDatabase.size());
        System.out.println("-------------------------------------");
        
        Filter f = new YearAfterFilter(2000);        
        int minRatings = 20;
        ArrayList<Rating> aveRat = sr.getAverageRatingsByFilter(minRatings, f);
        System.out.println("Found " + aveRat.size() + " movies");
        System.out.println("-------------------------------------");
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("-------------------------------------");
        
        
    }

    public void printAverageRatingsByGenre(){
        //ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings sr = new ThirdRatings();
        int raters = sr.getRaterSize();
        System.out.println("read data for " + raters + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        Filter f = new GenreFilter("Comedy");        
        int minRatings = 20;
        ArrayList<Rating> aveRat = sr.getAverageRatingsByFilter(minRatings, f);
        System.out.println("Found " + aveRat.size() + " movies");
        System.out.println("-------------------------------------");
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem()) + "\t" 
            + MovieDatabase.getTitle(r.getItem()) + "\t" + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("-------------------------------------");
        
        
    }

    public void printAverageRatingsByMinutes(){
        //ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings sr = new ThirdRatings();
        int raters = sr.getRaterSize();
        System.out.println("read data for " + raters + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        Filter f = new MinutesFilter(105, 135);        
        int minRatings = 5;
        ArrayList<Rating> aveRat = sr.getAverageRatingsByFilter(minRatings, f);
        System.out.println("Found " + aveRat.size() + " movies");
        System.out.println("-------------------------------------");
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + "\t" + "Time: " 
            + MovieDatabase.getMinutes(r.getItem()) + "\t" 
            + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("-------------------------------------");
        
        
    }

    public void printAverageRatingsByDirectors(){
        //ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings sr = new ThirdRatings();
        int raters = sr.getRaterSize();
        System.out.println("read data for " + raters + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");        
        int minRatings = 4;
        ArrayList<Rating> aveRat = sr.getAverageRatingsByFilter(minRatings, f);
        System.out.println("Found " + aveRat.size() + " movies");
        System.out.println("-------------------------------------");
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + "\t"  
            + MovieDatabase.getTitle(r.getItem()) + "\t"
            + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println("-------------------------------------");
        
        
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        //ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings sr = new ThirdRatings();
        int raters = sr.getRaterSize();
        System.out.println("read data for " + raters + " raters");
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
        int minRatings = 8;
        ArrayList<Rating> aveRat = sr.getAverageRatingsByFilter(minRatings, f);
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

    public void printAverageRatingsByDirectorsAndMinutes(){
        //ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings sr = new ThirdRatings();
        int raters = sr.getRaterSize();
        System.out.println("read data for " + raters + " raters");
        System.out.println("-------------------------------------");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------");
        
        AllFilters f = new AllFilters();
        MinutesFilter m = new MinutesFilter(90, 180);
        DirectorsFilter d = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        f.addFilter(m);
        f.addFilter(d);
        int minRatings = 3;
        ArrayList<Rating> aveRat = sr.getAverageRatingsByFilter(minRatings, f);
        System.out.println("Found " + aveRat.size() + " movies");
        System.out.println("-------------------------------------");
        // sort arraylist
        Collections.sort(aveRat);
        for(Rating r: aveRat){
            System.out.println(r.getValue() + "\t" + "Time: " 
            + MovieDatabase.getMinutes(r.getItem()) + "\t" 
            + MovieDatabase.getTitle(r.getItem()) + "\t" 
            + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println("-------------------------------------");
        
        
    }

}
