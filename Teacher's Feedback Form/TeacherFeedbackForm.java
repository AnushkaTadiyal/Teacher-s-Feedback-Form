import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeacherFeedbackForm extends JFrame implements ActionListener {

    private JTextField txtName, txtSubject;
    private JComboBox<String> cmbRating;
    private JTextArea txtComments;
    private JButton btnSubmit, btnClear;

    public TeacherFeedbackForm() {
        setTitle("Teacher Feedback Form");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("Teacher Feedback Form", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);

        // Center form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        formPanel.add(new JLabel("Teacher Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);
        formPanel.add(new JLabel("Subject:"));
        txtSubject = new JTextField();
        formPanel.add(txtSubject);

        formPanel.add(new JLabel("Rating (1–5):"));
        cmbRating = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        formPanel.add(cmbRating);

        formPanel.add(new JLabel("Comments:"));
        txtComments = new JTextArea(4, 20);
        formPanel.add(new JScrollPane(txtComments));

        add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        btnSubmit = new JButton("Submit");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnClear);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event handling
        btnSubmit.addActionListener(this);
        btnClear.addActionListener(this);
    }
 @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            if (txtName.getText().isEmpty() || txtSubject.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String feedback = String.format(
                    "Feedback Submitted Successfully!\n\nTeacher: %s\nSubject: %s\nRating: %s\nComments: %s",
                    txtName.getText(), txtSubject.getText(), cmbRating.getSelectedItem(), txtComments.getText());
            JOptionPane.showMessageDialog(this, feedback, "Thank You", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnClear) {
            txtName.setText("");
            txtSubject.setText("");
            txtComments.setText("");
            cmbRating.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        new TeacherFeedbackForm().setVisible(true);
    }
}
