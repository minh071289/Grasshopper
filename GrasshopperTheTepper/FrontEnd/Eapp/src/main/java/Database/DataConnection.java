package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataConnection {
    public List<Question> getDinosaurDatabase() {
        List<Question> dinosaurDatabase = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/tepper";
        String username = "root";
        String password = "dangminhcit";

        try {
            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create and execute SQL query
            String query = "SELECT * FROM questions";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process results
            while (resultSet.next()) {
                int questionId = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question_text");
                String choiceA = resultSet.getString("choice_a");
                String choiceB = resultSet.getString("choice_b");
                String correctAnswer = resultSet.getString("correct_answer");

                Question data = new Question(questionId,questionText,choiceA,choiceB,correctAnswer);
                dinosaurDatabase.add(data);
            }
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dinosaurDatabase;
    }

    public static void main(String[] args) {
        DataConnection connection = new DataConnection();
        List<Question> list = connection.getDinosaurDatabase();
        for(Question question : list) {
            System.out.println(question.getQuestionId());
            System.out.println(question.getQuestion());
            System.out.println(question.getChoiceA());
            System.out.println(question.getAnswer());
        }
    }
}
