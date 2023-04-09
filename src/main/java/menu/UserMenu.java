package menu;

import entity.user.User;
import print.Print;

import java.util.Scanner;

public class UserMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(User user) {
        while (true) {
            Print.printUserMenu();
            String command = scanner.next();
            if (command.equals("1")) {
                TweetMenu tweetMenu = new TweetMenu();
                tweetMenu.showMenu(user);
            } else if (command.equals("2")) {
                FollowMenu followMenu = new FollowMenu();
                followMenu.showMenu(user);
            } else if (command.equals("3")) {
                Profile profile = new Profile();
                profile.showMenu(user);
            } else if ((command.equals("4"))) {
                break;
            } else {
                System.out.println("your input is invalid. ");
            }
        }
    }

}
