package Model;

public interface Player {

    int placeWord(Word word);
    boolean challenge(String s);
    int[] getTiles();
}
