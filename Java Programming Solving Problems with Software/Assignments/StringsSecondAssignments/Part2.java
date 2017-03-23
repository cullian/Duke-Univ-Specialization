
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int index = 0;
        while (true){
            index = stringb.indexOf(stringa, index);
            if (index == -1){
                break;
            }
            count++;
            index += stringa.length();
        }
        return count;
        
    }
    
    public void testHowMany (){
        int t = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println(t);
        t = howMany("AA","ATAAAA");
        System.out.println(t);
    }
}
