import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder m = new StringBuilder(message);
        String s = "";
        for(int i= whichSlice; i < message.length(); i += totalSlices){
            s = s + m.charAt(i);
        }
        return s;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String s = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(s);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String s = fr.asString();

        HashMap<String, HashSet<String>> dics = new HashMap<String, HashSet<String>>();
        String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        System.out.println("Reading Dics");
        for(String l : langs){
            FileResource dicFile = new FileResource("dictionaries\\" + l);
            HashSet<String> dic = readDictionary(dicFile);
            dics.put(l, dic);
        }
        String decrypted = breakForAllLangs(s, dics);
        
//         int cw = countWords(decrypted, dic);
//         System.out.println("This file contains " + cw + " valid words out of " + decrypted.split("\\W+").length);
//         System.out.println(decrypted);
    }

    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dic = new HashSet<String>();
        for(String s : fr.lines()){
            dic.add(s.toLowerCase());
        }
        return dic;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] w = message.split("\\W+");
        for(String s : w){
            if(dictionary.contains(s.toLowerCase())){
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int best = 0;
        String decrypted = "";
        int[] key = null;
        int[] bestkey = null;
        for(int i = 1; i < 101; i++){
            key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(key);
            String d = vc.decrypt(encrypted);
            int cw = countWords(d, dictionary);
            if(cw > best){
                best = cw;
                decrypted = d;
                bestkey = key;
            }
        }

        for(int i = 0; i < bestkey.length; i++){
            System.out.println(bestkey[i]);
        }

        System.out.println("Key length = " + bestkey.length);
        
        return decrypted;
    }


    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap <Character, Integer> charCount = new HashMap <Character, Integer>();
        for(String s : dictionary){
            for (int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(Character.isLetter(c)){
                    if(charCount.containsKey(c)){
                        int cc = charCount.get(c) + 1;
                        charCount.put(c, cc);
                    }
                    else{
                        charCount.put(c, 1);
                    }
                }
            }
        }
        int max = 0;
        char mChar = ' ';
        for(char c : charCount.keySet()){
            if(charCount.get(c) > max){
                max = charCount.get(c);
                mChar = c;
            }
        }
        return mChar;
    }


    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String bestLang = "";
        for(String l : languages.keySet()){
            HashSet<String> dic = languages.get(l);
            System.out.println("LANGUAGE = " + l);
            String decrypted = breakForLanguage(encrypted, dic);
            int cw = countWords(decrypted, dic);
            if(cw > max){
                max = cw;
                bestLang = l;
            }
        }
        System.out.println("Best Language = " + bestLang);
        System.out.println("Max Wordcount = " + max);
        String decrypted = breakForLanguage(encrypted, languages.get(bestLang));
        System.out.println(decrypted);
        
        return decrypted;
        
    }

}
