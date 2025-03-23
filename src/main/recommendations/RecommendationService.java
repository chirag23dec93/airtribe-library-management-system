package recommendations;

import java.util.List;

import library.LibraryBranch;
import libraryBooks.Book;
import patron.Patron;

public class RecommendationService {

    public static List<Book> getRecommendations(LibraryBranch branch,Patron patron , RecommendationEnum recommendationEnum){
        switch (recommendationEnum) {
            case COLLABORATIVE:
                    CollaborativeFilteringRecommendation recommendation = new CollaborativeFilteringRecommendation();
                    return recommendation.recommend(branch, patron);
            case GENRE:
                    GenreBasedRecommendation genreBasedRecommendation = new GenreBasedRecommendation();
                    return genreBasedRecommendation.recommend(branch, patron);       
        
            case NEW_ARRIVALS:
                    NewArrivalsRecommendation newArrivalsRecommendation = new NewArrivalsRecommendation();
                    return newArrivalsRecommendation.recommend(branch, patron); 
            case TOP_RATED:
                    TopRatedRecommendation topRatedRecommendation = new TopRatedRecommendation();
                    return topRatedRecommendation.recommend(branch, patron);
            case TRENDING:
                    TrendingBooksRecommendation trendingBooksRecommendation = new TrendingBooksRecommendation();
                    return trendingBooksRecommendation.recommend(branch, patron);
            default:
                return  null;
        }
    }

}
