package recommendations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import library.LibraryBranch;
import libraryBooks.Book;
import patron.Patron;

//recommend same genre books and filter out best rating books
class GenreBasedRecommendation implements RecommendationStrategy {
    @Override
    public List<Book> recommend(LibraryBranch libraryBranch, Patron patron) {
        List<Book> books = new ArrayList<>();
        for (Book book : libraryBranch.getBooks().values()) {
            if (book.getGenre().equalsIgnoreCase(patron.getFavoriteGenre())) {
                books.add(book);
            }
        }
        books.sort(Comparator.comparingDouble(Book::getRating).reversed());
        return books.subList(0, Math.min(books.size(), 5));
    }
}