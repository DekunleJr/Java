import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private double balance;
    private Member owner;

    public Account(Member owner) {
        this.owner = owner;
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public Member getOwner() {
        return owner;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
