
package BackEnd;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class DictionaryCommandLine {
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private final String path = "src/main/resources/Texts/dictionaries.txt";
    private Scanner input = new Scanner(System.in);

    public void showAllWords() {
        dictionaryManagement.sortDictionary(dictionary);
        printWord();
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline(dictionary);
        showAllWords();
    }

    public void dictionarySearcher() {
        String word = input.nextLine();
        List<String> list = dictionaryManagement.lookupWord(dictionary, word);

        if (list == null) {
            System.out.println("Chars are invalid!");
        }

        for (String key : list) {
            System.out.println(key);
        }
    }

    public void dictionaryAdvanced() throws InterruptedException {
        while (true) {
            System.out.print("\nWelcome to My Application!\n" +
                    "[0] Exit\n" +
                    "[1] Add\n" +
                    "[2] Remove\n" +
                    "[3] Update\n" +
                    "[4] Display\n" +
                    "[5] Lookup\n" +
                    "[6] Search\n" +
                    "[7] Game\n" +
                    "[8] Import from file\n" +
                    "[9] Export to file\n" +
                    "Your action: ");
            int n = input.nextInt();
            input.nextLine();

            switch (n) {
                case 0:
                    return;
                case 1:
                    System.out.print("Insert word: ");
                    String wordTarget = input.nextLine().trim();
                    System.out.print("Insert means: ");
                    String wordExplain = input.nextLine().trim();

                    Word newWord = new Word(wordTarget, wordExplain);
                    dictionary.add(newWord);
                    dictionaryManagement.addWord(newWord, path);

                    System.out.println("Insert Successlly!");
                    break;
                case 2:
                    System.out.print("Remove word: ");
                    String word = input.nextLine();

                    int index = dictionaryManagement.searchWord(dictionary, word);

                    if (index == -1) {
                        System.out.println("Can't find this word");
                        break;
                    }

                    dictionaryManagement.deleteWord(dictionary, index, path);

                    System.out.println("Remove Successlly!");
                    break;
                case 3:
                    System.out.print("Word need to update: ");
                    word = input.nextLine();

                    index = dictionaryManagement.searchWord(dictionary, word);

                    if (index == -1) {
                        System.out.println("Can't find this word");
                        break;
                    }

                    System.out.print("Explain for this word: ");
                    word = input.nextLine();
                    dictionaryManagement.updateWord(dictionary, index, word, path);

                    System.out.println("Update Successlly!");
                    break;
                case 4:
                    System.out.println("The list words are:");
                    showAllWords();
                    System.out.println("Show Successlly!");
                    break;
                case 5:
                    System.out.print("Chars you want to find: ");

                    dictionarySearcher();

                    System.out.println("Lookup Successlly!");
                    break;
                case 6:
                    System.out.print("Word you want to find: ");
                    word = input.nextLine();

                    index = dictionaryManagement.searchWord(dictionary, word);
                    System.out.println(dictionary.get(index).getWordExplain());
                    System.out.println("Search Successlly!");
                    break;
                case 7:
                    Random generator = new Random();
                    int value = generator.nextInt(1000000)%(dictionary.size()+1);
                    if(value == dictionary.size()) {
                        value = 0;
                    }
                    GameCommandline game = new GameCommandline(dictionary.get(value).getWordTarget());
                    while(!game.checkGame()) {
                        char guess = game.insertWord();
                        game.renderFigure(guess);
                    }
                    break;
                case 8:
                    dictionaryManagement.insertFromFile(dictionary, path);
                    dictionaryManagement.setTree(dictionary);
                    System.out.println("Imsert Successlly!");
                    break;
                case 9:
                    dictionaryManagement.exportToFile(dictionary, path);
                    System.out.println("Export Successlly!");
                    break;
                default:
                    System.out.println("Action not supported");
            }
        }
    }

    /** Xuất ra mảng Word */
    private void printWord() {
        int maxLength = 20;
        int count = 8; // Độ dài lớn nhất của số đếm
        System.out.print("No      | English");

        for(int i = 7; i <= maxLength; i++) {
            System.out.print(" ");
        }
        System.out.println("| Vietnamese");
        for(int i = 0; i < dictionary.size(); i++) {
            Integer j = i+1;
            String s = j.toString();
            System.out.print(s);

            for(int k = 1; k <= count - s.length(); k++) {
                System.out.print(" ");
            }

            System.out.print("| " + dictionary.get(i).getWordTarget());

            for(int k = 1; k <= maxLength - dictionary.get(i).getWordTarget().length(); k++) {
                System.out.print(" ");
            }

            System.out.print(" | " + dictionary.get(i).getWordExplain() + "\n");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DictionaryCommandLine dictionaryCommandline = new DictionaryCommandLine();
        dictionaryCommandline.dictionaryAdvanced();
    }
}
