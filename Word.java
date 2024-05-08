public class Word {
    private String wordTarget;
    private String wordExplain;

    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
    }

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }


    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }
}