import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CooperativeApp {
    private List<Member> members = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private int nextMemberId = 1;
    private int nextTransactionId = 1;
    private static final String DATA_FILE = "cooperative.dat";
    private static final long WITHDRAWAL_ELIGIBILITY_PERIOD_MS = 90L * 24 * 60 * 60 * 1000; // 90 days
    private static final double ANNUAL_INTEREST_RATE = 0.05; // 5%

    public void addMember(String name) {
        Member member = new Member(nextMemberId++, name);
        members.add(member);
        Account account = new Account(member);
        accounts.add(account);
    }

    public Member findMemberById(int memberId) {
        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                return m;
            }
        }
        return null;
    }

    public Account findAccountByMember(Member member) {
        for (Account a : accounts) {
            if (a.getOwner().equals(member)) {
                return a;
            }
        }
        return null;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void updateMember(int memberId, String newName) {
        Member member = findMemberById(memberId);
        if (member != null) {
            member.setName(newName);
        }
    }

    public void makeContribution(int memberId, double amount) {
        Member member = findMemberById(memberId);
        if (member != null) {
            if (amount > 500000) { // Cash inflow limit
                return;
            }
            Account account = findAccountByMember(member);
            account.deposit(amount);
            Contribution contribution = new Contribution(nextTransactionId++, member, amount);
            transactions.add(contribution);
        }
    }

    public boolean makeWithdrawal(int memberId, double amount) {
        Member member = findMemberById(memberId);
        if (member != null) {
            long membershipDuration = new java.util.Date().getTime() - member.getMembershipDate().getTime();
            if (membershipDuration < WITHDRAWAL_ELIGIBILITY_PERIOD_MS) {
                return false;
            }
            if (amount > 200000) { // Withdrawal limit
                return false;
            }
            Account account = findAccountByMember(member);
            if (account.withdraw(amount)) {
                Withdrawal withdrawal = new Withdrawal(nextTransactionId++, member, amount);
                transactions.add(withdrawal);
                return true;
            }
        }
        return false;
    }

    public void calculateAndApplyInterest() {
        for (Account account : accounts) {
            double interest = account.getBalance() * ANNUAL_INTEREST_RATE;
            account.deposit(interest);
            InterestPayment interestPayment = new InterestPayment(nextTransactionId++, account.getOwner(), interest);
            transactions.add(interestPayment);
        }
    }

    public String getFinancialReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n--- Financial Report ---\n");
        double totalContributions = 0;
        double totalWithdrawals = 0;

        report.append("\n--- Transactions ---\n");
        for (Transaction t : transactions) {
            String transactionType;
            if (t instanceof Contribution) {
                transactionType = "Contribution";
                totalContributions += t.getAmount();
            } else if (t instanceof Withdrawal) {
                transactionType = "Withdrawal";
                totalWithdrawals += t.getAmount();
            } else {
                transactionType = "Interest";
            }
            report.append("Date: ").append(t.getDate()).append(", Type: ").append(transactionType).append(", Amount: ").append(t.getAmount()).append(", Member ID: ").append(t.getMember().getMemberId()).append("\n");
        }

        report.append("\n--- Summary ---\n");
        report.append("Total Contributions: ").append(totalContributions).append("\n");
        report.append("Total Withdrawals: ").append(totalWithdrawals).append("\n");
        report.append("Net Flow: ").append(totalContributions - totalWithdrawals).append("\n");
        return report.toString();
    }

    public void saveData() {
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
    public void loadData() {
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
