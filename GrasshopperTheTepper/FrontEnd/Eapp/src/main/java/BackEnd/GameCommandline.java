package BackEnd;

import java.util.Scanner;

public class GameCommandline{
    private int count;
    private char[] ans;
    private char[] hidden;
    private int wordCount;
    private boolean endGame = false;

    public GameCommandline(String data) {
        count = 7;
        ans = data.toCharArray();
        wordCount = ans.length;
        hidden = new char[wordCount];
        for(int i=0; i<wordCount; i++) {
            hidden[i] = '-';
        }
    }

    private static final String figure[] = {
            " ------------- \n | \n | \n | \n | \n | \n ----- \n",
            " ------------- \n |  | \n | \n | \n | \n | \n ----- \n",
            " ------------- \n |  | \n |  O \n | \n | \n | \n ----- \n",
            " ------------- \n |  | \n |  O \n |  | \n | \n | \n ----- \n",
            " ------------- \n |  | \n |  O \n | /| \n | \n | \n ----- \n",
            " ------------- \n |  | \n |  O \n | /|\\ \n | \n | \n ----- \n",
            " ------------- \n |  | \n |  O \n | /|\\ \n | / \n | \n ----- \n",
            " ------------- \n |  | \n |  O \n | /|\\ \n | / \\ \n | \n ----- \n",
    };

    public void renderFigure(char c) throws InterruptedException {
        boolean ok = false;
        for(int i=0; i<wordCount; i++) {
            if(ans[i] == c) {
                this.updateHidden(i, c);
                ok = true;
            }
        }
        if(!ok) {
            System.out.println(figure[--this.count]);
            System.out.println("Incorrect!!!");
            Thread.sleep(500);
        }
        else {
            System.out.println(figure[this.count]);
            System.out.println("Correct!!!");
            Thread.sleep(500);
        }
        if(this.count == 0) {
            endGame = true;
            System.out.println("YOU LOSE.");
        }
        else if(checkHidden()) {
            endGame = true;
            System.out.println("YOU WIN.");
        }
    }

    public char insertWord() {
        Scanner input = new Scanner(System.in);
        System.out.print("HiddenWord: ");
        System.out.println(hidden);
        System.out.print("One word you guess: ");
        return input.next().charAt(0);
    }

    public void updateHidden(int id, char c) {
        hidden[id] = c;
    }

    public boolean checkHidden() {
        for(int i=0; i<wordCount; i++) {
            if(hidden[i] == '-') {
                return false;
            }
        }
        return true;
    }

    public boolean checkGame() {
        return endGame;
    }
}