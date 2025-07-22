import java.io.Serializable;

public class Contribution extends Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private double amount;

    public Contribution(int transactionId, Member member, double amount) {
        super(transactionId, member);
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
