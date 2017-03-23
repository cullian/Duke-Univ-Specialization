
/**
 * Write a description of FourthRatings here.
 * 
 * @author William Cullian 
 * @version 1.0 3/20/17
 */

import java.util.*;

public class FourthRatings {

    // Returns average rating of movie id as long as minimalRaters
    // have rated it. Otherwise it returns 0.0
    private double getAverageByID(String id, int minimalRaters){
        double total = 0.0;
        int count = 0;
        for(Rater r : RaterDatabase.getRaters()){
            if(r.hasRating(id)){
                count++;
                total += r.getRating(id);
            }
        }
        if(count < minimalRaters || count == 0){return 0.0;}
        return total / count;
    }

    // Returns a list of the average rating of all movies that have 
    // at least minimalRaters raters.
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

    // Returns a list of the average rating of a filtered list of
    // movies that have at least minimalRaters raters.
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

    // Returns the dot product (similarity) value for the two raters
    private double dotProduct(Rater me, Rater r){
        double total = 0.0;
        // for each rating in me
        for(String myItem : me.getItemsRated()){
            // check if rater r has this rating
            if(r.hasRating(myItem)){
                // get dot product for this rating and add it to total
                total += (me.getRating(myItem) - 5.0) * (r.getRating(myItem) - 5.0);
            }
        }
        
        return total;
    }

    // Returns a Inversly sorted list of raters and their similarity 
    // weight to the rater id
    private ArrayList<Rating> getSimilarities(String id){
        // an arraylist of raters(raterid, dot product)
        ArrayList<Rating> listOfSimilarRatersWeights = new ArrayList<Rating>();
        // get the rater and make sure it exists
        Rater me = RaterDatabase.getRater(id);
        if(me == null){
            System.out.println("getSimilarities(String id): Rater not found");
            return listOfSimilarRatersWeights;
        }
        // go thru each rater
        for(Rater r : RaterDatabase.getRaters()){
            // if this is not the selected rater then ...
            // add the rater and its dot product to the list
            if(!r.getID().equals(id)){
                double val = dotProduct(me, r);
                if(val >= 0.0){
                    Rating rat = new Rating(r.getID(), val);
                    listOfSimilarRatersWeights.add(rat);
                }
            }
        }
        // sort in reverse order
        Collections.sort(listOfSimilarRatersWeights, Collections.reverseOrder());
        return listOfSimilarRatersWeights;
    }

    // Returns a list of movies and their weighted average ratings
    // to rater id, using only the top numSimilarRaters with positive
    // rankings and at least minimalRaters of them
    public ArrayList<Rating> getSimilarRatings(String id, 
                int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
//         // an arraylist of raters(raterid, dot product)
//         ArrayList<Rating> simRatersWeightList = getSimilarities(id);
//         // the return list of raters(movieID, weighted average rating)
//         ArrayList<Rating> ret = new ArrayList<Rating>();
//         
//         // for each rater in list remove excess above numSimilarRaters
//         // and remove any negative ones
//         for(int i = simRatersWeightList.size() - 1; i >= numSimilarRaters; i--){
//             simRatersWeightList.remove(i);
//         }
//         
//         // for every movie
//         for(String movieID : MovieDatabase.filterBy(new TrueFilter())){
//             double weightedRatingTotal = 0.0;
//             int count = 0;
//             // for numSimilarRaters (or less) of similar raters who are
//             // positive
//             for(int i = 0; i < simRatersWeightList.size(); i++){
//                 Rating weight = simRatersWeightList.get(i);
//                 Rater rater = RaterDatabase.getRater(weight.getItem());
//                 double ratersRating = rater.getRating(movieID);
//                 if(ratersRating == -1){
//                     continue;
//                 }
//                 weightedRatingTotal += ratersRating * weight.getValue();
//                 count++;
//             }
//             // add weighted rating for movieID to ret
//             if(count >= minimalRaters){
//                 ret.add(new Rating(movieID, weightedRatingTotal / count));
//             }
//         }
//         Collections.sort(ret, Collections.reverseOrder());
//         return ret;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, 
                int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        // an arraylist of raters(raterid, dot product)
        ArrayList<Rating> simRatersWeightList = getSimilarities(id);
        // the return list of raters(movieID, weighted average rating)
        ArrayList<Rating> ret = new ArrayList<Rating>();
        
        // for each rater in list remove excess above numSimilarRaters
        // and remove any negative ones
        for(int i = simRatersWeightList.size() - 1; i >= numSimilarRaters; i--){
            simRatersWeightList.remove(i);
        }
        
        // for every movie
        for(String movieID : MovieDatabase.filterBy(filterCriteria)){
            double weightedRatingTotal = 0.0;
            int count = 0;
            // for numSimilarRaters (or less) of similar raters who are
            // positive
            for(int i = 0; i < simRatersWeightList.size(); i++){
                Rating weight = simRatersWeightList.get(i);
                Rater rater = RaterDatabase.getRater(weight.getItem());
                double ratersRating = rater.getRating(movieID);
                if(ratersRating == -1){
                    continue;
                }
                weightedRatingTotal += ratersRating * weight.getValue();
                count++;
            }
            // add weighted rating for movieID to ret
            if(count >= minimalRaters){
                ret.add(new Rating(movieID, weightedRatingTotal / count));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }

}
