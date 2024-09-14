import java.security.SecureRandom;
import java.util.Scanner;

public class ConsolePasswordGenerator {

    // Define character sets
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for options
        System.out.println("Include Uppercase Letters? (y/n): ");
        boolean includeUppercase = scanner.nextLine().trim().equalsIgnoreCase("y");

        System.out.println("Include Lowercase Letters? (y/n): ");
        boolean includeLowercase = scanner.nextLine().trim().equalsIgnoreCase("y");

        System.out.println("Include Numbers? (y/n): ");
        boolean includeNumbers = scanner.nextLine().trim().equalsIgnoreCase("y");

        System.out.println("Include Special Characters? (y/n): ");
        boolean includeSpecialChars = scanner.nextLine().trim().equalsIgnoreCase("y");

        System.out.println("Enter desired password length (6-32): ");
        int length = scanner.nextInt();

        // Validate input
        if (length < 6 || length > 32) {
            System.out.println("Password length must be between 6 and 32 characters.");
            return;
        }

        // Generate password
        String password = generatePassword(includeUppercase, includeLowercase, includeNumbers, includeSpecialChars, length);
        System.out.println("Generated Password: " + password);
    }

    // Password generation logic
    private static String generatePassword(boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSpecialChars, int length) {
        StringBuilder charPool = new StringBuilder();

        if (includeUppercase) {
            charPool.append(UPPERCASE);
        }
        if (includeLowercase) {
            charPool.append(LOWERCASE);
        }
        if (includeNumbers) {
            charPool.append(NUMBERS);
        }
        if (includeSpecialChars) {
            charPool.append(SPECIAL_CHARACTERS);
        }

        // Check if at least one option is selected
        if (charPool.length() == 0) {
            return "Error: No character types selected.";
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charPool.length());
            password.append(charPool.charAt(randomIndex));
        }

        return password.toString();
    }
}
        