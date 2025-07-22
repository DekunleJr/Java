import java.util.Date;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private int transactionId;
    private Date date;
    private Member member;

    public Transaction(int transactionId, Member member) {
        this.transactionId = transactionId;
        this.member = member;
        this.date = new Date();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Date getDate() {
        return date;
    }

    public Member getMember() {
        return member;
    }

    public abstract double getAmount();
}
