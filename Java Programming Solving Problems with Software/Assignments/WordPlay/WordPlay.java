
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordPlay {
    public boolean isVowel (char ch){
        String vowel = "AEIOUaeiou";
        if(vowel.indexOf(ch) == -1) return false;
        return true;        
    }

    public void testIsVowel (){
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }

    public String replaceVowels (String phrase, char ch){
        StringBuilder ret = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            if (isVowel(currChar)){
                ret.setCharAt(i, ch);
            }
        }
        return ret.toString();        
    }

    public void testReplaceVowels (){
        System.out.println("Hello World");
        System.out.println(replaceVowels("Hello World", '*'));
    }

    public String emphasize  (String phrase, char ch){
        StringBuilder ret = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            if (Character.toLowerCase(currChar) == Character.toLowerCase(ch)){
                if (i%2 == 0){
                    ret.setCharAt(i, '*');                
                }
                else{
                    ret.setCharAt(i, '+');
                }
            }
        }
        return ret.toString();        
    }

    public void testEmphasize (){
        System.out.println("dna ctgaaactga");
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println("Mary Bella Abracadabra");
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

}
