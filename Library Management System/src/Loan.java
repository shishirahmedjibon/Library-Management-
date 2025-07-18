

import java.time.LocalDate;

public class Loan {
    private LibraryItem item;
    private LibraryMember member;
    private String loanDate;
    private String dueDate;
    private boolean returned;

    public Loan(LibraryItem item, LibraryMember member, String dueDate) {
        this.item = item;
        this.member = member;
        this.loanDate = LocalDate.now().toString();
        this.dueDate = dueDate;
        this.returned = false;
    }

    // Getters
    public LibraryItem getItem() {
        return item;
    }

    public LibraryMember getMember() {
        return member;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markAsReturned() {
        this.returned = true;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "item=" + item.getTitle() +
                ", member=" + member.getName() +
                ", loanDate='" + loanDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", returned=" + returned +
                '}';
    }
}