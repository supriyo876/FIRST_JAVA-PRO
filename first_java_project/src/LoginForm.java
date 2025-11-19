import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JFrame {
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin, btnRegister;

    public LoginForm() {
        setTitle("Login");
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

        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");

        add(btnLogin);
        add(btnRegister);

        btnLogin.addActionListener(e -> login());
        btnRegister.addActionListener(e -> {
            new RegisterForm();
            dispose();
        });

        setVisible(true);
    }

    private void login() {
        String username = txtUser.getText();
        String password = new String(txtPass.getPassword());

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new WelcomePage(username);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}