# Library Management System UML Documentation

## Overview
The **Library Management System** is designed to handle multiple **library branches**, book management, patron interactions, and book recommendations.  
The system follows **Object-Oriented Programming (OOP) principles** and implements **design patterns** like the **Factory Pattern** and **Observer Pattern**.

1. It uses **Observer Pattern** for notifying Patrons whenever a book is returned and their is 2 another patron waiting in the queue for the same book.
2. **Factory Pattern** is used for creating book objects.
3. Recommendations are based on multiple strategies 
      1. Trending books  -> Books that are most frequently borrowed
      2. Top Rated books -> Books rated most 
      3. New Arrivals -> Books newly arrived , desc sorted on basis of publishing year
      4. Genre Base -> Books recommended to patrons on basis of their previous borrowed genre types
      5. Collaborative -> Books which have not been borrowed by patron till now.
4. Each Patron can borrow 2 books at max.
5. Notifications will be sent via SMS/EMAIL 
6. Each Book  has a queue of patrons waiting for it , this queue is maintained in Library Class   keeping in mind that patrons can borrow book from different branches.
7. Book Type enum is maintained for future additions of Audio / Ebook options. 



---

## 📌 Class Descriptions

### 🏛 Library
- **Attributes:**
  - `private Map<String, LibraryBranch>` → Stores multiple library branches.
  - `private Map<Book, Queue<Patron>>` → Handles book waiting lists.

### 📚 LibraryBranch
- **Attributes:**
  - `private String name` → Name of the branch.
  - `private Map<String, Book> books` → Holds books available in the branch.
  - `private Map<String, Patron> patrons` → Keeps track of patrons.
- **Methods:**
  - `public void transferBook(String ISBN, LibraryBranch targetBranch)` → Transfers a book to another branch.

### 📖 Book
- **Attributes:**
  - `private String title, author, ISBN`
  - `private int publicationYear, borrowCount`
  - `private boolean isAvailable`
  - `private double rating`
  - `private String genre`
- **Methods:**
  - `public void borrowBook(Patron patron, Library library)`
  - `public void returnBook(Library library, LibraryBranch branch)`

### 🏭 BookFactory
- **Creates `Book` objects.**

### 📂 BookGenre (Enum)
- Contains two **sub-enums**:
  - **FictionGenre:** Fantasy, Science Fiction, Mystery, etc.
  - **NonFictionGenre:** Biography, Self-Help, History, etc.

### 📂 BookType (Enum)
- Defines **book types**: `PHYSICAL`, `EBOOK`, `AUDIOBOOK`.

### 📕 PhysicalBook (Extends Book)
- Inherits all **book attributes** and adds a `BookType` parameter.

### 🔔 Observer (Interface)
- **Defines:** `void update(String message)`

### 👤 PatronObserver (Implements Observer)
- **Methods:**
  - `private void sendEmailNotification(String message)`
  - `private void sendSMSNotification(String message)`

### 👥 Patron
- **Attributes:**
  - `private String name, favoriteGenre`
  - `private Set<String> borrowedBooks`
  - `private List<Book> borrowingHistory`
  - `private PatronObserver observer`
  - `private int borrowingCount`
- **Methods:**
  - `public void borrowBook(Book book, Library library)`
  - `public void returnBook(Book book, LibraryBranch branch, Library library)`
  - `public void addToHistory(Book book)`
  - `public void notifyObserver(String message)`
  - `public List<Book> getBorrowingHistory()`

---

## 🔥 Recommendation System

### 🎯 RecommendationStrategy (Interface)
- **Defines:** `List<Book> recommend(LibraryBranch libraryBranch, Patron patron)`

### ⚡ Implementations:
- `GenreBasedRecommendation`
- `NewArrivalsRecommendation`
- `TopRatedRecommendation`
- `TrendingBooksRecommendation`

### 📊 RecommendationEnum (Enum)
- Options: `COLLABORATIVE`, `GENRE`, `NEW_ARRIVALS`, `TOP_RATED`, `TRENDING`

### 🚀 RecommendationService
- **Methods:**
  - `public static List<Book> getRecommendations(LibraryBranch branch, Patron patron, RecommendationEnum recommendationEnum)`

---

