import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterForm extends JFrame {
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnRegister, btnBack;

    public RegisterForm() {
        setTitle("Register");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Username:"));
        txtUser = new JTextField();
        add(txtUser);

        add(new JLabel("Password:"));
        txtPass = new JPasswordField();
        add(txtPass);

        btnRegister = new JButton("Register");
        btnBack = new JButton("Back");

        add(btnRegister);
        add(btnBack);

        btnRegister.addActionListener(e -> register());
        btnBack.addActionListener(e -> {
            new LoginForm();
            dispose();
        });

        setVisible(true);
    }

    private void register() {
        String username = txtUser.getText();
        String password = new String(txtPass.getPassword());

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO users(username, password) VALUES(?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registration Successful!");
            new LoginForm();
            dispose();

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}