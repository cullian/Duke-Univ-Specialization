
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    private void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    private void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
//        String st = "this is just a test yes this is a simple test";
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 200, 844); 
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
        String st = "this is a test yes this is really a test";
//        String st = "this is a test yes this is really a test yes a test this is wow";
//        FileResource fr = new FileResource();
//        String st = fr.asString();
//        st = st.replace('\n', ' ');
        EfficientMarkovWord markovWord = new EfficientMarkovWord(3); 
        runModel(markovWord, st, 50, 371); 
        markovWord.printHashMapInfo();
        
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 42;
        
        long startTime1 = System.nanoTime();
        MarkovWord mw = new MarkovWord(2);
        runModel(mw, st, size, seed);
        long endTime1 = System.nanoTime();
        System.out.println("Run time1: " + (endTime1 - startTime1) / 1000000);
        
        long startTime2 = System.nanoTime();
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        runModel(emw, st, size, seed);
        long endTime2 = System.nanoTime();
        System.out.println("Run time2: " + (endTime2 - startTime2) / 1000000);
        
        // em2.printHashMapInfo();
    
    
    }
}
