

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String memberId;
    private String name;
    private String email;
    private List<Loan> currentLoans;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.currentLoans = new ArrayList<>();
    }

    // Getters
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Loan> getCurrentLoans() {
        return currentLoans;
    }

    public void borrowItem(LibraryItem item, String dueDate) {
        if (item.isAvailable()) {
            item.setAvailable(false);
            currentLoans.add(new Loan(item, this, dueDate));
        }
    }

    public void returnItem(LibraryItem item) {
        currentLoans.removeIf(loan -> loan.getItem().getUniqueId().equals(item.getUniqueId()));
        item.setAvailable(true);
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", currentLoans=" + currentLoans.size() +
                '}';
    }
}