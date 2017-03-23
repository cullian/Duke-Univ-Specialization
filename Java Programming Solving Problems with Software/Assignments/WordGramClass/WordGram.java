
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i=0; i < myWords.length; i++){
            ret += myWords[i] + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        
        for(int i=0; i < myWords.length; i++){
            if(!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        int size = myWords.length;
        String[] newMyWords = new String[size];
        System.arraycopy(myWords, 1, newMyWords, 0, size - 1);
        newMyWords[size - 1] = word;

        WordGram out = new WordGram(newMyWords, 0, size);
        return out;
    }

    public int hashCode(){
        return toString().hashCode();
    }

}