package print;

public class Print {

    private static void printChoose(){
        System.out.println("please choose. ");
    }
    public static void printFirstMenu(){
        Print.printChoose();
        System.out.println("1)Sign in" );
        System.out.println("2)Sign up" );
        System.out.println("3)Exit" );
    }
    public static void printUserMenu(){
        Print.printChoose();
        System.out.println("1)Twit" );
        System.out.println("2)Friends" );
        System.out.println("3)Profile" );
        System.out.println("4)Exit" );
    }

    public static void printProfile(){
        Print.printChoose();
        System.out.println("1)Change username");
        System.out.println("2)Change password");
        System.out.println("3)Exit");
    }

    public static void printFollowMenu(){
        Print.printChoose();
        System.out.println("1)Show my followers");
        System.out.println("2)Show my following");
        System.out.println("3)Search");
        System.out.println("4)Exit");
    }

    public static void printTweetMenu(){
        Print.printChoose();
        System.out.println("1)Add new tweet");
        System.out.println("2)Show my tweets");
        System.out.println("3)Show other tweets");
        System.out.println("4)Show my comments");
        System.out.println("5)Exit");
    }

    public static void printTorC(){
        Print.printChoose();
        System.out.println("1)tweet ");
        System.out.println("2)comment ");
    }

    public static void printLorC(){
        Print.printChoose();
        System.out.println("1)Like ");
        System.out.println("2)Comment ");
    }

    public static void printEorD(){
        Print.printChoose();
        System.out.println("1)Edit ");
        System.out.println("1)Delete ");
    }

    public static void printIdOrZero(){
        Print.printChoose();
        System.out.println("Enter id:");
        System.out.println("or '0' for exit. ");
    }

}
