package Model;

import java.util.HashMap;
import java.util.Map;

public class HangmanWordlist {
    public static Map<String, String> getWordDefinitions() {
        Map<String, String> wordDefinitions = new HashMap<>();
        wordDefinitions.put("apple", "A ... a day keeps the doctor away");
        wordDefinitions.put("banana", "A long curved fruit");
        wordDefinitions.put("orange", "A round juicy citrus fruit");
        wordDefinitions.put("yummy", "To describe something tasty");
        wordDefinitions.put("media", "Technology");
        wordDefinitions.put("straight", "Without a break");
        wordDefinitions.put("beyond", "To infinity and ...!");
        wordDefinitions.put("home", "There's no place like ...");
        wordDefinitions.put("chocolates", "Life is like a box of ...");
        wordDefinitions.put("snowman", "Do you want to build ...?");
        wordDefinitions.put("twinkle", "... ... little star");
        wordDefinitions.put("ginger", "The ...-bread man");
        wordDefinitions.put("unicorn", "An imaginary animal");
        wordDefinitions.put("croissant", "A famous French delicacy");
        wordDefinitions.put("robotics", "A major in UET");
        wordDefinitions.put("pirates", "Sea roamers");
        wordDefinitions.put("volcano", "Lava could be seen during this");
        wordDefinitions.put("jungle", "A habitat");
        wordDefinitions.put("island", "Land amidst ocean");
        wordDefinitions.put("drummer", "A role in a band");
        wordDefinitions.put("kitten", "A baby pet");
        wordDefinitions.put("galaxy", "Milky's Way ...");

        // Add more words and their definitions as needed
        return wordDefinitions;
    }
}