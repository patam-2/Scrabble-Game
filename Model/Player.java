package Model;

public interface Player {

    default void actionPlay(int type) // maybe to be deleted
    {
        if (type == 1)
            placeWord(null);

        if (type == 2)
            challenge(null);

        else
            System.out.println("Please try again, wrong number");
    }

    int placeWord(Word word);
    boolean challenge(String s);
}
