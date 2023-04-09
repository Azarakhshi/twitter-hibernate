package command;

import connection.HibernateUtils;
import entity.comment.Comment;
import entity.like.Like;
import entity.tweet.Tweet;
import entity.user.User;
import repository.comment.impl.CommentRepositoryImpl;
import repository.like.impl.LikeRepositoryImpl;
import repository.tweet.impl.TweetRepositoryImpl;
import service.comment.CommentService;
import service.comment.impl.CommentServiceImpl;
import service.like.LikeService;
import service.like.impl.LikeServiceImpl;
import service.tweet.TweetService;
import service.tweet.impl.TweetServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TweetOperations {

    private final TweetService tweetService = new TweetServiceImpl(new TweetRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));

    public void add(User user, String message) {
        Tweet tweet = new Tweet(message);
        tweet.setUser(user);
        tweetService.save(tweet);
    }

    public void showUserTweets(User user) {
        List<Tweet> tweets = tweetService.findUser(user.getId());
        tweets.forEach(tweet -> {
            System.out.println(tweet.getId() + ")  message:  " + tweet.getMessage()
                    + " likes:" + tweet.getLikes().size());
            tweet.getComments().forEach(comment -> System.out.println("comment:" + comment.getUser().getUsername()
                    + " say:" + comment.getMessage()));
        });
    }

    public void showUserComments(User user) {
        CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        List<Comment> comments = commentService.findUser(user.getId());
        comments.forEach(comment -> {
            System.out.println(comment.getId() + ")message: " + comment.getMessage()
                    + " likes: " + comment.getLikes().size());
        });
    }

    public Boolean isTweetForUser(User user, Long id) {
        for (Tweet tweet : user.getTweets()) {
            if (Objects.equals(tweet.getId(), id))
                return true;
        }
        return false;
    }

    public Boolean isCommentForUser(User user, Long id) {
        for (Comment comment : user.getComments()) {
            if (Objects.equals(comment.getId(), id))
                return true;
        }
        return false;
    }

    public void editTweet(Long id, String message) {
        Optional<Tweet> optionalTweet = tweetService.findById(id);
        optionalTweet.ifPresent(tweet -> {
            tweet.setMessage(message);
            tweetService.update(tweet);
        });
    }

    public void deleteTweet(Long id) {
        Optional<Tweet> optionalTweet = tweetService.findById(id);
        optionalTweet.ifPresent(tweetService::delete);
    }

    public void editComment(Long id, String message) {
        CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        Optional<Comment> optionalComment = commentService.findById(id);
        optionalComment.ifPresent(comment -> {
            comment.setMessage(message);
            commentService.update(comment);
        });
    }

    public void deleteComment(Long id) {
        CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        Optional<Comment> optionalComment = commentService.findById(id);
        optionalComment.ifPresent(commentService::delete);
    }

    public void showOther() {
        List<Tweet> tweets = tweetService.findOther();
        tweets.forEach(this::showTweetInformation);
    }

    public Tweet showTweet(Long id) {
        Optional<Tweet> optionalTweet = tweetService.findById(id);
        optionalTweet.ifPresentOrElse(this::showTweetInformation, () -> {
            System.out.println("Tweet not found");
        });
        return optionalTweet.orElse(null);
    }

    public void likeForTweet(User user, Tweet tweet) {
        LikeService likeService = new LikeServiceImpl(new LikeRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        Optional<Like> optionalLike = likeService.existLike(user, tweet);
        optionalLike.ifPresentOrElse(likeService::delete, () -> {
            Like like = new Like();
            like.setTweet(tweet);
            like.setUser(user);
            likeService.save(like);
        });
    }

    public void commentForTweet(User user, Tweet tweet, String message) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setTweet(tweet);
        comment.setMessage(message);
        CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        commentService.save(comment);

    }

    public void likeForComment(User user, Long id) {
        CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        LikeService likeService = new LikeServiceImpl(new LikeRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        Optional<Comment> optionalComment = commentService.findById(id);
        optionalComment.ifPresentOrElse(comment -> {
            Like like = new Like();
            like.setUser(user);
            like.setComment(comment);
            likeService.save(like);
        }, () -> {
            System.out.println("Comment not found");
        });
    }

    public void replyForComment(User user, Long id, String message) {
        CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        Optional<Comment> optionalComment = commentService.findById(id);
        optionalComment.ifPresentOrElse(comment -> {
            Comment newComment = new Comment();
            newComment.setMessage(message);
            newComment.setId(id);
            newComment.setUser(user);
            commentService.save(newComment);
        }, () -> {
            System.out.println("your input is invalid. ");
        });

    }

    private void showTweetInformation(Tweet tweet) {
        System.out.println("Tweet by " + tweet.getUser().getUsername() + " with id " + tweet.getId() + "- message:  "
                + tweet.getMessage() + ". number of likes: " + tweet.getLikes().size());
        tweet.getComments().forEach(comment -> {
            if (comment.getComment() == null) {
                System.out.println("id: " + comment.getId() + " comment: " + comment.getUser().getUsername()
                        + " say to " + tweet.getUser().getUsername() + ": " + comment.getMessage()
                        + ". number of likes: " + comment.getLikes().size());
            } else {
                System.out.println("id: " + comment.getId() + " comment: " + comment.getUser().getUsername()
                        + " say to " + comment.getComment().getUser().getUsername() + " comment with id " +
                        comment.getComment().getId() + " : " + comment.getMessage()
                        + ". number of likes: " + comment.getLikes().size());
            }
        });
        System.out.println("-----------------------------------");
    }

}
