
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {

    private int [] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int[26];
        for (int i = 0; i < message.length(); i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex]++;
            }
        }
        return counts;
    }
    
    private int maxIndex(int [] values){
        int max = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > values[max]){
                max = i;
            }
        }
        return max;
    }
    
    private String halfOfString (String message, int start){
        String half = "";
        for (int i = start; i < message.length(); i+=2){
            half += message.charAt(i);
        }
        return half;
    }

    public void simpleTests (){
        FileResource fr = new FileResource();
        String message = fr.asString();
//        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        CaesarCipherTwo cc2 = new CaesarCipherTwo(14, 24);
//        System.out.println(message);
//        message = cc2.encrypt(message);
//        System.out.println(message);
//        message = cc2.decrypt(message);
//        System.out.println(message);
//        message = cc2.encrypt(message);
//        System.out.println(message);
        message = breakCaesarCipher(message);
        System.out.println(message);
    }

    public String breakCaesarCipher (String input){
        
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);
        int [] freqs1 = countLetters(half1);
        int [] freqs2 = countLetters(half2);
        int maxDex1 = maxIndex(freqs1);
        int maxDex2 = maxIndex(freqs2);
        int dkey1 = maxDex1 - 4;
        if (maxDex1 < 4) {
            dkey1 = 26 - (4 - maxDex1);
        }
        int dkey2 = maxDex2 - 4;
        if (maxDex2 < 4) {
            dkey2 = 26 - (4 - maxDex2);
        }
        System.out.println("key1 is: " + dkey1);
        System.out.println("key2 is: " + dkey2);
        
        CaesarCipherTwo  cc = new CaesarCipherTwo(26 - dkey1, 26 - dkey2);
        return cc.encrypt(input);        
    }
    
}
