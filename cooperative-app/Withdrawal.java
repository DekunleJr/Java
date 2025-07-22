

public class Withdrawal extends Transaction {
    private static final long serialVersionUID = 1L;
    private double amount;

    public Withdrawal(int transactionId, Member member, double amount) {
        super(transactionId, member);
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
