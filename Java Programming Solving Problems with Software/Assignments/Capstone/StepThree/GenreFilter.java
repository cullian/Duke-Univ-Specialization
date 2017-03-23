
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
