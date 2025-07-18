



import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books;
    private List<LibraryMember> members;
    private List<Loan> loans;

    public LibraryService() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Book operations
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getUniqueId().equals(isbn));
    }

    public Book findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getUniqueId().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    // Member operations
    public void registerMember(LibraryMember member) {
        members.add(member);
    }

    public LibraryMember findMemberById(String memberId) {
        return members.stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    // Loan operations
    public void checkoutBook(String memberId, String isbn, String dueDate) throws BookNotAvailableException {
        LibraryMember member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);

        if (member == null || book == null) {
            throw new IllegalArgumentException("Member or book not found");
        }

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book with ISBN " + isbn + " is not available");
        }

        member.borrowItem(book, dueDate);
        loans.add(new Loan(book, member, dueDate));
    }

    public void returnBook(String memberId, String isbn) {
        LibraryMember member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);

        if (member == null || book == null) {
            throw new IllegalArgumentException("Member or book not found");
        }

        member.returnItem(book);
        loans.stream()
                .filter(loan -> loan.getItem().getUniqueId().equals(isbn) && !loan.isReturned())
                .findFirst()
                .ifPresent(Loan::markAsReturned);
    }

    // Reporting methods
    public void printAllBooks() {
        System.out.println("\n=== All Books ===");
        books.forEach(System.out::println);
    }

    public void printAllMembers() {
        System.out.println("\n=== All Members ===");
        members.forEach(System.out::println);
    }

    public void printCurrentLoans() {
        System.out.println("\n=== Current Loans ===");
        loans.stream()
                .filter(loan -> !loan.isReturned())
                .forEach(System.out::println);
    }

    public void printOverdueLoans() {
        System.out.println("\n=== Overdue Loans ===");
        // In a real implementation, you would compare dueDate with current date
        loans.stream()
                .filter(loan -> !loan.isReturned() /* && loan.getDueDate().isBefore(LocalDate.now()) */)
                .forEach(System.out::println);
    }

    // Getters for data
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public List<LibraryMember> getAllMembers() {
        return new ArrayList<>(members);
    }

    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }
}