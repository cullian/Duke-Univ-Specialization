
/**
 * Write a description of GenreFilter here.
 * 
 * @author William Cullian 
 * @version 1.0 3/20/17
 */

public class GenreFilter implements Filter {
	private String genre;
	
	public GenreFilter(String gen) {
		genre = gen;
	}
	
	@Override
	public boolean satisfies(String id) {
	    String[] gens = MovieDatabase.getGenres(id).split("\\W+");
	    for(String s : gens){
	        if(genre.equals(s)){return true;}
	    }
		return false;
	}

}
