package Database;

public class Question {
    private int questionId;
    private String question;
    private String choiceA;
    private String choiceB;
    private String answer;

    public Question(int questionId, String question, String choiceA, String choiceB, String answer) {
        this.questionId = questionId;
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
