import javax.swing.*;
import java.awt.*;

public class CooperativeGUI extends JFrame {
    private CooperativeApp app;

    public CooperativeGUI(CooperativeApp app) {
        this.app = app;
        setTitle("Cooperative Financial Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        JButton addMemberButton = new JButton("Add Member");
        JButton viewMemberButton = new JButton("View Member");
        JButton viewMembersButton = new JButton("View All Members");
        JButton updateMemberButton = new JButton("Update Member");
        JButton transactionButton = new JButton("Make a Transaction");
        JButton reportButton = new JButton("View Financial Report");
        JButton exitButton = new JButton("Exit");

        panel.add(addMemberButton);
        panel.add(viewMemberButton);
        panel.add(viewMembersButton);
        panel.add(updateMemberButton);
        panel.add(transactionButton);
        panel.add(reportButton);
        panel.add(exitButton);

        add(panel);

        addMemberButton.addActionListener(e -> addMember());
        viewMemberButton.addActionListener(e -> viewMember());
        viewMembersButton.addActionListener(e -> viewAllMembers());
        updateMemberButton.addActionListener(e -> updateMember());
        transactionButton.addActionListener(e -> makeTransaction());
        reportButton.addActionListener(e -> viewFinancialReport());
        exitButton.addActionListener(e -> {
            app.saveData();
            System.exit(0);
        });
    }

    private void viewMember() {
        String memberIdStr = JOptionPane.showInputDialog(this, "Enter member ID:", "View Member", JOptionPane.PLAIN_MESSAGE);
        try {
            int memberId = Integer.parseInt(memberIdStr);
            Member member = app.findMemberById(memberId);
            if (member != null) {
                Account account = app.findAccountByMember(member);
                String message = "ID: " + member.getMemberId() + "\n" +
                                 "Name: " + member.getName() + "\n" +
                                 "Membership Date: " + member.getMembershipDate() + "\n" +
                                 "Balance: " + account.getBalance();
                JOptionPane.showMessageDialog(this, message, "Member Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Member not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMember() {
        String memberIdStr = JOptionPane.showInputDialog(this, "Enter member ID:", "Update Member", JOptionPane.PLAIN_MESSAGE);
        try {
            int memberId = Integer.parseInt(memberIdStr);
            Member member = app.findMemberById(memberId);
            if (member != null) {
                String newName = JOptionPane.showInputDialog(this, "Enter new name:", "Update Member", JOptionPane.PLAIN_MESSAGE);
                if (newName != null && !newName.trim().isEmpty()) {
                    app.updateMember(memberId, newName);
                    JOptionPane.showMessageDialog(this, "Member updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Member not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMember() {
        String name = JOptionPane.showInputDialog(this, "Enter member name:", "Add Member", JOptionPane.PLAIN_MESSAGE);
        if (name != null && !name.trim().isEmpty()) {
            app.addMember(name);
            JOptionPane.showMessageDialog(this, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewAllMembers() {
        StringBuilder sb = new StringBuilder();
        for (Member member : app.getMembers()) {
            Account account = app.findAccountByMember(member);
            sb.append("ID: ").append(member.getMemberId())
              .append(", Name: ").append(member.getName())
              .append(", Balance: ").append(account.getBalance()).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "All Members", JOptionPane.INFORMATION_MESSAGE);
    }

    private void makeTransaction() {
        JTextField memberIdField = new JTextField();
        JTextField amountField = new JTextField();
        Object[] message = {
            "Member ID:", memberIdField,
            "Amount:", amountField
        };
        String[] options = {"Contribute", "Withdraw"};
        int choice = JOptionPane.showOptionDialog(this, message, "Make Transaction", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        try {
            int memberId = Integer.parseInt(memberIdField.getText());
            double amount = Double.parseDouble(amountField.getText());

            if (choice == 0) { // Contribution
                app.makeContribution(memberId, amount);
                JOptionPane.showMessageDialog(this, "Contribution successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (choice == 1) { // Withdrawal
                if (app.makeWithdrawal(memberId, amount)) {
                    JOptionPane.showMessageDialog(this, "Withdrawal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Withdrawal failed. Check eligibility or balance.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewFinancialReport() {
        String report = app.getFinancialReport();
        JTextArea textArea = new JTextArea(report);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Financial Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        CooperativeApp app = new CooperativeApp();
        app.loadData();
        UserManager userManager = new UserManager();

        LoginDialog loginDialog = new LoginDialog(null, userManager);
        loginDialog.setVisible(true);

        if (loginDialog.isAuthenticated()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new CooperativeGUI(app).setVisible(true);
                }
            });
        } else {
            System.exit(0);
        }
    }
}
