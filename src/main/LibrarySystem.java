
import java.util.List;

import library.Library;
import library.LibraryBranch;
import libraryBooks.Book;
import libraryBooks.BookFactory;
import libraryBooks.BookGenre;
import libraryBooks.BookType;
import patron.Patron;
import recommendations.RecommendationEnum;
import recommendations.RecommendationService;
 
public class LibrarySystem {
    public static void main(String[] args) {
        Library noidaLibrary = new Library();
        LibraryBranch sector62Branch = new LibraryBranch("Sector-62");
        LibraryBranch noidaExtensioBranch = new LibraryBranch("noida-extension"); 
        
        noidaLibrary.addBranch(sector62Branch);
        noidaLibrary.addBranch(noidaExtensioBranch);
        
        Patron gaurav = new Patron("Gaurav",BookGenre.FictionGenre.ADVENTURE.name());
        Patron arav = new Patron("Arav", BookGenre.FictionGenre.DYSTOPIAN.name()); 
        Patron kriti = new Patron("Kriti", BookGenre.FictionGenre.FANTASY.name());
        Patron aniket = new Patron("Arav", BookGenre.NonFictionGenre.BIOGRAPHY.name());
        Patron shubham = new Patron("Shubham",BookGenre.NonFictionGenre.BUSINESS_FINANCE.name());
        Patron devyani = new Patron("Devyani", BookGenre.FictionGenre.HORROR.name()); 
        Patron pranjal = new Patron("Pranjal", BookGenre.NonFictionGenre.SCIENCE.name());
        Patron ranveer = new Patron("Ranveer", BookGenre.NonFictionGenre.BIOGRAPHY.name());

        noidaExtensioBranch.getPatrons().putIfAbsent(ranveer.getName(), ranveer);
        noidaExtensioBranch.getPatrons().putIfAbsent(pranjal.getName(), pranjal);
        noidaExtensioBranch.getPatrons().putIfAbsent(gaurav.getName(), gaurav);
        noidaExtensioBranch.getPatrons().putIfAbsent(devyani.getName(), devyani);
        sector62Branch.getPatrons().putIfAbsent(shubham.getName(), shubham);
        sector62Branch.getPatrons().putIfAbsent(aniket.getName(), aniket);
        sector62Branch.getPatrons().putIfAbsent(kriti.getName(), kriti);
        sector62Branch.getPatrons().putIfAbsent(arav.getName(), ranveer);

        Book book1 = BookFactory.createBook(BookType.PHYSICAL,"Treasure Island", "Robert Louis Stevenso", "456789", 1965,BookGenre.FictionGenre.ADVENTURE.name());
        Book book2 = BookFactory.createBook(BookType.PHYSICAL,"The Call of the Wild", "Jack London", "456788", 1954, BookGenre.FictionGenre.ADVENTURE.name());

        Book book3 = BookFactory.createBook(BookType.PHYSICAL,"1984", "George Orwell", "456779", 1960,BookGenre.FictionGenre.DYSTOPIAN.name());
        Book book4 = BookFactory.createBook(BookType.PHYSICAL,"Brave New World", "Aldous Huxley", "656788", 1978, BookGenre.FictionGenre.DYSTOPIAN.name());

        Book book5 = BookFactory.createBook(BookType.PHYSICAL,"The Lord of the Rings", "J.R.Louiveig", "476789", 1945,BookGenre.FictionGenre.FANTASY.name());
        Book book6 = BookFactory.createBook(BookType.PHYSICAL,"The Hobbit ", "J.R.Louiveig", "436788", 1854, BookGenre.FictionGenre.FANTASY.name());

        Book book7 = BookFactory.createBook(BookType.PHYSICAL,"The Story of My Experiments with Truth", "Mahatma Gandhi", "156789", 1945,BookGenre.NonFictionGenre.BIOGRAPHY.name());
        Book book8 = BookFactory.createBook(BookType.PHYSICAL,"The Call of the Wild", "Jack London", "456788", 1954, BookGenre.NonFictionGenre.BIOGRAPHY.name());

        Book book9 = BookFactory.createBook(BookType.PHYSICAL,"The Intelligent Investor", "George Orwell", "4886779", 1960,BookGenre.NonFictionGenre.BUSINESS_FINANCE.name());
        Book book10 = BookFactory.createBook(BookType.PHYSICAL,"Common Stocks and Uncommon Profits", "Aldous Huxley", "659788", 1968, BookGenre.NonFictionGenre.BUSINESS_FINANCE.name());

        Book book11 = BookFactory.createBook(BookType.PHYSICAL,"Dracula", " Bram Stoker", "379789", 1545,BookGenre.FictionGenre.HORROR.name());
        Book book12 = BookFactory.createBook(BookType.PHYSICAL,"The Hobbit ", "J.R.Louiveig", "131788", 1854, BookGenre.FictionGenre.HORROR.name());
        
        Book book13 = BookFactory.createBook(BookType.PHYSICAL,"A Brief History of Time", "Steven Speilberg", "479781", 1755,BookGenre.NonFictionGenre.SCIENCE.name());
        Book book14 = BookFactory.createBook(BookType.PHYSICAL,"Astrophysics for People in a Hurry ", "Neil deGrasse Tyson", "431728", 1914, BookGenre.NonFictionGenre.SCIENCE.name());
        
        noidaExtensioBranch.addBook(book1);
        noidaExtensioBranch.addBook(book2);
        noidaExtensioBranch.addBook(book3);
        noidaExtensioBranch.addBook(book4);
        noidaExtensioBranch.addBook(book5);
        noidaExtensioBranch.addBook(book6);
        noidaExtensioBranch.addBook(book14);
        sector62Branch.addBook(book7);
        sector62Branch.addBook(book8);
        sector62Branch.addBook(book9);
        sector62Branch.addBook(book10);
        sector62Branch.addBook(book11);
        sector62Branch.addBook(book12);
        sector62Branch.addBook(book13);
    
        // Gaurav borrows book from noidaExtensioBranch Library
        gaurav.borrowBook(book1,noidaLibrary);
        
        // devyani tries to borrow but is added to the reservation queue
        devyani.borrowBook(book1,noidaLibrary);
        
        // Gaurav returns the book, devyani is notified
        gaurav.returnBook(book1,noidaExtensioBranch,noidaLibrary);
        
        // Transfer book to another branch
        noidaExtensioBranch.transferBook("456789", sector62Branch);

        shubham.returnBook(book14, noidaExtensioBranch, noidaLibrary);

        devyani.borrowBook(book14, noidaLibrary);
        arav.borrowBook(book1, noidaLibrary);
        aniket.borrowBook(book11, noidaLibrary);
        kriti.borrowBook(book10, noidaLibrary);
        gaurav.borrowBook(book9, noidaLibrary);
 
        System.out.println("History devyani :" + devyani.getBorrowingHistory());
        devyani.borrowBook(book2, noidaLibrary);
        // Get recommendations for Gaurav
        List<Book> recommendations =  RecommendationService.getRecommendations(noidaExtensioBranch, gaurav, RecommendationEnum.NEW_ARRIVALS);
        System.out.println("Recommendations for Gaurav :");
        for (Book book : recommendations) {
            System.out.println(book.getTitle());
        }
    }
}
