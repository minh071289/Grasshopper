
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class emotionTepper {
    private static final String[] happyEmotions = {
            "(//o_o//)", "(^_^)", "(>_<)", "(¬‿¬)", "(ಠ_ಠ)", "(◕‿◕)", "*(^O^)*", "w(^o^)W", "(n_n)", "(*-*)"
    };

    private static final String[] sadEmotions = {
            "(T_T)", "@_@", "(=_=)", "Ò_Ó", "(-_-;)", "(;_;)", "(x_x)", "(>_<')", "(Y_Y)", "(õ_ó)"
    };

    List<String> usedEmotions;
    Random random;

    public emotionTepper() {
        usedEmotions = new ArrayList<>();
        random = new Random();
    }

    public String getRandomHappyEmotion() {
        return getRandomEmotion(happyEmotions);
    }

    public String getRandomSadEmotion() {
        return getRandomEmotion(sadEmotions);
    }

    private String getRandomEmotion(String[] emotionArray) {
        if (usedEmotions.size() == emotionArray.length) {
            // All emotions have been used, reset the list
            usedEmotions.clear();
        }

        String randomEmotion;
        do {
            randomEmotion = emotionArray[random.nextInt(emotionArray.length)];
        } while (usedEmotions.contains(randomEmotion));

        usedEmotions.add(randomEmotion);
        return randomEmotion;
    }
}

