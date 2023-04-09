package menu;

import connection.HibernateUtils;
import entity.user.User;
import print.Print;
import repository.user.impl.UserRepositoryImpl;
import service.user.UserService;
import service.user.impl.UserServiceImpl;

import java.util.Objects;
import java.util.Scanner;

public class Profile {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(User user) {
        Profile profile = new Profile();
        while (true) {
            Print.printProfile();
            String command = scanner.next();
            if (command.equals("1")) {
                System.out.print("Enter your new username: ");
                profile.changeUsername(user);
            } else if (command.equals("2")) {
                System.out.print("Enter your old password: ");
                profile.changePassword(user);
            } else if (command.equals("3")) {
                break;
            } else {

            }
        }
    }

    public void changeUsername(User user){
        String username = scanner.next();
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
        user.setUsername(username);
        userService.update(user);
    }

    public void changePassword(User user){
        while (true){
            String oldPassword = scanner.next();
            if(Objects.equals(user.getPassword(), oldPassword)){
                System.out.print("Enter new password: ");
                String newPassword = scanner.next();
                user.setPassword(newPassword);
                UserService userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
                userService.update(user);
                break;
            } else{
                System.out.print("Wrong old password. Enter again: ");
            }
        }
    }

}
