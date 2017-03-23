
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;


public class tester {
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println(s);
        s = cc.encrypt(s);
        System.out.println(s);
        s = cc.decrypt(s);
        System.out.println(s);
        char c = 'd';
        System.out.println(c);
        c = cc.encryptLetter(c);
        System.out.println(c);
        c = cc.decryptLetter(c);
        System.out.println(c);
        System.out.println(cc);
    }

    public void testCaesarCracker(){
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println(s);
        s = cc.decrypt(s);
        System.out.println(s);
        System.out.println(cc);
    }

    public void testVigenereCipher(){
        int[] rome = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(rome);
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println(s);
        s = vc.encrypt(s);
        System.out.println(s);
        s = vc.decrypt(s);
        System.out.println(s);
        System.out.println(vc);
    }

    public void testTotalSlices(){
        VigenereBreaker vb = new VigenereBreaker();
        String s = vb.sliceString("abcdefghijklm", 0, 3);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 1, 3);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 2, 3);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 0, 4);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 1, 4);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 2, 4);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 3, 4);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 0, 5);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 1, 5);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 2, 5);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 3, 5);
        System.out.println(s);
        s = vb.sliceString("abcdefghijklm", 4, 5);
        System.out.println(s);
    }

    public void testTryKeyLength(){
//         int[] flute = {5, 11, 20, 19, 4};
//         VigenereCipher vc = new VigenereCipher(flute);
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String s = fr.asString();
//        System.out.println(s);
//        s = vc.encrypt(s);
        int[] bestkey = vb.tryKeyLength(s, 38, 'e');

        for(int i = 0; i < bestkey.length; i++){
            System.out.println(bestkey[i]);
        }

        System.out.println("Key length = " + bestkey.length);
        FileResource dicFile = new FileResource("dictionaries\\English");
        HashSet<String> dic = vb.readDictionary(dicFile);
        VigenereCipher vc = new VigenereCipher(bestkey);
        String d = vc.decrypt(s);

        int cw = vb.countWords(d, dic);
        System.out.println("This file contains " + cw + " valid words out of " + s.split("\\W+").length);

//        System.out.println(i[4]);
    }

    public void testReadDictionary(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource dicFile = new FileResource("dictionaries\\English");
        HashSet<String> dic = vb.readDictionary(dicFile);
        for(String s : dic){
            System.out.println(s);
        }
    }


    public void testCountWords(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        FileResource dicFile = new FileResource("dictionaries\\English");
        HashSet<String> dic = vb.readDictionary(dicFile);
        int cw = vb.countWords(s, dic);
        System.out.println(cw);
    }

}
