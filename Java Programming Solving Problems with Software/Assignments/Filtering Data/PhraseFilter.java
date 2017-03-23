
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String reqType;
    private String phrase;
    private String name;
    
    public PhraseFilter(String req, String phrz, String n){
        reqType = req;
        phrase = phrz;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if(reqType.equals("start") && qe.getInfo().startsWith(phrase)){
            return true;
        }
        if(reqType.equals("end") && qe.getInfo().endsWith(phrase)){
            return true;
        }
        if(reqType.equals("any") && qe.getInfo().contains(phrase)){
            return true;
        }
        return false;
    }

    public String getName(){
        return name;
    }
}
