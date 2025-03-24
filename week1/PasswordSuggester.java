package week1;

import java.util.Random;

public class PasswordSuggester {
    public static void main(String[] args) {
        String password = "keshav123"; // Example input

        if (isStrong(password)) {
            System.out.println("Your Password is Strong");
        } else {
            String suggested = generateStrongPassword(password);
            System.out.println("Suggested Password: " + suggested);
        }
    }
    
    // Check if password meets all the criteria
    private static boolean isStrong(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*].*");
    }
    
    // Generate a strong password suggestion by appending any missing character types
    private static String generateStrongPassword(String password) {
        Random random = new Random();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*";
        
        StringBuilder sb = new StringBuilder(password);
        
        if (!password.matches(".*[A-Z].*")) {
            sb.append(upper.charAt(random.nextInt(upper.length())));
        }
        if (!password.matches(".*[a-z].*")) {
            sb.append(lower.charAt(random.nextInt(lower.length())));
        }
        if (!password.matches(".*\\d.*")) {
            sb.append(digits.charAt(random.nextInt(digits.length())));
        }
        if (!password.matches(".*[!@#$%^&*].*")) {
            sb.append(special.charAt(random.nextInt(special.length())));
        }
        
        // Ensure the suggested password has at least 8 characters
        while (sb.length() < 8) {
            sb.append(lower.charAt(random.nextInt(lower.length())));
        }
        
        // Shuffle the characters in the final password to randomize their positions
        return shuffleString(sb.toString());
    }
    
    // Shuffle a string using Fisher-Yates shuffle algorithm
    private static String shuffleString(String input) {
        char[] a = input.toCharArray();
        Random random = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }
}

