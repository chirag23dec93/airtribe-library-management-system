package libraryBooks;

// Factory Pattern for Book Creation
// To efficiently implement OCP principle switch case is added
// in future Book class can be extended with other kind of books like Ebook , audiobook
public class BookFactory {
    public static Book createBook(BookType type,String title, String author, String ISBN, int publicationYear, String genre) {
        switch (type) {
            case PHYSICAL:
                    return new PhysicalBook(title, author, ISBN, publicationYear, genre);
            default:
                    return new Book(title, author, ISBN, publicationYear, genre);
                }
    }
}