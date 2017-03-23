
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
public boolean twoOccurrences(String stringa, String stringb){
    int i = stringb.indexOf(stringa);
    if (i == -1){ 
        return false;
    }
    
    if (stringb.indexOf(stringa, i + stringa.length()) == -1) {
        return false;
    }
    return true;
}
                                 
public void testing() {
        String[] a = {"by",
        "a",
        "atg"};
        String[] b = {"A story by Abby Long",
        "banana",
        "ctgtatgta"};
/*        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
*/
        System.out.println("START");
        for (int i = 0; i < 3; i++){
        boolean result = twoOccurrences(a[i], b[i]);
        System.out.println(a[i]);
        System.out.println(b[i]);
        System.out.println(result);
        }

        for (int i = 0; i < 3; i++){
        String result = lastPart(a[i], b[i]);
        System.out.println(a[i]);
        System.out.println(b[i]);
        System.out.println(result);
        }

}
public String lastPart(String stringa, String stringb){
    int i = stringb.indexOf(stringa);
    if (i == -1){ 
        return stringb;
    }
    return stringb.substring(i);
}
                                 
}