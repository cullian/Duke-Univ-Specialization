
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 42;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void runMarkov2() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 531;
        
        
        EfficientMarkovModel em = new EfficientMarkovModel(5);
        runModel(em, st, size, seed);
        em.printHashMapInfo();
    

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void testHashMap(){
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        int seed = 42;
        
        EfficientMarkovModel m2 = new EfficientMarkovModel(2);
        runModel(m2, st, size, seed);
        m2.printHashMapInfo();
    
    
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        long startTime1 = System.nanoTime();
        MarkovModel m2 = new MarkovModel(2);
        runModel(m2, st, size, seed);
        long endTime1 = System.nanoTime();
        System.out.println("Run time1: " + (endTime1 - startTime1) / 1000000);
        
        long startTime2 = System.nanoTime();
        EfficientMarkovModel em2 = new EfficientMarkovModel(2);
        runModel(em2, st, size, seed);
        long endTime2 = System.nanoTime();
        System.out.println("Run time2: " + (endTime2 - startTime2) / 1000000);
        
        // em2.printHashMapInfo();
    
    
    }
}
