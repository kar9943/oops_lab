
import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctOption;

    Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    void display(int qNo) {
        System.out.println("\nQ" + qNo + ". " + questionText);

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

class Quiz {
    List<Question> questions = new ArrayList<>();
    int score = 0;

    void addQuestion(Question q) {
        questions.add(q);
    }

    void start(Scanner sc) {
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.display(i + 1);

            int ans;

            while (true) {
                System.out.print("Enter your answer (1-"
                        + q.options.length + "): ");

                if (sc.hasNextInt()) {
                    ans = sc.nextInt();

                    if (ans >= 1 && ans <= q.options.length) {
                        break;
                    }
                } else {
                    sc.next();
                }

                System.out.println("Invalid input. Try again.");
            }

            if (ans == q.correctOption) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! Correct answer: "
                        + q.options[q.correctOption - 1]);
            }
        }
    }

    int getScore() {
        return score;
    }

    int getTotal() {
        return questions.size();
    }
}

class Result {
    int score;
    int total;

    Result(int score, int total) {
        this.score = score;
        this.total = total;
    }

    double getPercentage() {
        if (total == 0) {
            return 0.0;
        }

        return score * 100.0 / total;
    }

    String getGrade() {
        double percent = getPercentage();

        if (percent >= 90) {
            return "A+";
        } else if (percent >= 75) {
            return "A";
        } else if (percent >= 60) {
            return "B";
        } else if (percent >= 40) {
            return "C";
        } else {
            return "Fail";
        }
    }

    void display() {
        System.out.println("\n----- RESULT -----");
        System.out.println("Score      : " + score + "/" + total);
        System.out.printf("Percentage : %.2f%%%n", getPercentage());
        System.out.println("Grade      : " + getGrade());
    }
}

public class QuizManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Quiz quiz = new Quiz();

        quiz.addQuestion(new Question(
                "What is the size of int in Java?",
                new String[]{"2 bytes", "4 bytes", "8 bytes", "16 bytes"},
                2));

        quiz.addQuestion(new Question(
                "Which keyword is used for inheritance in Java?",
                new String[]{"implements", "extends", "inherits", "super"},
                2));

        quiz.addQuestion(new Question(
                "Which OOP concept means hiding data?",
                new String[]{"Polymorphism", "Abstraction",
                        "Encapsulation", "Inheritance"},
                3));

        quiz.addQuestion(new Question(
                "Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"},
                3));

        System.out.println("===== WELCOME TO THE QUIZ =====");
        quiz.start(sc);

        Result result = new Result(quiz.getScore(), quiz.getTotal());
        result.display();

        sc.close();
    }
}
