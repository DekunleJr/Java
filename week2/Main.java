// Java/week2/Main.java
package Java.week2;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0618260300");
        Book book2 = new Book("Pride and Prejudice", "Jane Austen", "978-0141439518");

        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(book1);
        bookCollection.addBook(book2);

        System.out.println("All books:");
        for (Book book : bookCollection.getAllBooks()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + ", ISBN: " + book.getISBN());
        }

        bookCollection.removeBook("978-0141439518");
        System.out.println("\nAfter removing Pride and Prejudice:");
        for (Book book : bookCollection.getAllBooks()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + ", ISBN: " + book.getISBN());
        }
    }
}
