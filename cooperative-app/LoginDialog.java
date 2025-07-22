import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean authenticated = false;
    private UserManager userManager;

    public LoginDialog(Frame parent, UserManager userManager) {
        super(parent, "Login", true);
        this.userManager = userManager;
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> authenticate());
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register());
        add(registerButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

        pack();
        setLocationRelativeTo(parent);
    }

    private void authenticate() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userManager.authenticate(username, password)) {
            authenticated = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void register() {
        JTextField regUsernameField = new JTextField();
        JPasswordField regPasswordField = new JPasswordField();
        Object[] message = {
            "Username:", regUsernameField,
            "Password:", regPasswordField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Register", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = regUsernameField.getText();
            String password = new String(regPasswordField.getPassword());
            if (userManager.addUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
