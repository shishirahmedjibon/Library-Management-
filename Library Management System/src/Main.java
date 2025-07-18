
import java.util.Scanner;

public class Main {
    private static LibraryService libraryService = new LibraryService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Checkout Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View All Members");
            System.out.println("7. View Current Loans");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    registerMember();
                    break;
                case 3:
                    checkoutBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    libraryService.printAllBooks();
                    break;
                case 6:
                    libraryService.printAllMembers();
                    break;
                case 7:
                    libraryService.printCurrentLoans();
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addBook() {
        System.out.println("\n=== Add New Book ===");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Publication Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Book book = new Book(isbn, title, author, year);
        LibraryStaff staff = new LibraryStaff("SYS", "System", "Admin");
        staff.addBookToSystem(libraryService, book);

        System.out.println("Book added successfully!");
    }

    private static void registerMember() {
        System.out.println("\n=== Register New Member ===");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        LibraryMember member = new LibraryMember(memberId, name, email);
        libraryService.registerMember(member);

        System.out.println("Member registered successfully!");
    }

    private static void checkoutBook() {
        System.out.println("\n=== Checkout Book ===");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        try {
            libraryService.checkoutBook(memberId, isbn, dueDate);
            System.out.println("Book checked out successfully!");
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.println("\n=== Return Book ===");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();

        try {
            libraryService.returnBook(memberId, isbn);
            System.out.println("Book returned successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}