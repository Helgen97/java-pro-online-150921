package ua.kiev.prog.ShortLinkServlet;

import java.util.Locale;
import java.util.Random;

public class RandomString {
    private static final String upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowerLetters = upperLetters.toLowerCase(Locale.ROOT);
    private static final String numbers = "0123456789";
    private static final String allLetters = upperLetters + lowerLetters + numbers;
    private static final Random random = new Random();

    public static String randomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(allLetters.length());
            sb.append(allLetters.charAt(randomIndex));
        }
        return sb.toString();
    }
}
