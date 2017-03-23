
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class DirectorsFilter implements Filter {
	private String directors;
	
	public DirectorsFilter(String dir) {
		directors = dir;
	}
	
	@Override
	public boolean satisfies(String id) {
	    String[] dirs = directors.split(",");
	    //System.out.println("Size of dirs: " + dirs.length);
	    for(String s : dirs){
	        //System.out.println(s);
	        //System.out.println(MovieDatabase.getDirector(id));
	        if(MovieDatabase.getDirector(id).contains(s)){return true;}
	    }
		return false;
	}

}
