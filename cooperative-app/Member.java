import java.util.Date;

import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private int memberId;
    private String name;
    private Date membershipDate;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.membershipDate = new Date();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setName(String name) {
        this.name = name;
    }
}
