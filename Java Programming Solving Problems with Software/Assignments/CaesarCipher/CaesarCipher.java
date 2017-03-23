
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher (int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + 
            alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt (String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int indx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (indx != -1){
                char eChar = shiftedAlphabet.charAt(indx);                
                if (Character.isLowerCase(currChar)){
                    eChar = Character.toLowerCase(eChar);                                 
                }
                encrypted.setCharAt(i, eChar);
            }
        }
        return encrypted.toString();        
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }


/*    public void testEncrypt (){
        System.out.println("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"));
        System.out.println("First Legion");
        System.out.println(encrypt("First Legion"));
        System.out.println(encrypt("First Legion"));
    }
*/
    public void testCaesar  (){
        int key = 15;
//        FileResource fr = new FileResource();
//        String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message);
        System.out.println("key is " + key + "\n" + encrypted);

    }

/*    public String encryptTwoKeys (String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
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

    public void testEncryptTwoKeys (){
        System.out.println("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        System.out.println("FIRST LEGION ATTACK EAST FLANK!");
        System.out.println(encryptTwoKeys("FIRST LEGION ATTACK EAST FLANK!", 23, 17));
        System.out.println("First Legion");
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
        System.out.println(encryptTwoKeys("First Legion", 17, 23));
    }
*/

}
