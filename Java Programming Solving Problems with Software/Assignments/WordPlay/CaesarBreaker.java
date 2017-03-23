
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;


public class CaesarBreaker {

    public int [] countLetters(String message){
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
    public int maxIndex(int [] values){
        int max = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > values[max]){
                max = i;
            }
        }
        return max;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int [] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        System.out.println("key is: " + dkey);
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public void testMaxIndex(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int [] val = countLetters(encrypted);
        int i = maxIndex(val);
        System.out.println(i);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String message = decrypt(encrypted);
        System.out.println(message);
    }
    
    public String halfOfString (String message, int start){
        String half = "";
        for (int i = start; i < message.length(); i+=2){
            half += message.charAt(i);
        }
        return half;
    }

    public void testHalfOfString(){
        String half = halfOfString("Qbkm Zgis", 0);
        System.out.println(half);
        half = halfOfString("Qbkm Zgis", 1);
        System.out.println(half);
    }
    
    public int getKey (String s){
        int [] val = countLetters(s);
        int i = maxIndex(val);
        return i;
    }
    
    public String decryptTwoKeys (String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);
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
        
        return cc.encryptTwoKeys(encrypted, 26 - dkey1, 26 - dkey2);
    }

    public void testDecryptTwoKeys(){
        CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource();
//        String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
//        String message = cc.encryptTwoKeys(encrypted, 26 - 2, 26 - 20);
        String encrypted = fr.asString();
        String message = decryptTwoKeys(encrypted);
        System.out.println(message);
    }
    
}
