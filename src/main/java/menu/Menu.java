package menu;

import connection.HibernateUtils;
import entity.user.User;
import print.Print;
import repository.user.impl.UserRepositoryImpl;
import service.user.UserService;
import service.user.impl.UserServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);


    public void showMenu() {
        Menu menu = new Menu();
        while (true) {
            Print.printFirstMenu();
            String command = scanner.next();
            if (command.equals("1")) {
                menu.loginMenu();
            } else if (command.equals("2")) {
                menu.signupMenu();
            } else if ((command.equals("3"))) {
                break;
            } else {
                System.out.println("your input is invalid. ");
            }

        }
    }


    public void loginMenu() {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        Optional<User> optionalUser = userService.findUser(username, password);
        optionalUser.ifPresentOrElse(user -> {
            UserMenu userMenu = new UserMenu();
            userMenu.showMenu(user);
        }, () -> System.out.println("your input is invalid. "));
    }

    public void signupMenu() {
        System.out.print("Enter your firstname: ");
        String firstname = scanner.next();
        System.out.print("Enter your lastname: ");
        String lastname = scanner.next();
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        User user = new User(firstname, lastname, username, password);
        userService.save(user);
        UserMenu userMenu = new UserMenu();
        userMenu.showMenu(user);
    }

}
