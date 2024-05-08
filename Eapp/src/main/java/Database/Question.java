package Database;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Question> getDinosaurDatabase() {
        List<Question> dinosaurDatabase = new ArrayList<>();
        dinosaurDatabase.add(new Question(1, "Synonym  for  Happy", "Elated", "Sad", "A"));
        dinosaurDatabase.add(new Question(2, "Antonym  for  Ancient", "Modern", "Antique", "A"));
        dinosaurDatabase.add(new Question(3, "Synonym  for  Difficult", "Simple", "Complex", "B"));
        dinosaurDatabase.add(new Question(4, "Antonym  for  Generous", "Stingy", "Kind", "B"));
        dinosaurDatabase.add(new Question(5, "Synonym  for  Beautiful", "Stunning", "Repulsive", "A"));
        dinosaurDatabase.add(new Question(6, "Antonym  for  Rapid", "Speedy", "Slow", "B"));
        dinosaurDatabase.add(new Question(7, "Synonym  for  Big", "Gigantic", "Small", "A"));
        dinosaurDatabase.add(new Question(8, "Antonym  for  Strong", "Weak", "Powerful", "A"));
        dinosaurDatabase.add(new Question(9, "Synonym  for  Intelligent", "Smart", "Indigenous", "A"));
        dinosaurDatabase.add(new Question(10, "Antonym  for  Abundant", "Plentiful", "Scarce", "B"));
        dinosaurDatabase.add(new Question(11, "Synonym  for  Funny", "Serious", "Hilarious", "B"));
        dinosaurDatabase.add(new Question(12, "Antonym  for  Victory", "Defeat", "Success", "A"));
        dinosaurDatabase.add(new Question(13, "Synonym  for  Clear", "Transparent", "Cloudy", "A"));
        dinosaurDatabase.add(new Question(14, "Antonym  for  Benevolent", "Malevolent", "Delicate", "A"));
        dinosaurDatabase.add(new Question(15, "Synonym  for  Terminate", "Start", "End", "B"));
        dinosaurDatabase.add(new Question(16, "Antonym  for  Positive", "Hopeful", "Negative", "B"));
        dinosaurDatabase.add(new Question(17, "Synonym  for  Famous", "Renowned", "Unknown", "A"));
        dinosaurDatabase.add(new Question(18, "Antonym  for  Expand", "Contract", "Increase", "A"));
        dinosaurDatabase.add(new Question(19, "Synonym  for  Peace", "War", "Harmony", "B"));
        dinosaurDatabase.add(new Question(20, "Antonym  for  Broad", "Wide", "Narrow", "B"));
        dinosaurDatabase.add(new Question(21, "Synonym  for  Elevate", "Raise", "Drop", "A"));
        dinosaurDatabase.add(new Question(22, "Antonym  for  Follow", "Lead", "Group", "A"));
        dinosaurDatabase.add(new Question(23, "Synonym  for  Fresh", "New", "Stale", "A"));
        dinosaurDatabase.add(new Question(24, "Synonym  for  Wise", "Smart", "Dense", "A"));
        dinosaurDatabase.add(new Question(25, "Synonym  for  Basic", "Maximalist", "Essential", "B"));
        dinosaurDatabase.add(new Question(26, "Antonym  for  Angry", "Content", "Serious", "A"));
        dinosaurDatabase.add(new Question(27, "Synonym  for  Small", "Large", "Petite", "B"));
        dinosaurDatabase.add(new Question(28, "Synonym  for  Cold", "Warm", "Chilly", "B"));
        dinosaurDatabase.add(new Question(29, "Antonym  for  Hot", "Freezing", "Boiling", "A"));
        dinosaurDatabase.add(new Question(30, "Synonym  for  Bright", "Luminous", "Dark", "A"));
        dinosaurDatabase.add(new Question(31, "Antonym  for  Soft", "Smooth", "Solid", "B"));
        dinosaurDatabase.add(new Question(32, "Synonym  for  Quiet", "Loud", "Silent", "B"));
        dinosaurDatabase.add(new Question(33, "Antonym  for  Friend", "Rival", "Companion", "A"));
        dinosaurDatabase.add(new Question(34, "Synonym  for  Fat", "Overweight", "Skinny", "A"));
        dinosaurDatabase.add(new Question(35, "Antonym  for  Tall", "Soaring", "Short", "B"));
        dinosaurDatabase.add(new Question(36, "Synonym  for  Support", "Hate", "Advocate", "B"));
        return dinosaurDatabase;
    }
}
