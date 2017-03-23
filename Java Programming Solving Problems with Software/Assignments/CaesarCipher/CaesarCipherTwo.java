
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo (int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + 
            alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + 
            alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt (String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int indx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (indx != -1){
                char eChar = i%2 == 0 ? shiftedAlphabet1.charAt(indx) : shiftedAlphabet2.charAt(indx);
                if (Character.isLowerCase(currChar)){
                    eChar = Character.toLowerCase(eChar);                                 
                }
                encrypted.setCharAt(i, eChar);
            }
        }
        return encrypted.toString();        
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc2.encrypt(input);
    }

}
