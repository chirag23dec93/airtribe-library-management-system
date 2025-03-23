package recommendations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import library.LibraryBranch;
import libraryBooks.Book;
import patron.Patron;

// Trending Books: Books that are most frequently borrowed
class TrendingBooksRecommendation implements RecommendationStrategy {
    
    @Override
    public List<Book> recommend(LibraryBranch libraryBranch, Patron patron) {
        List<Book> trending = new ArrayList<>();
        for (Book book : libraryBranch.getBooks().values()) {
            trending.add(book);
        }
        List<Book> books = new ArrayList<>(trending);
        books.sort(Comparator.comparingInt(Book::getBorrowCount).reversed());
        return books.subList(0, Math.min(books.size(), 5));
    }
}

