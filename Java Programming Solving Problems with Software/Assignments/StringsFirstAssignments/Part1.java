
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
public String findSimpleGene(String dna){
    int start = dna.indexOf("ATG");
    if (start == -1) return "";
    int stop = dna.indexOf("TAA",start + 3);
    if (stop == -1) return "";
    if ((stop - start) % 3 != 0) return "";
    return dna.substring(start, stop + 3);
}
                                 
public void testSimpleGene() {
        String[] dna = {"CCCGGGAAATAACCC",
        "ATGCCCGGGAAACCC",
        "CCCGGGAAAACCC",
        "ATGCCCGGGAAATAACCC",
        "ATGCCCGGAAATAACCC"};
        System.out.println("START");
        for (int i = 0; i < 5; i++){
        String gene = findSimpleGene(dna[i]);
        System.out.println(dna[i]);
        System.out.println(gene);
        }
    }
}
