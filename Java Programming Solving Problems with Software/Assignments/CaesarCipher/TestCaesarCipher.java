
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
    
    public String breakCaesarCipher (String input){
        int [] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        System.out.println("key is: " + dkey);
        CaesarCipher cc = new CaesarCipher(26 - dkey);
        return cc.encrypt(input);        
    }
    
    public void simpleTests (){
//        FileResource fr = new FileResource();
//        String s = fr.asString();
        String s = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        s = cc.encrypt(s);
        System.out.println(s);
        s = cc.decrypt(s);
        System.out.println(s);
        s = cc.encrypt(s);
        System.out.println(s);
        s = breakCaesarCipher(s);
        System.out.println(s);
    }

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
    

}
