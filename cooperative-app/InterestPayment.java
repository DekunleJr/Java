public class InterestPayment extends Transaction {
    private double amount;

    public InterestPayment(int transactionId, Member member, double amount) {
        super(transactionId, member);
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
