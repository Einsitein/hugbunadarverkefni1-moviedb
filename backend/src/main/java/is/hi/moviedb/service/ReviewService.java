public interface ReviewService {
    rateMovie(long userId,long mediaId,int rating,String text);

    deleteRating(long userId,long mediaId);

    changeRating(long userId,long mediaId,int rating, String text);
}