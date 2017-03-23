
/**
 * FirstRatings.
 * Loads movies and ratings from csv files
 * @author (William Cullian) 
 * @version (1.0 3/10/2017)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings{

    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movies = new ArrayList<Movie>();
        
        for (CSVRecord currentRow : parser) {
            String id = currentRow.get("id");
            String title = currentRow.get("title");
            String year = currentRow.get("year");
            String genres = currentRow.get("genre");
            String director = currentRow.get("director");
            String country = currentRow.get("country");
            int minutes = Integer.parseInt(currentRow.get("minutes"));
            String poster = currentRow.get("poster");
            Movie mov = new Movie(id, title, year, genres, director, country, poster, minutes);
            movies.add(mov);
        }
        return movies;
    }

    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> ratings = new ArrayList<Rater>();
        
        for (CSVRecord currentRow : parser) {
            // get data for row
            String rater_id = currentRow.get("rater_id");
            String movie_id = currentRow.get("movie_id");
            double rating = Double.parseDouble(currentRow.get("rating"));
            int time = Integer.parseInt(currentRow.get("time"));
            // check if we already have that rater and add rating
            boolean found = false;
            for(Rater r : ratings){
                if(r.getID().equals(rater_id)){
                    r.addRating(movie_id, rating);
                    found = true;
                    break;
                }
            }
            // if not, create new rater and add rating
            if(!found){
                Rater rat = new EfficientRater(rater_id);
                rat.addRating(movie_id, rating);
                ratings.add(rat);
            }
        }
        return ratings;
    }
    
    public void testLoadRaters(){
        //String fname = "data/ratings_short.csv";
        String fname = "data/ratings.csv";
        String rId = "193";
        ArrayList<Rater> rat = loadRaters(fname);
        System.out.println("Number of raters: " + rat.size());
        int maxRatings = 0;
        for(Rater r : rat){
            //System.out.println("rater #" + r.getID() + "\tNumber of ratings : " + r.numRatings());
            ArrayList<String> items = r.getItemsRated();
            for(String s : items){
                //System.out.println(s + " : " + r.getRating(s));
            }
            if(r.getID().equals(rId)){
                System.out.println("rater #" + r.getID() + "\tNumber of ratings : " + r.numRatings());
            }
            if(items.size() > maxRatings){
                maxRatings = items.size();
            }
        }
        
        System.out.println("Max number of ratings : " + maxRatings);
        for(Rater r : rat){
            if(r.getItemsRated().size() == maxRatings){
                System.out.println("rater #" + r.getID() + "\tNumber of ratings : " + r.numRatings());
            }
            
        }
        
        String movToSearch4 = "1798709";
        System.out.println("Movie to find ratings on : " + movToSearch4);
        int numRaters = 0;
        for(Rater r : rat){
            if(r.hasRating(movToSearch4)){
                    numRaters++;
            }
        }
        System.out.println("Number of raters for this movie : " + numRaters);
        
        ArrayList<String> mList = new ArrayList<String>();
        for(Rater r : rat){
            ArrayList<String> items = r.getItemsRated();
            for(String s : items){
                if(!mList.contains(s)){
                    mList.add(s);
                }
            }
        }
        System.out.println("Number of movies rated : " + mList.size());
        
        
        
    }
    
    public void testLoadMovies(){
//        String fname = "data/ratedmovies_short.csv";
      String fname = "data/ratedmoviesfull.csv";
        ArrayList<Movie> m = loadMovies(fname);
        System.out.println("Number of movies: " + m.size());
        int comedyCount = 0;
        int gr150min = 0;
        for(Movie mv : m){
            //System.out.println(mv);
            if(mv.getGenres().contains("Comedy")){
                comedyCount++;
                //System.out.println(mv);
            }
            if(mv.getMinutes() > 150){
                gr150min++;
                //System.out.println(mv);
            }
        }
        System.out.println("Number of comedys: " + comedyCount);
        System.out.println("Number of movies over 150 minutes: " + gr150min);
        
        HashMap<String, Integer> d = directorsMovieCount(m);
        int lgest = findLargest(d);
        System.out.println("maximum number of movies by any director: " + lgest);
        for(String dname : d.keySet()){
            if(d.get(dname) == lgest){
                System.out.println(dname + " : " + d.get(dname));
            }
        }
    }
    
    private int findLargest(HashMap<String, Integer> directors){
        int l = 0;
        for(int i : directors.values()){
            if(i > l){
                l = i;
            }
        }
        return l;
    }
    
    private HashMap<String, Integer> directorsMovieCount(ArrayList<Movie> movs){
        HashMap<String, Integer> dmc = new HashMap<String, Integer>();
        for(Movie m : movs){
            String[] names = m.getDirector().split(", ");
            for(String name : names){
                if(dmc.containsKey(name)){
                    dmc.put(name, dmc.get(name) + 1);
                }
                else{
                    dmc.put(name, 1);
                }
            }
        }
        
//         for(String dir : dmc.keySet()){
//             System.out.println(dir + " : " + dmc.get(dir));
//         }

        return dmc;
    }
    

}
