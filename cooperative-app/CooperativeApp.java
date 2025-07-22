import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CooperativeApp {
    private List<Member> members = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private int nextMemberId = 1;
    private int nextTransactionId = 1;
    private static final String DATA_FILE = "cooperative.dat";

    public static void main(String[] args) {
        CooperativeApp app = new CooperativeApp();
        app.run();
    }

    public void run() {
        loadData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCooperative Financial Management System");
            System.out.println("1. Add Member");
            System.out.println("2. View Member");
            System.out.println("3. Update Member");
            System.out.println("4. Make Contribution");
            System.out.println("5. Make Withdrawal");
            System.out.println("6. View Financial Report");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addMember(scanner);
                    break;
                case 2:
                    viewMember(scanner);
                    break;
                case 3:
                    updateMember(scanner);
                    break;
                case 4:
                    makeContribution(scanner);
                    break;
                case 5:
                    makeWithdrawal(scanner);
                    break;
                case 6:
                    viewFinancialReport();
                    break;
                case 7:
                    saveData();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addMember(Scanner scanner) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        Member member = new Member(nextMemberId++, name);
        members.add(member);
        Account account = new Account(member);
        accounts.add(account);
        System.out.println("Member added successfully. Member ID: " + member.getMemberId());
    }

    private void viewMember(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Member member = findMemberById(memberId);
        if (member != null) {
            Account account = findAccountByMember(member);
            System.out.println("Member ID: " + member.getMemberId());
            System.out.println("Name: " + member.getName());
            System.out.println("Membership Date: " + member.getMembershipDate());
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Member not found.");
        }
    }

    private void updateMember(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Member member = findMemberById(memberId);
        if (member != null) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            member.setName(newName);
            System.out.println("Member updated successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    private void makeContribution(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Member member = findMemberById(memberId);
        if (member != null) {
            System.out.print("Enter contribution amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            if (amount > 500000) { // Cash inflow limit
                System.out.println("Contribution amount exceeds the limit of 500,000.");
                return;
            }

            Account account = findAccountByMember(member);
            account.deposit(amount);
            Contribution contribution = new Contribution(nextTransactionId++, member, amount);
            transactions.add(contribution);
            System.out.println("Contribution successful.");
        } else {
            System.out.println("Member not found.");
        }
    }

    private void makeWithdrawal(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Member member = findMemberById(memberId);
        if (member != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            if (amount > 200000) { // Withdrawal limit
                System.out.println("Withdrawal amount exceeds the limit of 200,000.");
                return;
            }

            Account account = findAccountByMember(member);
            if (account.withdraw(amount)) {
                Withdrawal withdrawal = new Withdrawal(nextTransactionId++, member, amount);
                transactions.add(withdrawal);
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

    private void viewFinancialReport() {
        System.out.println("\n--- Financial Report ---");
        double totalContributions = 0;
        double totalWithdrawals = 0;

        for (Transaction t : transactions) {
            if (t instanceof Contribution) {
                totalContributions += t.getAmount();
            } else if (t instanceof Withdrawal) {
                totalWithdrawals += t.getAmount();
            }
        }

        System.out.println("Total Contributions: " + totalContributions);
        System.out.println("Total Withdrawals: " + totalWithdrawals);
        System.out.println("Net Flow: " + (totalContributions - totalWithdrawals));
    }

    private Member findMemberById(int memberId) {
        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                return m;
            }
        }
        return null;
    }

    private Account findAccountByMember(Member member) {
        for (Account a : accounts) {
            if (a.getOwner().equals(member)) {
                return a;
            }
        }
        return null;
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(members);
            oos.writeObject(accounts);
            oos.writeObject(transactions);
            oos.writeInt(nextMemberId);
            oos.writeInt(nextTransactionId);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            members = (List<Member>) ois.readObject();
            accounts = (List<Account>) ois.readObject();
            transactions = (List<Transaction>) ois.readObject();
            nextMemberId = ois.readInt();
            nextTransactionId = ois.readInt();
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
