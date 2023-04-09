package menu;

import entity.user.User;
import command.FollowOperations;
import print.Print;

import java.util.Scanner;

public class FollowMenu {

    private final Scanner scanner = new Scanner(System.in);


    public void showMenu(User user) {
        while (true) {
            Print.printFollowMenu();
            String command = scanner.next();
            if (command.equals("1")) {
                FollowOperations followOperations = new FollowOperations();
                followOperations.showFollower(user);
            } else if (command.equals("2")) {
                FollowOperations followOperations = new FollowOperations();
                followOperations.showFollowing(user);
            } else if (command.equals("3")) {
                System.out.print("Enter name: ");
                String username = scanner.next();
                FollowOperations followOperations = new FollowOperations();
                Boolean check = followOperations.searchByUsername(username);
                if(check){
                    System.out.println("Enter user id for follow or unfollow");
                    Long id = scanner.nextLong();
                    followOperations.followOrUnfollow(user, id);
                } else {
                    System.out.println("Nothing found");
                }
            } else if ((command.equals("4"))) {
                break;
            } else {
                System.out.println("Wrong command.");
            }
        }
    }

}
