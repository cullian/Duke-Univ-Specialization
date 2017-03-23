
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
public String findSimpleGene(String dna, String startCodon, String stopCodon){
    int start = dna.toUpperCase().indexOf(startCodon.toUpperCase());
    if (start == -1) return "";
    int stop = dna.toUpperCase().indexOf(stopCodon.toUpperCase(),start + 3);
    if (stop == -1) return "";
    if ((stop - start) % 3 != 0) return "";
    return dna.substring(start, stop + 3);
}
                                 
public void testSimpleGene() {
        String[] dna = {"CCCGGGAAATAACCC",
        "ATGCCCGGGAAACCC",
        "CCCGGGAAAACCC",
        "ATGCCCGGGAAATAACCC",
        "ATGCCCGGAAATAACCC",
        "ATGGGTTAAGTC",
        "gatgctataat"};
        String start = "ATG", stop = "TAA";
        System.out.println("START");
        for (int i = 0; i < 7; i++){
        String gene = findSimpleGene(dna[i], start, stop);
        System.out.println(dna[i]);
        System.out.println(gene);
        }
    }

}
