import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class TeacherFeedbackFormImproved extends JFrame implements ActionListener {

    private JComboBox<String> cmbTeacher, cmbSubject, cmbRating;
    private JTextArea txtComments;
    private JButton btnSubmit, btnClear, btnExit;

    public TeacherFeedbackFormImproved() {
        setTitle("Teacher Feedback Form - Improved Version");
        setSize(550, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255));

        // Header
        JLabel header = new JLabel("Teacher Feedback Form", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(30, 144, 255));
        header.setForeground(Color.WHITE);
        add(header, BorderLayout.NORTH);

        // Center form
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(new Color(240, 248, 255));

        // Teacher Dropdown
        formPanel.add(new JLabel("Select Teacher:"));
        String[] teachers = { "--Select--", "Mr. Sharma", "Ms. Gupta", "Mr. Verma", "Dr. Mehta" };
        cmbTeacher = new JComboBox<>(teachers);
        formPanel.add(cmbTeacher);

        // Subject Dropdown
        formPanel.add(new JLabel("Subject:"));
        String[] subjects = { "--Select--", "Mathematics", "Physics", "Chemistry", "Computer Science" };
        cmbSubject = new JComboBox<>(subjects);
        formPanel.add(cmbSubject);

        // Rating Dropdown
        formPanel.add(new JLabel("Rating (1–5):"));
        String[] ratings = { "1", "2", "3", "4", "5" };
        cmbRating = new JComboBox<>(ratings);
        formPanel.add(cmbRating);

        // Comments Field
        formPanel.add(new JLabel("Comments:"));
        txtComments = new JTextArea(4, 20);
        txtComments.setLineWrap(true);
        txtComments.setWrapStyleWord(true);
        formPanel.add(new JScrollPane(txtComments));

        add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        btnSubmit = new JButton("Submit");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        btnSubmit.setBackground(new Color(60, 179, 113));
        btnSubmit.setForeground(Color.WHITE);
        btnClear.setBackground(new Color(255, 165, 0));
        btnExit.setBackground(new Color(220, 20, 60));
        btnExit.setForeground(Color.WHITE);

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        btnSubmit.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            handleSubmit();
        } else if (e.getSource() == btnClear) {
            handleClear();
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    private void handleSubmit() {
        String teacher = (String) cmbTeacher.getSelectedItem();
        String subject = (String) cmbSubject.getSelectedItem();
        String rating = (String) cmbRating.getSelectedItem();
        String comments = txtComments.getText().trim();

        if (teacher.equals("--Select--") || subject.equals("--Select--") || comments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all fields before submitting.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        String feedbackData = "Date: " + timestamp +
                "\nTeacher: " + teacher +
                "\nSubject: " + subject +
                "\nRating: " + rating +
                "\nComments: " + comments + "\n--------------------------\n";

        try (FileWriter writer = new FileWriter("TeacherFeedback.txt", true)) {
            writer.write(feedbackData);
            JOptionPane.showMessageDialog(this, "Feedback submitted successfully!\nThank you for your time.");
            handleClear();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving feedback: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleClear() {
        cmbTeacher.setSelectedIndex(0);
        cmbSubject.setSelectedIndex(0);
        cmbRating.setSelectedIndex(0);
        txtComments.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TeacherFeedbackFormImproved().setVisible(true));
    }
}