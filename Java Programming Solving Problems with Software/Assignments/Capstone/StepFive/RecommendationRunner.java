
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author William Cullian 
 * @version 1.0 3/21/17
 */

import java.util.*;


public class RecommendationRunner implements Recommender{
//         It returns a list of strings representing movie IDs 
//         that will be used to present movies to the user for 
//         them to rate.

    public ArrayList<String> getItemsToRate (){
        ArrayList<String> ret = new ArrayList<String>();
        int sampleSize = 15;
        Random myRandom = new Random();
        MovieDatabase.initialize("ratedmoviesfull.csv");

        int i = 0;
        while(i < sampleSize){
            int index = myRandom.nextInt(MovieDatabase.size());
            String rMov = "";
            int count = 0;
            for(String id : MovieDatabase.filterBy(new TrueFilter())){
                if(count == index){
                    rMov = id;
                    break;
                }
                count++;
            }
            if(!ret.contains(rMov)){
                ret.add(rMov);
                i++;
            }
        }

        return ret;
    }

    public void printRecommendationsFor(String webRaterID){
        // initialize
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // set arguments
        int minSimRat = 20;
        int minRatings = 4;
        // get rater
        Rater thisRater = RaterDatabase.getRater(webRaterID);
        // call getSimilarRatings for list of recommended movies 
        ArrayList<Rating> recMovies = 
            fr.getSimilarRatings(webRaterID, minSimRat, minRatings);
        // check for movies already rated by webRaterID
        ArrayList<Rating> items4Removal = new ArrayList<Rating>();
        for(Rating r : recMovies){
            if(thisRater.hasRating(r.getItem())){
                items4Removal.add(r);
            }
        }
        // remove them
        for(Rating r : items4Removal){
            recMovies.remove(r);
        }

        // print style
        System.out.println("<style>");
        System.out.println("table {");
        System.out.println("border-collapse: collapse;");
        System.out.println("border: 1px solid black;");
        System.out.println("margin: 0 auto;");
        System.out.println("width: 100%;");
        System.out.println("}");
        System.out.println("th, td {");
        System.out.println("padding: 5px;");
        System.out.println("text-align: left;");
        System.out.println("vertical-align: middle;");
        System.out.println("}");
        System.out.println("tr:nth-child(even){");
        System.out.println("background-color: #f2f2f2;");
        System.out.println("}");
        System.out.println("tbody tr:hover {");
        System.out.println("background: lightsteelblue;");
        System.out.println("}");
        
        System.out.println("</style>");

        // Print table

        System.out.println("<table>");
        if(recMovies.size() > 0){
            System.out.println("<thead>");
            System.out.println("<tr>");
            System.out.println("<th></th>");
            System.out.println("<th>Movie Title</th>");
            System.out.println("<th>Year</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("<th>Director</th>");
            System.out.println("<th>Country</th>");
            System.out.println("<th>Length</th>");
            System.out.println("</tr>");
            System.out.println("</thead>");
            System.out.println("<tbody>");
            int limit = recMovies.size();
            if(limit > 20){limit = 20;}
            for(int r = 0; r < limit; r++){
                String id = recMovies.get(r).getItem();
                String s = MovieDatabase.getPoster(id);
                String source = "";
                if(!s.startsWith("data") && s.length() > 6){
                    source = "data" + s.substring(6);
                }
                else{
                    source = s;
                }
                
                System.out.println("<tr>");
                // image
                System.out.println("<td>");
                System.out.println("<a href=\"" + source + "\" target=\"_blank\">");
                System.out.println("<img src=\"" + source + "\" alt = \"" + MovieDatabase.getTitle(id) + "\" style=\"height:50px;align:right\">");            
                System.out.println("</a>");            
                System.out.println("</td>");
                // movie title
                System.out.println("<td>");
                System.out.println("<a href=\"http://www.imdb.com/title/tt" + id + "/\" target=\"_blank\">" + MovieDatabase.getTitle(id) + "</a>");            
                System.out.println("</td>");
                // year
                System.out.println("<td>");
                System.out.println(MovieDatabase.getYear(id));            
                System.out.println("</td>");
                // genre
                System.out.println("<td>");
                System.out.println(MovieDatabase.getGenres(id));
                System.out.println("</td>");
                // director
                System.out.println("<td>");
                System.out.println(MovieDatabase.getDirector(id));
                System.out.println("</td>");
                // country
                System.out.println("<td>");
                System.out.println(MovieDatabase.getCountry(id));
                System.out.println("</td>");
                // length
                System.out.println("<td>");
                System.out.println(MovieDatabase.getMinutes(id));
                System.out.println("</td>");
                System.out.println("</tr>");
            }
            System.out.println("</tbody>");
        }
        else{
            System.out.println("<h1>Not enough recommendations - SORRY</h1>");
        }
        System.out.println("</table>");
    }

}
