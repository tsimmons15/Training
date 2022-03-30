package UsernameValidation;

import java.util.Scanner;

public class UsernameApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        System.out.println("Usernames must be at least 6 characters " +
                           "and cannot contain ! or #.");
        String username = "";
        boolean cont = true;
        while (cont) {
            System.out.print("Enter username: ");
            username = in.nextLine();

            try {
                cont = !isValidUsername(username);
            }
            catch (InvalidUsernameException iue) {
                System.out.println(iue.getMessage());
            } catch (Exception e) {
                System.out.println("Your guess is as good as mine...");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        }

        System.out.println("Greetings, " + username + "!");

        in.close();
    }

    public static boolean isValidUsername(String username) throws InvalidUsernameException {
        String invalidChars = "!#";
        if(username.length() < 6) {
            throw new InvalidUsernameException("The length was too short. Was " + username.length() + " must be at least 6.");
        }
        containsInvalidChar(username, invalidChars);

        return true;
    }
    private static void containsInvalidChar(String s, String chars) throws InvalidUsernameException{
        for(int i = 0; i < chars.length(); i++) {
            if (s.contains(chars.charAt(i) + "")) {
                throw new InvalidUsernameException("Contained invalid char: " + chars.charAt(i));
            }
        }
    }
}

class InvalidUsernameException extends Exception {
    InvalidUsernameException(String message) {
        super("Invalid username!\n" + message);
    }
}
