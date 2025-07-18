

public class LibraryStaff {
    private String staffId;
    private String name;
    private String position;

    public LibraryStaff(String staffId, String name, String position) {
        this.staffId = staffId;
        this.name = name;
        this.position = position;
    }

    // Getters
    public String getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public void addBookToSystem(LibraryService service, Book book) {
        service.addBook(book);
        System.out.println("Book " + book.getTitle() + " added by " + name);
    }

    public void removeBookFromSystem(LibraryService service, String isbn) {
        service.removeBook(isbn);
        System.out.println("Book with ISBN " + isbn + " removed by " + name);
    }

    @Override
    public String toString() {
        return "LibraryStaff{" +
                "staffId='" + staffId + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}