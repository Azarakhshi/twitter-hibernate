package menu;

import entity.tweet.Tweet;
import entity.user.User;
import command.TweetOperations;
import print.Print;

import java.util.Scanner;

public class TweetMenu {

    private final Scanner scanner = new Scanner(System.in);


    public void showMenu(User user) {
        while (true) {
            Print.printTweetMenu();
            String command = scanner.next();
            if (command.equals("1")) {
                add(user);
            } else if (command.equals("2")) {
                showMyTweet(user);
            } else if ((command.equals("3"))) {
                if (showOtherTweet(user)) return;
            } else if ((command.equals("4"))) {
                if (showMyComment(user)) return;
            } else if ((command.equals("5"))) {
                break;
            } else {
                System.out.println("your input is invalid. ");
            }
        }
    }

    private boolean showOtherTweet(User user) {
        TweetOperations tweetOperations = new TweetOperations();
        tweetOperations.showOther();
        Print.printIdOrZero();
        Long id = scanner.nextLong();
        if (id == 0) return true;
        Tweet tweet = tweetOperations.showTweet(id);
        if (tweet == null) return true;
        Print.printTorC();
        String workPlace = scanner.next();
        if (workPlace.equalsIgnoreCase("1")) {
            workWithTweet(user, tweetOperations, tweet);
        } else if (workPlace.equalsIgnoreCase("2")) {
            workWithComment(user, tweetOperations);
        } else {
            System.out.println("your input is invalid. ");
        }
        return false;
    }

    private void workWithComment(User user, TweetOperations tweetOperations) {
        Long id;
        System.out.print("Enter comment id: ");
        id = scanner.nextLong();
        Print.printLorC();
        String work = scanner.next();
        if (work.equalsIgnoreCase("1")) {
            tweetOperations.likeForComment(user, id);
        } else if (work.equalsIgnoreCase("2")) {
            System.out.print("Enter message: ");
            scanner.nextLine();
            String message = scanner.nextLine();
            tweetOperations.replyForComment(user, id, message);
        } else {
            System.out.println("your input is invalid. ");
        }
    }

    private void workWithTweet(User user, TweetOperations tweetOperations, Tweet tweet) {
        Print.printLorC();
        String work = scanner.next();
        if (work.equalsIgnoreCase("1")) {
            tweetOperations.likeForTweet(user, tweet);
        } else if (work.equalsIgnoreCase("2")) {
            System.out.print("Enter message: ");
            scanner.nextLine();
            String message = scanner.nextLine();
            tweetOperations.commentForTweet(user, tweet, message);
        } else {
            System.out.println("your input is invalid. ");
        }
    }

    private boolean showMyComment(User user) {
        TweetOperations tweetOperations = new TweetOperations();
        tweetOperations.showUserComments(user);
        Print.printIdOrZero();
        Long id = scanner.nextLong();
        if (id == 0)
            return true;
        if (tweetOperations.isCommentForUser(user, id)) {
            Print.printEorD();
            String op = scanner.next();
            if (op.equalsIgnoreCase("1")) {
                System.out.print("Enter new message: ");
                scanner.nextLine();
                String message = scanner.nextLine();
                tweetOperations.editComment(id, message);
            } else if (op.equalsIgnoreCase("2")) {
                tweetOperations.deleteComment(id);
            } else {
                System.out.println("your input is invalid. ");
            }
        } else {
            System.out.println("not comment.");
        }
        return false;
    }

    private void showMyTweet(User user) {
        TweetOperations tweetOperations = new TweetOperations();
        tweetOperations.showUserTweets(user);
        Print.printIdOrZero();
        Long id = scanner.nextLong();
        if (id == 0)
            return;
        if (tweetOperations.isTweetForUser(user, id)) {
            Print.printEorD();
            String op = scanner.next();
            if (op.equalsIgnoreCase("1")) {
                System.out.print("Enter new message: ");
                scanner.nextLine();
                String message = scanner.nextLine();
                tweetOperations.editTweet(id, message);
            } else if (op.equalsIgnoreCase("2")) {
                tweetOperations.deleteTweet(id);
            } else {
                System.out.println("your input is invalid. ");
            }
        } else {
            System.out.println("not tweet ");
        }
    }

    private void add(User user) {
        System.out.print("Enter message: ");
        scanner.nextLine();
        String message = scanner.nextLine();
        TweetOperations tweetOperations = new TweetOperations();
        tweetOperations.add(user, message);
    }

}
