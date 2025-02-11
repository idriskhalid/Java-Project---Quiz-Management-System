import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1. Login Page
class LoginPage extends JFrame {
    private JRadioButton teacherRadio, studentRadio;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Quiz System Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        teacherRadio = new JRadioButton("Teacher");
        studentRadio = new JRadioButton("Student");
        ButtonGroup group = new ButtonGroup();
        group.add(teacherRadio);
        group.add(studentRadio);

        add(new JLabel("Select Role:"));
        add(teacherRadio);
        add(new JLabel(""));
        add(studentRadio);
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);
        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> authenticateUser());
        add(loginBtn);

        setVisible(true);
    }

    private void authenticateUser() {
        // Simplified authentication (add proper validation in real system)
        if (teacherRadio.isSelected()) {
            new TeacherDashboard().setVisible(true);
        } else if (studentRadio.isSelected()) {
            new StudentDashboard().setVisible(true);
        }
        this.dispose();
    }
}

// 2. Teacher Dashboard
class TeacherDashboard extends JFrame {
    private List<Question> questionBank = new ArrayList<>();
    private Map<String, Student> students = new HashMap<>();

    public TeacherDashboard() {
        setTitle("Teacher Dashboard");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Create Questions", createQuestionPanel());
        tabs.addTab("Manage Students", createStudentPanel());
        tabs.addTab("View Results", createResultsPanel());

        add(tabs, BorderLayout.CENTER);
    }

    private JPanel createQuestionPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        // Question Type Selection
        JComboBox<String> questionType = new JComboBox<>(new String[]{"Text Answer", "Multiple Choice"});
        JTextArea questionField = new JTextArea();
        JPanel answerPanel = new JPanel();
        
        questionType.addActionListener(e -> {
            answerPanel.removeAll();
            if (questionType.getSelectedIndex() == 0) {
                answerPanel.add(new JLabel("Correct Answer:"));
                answerPanel.add(new JTextField(20));
            } else {
                answerPanel.add(new JLabel("Options (comma separated):"));
                answerPanel.add(new JTextField(20));
                answerPanel.add(new JLabel("Correct Answer:"));
                answerPanel.add(new JTextField(5));
            }
            answerPanel.revalidate();
            answerPanel.repaint();
        });

        JButton saveBtn = new JButton("Save Question");
        saveBtn.addActionListener(e -> {
            // Save question logic
            if (questionType.getSelectedIndex() == 0) {
                questionBank.add(new TextQuestion(questionField.getText(), 
                    ((JTextField)answerPanel.getComponent(1)).getText()));
            } else {
                String[] options = ((JTextField)answerPanel.getComponent(1)).getText().split(",");
                String correct = ((JTextField)answerPanel.getComponent(3)).getText();
                questionBank.add(new MultipleChoiceQuestion(questionField.getText(), 
                    List.of(options), correct));
            }
            JOptionPane.showMessageDialog(this, "Question saved!");
        });

        panel.add(new JLabel("Question Type:"));
        panel.add(questionType);
        panel.add(new JLabel("Question Text:"));
        panel.add(new JScrollPane(questionField));
        panel.add(answerPanel);
        panel.add(saveBtn);

        return panel;
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        
        JButton addBtn = new JButton("Add Student");
        addBtn.addActionListener(e -> {
            students.put(emailField.getText(), new Student(nameField.getText(), emailField.getText()));
            JOptionPane.showMessageDialog(this, "Student added!");
        });

        panel.add(new JLabel("Student Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Student Email:"));
        panel.add(emailField);
        panel.add(addBtn);

        return panel;
    }

    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> studentList = new JList<>(listModel);
        students.values().forEach(s -> listModel.addElement(s.getEmail()));
        
        JTextArea resultArea = new JTextArea();
        
        studentList.addListSelectionListener(e -> {
            Student s = students.get(studentList.getSelectedValue());
            resultArea.setText(s.getResults());
        });

        panel.add(new JScrollPane(studentList), BorderLayout.WEST);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        return panel;
    }
}

// 3. Student Dashboard
class StudentDashboard extends JFrame {
    public StudentDashboard() {
        setTitle("Student Quiz Interface");
        setSize(500, 400);
        // Add quiz taking interface components
    }
}

// Data Classes
class Student {
    private String name;
    private String email;
    private Map<Question, String> answers = new HashMap<>();

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void submitAnswer(Question q, String answer) {
        answers.put(q, answer);
    }

    public String getResults() {
        StringBuilder sb = new StringBuilder();
        answers.forEach((q, a) -> sb.append(q.getQuestionText())
            .append("\nYour Answer: ").append(a)
            .append("\nCorrect: ").append(q.isCorrect(a)).append("\n\n"));
        return sb.toString();
    }

    public String getEmail() { return email; }
}

// Question Classes (extend your existing implementation)
class TextQuestion implements Question {
    private String text;
    private String correctAnswer;

    public TextQuestion(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    @Override public String getQuestionText() { return text; }
    @Override public boolean isCorrect(String answer) { return answer.equalsIgnoreCase(correctAnswer); }
    public JPanel createQuestionPanel() {
		return null; /* Create text answer panel */ }
}

// Main Class
public class QuizGUI {
    public static void main(String[] args) {
        new LoginPage();
    }
}