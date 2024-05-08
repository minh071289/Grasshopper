package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Hangman {
    private static final int MAX_WRONG = 6;
    private Map<String, String> wordMap;
    private int wrongGuess = 0;
    private String key;
    private String hint;
    private List<Character> wordToGuess = new ArrayList<>();

    /**
     * Constructor for Hangman, set word to _
     *
     * @param wordMap
     */
    public Hangman(Map<String, String> wordMap) {
        this.wordMap = wordMap;
        Map.Entry<String,String> randomWord = getRandomEntry();
        key = randomWord.getKey().toUpperCase();
        hint = randomWord.getValue();
        for (int i = 0; i < getKey().length(); ++i) {
            wordToGuess.add('_');
        }
    }

    /**
     * Return a random word
     *
     * @return word & definition
     */
    public Map.Entry<String, String> getRandomEntry() {
        Map.Entry<String, String>[] entries = wordMap.entrySet().toArray(new Map.Entry[0]);
        Random random = new Random();
        int randomIndex = random.nextInt(entries.length);
        return entries[randomIndex];
    }

    public String getKey() {
        return key;
    }

    public String getHint() {
        return hint;
    }

    public void atGuess(String c) {
        if (!getKey().contains(c)) {
            wrongGuess++;
        } else {
            for (int i = 0; i < getKey().length(); ++i) {
                if (getKey().charAt(i) == c.charAt(0)) {
                    wordToGuess.set(i, c.charAt(0));
                }
            }
        }
    }

    public boolean isOver() {
        return wrongGuess >= MAX_WRONG;
    }

    public boolean isWin() {
        return !wordToGuess.contains('_');
    }

    public int getWrongGuess() {
        return wrongGuess;
    }

    public List<Character> getWordToGuess() {
        return wordToGuess;
    }
}
